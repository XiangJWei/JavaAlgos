package com.xiangjw.algo.lru;

/**
 * 利用数组实现lru缓存算法
 * 添加元素时，如果该值已存在，就挪到最新；如果不存在且数组没满，就直接插到最新；如果不存在但数组满了，就删除最旧插入最新；
 * 获取元素时，如果存在就挪到最新并返回。否则返回空
 * 
 * @author xiangjw
 *
 * @param <T>
 */
public class LruArray<T> {
	private T[] arr;
	
	private int length;
	
	private int sizeOfArray;
	
	public LruArray(int size) {
		arr = (T[])new Object[size];
		length = 0;
		sizeOfArray = size;
	}
	
	/**
	 * 查找元素
	 * @param info
	 * @return
	 */
	public int find(T info) {
		int index = -1;
		for(int i = 0 ; i < length ; i ++) {
			if(arr[i].equals(info)) {
				index = i;
			}
		}
		
		if(index >= 0) {
			//存在,直接挪动元素到最新
			T temp = arr[index];
			for(int i = index ; i < length - 1 ; i ++) {
				arr[i] = arr[i + 1];
			}
			arr[length - 1] = temp;
		}
		return index;
	}
	
	/**
	 * 添加元素
	 * @param info
	 */
	public void put(T info) {
		if(info == null) {
			throw new IllegalArgumentException("Value cannot be null");
		}
		
		int index = find(info);
		
		if(index < 0){
			//不存在
			if(length == sizeOfArray) {
				//长度已达上限，删除最老数据
				for(int i = 0 ; i < length - 1 ; i ++) {
					arr[i] = arr[i + 1];
				}
				
				length --;
			}
			
			//链表长度还没到上限，直接插入到最新
			arr[length] = info;
			length ++;
		}
	}
	
	public void print() {
		System.out.print("占用空间:" + sizeOfArray + "，元素个数" + length);
		StringBuffer buffer = new StringBuffer("，[");
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
		for(int i = 0 ; i < 10000 ; i ++) {
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
		
		System.out.println("耗时：" + (System.currentTimeMillis() - before) + "ms");
	}
}
