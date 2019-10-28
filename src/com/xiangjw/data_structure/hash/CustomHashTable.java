package com.xiangjw.data_structure.hash;

import java.util.Random;

import com.xiangjw.data_structure.array.DynamicArray;
import com.xiangjw.data_structure.linkedlist.CustomLinkedList;
import com.xiangjw.data_structure.linkedlist.SkipLinkedList;

/**
 * 设计一个简单的散列表，类似hashmap
 * 利用数组来存储主数据，当出现散列冲突时再利用链表来存储多个散列冲突的值
 * 特点，无序，可快速查找、插入、删除。本例中key不可重复
 * 对于散列表来说散列函数的设计和散列冲突的处理是其重点，决定了散列表的性能
 * 当大数据量时还应考虑数组的扩容，不然数据量越来越大，散列表性能越来越低。
 * 		本例中采用的一次性扩容策略，导致临界扩容的那一次put会慢（数据拷贝带来性能差异），
 * 		有一种思路是扩容时不做数据全量拷贝，而是保留新旧array，每次拷贝一小部分，把拷贝的时间均分到多次put中。直到全部拷贝完成。
 * 
 * 
 * @author xiangjw
 *
 */
public class CustomHashTable<K , V> {
	
	private static final float SP_OF_LIMIT = 0.75f;

	private CustomLinkedList<KVObject>[] arr;//数组加链表形式存储
	private int totalSize;//整个table存放的总数据量（含链表里的冲突数据）
	private int length;//数组使用了的数据量大小（不含链表里的冲突数据）
	private int sizeOfArr;//数组空间大小
	//装载因子 = totalSize/sizeOfArr
	
	public CustomHashTable(int size) {
		this.totalSize = 0;
		this.length = 0;
		this.sizeOfArr = size;
		this.arr = new CustomLinkedList[size];
	}
	
	/**
	 * 散列表中添加元素
	 * @param key
	 * @param val
	 */
	public void put(K key , V val) {
		if(totalSize * 1.0f / sizeOfArr >= SP_OF_LIMIT) {
			//装载因子超了，需要扩容了
			resize(sizeOfArr * 2);
		}
		
		KVObject object = new KVObject(key , val);
		int hashVal = getHashValue(key);//根据key判断应该存在数组的哪一个下面
		if(arr[hashVal] == null) {
			CustomLinkedList link = new CustomLinkedList<KVObject>();
			link.addToFirst(object);
			arr[hashVal] = link;
			length ++;
		}else {
			if(arr[hashVal].getLength() == 0) {
				length ++;
			}else {
				CustomLinkedList<KVObject>.Iterator p = arr[hashVal].getIterator();
				while (p.haveData()) {
					KVObject obj = p.getCurr();
					if(obj.key.equals(key)) {//元素key已存在，则更新值即可
						obj.val = val;
						return;
					}
					
					p.moveToNext();
				}
			}
			
			arr[hashVal].addToLast(object);
		}
		
		totalSize ++;
	}
	
	/**
	 * 散列函数
	 * @param key
	 * @return
	 */
	public int getHashValue(K key) {
		return getHashValue(key , sizeOfArr);
	}
	
	public int getHashValue(K key , int newSize) {
		int hash = key.hashCode();
		return hash % newSize;
	}
	
	/**
	 * 根据key查找元素
	 * @param key
	 * @return
	 */
	public V get(K key) {
		int hashVal = getHashValue(key);
		if(arr[hashVal] == null) {
			return null;
		}
		
		CustomLinkedList<KVObject> list = arr[hashVal];
		if(list == null) {
			return null;
		}
		
		CustomLinkedList<KVObject>.Iterator p = list.getIterator();
		while (p.haveData()) {
			KVObject obj = p.getCurr();
			if(obj.key.equals(key)) {
				return obj.val;
			}
			
			p.moveToNext();
		}
		
		return null;
	}
	
	/**
	 * 散列表根据key删除元素
	 * @param key
	 */
	public void remove(K key) {
		int hashVal = getHashValue(key);
		if(arr[hashVal] == null) {
			return;
		}
		
		CustomLinkedList<KVObject> list = arr[hashVal];
		if(list == null || list.getLength() == 0) {
			return;
		}
		CustomLinkedList<KVObject>.Iterator p = list.getIterator();
		while (p.haveData()) {
			KVObject obj = p.getCurr();
			if(obj.key.equals(key)) {
				list.removeNode(obj);
				totalSize --;
				break;
			}
			p.moveToNext();
		}
		
		if(list.getLength() == 0) {
			length --;
			
			if(totalSize * 1.0f / sizeOfArr <= SP_OF_LIMIT / 2.0f && sizeOfArr / 2 > length) {
				//装载因子小了，可以考虑缩容
				resize(sizeOfArr / 2);
			}
		}
	}
	
	/**
	 * 数组扩容/缩容
	 * @param size
	 */
	private void resize(int size) {
		CustomLinkedList<KVObject>[] temp = new CustomLinkedList[size];
		for(int i = 0 ; i < size ; i ++) {
			temp[i] = new CustomLinkedList<KVObject>();
		}
		
		int newLength = 0;
		for(int i = 0 ; i < sizeOfArr ; i ++) {
			CustomLinkedList<KVObject> list = arr[i];
			if(list == null) {
				continue;
			}
			CustomLinkedList<KVObject>.Iterator p = list.getIterator();
			while (p.haveData()) {
				KVObject obj = p.getCurr();
				int newIndex = getHashValue(obj.key , size);
				if(temp[newIndex].getLength() == 0) {
					newLength ++;
				}
				temp[newIndex].addToLast(obj);
				
				p.moveToNext();
			}
		}
		
		this.arr = temp;
		this.sizeOfArr = size;
		this.length = newLength;
	}
	
	public void print() {
		System.out.println("散列表 占用存储空间：" + sizeOfArr + "，占用数组元素：" + length + "个，总元素个数：" + totalSize);
		for(int i = 0 ; i < sizeOfArr ; i ++) {
			CustomLinkedList<KVObject> list = arr[i];
			if(list == null) {
				continue;
			}
			CustomLinkedList<KVObject>.Iterator p = list.getIterator();
			System.out.print("第" + i + "层：");
			while (p.haveData()) {
				KVObject obj = p.getCurr();
				
				System.out.print(obj.key + "," + obj.val + ";");
				
				p.moveToNext();
			}
			
			System.out.println("");
		}
		
		System.out.println("");
	}
	
	class KVObject{
		private K key;
		private V val;
		public KVObject(K key, V val) {
			super();
			this.key = key;
			this.val = val;
		}
	}
	
	public static void main(String[] args) {
		CustomHashTable<Integer, String> map = new CustomHashTable<Integer, String>(5);
		map.put(2, "hello");map.print();
		map.put(7, "world");map.print();
		map.put(3, "this");map.print();
		map.put(26, "is");map.print();
		map.put(2, "my");map.print();
		map.put(5, "girl");map.print();
		map.put(15, "but");map.print();
		map.put(19, "hehe");map.print();
		map.put(6, "wawa");map.print();
		map.put(86, "wawa");map.print();
		
		int searchKey = 2;
		System.out.println("查找元素" + searchKey + "，结果为：" + map.get(searchKey));
		searchKey = 25;
		System.out.println("查找元素" + searchKey + "，结果为：" + map.get(searchKey) + "\n");
		
		map.remove(2);map.print();
		map.remove(10);map.print();
		map.remove(7);map.print();
		map.remove(3);map.print();
		map.remove(5);map.print();
		map.remove(9);map.print();
		map.remove(19);map.print();
		map.remove(6);map.print();
		
		//性能对比时刻
		int size = 100 , length = 1000;
		DynamicArray[] array = new DynamicArray[size];
		CustomLinkedList[] link = new CustomLinkedList[size];
		CustomHashTable<Integer, Integer>[] table = new CustomHashTable[size];
		SkipLinkedList[] skipList = new SkipLinkedList[size];
		
		Random random = new Random();
		KV[] testData = new KV[length];
		for(int i = 0 ; i < length ; i ++) {
			testData[i] = new KV(random.nextInt(length), random.nextInt(length));
		}
		
		long before = System.currentTimeMillis();
		for(int i = 0 ; i < size ; i ++) {
			array[i] = new DynamicArray<Integer>(10);
			for(int j = 0 ; j < length ; j ++) {
				array[i].add(testData[j].getKey() , testData[j].getValue());
			}
		}
		System.out.println(size + "个数组插入" + length + "个数据，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < size ; i ++) {
			link[i] = new CustomLinkedList<Integer>();
			for(int j = 0 ; j < length ; j ++) {
				link[i].addToLast(testData[j].getValue());
			}
		}
		System.out.println(size + "个链表插入" + length + "个数据，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < size ; i ++) {
			table[i] = new CustomHashTable<Integer, Integer>(10);
			for(int j = 0 ; j < length ; j ++) {
				table[i].put(testData[j].getKey(), testData[j].getValue());
			}
		}
		System.out.println(size + "个散列表插入" + length + "个数据，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < size ; i ++) {
			skipList[i] = new SkipLinkedList();
			for(int j = 0 ; j < length ; j ++) {
				skipList[i].insert(testData[j].getValue());
			}
		}
		System.out.println(size + "个跳表插入" + length + "个数据，耗时" + (System.currentTimeMillis() - before) + "ms");
	
		int[] randomSearch = new int[length / 2];
		for(int i = 0 ; i < randomSearch.length ; i ++) {
			randomSearch[i] = random.nextInt(length);
		}
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < size ; i ++) {
			for(int j = 0 ; j < randomSearch.length ; j ++) {
				array[i].query(randomSearch[i]);
			}
		}
		System.out.println(size + "个数组，每个" + length + "条数据，查找" + randomSearch.length + "次，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < size ; i ++) {
			for(int j = 0 ; j < randomSearch.length ; j ++) {
				link[i].isExist(randomSearch[i]);
			}
		}
		System.out.println(size + "个链表，每个" + length + "条数据，查找" + randomSearch.length + "次，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < size ; i ++) {
			for(int j = 0 ; j < randomSearch.length ; j ++) {
				table[i].get(randomSearch[i]);
			}
		}
		System.out.println(size + "个链表，每个" + length + "条数据，查找" + randomSearch.length + "次，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < size ; i ++) {
			for(int j = 0 ; j < randomSearch.length ; j ++) {
				skipList[i].find(randomSearch[i]);
			}
		}
		System.out.println(size + "个跳表，每个" + length + "条数据，查找" + randomSearch.length + "次，耗时" + (System.currentTimeMillis() - before) + "ms");
		
	}
}

class KV{
	private int key;
	
	private int value;
	
	public int getKey() {
		return key;
	}

	public int getValue() {
		return value;
	}



	public KV(int key, int value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	
}