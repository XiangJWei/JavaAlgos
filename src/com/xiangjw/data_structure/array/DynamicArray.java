package com.xiangjw.data_structure.array;

/**
 * 动态数组，即内存可根据元素个数自动伸缩的数组
 * 可以理解为一个简单的ArrayList
 * @author xiangjw
 *
 * @param <T> 数组存放的元素类型
 */
public class DynamicArray<T> {

	private T[] arr;
	private int length;
	private int sizeOfArr;
	
	/**
	 * 初始化
	 * @param size 数组默认占用的存储空间大小
	 */
	public DynamicArray(int size){
		this.arr = (T[]) new Object[size];
		this.length = 0;
		this.sizeOfArr = size;
	}
	
	/**
	 * 往数组中指定位置添加元素，已有元素后移
	 * @param index
	 * @param val
	 * @return
	 */
	public boolean add(int index , T val) {
		if(index > sizeOfArr) {
			System.out.println("out of sizeOfArr");
			return false;
		}
		if(index < 0 || index > length) {
			System.out.println("out of length");
			return false;
		}
		
		//要扩容了
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
	
	/**
	 * 当数组容量不够或者过剩时，调整新的内存空间给数组
	 * @param size
	 */
	private void resize(int size) {
		T[] temp = (T[]) new Object[size];//定义新空间的数组
		System.arraycopy(arr, 0, temp, 0, length);//数据全量拷贝
		this.arr = temp;//新数组设为主数据
		this.sizeOfArr = size;
	}
	
	/**
	 * 往数组尾追加元素
	 * @param val
	 * @return
	 */
	public boolean add(T val) {
		return add(length , val);
	}
	
	/**
	 * 更新指定位置的元素值
	 * @param index
	 * @param val
	 * @return
	 */
	public boolean update(int index , T val) {
		if(index < 0 || index >= length) {
			System.out.println("out of length");
			return false;
		}
		
		arr[index] = val;
		return true;
	}
	
	/**
	 * 删除指定位置的元素
	 * @param index
	 * @return
	 */
	public boolean remove(int index) {
		if(index < 0 || index >= length) {
			System.out.println("out of length");
			return false;
		}
		
		//空间过剩要缩容了
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
	
	public int getLength() {
		return length;
	}

	/**
	 * 查找值
	 * @param val
	 * @return
	 */
	public int query(T val) {
		for(int i = 0 ; i < length ; i ++) {
			if(val.equals(arr[i])) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 按指定下标获取值
	 * @param index
	 * @return
	 */
	public T findByIndex(int index) {
		if(index < 0 || index >= length) {
			System.out.println("out of length");
			return null;
		}
		
		return arr[index];
	}
	
	/**
	 * 打印数组信息
	 */
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
