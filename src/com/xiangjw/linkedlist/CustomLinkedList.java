package com.xiangjw.linkedlist;

import com.xiangjw.lru.LruLinkedList.Node;

public class CustomLinkedList<T> {

	private Node firstNode;
	
	private int length;
	
	public CustomLinkedList() {
		this.length = 0;
		this.firstNode = null;
	}
	
	public void addToFirst(T val) {
		Node node = new Node(val, firstNode);
		firstNode = node;
		length++;
	}
	
	public void addToLast(T val) {
		if(length == 0) {
			addToFirst(val);
		}else if(length == 1) {
			Node node = new Node(val, null);
			firstNode.setNext(node);
			length++;
		}else {
			Node p = firstNode;
			while(p != null) {
				if(p.getNext() == null) {
					break;
				}
				p = p.getNext();
			}
			
			Node node = new Node(val, null);
			p.setNext(node);
			length++;
		}
	}
	
	public void addAfter(T compare , T val) {
		if(length == 0) {
			return;
		}else {
			Node p = firstNode;
			while(p != null) {
				if(p.getData().equals(compare)) {
					Node node = new Node(val, p.getNext());
					p.setNext(node);
					length++;
					break;
				}
				p = p.getNext();
			}
			
		}
	}
	
	public void removeLast() {
		if(length == 0) {
			return;
		}
		if(length == 1) {
			firstNode = null;
			length --;
			return;
		}
		Node p = firstNode;
		while(p != null) {
			if(p.getNext() != null && p.getNext().getNext() == null) {
				p.setNext(null);
				length --;
				return;
			}
			
			p = p.getNext();
		}
	}
	
	
	/**
	 * 是否是回形串
	 * 即 12321
	 */
	public boolean isPalindrome() {
		if(firstNode == null) {
			return false;
		}
		if(firstNode.getNext() == null) {
			return true;
		}
		
		//使用快慢指针,找到中间节点
		Node p = firstNode;
		Node q = firstNode;
		
		Node leftLast = null;
		Node newListRight = null;
		while(q != null) {
			if(q.getNext() == null) {
				//基数行数
				leftLast = p;
				newListRight = p;
				break;
			}else if(q.getNext().getNext() == null) {
				//偶数行数
				leftLast = p;
				newListRight = p.getNext();
				break;
			}
			p = p.getNext();
			q = q.getNext().getNext();
		}
		
		if(leftLast != null && newListRight != null) {
			//把左半边顺序翻转，创建一个新的链表，右半边维持不变
			Node start = firstNode;
			Node newListLeft = null;
			while(start != leftLast) {
				if(newListLeft == null) {
					newListLeft = new Node(start.getData(), null);
				}else {
					Node temp = new Node(start.getData(), newListLeft);
					newListLeft = temp;
				}
				start = start.getNext();
			}
			
			Node temp = new Node(start.getData(), newListLeft);
			newListLeft = temp;
			
			//对比newListLeft和newListRight
			Node pLeft = newListLeft;
			Node pRight = newListRight;
			while(pLeft != null && pRight != null) {
				if(!pLeft.getData().equals(pRight.getData())) {
					return false;
				}
				
				pLeft = pLeft.getNext();
				pRight = pRight.getNext();
			}
			
			return true;
		}
		
		return false;
	}
	
	public void print() {
		System.out.print("元素个数" + length);
		StringBuffer buffer = new StringBuffer("，[");
		Node p = firstNode;
		while (p != null) {
			buffer.append(p.getData() == null ? "null" : p.getData().toString()).append(",");
			p = p.getNext();
		}
		buffer.append("]");
		
		System.out.println(buffer.toString());
	}
	
	private class Node{
		private T data;
		private Node next;
		
		public Node(T data, CustomLinkedList<T>.Node next) {
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
		CustomLinkedList<Character> list = new CustomLinkedList();
		list.addToFirst('a');
		list.print();
		list.addToLast('b');
		list.print();
		list.addAfter('c' , 'e');
		list.print();
		list.addAfter('b' , 'e');
		list.print();
		list.addToLast('b');
		list.print();
		list.addToLast('a');
		list.addToLast('f');
		list.print();
		
		System.out.println("是回形串吗？  " + list.isPalindrome());
		list.print();
		
		list.removeLast();list.print();
		list.removeLast();list.print();
		list.removeLast();list.print();
		list.removeLast();list.print();
		list.removeLast();list.print();
		list.removeLast();list.print();
	}
}
