package com.xiangjw.sort;

public class SortTest {

	/**
	 * 冒泡排序
	 * @param a
	 * @param m
	 */
	public static void sortByPop(int[] a , int length) {
		if(a == null || length < 2) {
			System.out.println("长度不够");
			return;
		}
		
		for(int p = 0 ; p < length - 1 ; p++) {
			boolean isSwaped = false;
			for(int q = 0 ; q < length - p - 1 ; q ++) {
				if(a[q] > a[q + 1]) {
					int temp = a[q + 1];
					a[q + 1] = a[q];
					a[q] = temp;
					isSwaped = true;
				}
			}
			
			if(!isSwaped) {
				//某一次没有发生任何顺序调换，说明全数组已经顺序ok，终止
				return;
			}
		}
		
	}
	
	/**
	 * 插入排序
	 * @param a
	 * @param length
	 */
	public static void sortByInsert(int[] a , int length) {
		if(a == null || a.length < 2) {
			System.out.println("长度不够");
			return;
		}
		
		for(int i = 1 ; i < length ; i ++) {
			int temp = a[i];
			int j = i - 1;
			for(; j >= 0 ; j--) {
				if(temp < a[j]) {
					a[j + 1] = a[j];//数据移动
				}else{
					break;//找到对应位置，马上结束循环直接插入
				}
			}
			
			a[j + 1] = temp;//插入数据
		}
	}
	
	/**
	 * 选择排序
	 * @param a
	 * @param length
	 */
	public static void sortBySel(int[] a , int length) {
		if(a == null || a.length < 2) {
			System.out.println("长度不够");
			return;
		}
		
		for(int i = 0 ; i < length - 1 ; i ++) {
			int minVal = i;
			for(int j = i + 1 ;  j < length ; j ++) {
				if(a[j] < a[minVal]) {
					minVal = j;
				}
			}
			
			int temp = a[i];
			a[i] = a[minVal];
			a[minVal] = temp;
		}
	}
	
	public static void print(int[] arr , int length) {
		System.out.print("数组占用空间:" + arr.length + "，数组元素个数" + length);
		StringBuffer buffer = new StringBuffer("，[");
		for(int i = 0 ; i < arr.length ; i ++) {
			if(i == 0) {
				buffer.append(arr[i]);
			}else {
				buffer.append(",").append(arr[i]);
			}
		}
		buffer.append("]");
		
		System.out.println(buffer.toString());
	}
	
	public static void main(String[] args) {
		int[] a = {1, 2, 7 , 3, 5, 2, 10};
		int length = 7;
		print(a , length);
		sortByPop(a , length);
		print(a , length);
		
		int[] a1 = {1, 2, 7 , 3, 5, 2, 10};
		sortByInsert(a1 , length);
		print(a1 , length);
		
		int[] a2 = {1, 2, 7 , 3, 5, 2, 10};
		sortBySel(a2 , length);
		print(a2 , length);
	}
}
