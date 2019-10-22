package com.xiangjw.heap;

/**
 * �󶥶�
 * @author Administrator
 *
 */
public class CustomHeap {

	private int[] arr;//a[0]����Ԫ�أ����ڼ��㸸�ӽڵ�
	private int length;
	
	public CustomHeap(int size) {
		this.arr = new int[size];
		this.length = 0;
	}
	
	/**
	 * ���Ԫ�أ����ѻ�
	 * @param val
	 */
	public void add(int val) {
		int index = length + 1;
		if(index == arr.length) {
			System.out.println("��������");
			return;
		}
		
		arr[index] = val;
		
		
		for(int i = index ; i >= 2 ; i = i / 2) {
			int temp = arr[i / 2];
			if(temp < arr[i]) {
				arr[i / 2] = arr[i];
				arr[i] = temp;
			}else {
				break;
			}
		}
		
		length ++;
	}
	
	/**
	 * ɾ���Ѷ�Ԫ�أ����ѻ�
	 */
	public void delTop() {
		if(length == 0) {
			return;
		}
		
		if(length  == 1) {
			arr[length --] = 0;
			return;
		}
		
		arr[1] = arr[length];
		int i = 1;
		while(i * 2 <= length) {
			int tempLeft = arr[i * 2];
			int tempRight = Integer.MIN_VALUE;
			if(i * 2 + 1 <= length) {
				tempRight = arr[i * 2 + 1];
			}
			int index = tempLeft > tempRight ? (i * 2) : (i * 2 + 1);
			if(arr[i] < arr[index]) {
				int temp = arr[i];
				arr[i] = arr[index];
				arr[index] = temp;
			}else {
				break;
			}
			
			i = index;
		}
		length --;
	}
	
	public void print() {
		System.out.print("�ѵĴ�С��" + length + "-----");
		StringBuffer buffer = new StringBuffer();
		for(int i = 1 ; i <= length ; i ++) {
			if(i != 1) {
				buffer.append(",");
			}
			buffer.append(arr[i]);
		}
		
		System.out.println(buffer.toString());
	}
	
	public static void main(String[] args) {
		CustomHeap heap = new CustomHeap(10);
		heap.add(15);heap.print();
		heap.add(10);heap.print();
		heap.add(3);heap.print();
		heap.add(50);heap.print();
		heap.add(7);heap.print();
		heap.add(1);heap.print();
		heap.add(25);heap.print();
		heap.add(100);heap.print();
		heap.delTop();heap.print();
		heap.delTop();heap.print();
		heap.delTop();heap.print();
		heap.delTop();heap.print();
		heap.delTop();heap.print();
	}
}
