package com.xiangjw.linkedlist;

/**
 * 跳表，又称跳跃链表
 * @author Administrator
 *
 */
public class SkipLinkedList {
	
	private Node firstNode;//存储单链表
	private int length;
	private int levelCount;
	
	private static final float SKIPLIST_P = 0.5f;
	private static final int MAX_LEVEL = 16;
	
	public SkipLinkedList() {
		this.firstNode = new Node(-1 , 0);//哨兵
		this.length = 0;
		this.levelCount = 1;
	}
	
	public void insert(int val) {
		if(val < 0){
			return;
		}
		int level = randomLevel();
		
		Node p = firstNode;
		Node item = new Node(val , level);
		//先补充第一个节点
		for(int i = p.currLevel ; i < level ; i ++) {
			if(p.next == null) {
				p.next = new Node[level];
				p.next[i] = new Node(-1 , level);
			}else if(p.next[i] == null){
				p.next[i] = new Node(p.next[0].data , level);
			}else {
				p.next[i].data = p.next[0].data;
			}
			
			p.next[i].currLevel = level;
		}
		
		for(int i = 0; i < level ; i ++) {
			Node m = firstNode;
			while(m.next != null) {
				if(m.data < val && (m.next[i].data >= val || m.next[i] == null)) {
					break;
				}
				m = m.next[i];
			}
			
			item.next[i] = m.next[i];
			m.next[i] = item;
		}
		
		if(levelCount < level) {
			levelCount = level;
		}
		
		length++;
	}
	
	public void delete(int val) {
		
	}
	
	public int get() {
		
		return -1;
	}
	
	private int randomLevel() {
		int level = 1;
		
		while(Math.random() < SKIPLIST_P && level <=MAX_LEVEL) {
			level ++;
		}
		
		return level;
	}
	
	public void print() {
		Node p = firstNode;
		StringBuffer buffer = new StringBuffer();
		while(p.next != null && p.next[0] != null) {
			buffer.append(p.next[0].data).append(",").append(p.next[0].currLevel).append(";");
			p = p.next[0];
		}
		
		System.out.println("当前链表：" + buffer.toString());
	}
	
	private class Node {
		private int data;
		private Node[] next;//next[0]存放原本链表的下一个，next[1]存放跳表上一层的对应对象，以此类推。
		private int currLevel;//当前节点共有多少层
		public Node() {
			super();
		}
		public Node(int data , int currLevel) {
			super();
			this.data = data;
			this.currLevel = currLevel;
		}
	}
	
	public static void main(String[] args) {
		SkipLinkedList list = new SkipLinkedList();
		list.insert(5);list.print();
		list.insert(3);list.print();
		list.insert(1);list.print();
		list.insert(2);list.print();
		list.insert(5);list.print();
	}
}
