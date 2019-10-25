package com.xiangjw.data_structure.queue;

/**
 * 数组实现队列，头出尾进
 * 尾插入，头删除（逻辑删除，数组内容依然存在，只是指针变了，摊还时间复杂度O(1)）
 * 空间不够了就扩容
 * 随着队列的不断存取，存在比较多的空间浪费，这时推荐使用ArrayCircleQueue
 * 
 * @author xiangjw
 *
 * @param <T>
 */
public class ArrayQuene<T> {

	private T[] arr;
	private int length;
	private int sizeOfArr;
	
	private int firstIndex;//指向头
	private int lastIndex;//指向尾
	
	
	public ArrayQuene(int size) {
		this.arr = (T[])new Object[size];
		this.length = 0;
		this.sizeOfArr = size;
		this.firstIndex = 0;
		this.lastIndex = -1;
	}
	
	public void push(T data) {
		//空间多了要扩容
		if(length + firstIndex == sizeOfArr) {
			resize(sizeOfArr << 1);
		}
		
		arr[++lastIndex] = data;
		length ++;
	}
	
	public int getLength() {
		return length;
	}

	public T pull() {
		if(length <= 0) {
			return null;
		}
		
		T temp = arr[firstIndex];
		arr[firstIndex++] = null;
		length --;
		
		//空间浪费要缩容
		if(sizeOfArr > 1 && sizeOfArr > length << 1) {
			resize(sizeOfArr >> 1);
		}
		return temp;
	}
	
	public T get() {
		if(length <= 0) {
			return null;
		}
		
		return arr[firstIndex];
	}
	
	/**
	 * 当数组容量不够或者过剩时，调整新的内存空间给数组
	 * @param size
	 */
	public void resize(int size) {
		T[] temp = (T[])new Object[size];
		System.arraycopy(arr, firstIndex, temp, 0, length);//这里有个位移
		arr = temp;
		sizeOfArr = size;
		
		//移动数组后，需重新设置起止指针
		firstIndex = 0;
		lastIndex = length -1;
	}
	
	/**
	 * 打印数组
	 */
	public void print() {
		System.out.print("数组占用空间:" + sizeOfArr + "，数组元素个数" + length);
		StringBuffer buffer = new StringBuffer("，队列尾-->[");
		for(int i = 0 ; i < sizeOfArr ; i ++) {
			if(i == 0) {
				buffer.append(arr[i]);
			}else {
				buffer.append(",").append(arr[i]);
			}
		}
		buffer.append("]-->队列头");
		
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
