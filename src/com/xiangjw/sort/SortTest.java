package com.xiangjw.sort;

import java.util.Random;

public class SortTest {

	/**
	 * 冒泡排序
	 * 相邻两个元素比较，决定是否互换
	 * 最好情况：O(n) 最坏情况O(n²)  平均情况O(n²)
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
	 * 假设数组左端有序，右端无序。每次从右端拿出一个元素，往左端对比插入
	 * 最好情况：O(n) 最坏情况O(n²)  平均情况O(n²)
	 * @param a
	 * @param length
	 */
	public static void sortByInsert(int[] a , int length) {
		if(a == null || a.length < 2) {
			System.out.println("长度不够");
			return;
		}
		
		for(int i = 1 ; i < length ; i ++) {
			int temp = a[i];//右端第一个元素
			int j = i - 1;//左端最后元素开始往左依次对比，如果左端大则后移，否则就已经找到元素在左端中的顺序位置。
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
	 * 假设数组左端有序，右端无序。每次从右端选择出最大的元素，插入左端的末尾即可
	 * 不稳定，存在跨位互换
	 * 最好情况：O(n²) 最坏情况O(n²)  平均情况O(n²)
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
	
	/**
	 * 归并排序
	 * 
	 * @param a
	 * @param start
	 * @param length
	 */
	public static void sortByMerge(int[] a , int start , int length) {
		if(length == 1) {
			return;
		}
		
		//把数组分成两部分
		//先对前半部分排序，再对后半部分排序
		sortByMerge(a , start , length / 2);
		sortByMerge(a , start + length / 2 , length - length / 2);
		
		//最后归并前半部分和后半部分，组成一个排序完成的数组。
		//借用一个临时数组
		int tempArr[] = new int[length];
		int i = start , j = start + length / 2;
		int index = 0;
		while(i < start + length / 2 && j < start + length) {
			if(a[i] <= a[j]) {//谁小先放谁
				tempArr[index++] = a[i++];
			}else {
				tempArr[index++] = a[j++];
			}
		}
		
		//两部分数组可能一个对比完了，另一个还没完，就把没完的那一部分直接追加到临时数组
		if(i < start + length / 2) {
			for(;index < length ; index ++) {
				tempArr[index] = a[i++];
			}
		}else if(j < start + length) {
			for(;index < length ; index ++) {
				tempArr[index] = a[j++];
			}
		}
		
		//拷贝临时数组排序好的数据到原数组，完成本轮数组的排序工作
		for(index = 0;index < length ; index ++) {
			a[start++] = tempArr[index];
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
	
	public static int[] getTestArr(int length) {
		int[] testArr = new int[length];
		Random random =new Random();
		for(int i = 0; i < length ; i ++) {
			testArr[i] = random.nextInt();
		}
		
		return testArr;
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
		
		int[] a3 = {1, 2, 7 , 3, 5, 2, 10};
		sortByMerge(a3 , 0 , length);
		print(a3 , length);
		
		//性能对比测试
		long before = System.currentTimeMillis();
		int num = 10 , size = 10000;
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByPop(testArr, size);
		}
		System.out.println("冒泡排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByInsert(testArr, size);
		}
		System.out.println("插入排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortBySel(testArr, size);
		}
		System.out.println("选择排序，耗时" + (System.currentTimeMillis() - before) + "ms");
	}
}
