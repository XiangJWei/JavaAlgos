package com.xiangjw.queue;

/**
 * ����ʵ�ֶ���
 * β���룬ͷɾ�����߼�ɾ��������������Ȼ���ڣ�ֻ��ָ����ˣ�̯��ʱ�临�Ӷ�O(1)��
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class ArrayQuene<T> {

	private T[] arr;
	
	private int length;
	
	private int firstIndex;
	private int lastIndex;
	
	private int sizeOfArr;
	
	public ArrayQuene(int size) {
		this.arr = (T[])new Object[size];
		this.length = 0;
		this.sizeOfArr = size;
		this.firstIndex = 0;
		this.lastIndex = -1;
	}
	
	public void push(T data) {
		if(length == sizeOfArr) {
			resize(sizeOfArr << 1);
		}
		
		arr[++lastIndex] = data;
		length ++;
	}
	
	public T pull() {
		if(length <= 0) {
			return null;
		}
		
		T temp = arr[firstIndex];
		arr[firstIndex++] = null;
		length --;
		
		if(sizeOfArr > 1 && sizeOfArr > length << 1) {
			resize(sizeOfArr >> 1);
		}
		return temp;
	}
	
	public void resize(int size) {
		T[] temp = (T[])new Object[size];
		System.arraycopy(arr, firstIndex, temp, 0, length);//�����и�λ��
		arr = temp;
		sizeOfArr = size;
		
		//�ƶ������������������ָֹ��
		firstIndex = 0;
		lastIndex = length -1;
	}
	
	public void print() {
		System.out.print("����ռ�ÿռ�:" + sizeOfArr + "������Ԫ�ظ���" + length);
		StringBuffer buffer = new StringBuffer("������β-->[");
		for(int i = 0 ; i < sizeOfArr ; i ++) {
			if(i == 0) {
				buffer.append(arr[i]);
			}else {
				buffer.append(",").append(arr[i]);
			}
		}
		buffer.append("]-->����ͷ");
		
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
