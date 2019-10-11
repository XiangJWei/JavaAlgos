package com.xiangjw.array;

/**
 * ��̬�����װ�࣬���ɲ���ɾ�����ҵ�
 * 1) ����Ĳ��롢ɾ���������±�������ʲ�����
 * 2�������е�������int���͵ģ�
 * @author Administrator
 *
 */
public class CustomArray {
	private int []arr;
	
	/**
	 * ����ʵ�ʵĳ���
	 */
	private int length;
	
	/**
	 * ����ռ�õĿռ��С
	 */
	private int sizeOfArr;
	
	public CustomArray(int sizeOfArr) {
		this.sizeOfArr = sizeOfArr;
		this.length = 0;
		this.arr = new int[sizeOfArr];
	}
	
	/**
	 * ƽ��ʱ�临�Ӷȣ�O(n)
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
			i = length;//����δ������������ĩλ��
		}else {
			i = length - 1;//��������������ĩλ��
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
	 * ʱ�临�Ӷȣ�O(1)
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
	 * ʱ�临�Ӷȣ�O(1)
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
	 * ƽ��ʱ�临�Ӷȣ�O(n)
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
	 * ƽ��ʱ�临�Ӷȣ�O(n)
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
	 * ʱ�临�Ӷȣ�O(1)
	 */
	public int findByIndex(int index) {
		if(index < 0 || index >= length) {
			System.out.println("out Of length");
			return 0;
		}
		
		return arr[index];
	}
	
	/**
	 * �Ƿ��ǻ��δ�
	 * �� 12321
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
		System.out.print("����ռ�ÿռ�:" + sizeOfArr + "������Ԫ�ظ���" + length);
		StringBuffer buffer = new StringBuffer("��[");
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
		System.out.println("����Ԫ�أ�" + array.query(7));
		System.out.println("����Ԫ�أ�" + array.query(9));
		System.out.println("����Ԫ�أ�" + array.findByIndex(6));
		System.out.println("����Ԫ�أ�" + array.findByIndex(3));
		
		array.remove(7);
		array.print();
		array.remove(2);
		array.print();
		array.add(2);
		array.print();
		array.remove(2);
		array.print();
		
		System.out.println("�ǻ��δ���  " + array.isPalindrome());
	}
}
