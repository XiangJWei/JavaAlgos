package com.xiangjw.linkedlist;

/**
 * Ìø±í£¬ÓÖ³ÆÌøÔ¾Á´±í
 * @author Administrator
 *
 */
public class SkipLinkedList<T> {
	
	private Node firstNode;//´æ´¢µ¥Á´±í
	
	private int length;
	
	private SkipNode[] skipArr;
	
	public SkipLinkedList() {
		this.firstNode = new Node(null , null);//ÉÚ±ø
		this.length = 0;
	}
	
	public int getDeep(int length) {
		if(length <= 2) {
			return 1;
		}
		
		return getDeep(length / 2) + 1;
	}
	
	public void initSkipArr() {
		if(length < 100) {
			return;
		}
		
		int deep = getDeep(length);
		skipArr = (SkipNode[]) new Object[deep];
		
		Node p = firstNode.getNext();
		int index = 0;
		while(p.getNext() != null) {
			
			p = p.getNext();
		}
	}
	
	
	
	private class Node {
		private T data;
		private Node next;
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
		public Node(T data, SkipLinkedList<T>.Node next) {
			super();
			this.data = data;
			this.next = next;
		}
	}
	
	private class SkipNode extends Node{
		private Node down;
		public Node getDown() {
			return down;
		}
		public void setDown(Node down) {
			this.down = down;
		}
		public SkipNode(T data, SkipLinkedList<T>.Node next, SkipLinkedList<T>.Node down) {
			super(data, next);
			this.down = down;
		}
	}
}
