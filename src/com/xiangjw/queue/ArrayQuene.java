package com.xiangjw.queue;

public class ArrayQuene<T> {

	private T[] arr;
	
	private int length;
	
	private int sizeOfArr;
	
	public ArrayQuene(int size) {
		this.arr = (T[])new Object[size];
		this.length = 0;
		this.sizeOfArr = size;
	}
	
	public void push(T data) {
		if(length == sizeOfArr) {
			resize(sizeOfArr << 1);
		}
		
		for(int i = length ; i > 0 ; i --) {
			arr[i] = arr[i - 1];
		}
		
		arr[0] = data;
		length ++;
	}
	
	public T pull() {
		if(length <= 0) {
			return null;
		}
		
		T temp = arr[--length];
		arr[length] = null;
		
		if(sizeOfArr > 1 && sizeOfArr > length << 1) {
			resize(sizeOfArr >> 1);
		}
		return temp;
	}
	
	public void resize(int size) {
		T[] temp = (T[])new Object[size];
		System.arraycopy(arr, 0, temp, 0, length);
		arr = temp;
		sizeOfArr = size;
	}
	
	public void print() {
		System.out.print("数组占用空间:" + sizeOfArr + "，数组元素个数" + length);
		StringBuffer buffer = new StringBuffer("，队列头<--[");
		for(int i = 0 ; i < sizeOfArr ; i ++) {
			if(i == 0) {
				buffer.append(arr[i]);
			}else {
				buffer.append(",").append(arr[i]);
			}
		}
		buffer.append("]<--队列尾");
		
		System.out.println(buffer.toString());
	}
	
	public static void main(String[] args) {
		ArrayQuene<String> stack = new ArrayQuene<String>(4);
		stack.push("abc");stack.print();
		stack.push("111");stack.print();
		stack.push("23");stack.print();
		stack.push("44");stack.print();
		stack.push("566");stack.print();
		stack.push("22");stack.print();

		stack.pull();stack.print();
		stack.pull();stack.print();
		stack.push("111");stack.print();
		stack.push("336f");stack.print();
		stack.pull();stack.print();
		stack.pull();stack.print();
		stack.pull();stack.print();
		stack.pull();stack.print();
		stack.pull();stack.print();
		stack.pull();stack.print();
		stack.pull();stack.print();
	}
}
