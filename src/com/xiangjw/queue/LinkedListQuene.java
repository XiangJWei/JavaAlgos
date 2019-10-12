package com.xiangjw.queue;

public class LinkedListQuene<T> {
	
	private Node firstNode;
	private int length;
	
	public LinkedListQuene(T unVal) {
		length = 0;
		firstNode = new Node(unVal, null);
	}
	
	public void push(T data) {
		Node temp = new Node(data, firstNode.getNext());
		firstNode.setNext(temp);
		length ++;
	}
	
	public T pull() {
		if(length == 0) {
			return null;
		}
		
		Node p = firstNode;
		while(p != null) {
			if(p.getNext() != null && p.getNext().getNext() == null) {
				Node temp = p.getNext();
				p.setNext(null);
				length --;
				return p.getData();
			}
			p = p.getNext();
		}
		
		
		return null;
	}
	
	public void print() {
		System.out.print("Ԫ�ظ���" + length);
		StringBuffer buffer = new StringBuffer("������ͷ<--[");
		Node p = firstNode.getNext();
		while (p != null) {
			buffer.append(p.getData() == null ? "null" : p.getData().toString()).append(",");
			p = p.getNext();
		}
		buffer.append("]<--����β");
		
		System.out.println(buffer.toString());
	}

	private class Node{
		private T data;
		private Node next;
		public Node(T data, LinkedListQuene<T>.Node next) {
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
		LinkedListQuene<String> stack = new LinkedListQuene<String>(null);
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
