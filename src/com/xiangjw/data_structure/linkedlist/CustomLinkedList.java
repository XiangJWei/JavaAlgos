package com.xiangjw.data_structure.linkedlist;


/**
 * 通用链表存储类
 * 	判断是否是回形串是一个难点
 * @author xiangjw
 *
 * @param <T>
 */
public class CustomLinkedList<T> {

	private Node firstNode;//链表首节点，里面存储有下一个节点的地址
	private int length;//链表节点数量
	
	public CustomLinkedList() {
		this.length = 0;
		this.firstNode = null;
	}
	
	public int getLength() {
		return length;
	}

	/**
	 * 添加元素到首节点
	 * @param val
	 */
	public void addToFirst(T val) {
		Node node = new Node(val, firstNode);
		firstNode = node;
		length++;
	}
	
	/**
	 * 添加元素到末尾节点
	 * @param val
	 */
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
	
	/**
	 * 获取首节点
	 * @return
	 */
	public T getFirst() {
		if(length == 0) {
			return null;
		}
		
		return firstNode.data;
	}
	
	/**
	 * 获取指定位置的节点
	 * @param index
	 * @return
	 */
	public T get(int index) {
		if(index >= length) {
			return null;
		}
		
		Node p = firstNode;
		for(int i = 0 ; i < index ; i ++) {
			p = p.getNext();
		}
		
		return p.getData();
	}
	
	/**
	 * 判断指定元素是否在链表中存在
	 * @param val
	 * @return
	 */
	public boolean isExist(T val) {
		Node p = firstNode;
		while(p != null) {
			if(p.getData().equals(val)) {
				return true;
			}
			p = p.getNext();
		}
		
		return false;
	}
	
	/**
	 * 往指定元素后追加中间节点
	 * @param compare
	 * @param val
	 */
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
	
	/**
	 * 删除最后一个节点
	 */
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
	 * 链表判断是否是回形串
	 * 原理是：按照快慢指针找到中间节点，然后前半部分链表进行反转，再以后半部分链表进行对比，都能匹配上就是回形串
	 * 注意链表元素是奇数和偶数时两个链表边界需要不同处理。
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
			
			//对比左半边和右半边
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
	
	/**
	 * 打印链表数据
	 */
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
	
	/**
	 * 链表的节点类
	 *
	 */
	private class Node{
		private T data;//本节点存的数据
		private Node next;//下一节点
		
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
