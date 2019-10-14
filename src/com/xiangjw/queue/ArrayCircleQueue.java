package com.xiangjw.queue;

/**
 * ������ʵ�ֵ�ѭ�����У��ȿ��Կ��ƴ�С�����ܱ����ڴ��Ƶ�����룬����
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
			//��������
			System.out.println("��������");
			return;
		}
		
		startIndex = next;
		arr[startIndex] = data;
		length ++;
	}
	
	public T pull() {
		if(startIndex == endIndex) {
			System.out.println("�����ѿ�");
			return null;
		}
		
		endIndex = getNext(endIndex);
		T temp = arr[endIndex];//ȡ��βָ�������
		arr[endIndex] = null;//����βָ��ʼ��ָ���
		length --;
		return temp;
	}
	
	public T get() {
		if(startIndex == endIndex) {
			System.out.println("�����ѿ�");
			return null;
		}
		
		T temp = arr[getNext(endIndex)];//ȡ��βָ�������
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
			//������յ�ǰ�棬˵��û���γɻ�������index�������յ�֮�伴��
			return (index > endIndex && index <= startIndex);
		}else if(startIndex < endIndex) {
			//������յ���棬˵���Ѿ����˻��������index���������յ�֮��
			return !(index > startIndex && index <= endIndex);
		}else {
			//�����յ㲻����ȣ���Ⱦ��ǿյġ�
			return false;
		}
	}
	
	public void print() {
		System.out.print("����ռ�ÿռ�:" + sizeOfArr + "������Ԫ�ظ���" + length);
		StringBuffer buffer = new StringBuffer("������β-->[");
		int index = getNext(endIndex);
		while(arrNotNull(index)) {
			buffer.append(arr[index]).append(",");
			index = getNext(index);
		}
		buffer.append("]-->����ͷ");
		
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
