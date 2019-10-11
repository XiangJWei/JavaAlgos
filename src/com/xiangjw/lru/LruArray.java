package com.xiangjw.lru;

public class LruArray<T> {
	private T[] arr;
	
	private int length;
	
	private int sizeOfArray;
	
	public LruArray(int size) {
		arr = (T[])new Object[size];
		length = 0;
		sizeOfArray = size;
	}
	
	public int find(T info) {
		for(int i = 0 ; i < length ; i ++) {
			if(arr[i].equals(info)) {
				return i;
			}
		}
		return -1;
	}
	
	public void put(T info) {
		if(info == null) {
			throw new IllegalArgumentException("Value cannot be null");
		}
		
		int index = find(info);
		if(index >= 0) {
			//����,ֱ��Ų��Ԫ�ص����
			T temp = arr[index];
			for(int i = index ; i < length - 1 ; i ++) {
				arr[i] = arr[i + 1];
			}
			arr[length - 1] = temp;
		}else {
			//������
			if(length == sizeOfArray) {
				//�����Ѵ����ޣ�ɾ������
				for(int i = 0 ; i < length - 1 ; i ++) {
					arr[i] = arr[i + 1];
				}
				
				length --;
			}
			
			//�����Ȼ�û�����ޣ�ֱ�Ӳ���
			arr[length] = info;
			length ++;
		}
	}
	
	public void print() {
		System.out.print("ռ�ÿռ�:" + sizeOfArray + "��Ԫ�ظ���" + length);
		StringBuffer buffer = new StringBuffer("��[");
		for(int i = 0 ; i < sizeOfArray ; i ++) {
			if(i == 0) {
				buffer.append(arr[i] == null ? "null" : arr[i].toString());
			}else {
				buffer.append(",").append(arr[i]);
			}
		}
		buffer.append("]");
		
		System.out.println(buffer.toString());
	}
	
	public static void main(String[] args) {
		long before = System.currentTimeMillis();
		LruArray<String> cache = new LruArray<String>(1000);
		for(int i = 0 ; i < 1000000 ; i ++) {
		cache.put("abc" + i);
		cache.put("123" + i);
		cache.put("33" + i);
		cache.put("43" + i);
		cache.put("123" + i);
		cache.put("3243" + i);
		cache.put("2342" + i);
		cache.put("12312" + i);
		cache.put("123" + i);
		cache.put("12312" + i);
		cache.put("12312" + i);
		cache.put("sds" + i);
		}
		cache.print();
		
		System.out.println("��ʱ��" + (System.currentTimeMillis() - before) + "ms");
	}
}
