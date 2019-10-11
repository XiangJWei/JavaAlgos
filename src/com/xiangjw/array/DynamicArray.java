package com.xiangjw.array;

public class DynamicArray<T> {

	private T[] arr;
	
	private int length;
	
	private int sizeOfArr;
	
	public DynamicArray(int size){
		this.arr = (T[]) new Object[size];
		this.length = 0;
		this.sizeOfArr = size;
	}
	
	public boolean add(int index , T val) {
		if(index > sizeOfArr) {
			System.out.println("out of sizeOfArr");
			return false;
		}
		if(index < 0 || index > length) {
			System.out.println("out of length");
			return false;
		}
		
		if(length == sizeOfArr) {
			resize(sizeOfArr << 1);
		}
		
		for(int i = length ; i > index ; i --) {
			arr[i] = arr[i - 1];
		}
		
		arr[index] = val;
		length ++;
		
		return true;
	}
	
	private void resize(int size) {
		T[] temp = (T[]) new Object[size];
		System.arraycopy(arr, 0, temp, 0, length);
		this.arr = temp;
		this.sizeOfArr = size;
	}
	
	public boolean add(T val) {
		return add(length , val);
	}
	
	public boolean update(int index , T val) {
		if(index < 0 || index >= length) {
			System.out.println("out of length");
			return false;
		}
		
		arr[index] = val;
		return true;
	}
	
	public boolean remove(int index) {
		if(index < 0 || index >= length) {
			System.out.println("out of length");
			return false;
		}
		
		if(sizeOfArr > length << 1) {
			resize(sizeOfArr >> 1);
		}
		
		for(int i = index ; i < length ; i ++) {
			if(i == length - 1) {
				arr[i] = null;
			}else {
				arr[i] = arr[i + 1];
			}
		}
		length --;
		return true;
	}
	
	public int query(T val) {
		for(int i = 0 ; i < length ; i ++) {
			if(val.equals(arr[i])) {
				return i;
			}
		}
		return -1;
	}
	
	public T findByIndex(int index) {
		if(index < 0 || index >= length) {
			System.out.println("out of length");
			return null;
		}
		
		return arr[index];
	}
	
	public void print() {
		System.out.print("数组占用空间:" + sizeOfArr + "，数组元素个数" + length);
		StringBuffer buffer = new StringBuffer("，[");
		for(int i = 0 ; i < sizeOfArr ; i ++) {
			if(i == 0) {
				buffer.append(arr[i] == null ? "null" : arr[i].toString());
			}else {
				buffer.append(",").append(arr[i]);
			}
		}
		buffer.append("]");
		
		System.out.println(buffer.toString());
	}
	
	public static void main(String []args) {
		DynamicArray<Integer> data= new DynamicArray<Integer>(1);
		data.print();
		data.add(2);data.print();
		data.add(3);data.print();
		data.add(4);data.print();
		data.add(5);data.print();
		data.add(6);data.print();
		data.add(7);data.print();
		data.add(8);data.print();
		data.remove(0);data.print();
		data.remove(0);data.print();
		data.remove(0);data.print();
		data.remove(0);data.print();
		data.remove(0);data.print();
		data.remove(0);data.print();
	}
}
