package com.xiangjw.stack;

/**
 * 链表实现栈   头插头删
 * @author Administrator
 *
 * @param <T>
 */
public class LinkedListStack<T> {
	
	private Node firstNode;
	
	private int length;
	
	public LinkedListStack(T unVal) {
		this.length = 0;
		this.firstNode = new Node(unVal, null);
	}
	
	public void push(T data) {
		Node temp = new Node(data, firstNode.getNext());
		firstNode.setNext(temp);
		length++;
	}
	
	public T pull() {
		if(length <= 0) {
			return null;
		}
		
		Node temp = firstNode.getNext();
		firstNode.setNext(firstNode.getNext().getNext());
		length --;
		return temp.getData();
	}
	
	public void print() {
		System.out.print("元素个数" + length);
		StringBuffer buffer = new StringBuffer("，栈顶<--[");
		Node p = firstNode.getNext();
		while (p != null) {
			buffer.append(p.getData() == null ? "null" : p.getData().toString()).append(",");
			p = p.getNext();
		}
		buffer.append("]<--栈底");
		
		System.out.println(buffer.toString());
	}

	private class Node{
		private T data;
		private Node next;
		public Node(T data, LinkedListStack<T>.Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	public static void main(String[] args) {
		LinkedListStack<String> stack = new LinkedListStack<String>(null);
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
