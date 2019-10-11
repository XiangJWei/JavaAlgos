package com.xiangjw.array;

/**
 * 静态数组封装类，集成插入删除查找等
 * 1) 数组的插入、删除、按照下标随机访问操作；
 * 2）数组中的数据是int类型的；
 * @author Administrator
 *
 */
public class CustomArray {
	private int []arr;
	
	/**
	 * 数组实际的长度
	 */
	private int length;
	
	/**
	 * 数组占用的空间大小
	 */
	private int sizeOfArr;
	
	public CustomArray(int sizeOfArr) {
		this.sizeOfArr = sizeOfArr;
		this.length = 0;
		this.arr = new int[sizeOfArr];
	}
	
	/**
	 * 平均时间复杂度：O(n)
	 */
	public boolean add(int index , int val) {
		if(index >= sizeOfArr) {
			System.out.println("out Of sizeOfArr");
			return false;
		}
		
		if(index == -1 || index > length) {
			System.out.println("out Of length");
			return false;
		}
		
		int i;
		if(length < sizeOfArr) {
			i = length;//数组未满，则不用舍弃末位。
		}else {
			i = length - 1;//数组已满，舍弃末位。
		}
		
		for(; i > index ; i --) {
			arr[i] = arr[i - 1];
		}
		
		arr[i] = val;
		if(length < sizeOfArr) {
			length ++;
		}
		return true;
	}
	
	/**
	 * 时间复杂度：O(1)
	 */
	public boolean add(int val) {
		if(length == sizeOfArr) {
			System.out.println("out Of sizeOfArr");
			return false;
		}
		
		arr[length++] = val;
		return true;
	}
	
	/**
	 * 时间复杂度：O(1)
	 */
	public boolean update(int index , int val) {
		if(index < 0 || index >= length) {
			System.out.println("out Of length");
			return false;
		}
		
		arr[index] = val;
		return true;
	}
	
	/**
	 * 平均时间复杂度：O(n)
	 */
	public boolean remove(int index) {
		if(index < 0 || index >= length) {
			System.out.println("out Of length");
			return false;
		}
		
		for(int i = index ; i < length ; i ++) {
			if(i == length - 1) {
				arr[i] = 0;
			}else {
				arr[i] = arr[i + 1];
			}
		}
		
		length --;
		return true;
	}
	
	/**
	 * 平均时间复杂度：O(n)
	 */
	public int query(int val) {
		for(int i = 0 ; i < length ; i ++) {
			if(arr[i] == val) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * 时间复杂度：O(1)
	 */
	public int findByIndex(int index) {
		if(index < 0 || index >= length) {
			System.out.println("out Of length");
			return 0;
		}
		
		return arr[index];
	}
	
	/**
	 * 是否是回形串
	 * 即 12321
	 */
	public boolean isPalindrome() {
		if(length == 0) {
			return false;
		}
		if(length == 1) {
			return true;
		}
		
		for(int i = 0 , j = length - 1 ; i <= j ; i ++ , j -- ) {
			if(arr[i] != arr[j]) {
				return false;
			}
		}
		
		return true;
	}
	
	public void print() {
		System.out.print("数组占用空间:" + sizeOfArr + "，数组元素个数" + length);
		StringBuffer buffer = new StringBuffer("，[");
		for(int i = 0 ; i < sizeOfArr ; i ++) {
			if(i == 0) {
				buffer.append(arr[i]);
			}else {
				buffer.append(",").append(arr[i]);
			}
		}
		buffer.append("]");
		
		System.out.println(buffer.toString());
	}
	
	public static void main(String []args) {
		CustomArray array = new CustomArray(5);
		array.print();
		array.add(5, 5);
		array.print();
		array.add(0, 5);
		array.print();
		array.add(7);
		array.print();
		array.add(1, 3);
		array.print();
		array.add(0, 2);
		array.print();
		array.add(3, 5);
		array.print();
		array.add(2, 9);
		array.print();
		array.add(-5);
		array.print();
		System.out.println("查找元素：" + array.query(7));
		System.out.println("查找元素：" + array.query(9));
		System.out.println("查找元素：" + array.findByIndex(6));
		System.out.println("查找元素：" + array.findByIndex(3));
		
		array.remove(7);
		array.print();
		array.remove(2);
		array.print();
		array.add(2);
		array.print();
		array.remove(2);
		array.print();
		
		System.out.println("是回形串吗？  " + array.isPalindrome());
	}
}
