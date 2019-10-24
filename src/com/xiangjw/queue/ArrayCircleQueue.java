package com.xiangjw.queue;

/**
 * 用数组实现的循环队列，既可以控制大小，又能避免内存的频繁申请，神器
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class ArrayCircleQueue<T> {

	private T[] arr;
	private int length;
	private int sizeOfArr;
	
	private int startIndex;
	private int endIndex;
	
	public ArrayCircleQueue(int size) {
		this.sizeOfArr = size;
		this.length = 0;
		this.arr = (T[])new Object[size];
		this.startIndex = 0;
		this.endIndex = 0;
	}
	
	public void push(T data) {
		int next = getNext(startIndex);
		if(next == endIndex) {
			//队列已满
			System.out.println("队列已满");
			return;
		}
		
		startIndex = next;
		arr[startIndex] = data;
		length ++;
	}
	
	public T pull() {
		if(startIndex == endIndex) {
			System.out.println("队列已空");
			return null;
		}
		
		endIndex = getNext(endIndex);
		T temp = arr[endIndex];//取到尾指针的数据
		arr[endIndex] = null;//控制尾指针始终指向空
		length --;
		return temp;
	}
	
	public T get() {
		if(startIndex == endIndex) {
			System.out.println("队列已空");
			return null;
		}
		
		T temp = arr[getNext(endIndex)];//取到尾指针的数据
		return temp;
	}
	
	private int getNext(int index) {
//		if(index == sizeOfArr - 1) {
//			return 0;
//		}else {
//			return ++ index;
//		}
		
		return (index + 1) % sizeOfArr;
	}
	
	private boolean arrNotNull(int index) {
		if(startIndex > endIndex) {
			//起点在终点前面，说明没有形成环，控制index在起点和终点之间即可
			return (index > endIndex && index <= startIndex);
		}else if(startIndex < endIndex) {
			//起点在终点后面，说明已经成了环，则控制index不在起点和终点之间
			return !(index > startIndex && index <= endIndex);
		}else {
			//起点和终点不会相等，相等就是空的。
			return false;
		}
	}
	
	public void print() {
		System.out.print("数组占用空间:" + sizeOfArr + "，数组元素个数" + length);
		StringBuffer buffer = new StringBuffer("，队列尾-->[");
		int index = getNext(endIndex);
		while(arrNotNull(index)) {
			buffer.append(arr[index]).append(",");
			index = getNext(index);
		}
		buffer.append("]-->队列头");
		
		System.out.println(buffer.toString());
	}
	
	public static void main(String[] args) {
		ArrayCircleQueue<String> stack = new ArrayCircleQueue<String>(4);
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
