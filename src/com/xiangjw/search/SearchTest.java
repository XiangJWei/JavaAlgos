package com.xiangjw.search;

import java.util.Random;

public class SearchTest {

	/**
	 * ѭ���ķ�ʽʵ�ֶ��ֲ���
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
	 * �ݹ�ķ�ʽʵ�ֶ��ֲ���
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
	 * ���ҵ�һ��ֵ���ڸ���ֵ��Ԫ��
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
				//�м�ֵ��ǰһ��Ҳ�����ֵ�����Ծͼ���������
				return getFirstSearch(arr , start , mid - 1 , value);
			}else {
				//�м�ֵ��ǰһ���������ֵ��˵��mid���ǵ�һ��ֵ��
				return mid;
			}
		}else if(arr[mid] > value) {
			return getFirstSearch(arr , start , mid - 1 , value);
		}else {
			return getFirstSearch(arr , mid + 1 , end , value);
		}
	}
	
	/**
	 * �������һ��ֵ���ڸ���ֵ��Ԫ��
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
				//�м�ֵ�ĺ�һ��Ҳ�����ֵ�����Ծͼ���������
				return getLastSearch(arr , mid + 1 , end , value);
			}else {
				//�м�ֵ��ǰһ���������ֵ��˵��mid���ǵ�һ��ֵ��
				return mid;
			}
		}else if(arr[mid] > value) {
			return getLastSearch(arr , start , mid - 1 , value);
		}else {
			return getLastSearch(arr , mid + 1 , end , value);
		}
	}
	
	/**
	 * ���ҵ�һ�����ڵ��ڸ���ֵ��Ԫ��
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
	 * �������һ��С�ڵ��ڸ���ֵ��Ԫ��
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
		
		System.out.println("ѭ��ʽ���ֲ���:" + next + "�����:" + binSearch(arr , length , next));
		for(int i = 0 ; i < size ; i ++) {
			binSearch(arr , length , random.nextInt(20000));
		}
		System.out.println("ѭ��ʽ���ֲ��ң������С" + length + "������" + size + "�� - �ܺ�ʱ" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		System.out.println("�ݹ�ʽ���ֲ���:" + next + "�����:" + binSearchByCircle(arr , 0 , length - 1 , next));
		for(int i = 0 ; i < size ; i ++) {
			binSearchByCircle(arr , 0 , length - 1 , random.nextInt(20000));
		}
		System.out.println("�ݹ�ʽ���ֲ��ң������С" + length + "������" + size + "�� - �ܺ�ʱ" + (System.currentTimeMillis() - before) + "ms");
	
		System.out.println("���ҵ�һ��ֵ���ڸ���ֵ��Ԫ��:" + next + "�����:" + getFirstSearch(arr , 0 , length - 1 , next));
		System.out.println("�������һ��ֵ���ڸ���ֵ��Ԫ��:" + next + "�����:" + getLastSearch(arr , 0 , length - 1 , next));
		System.out.println("���ҵ�һ��ֵ���ڵ��ڸ���ֵ��Ԫ��:" + next + "�����:" + getFirstAboveSearch(arr , 0 , length - 1 , next));
		System.out.println("�������һ��ֵС�ڵ��ڸ���ֵ��Ԫ��:" + next + "�����:" + getLastBelowSearch(arr , 0 , length - 1 , next));
	}
}
