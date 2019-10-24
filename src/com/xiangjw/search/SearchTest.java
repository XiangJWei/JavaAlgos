package com.xiangjw.search;

import java.util.Random;

public class SearchTest {

	/**
	 * 循环的方式实现二分查找
	 * @param arr
	 * @param length
	 * @param value
	 * @return
	 */
	public static int binSearch(int[] arr , int length , int value) {
		int start = 0;
		int end = length - 1;
		int mid = start + ((end - start) >> 1);
		
		while(start <= end) {
			if(arr[mid] == value) {
				return mid;
			}else if(arr[mid] > value) {
				end = mid - 1;
				mid = start + ((end - start) >> 1);
			}else {
				start = mid + 1;
				mid = start + ((end - start) >> 1);
			}
		}
		
		return -1;
	}
	
	/**
	 * 递归的方式实现二分查找
	 * @param arr
	 * @param start
	 * @param end
	 * @param value
	 * @return
	 */
	public static int binSearchByCircle(int[] arr , int start , int end , int value) {
		if(start > end) {
			return -1;
		}
		if(start == end) {
			return (arr[start] == value) ? start : -1;
		}
		
		int mid = start + ((end - start) >> 1);
		if(arr[mid] == value) {
			return mid;
		}else if(arr[mid] > value) {
			return binSearchByCircle(arr , start , mid - 1 , value);
		}else {
			return binSearchByCircle(arr , mid + 1 , end , value);
		}
	}
	
	
	/**
	 * 查找第一个值等于给定值的元素
	 * @return
	 */
	public static int getFirstSearch(int[] arr , int start , int end , int value) {
		if(start > end) {
			return -1;
		}
		if(start == end) {
			return (arr[start] == value) ? start : -1;
		}
		
		int mid = start + ((end - start) >> 1);
		if(arr[mid] == value) {
			if(mid > start && arr[mid - 1] == value) {
				//中间值的前一个也是这个值，所以就继续往下找
				return getFirstSearch(arr , start , mid - 1 , value);
			}else {
				//中间值的前一个不是这个值，说明mid就是第一个值了
				return mid;
			}
		}else if(arr[mid] > value) {
			return getFirstSearch(arr , start , mid - 1 , value);
		}else {
			return getFirstSearch(arr , mid + 1 , end , value);
		}
	}
	
	/**
	 * 查找最后一个值等于给定值的元素
	 * @return
	 */
	public static int getLastSearch(int[] arr , int start , int end , int value) {
		if(start > end) {
			return -1;
		}
		if(start == end) {
			return (arr[start] == value) ? start : -1;
		}
		
		int mid = start + ((end - start) >> 1);
		if(arr[mid] == value) {
			if(mid < end && arr[mid + 1] == value) {
				//中间值的后一个也是这个值，所以就继续往下找
				return getLastSearch(arr , mid + 1 , end , value);
			}else {
				//中间值的前一个不是这个值，说明mid就是第一个值了
				return mid;
			}
		}else if(arr[mid] > value) {
			return getLastSearch(arr , start , mid - 1 , value);
		}else {
			return getLastSearch(arr , mid + 1 , end , value);
		}
	}
	
	/**
	 * 查找第一个大于等于给定值的元素
	 * @return
	 */
	public static int getFirstAboveSearch(int[] arr , int start , int end , int value) {
		if(start > end) {
			return -1;
		}
		if(start == end) {
			return (arr[start] >= value) ? start : -1;
		}
		
		int mid = start + ((end - start) >> 1);
		if(arr[mid] >= value) {
			if(mid > start && arr[mid - 1] >= value) {
				return getFirstAboveSearch(arr , start , mid - 1 , value);
			}else {
				return mid;
			}
		}else {
			return getFirstAboveSearch(arr , mid + 1 , end , value);
		}
	}
	
	/**
	 * 查找最后一个小于等于给定值的元素
	 * @return
	 */
	public static int getLastBelowSearch(int[] arr , int start , int end , int value) {
		if(start > end) {
			return -1;
		}
		if(start == end) {
			return (arr[start] <= value) ? start : -1;
		}
		
		int mid = start + ((end - start) >> 1);
		if(arr[mid] <= value) {
			if(mid < end && arr[mid + 1] <= value) {
				return getLastBelowSearch(arr , mid + 1 , end , value);
			}else {
				return mid;
			}
		}else {
			return getLastBelowSearch(arr , start , mid - 1 , value);
		}
	}
	
	public static void main(String[] args) {
		int length = 1000000;
		int size = 10000;
		
		long before = System.currentTimeMillis();
		int[] arr = new int[length];
		for(int i = 0 ; i < length ; i ++) {
			arr[i] = i - i % 4;
		}

		Random random = new Random();
		int next = random.nextInt(20000);
		
		System.out.println("循环式二分查找:" + next + "，结果:" + binSearch(arr , length , next));
		for(int i = 0 ; i < size ; i ++) {
			binSearch(arr , length , random.nextInt(20000));
		}
		System.out.println("循环式二分查找，数组大小" + length + "，查找" + size + "次 - 总耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		System.out.println("递归式二分查找:" + next + "，结果:" + binSearchByCircle(arr , 0 , length - 1 , next));
		for(int i = 0 ; i < size ; i ++) {
			binSearchByCircle(arr , 0 , length - 1 , random.nextInt(20000));
		}
		System.out.println("递归式二分查找，数组大小" + length + "，查找" + size + "次 - 总耗时" + (System.currentTimeMillis() - before) + "ms");
	
		System.out.println("查找第一个值等于给定值的元素:" + next + "，结果:" + getFirstSearch(arr , 0 , length - 1 , next));
		System.out.println("查找最后一个值等于给定值的元素:" + next + "，结果:" + getLastSearch(arr , 0 , length - 1 , next));
		System.out.println("查找第一个值大于等于给定值的元素:" + next + "，结果:" + getFirstAboveSearch(arr , 0 , length - 1 , next));
		System.out.println("查找最后一个值小于等于给定值的元素:" + next + "，结果:" + getLastBelowSearch(arr , 0 , length - 1 , next));
	}
}
