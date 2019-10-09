package com.xiangjw.array;

public class ArrayTest {
	
	private static void printArrAddr() {
		int arr[] = new int[100];
		System.out.println("数组arr地址：" + System.identityHashCode(arr));
		System.out.println("数组arr[0]地址：" + System.identityHashCode(arr[0]));
		System.out.println("数组arr[10]地址：" + System.identityHashCode(arr[10]));
	}
	
	private static int array[] = new int[10];
	
	/**
	 * 数组中插入元素
	 * 
	 * @param index
	 * @param val
	 */
	private static void addToArr(int index , int val) {
		if(index >= array.length) {
			return;
		}
		for(int i  = array.length - 1 ; i > index ; i --) {
			array[i] = array[i - 1];
		}
		
		array[index] = val;
	}
	
	/**
	 * 数组中删除元素
	 * @param index
	 * @param val
	 */
	private static void delToArr(int index) {
		if(index >= array.length) {
			return;
		}
		for(int i  = index ; i < array.length - 1 ; i ++) {
			array[i] = array[i + 1];
		}
		
		array[array.length - 1] = 0;
	}
	
	private static void printArr() {
		StringBuffer buffer = new StringBuffer();
		for(int i = 0 ; i < array.length ; i ++) {
			if(i == 0) {
				buffer.append(array[i]);
			}else {
				buffer.append(",").append(array[i]);
			}
		}
		
		System.out.println("数组array：" + buffer.toString());
	}
	
	/**
	 * 数组越界测试
	 */
	private static void outOfBound() {
		int i = 0;
		int a[] = new int [3];
		for(; i <= 3 ; i ++) {
			a[i] = 0;
			System.out.println("数组a");
		}
	}
	
	public static void main (String []args) {
		printArrAddr();
		
		printArr();
		addToArr(8 , 11);
		addToArr(5 , 7);
		printArr();
		delToArr(4);
		printArr();
		
		outOfBound();
	}
}
