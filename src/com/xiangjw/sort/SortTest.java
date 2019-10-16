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
	 * 把N位的数组拆分成0~N/2和N/2~N的小问题，递归下去
	 * 也就是先把两个拆分数组分别排序，然后再组合两个数组进行排序
	 * 很稳定，时间复杂度O(nlogN)
	 * 但是会占用额外的内存空间，空间复杂度O(n)
	 * 由下至上，先处理子问题，再拼接两个子问题
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
	
	/**
	 * 快速排序
	 * 也属于分治算法，每次取最后一个节点作为比较点，其他元素和它比，比它小就放它左边，比它大就放右边。
	 * 然后再依次排序左边半部分和右边半部分，达到全局有序
	 * 非稳定的排序算法，但它属于原地排序，基本不需要额外的内存空间
	 * 由上至下，先处理本身，再处理两个子问题
	 * 大部分情况下的时间复杂度都可以做到 O(nlogn)，只有在极端情况下，才会退化到 O(n²)，比如数组极端有序或者极端无序时
	 * 
	 * @param a
	 * @param start
	 * @param length
	 */
	public static void sortByQuick(int[] a , int start , int length) {
		if(length <= 1) {
			return;
		}
		
		if(start < 0) {
			return;
		}
		
		//每次取最后一个节点作为比较点，其他元素和它比，比它小就放它左边，比它大就放右边
		int end = start + length - 1;
		int temp = a[end];
		int middleIndex = start;
		for(int index = start; index < end  ; index ++) {
			if(a[index] <= temp) {
				if(index == middleIndex) {
					middleIndex ++;
				}else if(a[index] != a[middleIndex]) {
					int swap = a[index];
					a[index] = a[middleIndex];
					a[middleIndex ++] = swap;
				}
			}
		}
		//循环结束，把比较点放在中间位置区，原有位置元素移到最后
		if(end != middleIndex && a[end] != a[middleIndex]) {
			a[end] = a[middleIndex];
			a[middleIndex] = temp;
		}
		
		//分别处理比较点两边的子序列
		sortByQuick(a , start , middleIndex - start);
		sortByQuick(a , middleIndex + 1 , end - middleIndex);
	}
	
	/**
	 * 获取第K小的元素值，时间复杂度O(n)
	 * 数组中存在重复值时就不适用了
	 * @param a
	 * @param start
	 * @param length
	 * @param k
	 * @return
	 */
	public static int getKth(int[] a , int start , int length , int k) {
		if(start < 0) {
			return -1;
		}
		if(length <= 1) {
			return a[start];
		}
		
		int end = start + length - 1;
		int middleIndex = start;
		int temp = a[end];
		for(int i = start ; i < end ; i ++) {
			if(a[i] < temp) {
				int swap = a[i];
				a[i] = a[middleIndex];
				a[middleIndex ++] = swap;
			}
		}
		
		a[end] = a[middleIndex];
		a[middleIndex] = temp;
		
		if(k == middleIndex + 1) {
			return a[middleIndex];
		}else if(k < middleIndex + 1) {
			return getKth(a, start, middleIndex - start, k);
		}else {
			return getKth(a, middleIndex + 1 , end - middleIndex, k);
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
		
		int[] a4 = {1, 2, 7 , 3, 5, 2, 10};
		sortByQuick(a4 , 0 , length);
		print(a4 , length);
		
		int[] a5 = {1, 2, 7 , 3, 5, 8, 10};
		int k = 4;
		System.out.println("获取到第" + k + "小的元素为：" + getKth(a5 , 0 , length , k));
		print(a5 , length);
		
		//性能对比测试
		long before = System.currentTimeMillis();
		int num = 100 , size = 10000;
		System.out.println("随机生成" + num + "个数组，每个数组" + size + "个元素，统计耗时情况如下：");
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
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByMerge(testArr, 0, size);
		}
		System.out.println("归并排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		

		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByQuick(testArr, 0, size);
		}
		System.out.println("快速排序，耗时" + (System.currentTimeMillis() - before) + "ms");
	}
}
