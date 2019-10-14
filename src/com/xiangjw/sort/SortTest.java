package com.xiangjw.sort;

public class SortTest {

	/**
	 * ð������
	 * @param a
	 * @param m
	 */
	public static void sortByPop(int[] a , int length) {
		if(a == null || length < 2) {
			System.out.println("���Ȳ���");
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
				//ĳһ��û�з����κ�˳�������˵��ȫ�����Ѿ�˳��ok����ֹ
				return;
			}
		}
		
	}
	
	/**
	 * ��������
	 * @param a
	 * @param length
	 */
	public static void sortByInsert(int[] a , int length) {
		if(a == null || a.length < 2) {
			System.out.println("���Ȳ���");
			return;
		}
		
		for(int i = 1 ; i < length ; i ++) {
			int temp = a[i];
			int j = i - 1;
			for(; j >= 0 ; j--) {
				if(temp < a[j]) {
					a[j + 1] = a[j];//�����ƶ�
				}else{
					break;//�ҵ���Ӧλ�ã����Ͻ���ѭ��ֱ�Ӳ���
				}
			}
			
			a[j + 1] = temp;//��������
		}
	}
	
	/**
	 * ѡ������
	 * @param a
	 * @param length
	 */
	public static void sortBySel(int[] a , int length) {
		if(a == null || a.length < 2) {
			System.out.println("���Ȳ���");
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
		System.out.print("����ռ�ÿռ�:" + arr.length + "������Ԫ�ظ���" + length);
		StringBuffer buffer = new StringBuffer("��[");
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
