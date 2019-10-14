package com.xiangjw.stack;

/**
 * 数组实现栈，尾插尾删
 * @author Administrator
 *
 * @param <T>
 */
public class ArrayStack<T> {

	private T[] arr;
	
	private int length;
	
	private int sizeOfArr;
	
	public ArrayStack(int size) {
		this.arr = (T[])new Object[size];
		this.length = 0;
		this.sizeOfArr = size;
	}
	
	public void push(T data) {
		//扩容
		if(length == sizeOfArr) {
			resize(sizeOfArr << 1);
		}
		
		arr[length] = data;
		length ++;
	}
	
	public T pull() {
		if(length <= 0) {
			return null;
		}
		
		T temp = arr[-- length];
		arr[length] = null;
		
		//缩容
		if(sizeOfArr > 1 && sizeOfArr > length << 1) {
			resize(sizeOfArr >> 1);
		}
		return temp;
	}
	
	public T get() {
		if(length <= 0) {
			return null;
		}
		
		T temp = arr[length - 1];
		return temp;
	}
	
	public int getLength() {
		return length;
	}

	private void resize(int newSize) {
		T[] temp = (T[])new Object[newSize];
		System.arraycopy(arr, 0, temp, 0, length);
		arr = temp;
		sizeOfArr = newSize;
	}
	
	public void print() {
		System.out.print("数组占用空间:" + sizeOfArr + "，数组元素个数" + length);
		StringBuffer buffer = new StringBuffer("，栈底-->[");
		for(int i = 0 ; i < sizeOfArr ; i ++) {
			if(i == 0) {
				buffer.append(arr[i]);
			}else {
				buffer.append(",").append(arr[i]);
			}
		}
		buffer.append("]-->栈顶");
		
		System.out.println(buffer.toString());
	}
	
	public static void main(String[] args) {
		ArrayStack<String> stack = new ArrayStack<String>(5);
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
