package com.xiangjw.heap;

/**
 * 大顶堆
 * @author Administrator
 *
 */
public class CustomHeap {

	private int[] arr;//a[0]不放元素，便于计算父子节点
	private int length;
	
	public CustomHeap(int size) {
		this.arr = new int[size];
		this.length = 0;
	}
	
	/**
	 * 添加元素，并堆化
	 * @param val
	 */
	public void add(int val) {
		int index = length + 1;
		if(index == arr.length) {
			System.out.println("容量已满");
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
	 * 删除堆顶元素，并堆化
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
		System.out.print("堆的大小：" + length + "-----");
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
