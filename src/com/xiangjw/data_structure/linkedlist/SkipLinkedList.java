package com.xiangjw.data_structure.linkedlist;

/**
 * 跳表，又称跳跃链表
 * 基础链表是有序的，上层增加N层索引，每次增加删除都确保有序，这样查找时通过层级索引来跳跃式遍历链表，实现快速查找，类似基于链表实现的一种类似“二分”的算法
 * 单链表中存储有序数据，然后多了n层索引，这样便于快速查找
 * 查找、增删效率都还比较高。
 * 
 * 本例中链表是从小到大排列，考虑了重复值情况
 * @author xiangjw
 *
 */
public class SkipLinkedList {
	
	private Node firstNode;//存储单链表
	private int length;//横向节点数量
	private int levelCount;//整个跳表中的最大层数
	
	private static final float SKIPLIST_P = 0.5f;
	private static final int MAX_LEVEL = 16;
	
	public SkipLinkedList() {
		this.firstNode = new Node(-1 , 0);//哨兵
		this.firstNode.forwards = new Node[MAX_LEVEL];
		this.length = 0;
		this.levelCount = 1;
	}
	
	/**
	 * 插入一个节点
	 * @param val
	 */
	public void insert(int val) {
		if(val < 0){
			return;
		}
		int level = randomLevel();
		
		Node p = firstNode;
		Node node = new Node(val , level);//要插入的节点
		Node[] brothers = new Node[level];//找到每一层这个待插节点的前节点。
		for(int i = level - 1 ; i >= 0 ; i --) {
			while(p.forwards[i] != null && p.forwards[i].data < val) {
				p = p.forwards[i];//先找到第i层最后一个小于val的节点，也就是新节点会插到它后面
			}
			
			brothers[i] = p;//赋值每一层的前节点
			//这里两层循环中的p无需重置，因为对同一节点来讲，i层已经比较过第i层p.data<val，那么第i-1层p.data也一定小于val。所以i-1层可以继续往p.forwards往后找i-1层的合理位置
		}
		
		//根据找到的前节点数据，我们来插入node吧
		for(int i = 0 ; i < level ; i ++) {
			node.forwards[i] = brothers[i].forwards[i];
			brothers[i].forwards[i] = node;
		}
		
		if(levelCount < level) {
			levelCount = level;
		}
		length++;
	}
	
	/**
	 * 删除一个节点
	 * @param val
	 */
	public void delete(int val) {
		if(val < 0){
			return;
		}
		
		Node p = firstNode;
		for(int i = levelCount - 1 ; i >= 0 ; i --) {
			while(p.forwards[i] != null && p.forwards[i].data < val) {
				p = p.forwards[i];//先找到最后一个小于val的节点
			}
			
			while(p.forwards[i] != null && p.forwards[i].data == val) {
				p.forwards[i] = p.forwards[i].forwards[i];//然后循环判断依次删除值相等的下一跳节点
			}
		}
		
		//删除之后还要判断索引层数有没有降低
		int delCount = 0;
		for(int i = levelCount - 1 ; i >= 0 ; i --) {
			if(firstNode.forwards[i] == null) {
				delCount ++;
			}else {
				break;
			}
		}
		levelCount -= delCount;
		length --;
	}
	
	/**
	 * 查找节点
	 * @param val
	 * @return
	 */
	public Node find(int val) {
		Node p = firstNode;
		//这里看似有两个循环，但是p节点在每一层是不用从头遍历的，可以跳跃式遍历，查找的时间复杂度O(logN)，和二分查找一样
		for(int i = levelCount - 1 ; i >= 0 ; i --) {
			while(p.forwards[i] != null && p.forwards[i].data < val) {
				p = p.forwards[i];//先找到最后一个小于val的节点
			}
			
			if(p.forwards[i] != null && p.forwards[i].data == val) {
				return p.forwards[i];
			}
		}
		
		return null;
	}
	
	private int randomLevel() {
		int level = 1;
		
		while(Math.random() < SKIPLIST_P && level <=MAX_LEVEL) {
			level ++;//这里为了使level随机概率比较均匀，为1的概率是50%，为2的概率是25%，为3的概率是12.5%，以此类推
		}
		
		return level;
	}
	
	public void print() {
		for(int i = levelCount - 1 ; i >= 0 ; i --) {
			Node p = firstNode;
			System.out.print("第" + i + "层：");
			int length = 0;
			while(p.forwards[i] != null) {
				System.out.print(p.forwards[i].data + ",");
				length++;
				p = p.forwards[i];
			}
			
			System.out.println("节点数：" + length);
		}
		
		System.out.println("最大层数：" + levelCount + "\n");
	}
	
	private class Node {
		private int data;
		private Node[] forwards;//是该节点在各个level索引的下一个数据节点
		private int currLevel;//当前节点索引层数，最底下基础链表也算一层,所以最小为1
		public Node() {
			super();
		}
		public Node(int data , int currLevel) {
			super();
			this.data = data;
			this.currLevel = currLevel;
			forwards = new Node[currLevel];
		}
	}
	
	public static void main(String[] args) {
		SkipLinkedList list = new SkipLinkedList();
		list.insert(5);list.print();
		list.insert(3);list.print();
		list.insert(1);list.print();
		list.insert(2);list.print();
		list.insert(5);list.print();
		
		Node node = list.find(5);
		System.out.println("查找节点5，结果为：" + (node == null ? "木有找到" : node.data));
		Node node2 = list.find(8);
		System.out.println("查找节点8，结果为：" + (node2 == null ? "木有找到" : node2.data) + "\n");
		
		list.delete(5);list.print();
		list.delete(2);list.print();
	}
}
