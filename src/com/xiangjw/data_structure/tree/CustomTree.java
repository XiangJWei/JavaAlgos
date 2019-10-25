package com.xiangjw.data_structure.tree;

import com.xiangjw.data_structure.queue.ArrayQuene;

/**
 * 定义一棵二叉树
 * 可以把二叉树理解为扩展了的单链表，从线性变为了树状
 * 并且进行其常规的前序、中序、后序、层次遍历
 * 按层次遍历需重点关注
 * 
 * @author xiangjw
 *
 * @param <T>
 */
public class CustomTree<T> {
	
	private Node rootTree;
	private int length;
	
	public CustomTree() {
		this.rootTree = null;
		this.length = 0;
	}
	
	public Node getRoot() {
		return rootTree;
	}
	
	/**
	 * 添加指定节点的左子节点
	 * @param parent
	 * @param val
	 * @return
	 */
	public Node addLeftChild(Node parent, T val) {
		Node newNode = new Node(val, null, null);//哨兵节点，主要用处是便于避免一些遍历的边界特殊处理
		if(parent != null) {
			parent.leftChild = newNode;
		}else {
			rootTree = newNode;
		}
		
		length++;
		return newNode;
	}
	
	/**
	 * 添加指定节点的右子节点
	 * @param parent
	 * @param val
	 * @return
	 */
	public Node addRightChild(Node parent, T val) {
		Node newNode = new Node(val, null, null);
		if(parent != null) {
			parent.rightChild = newNode;
		}else {
			rootTree = newNode;
		}
		
		length++;
		return newNode;
	}
	
	/**
	 * 前序遍历
	 * 递归实现，底层是栈的思想
	 */
	public void printBefore(){
		System.out.print("前序遍历：");
		printBefore(rootTree);
		System.out.println("");
	}
	
	private void printBefore(Node node) {
		if(node == null) {
			return;
		}
		
		//先打印自己
		if(node.data != null) {
			System.out.print(node.data + ",");
		}
		
		//再依次打印左子节点和右子节点
		printBefore(node.leftChild);
		printBefore(node.rightChild);
	}
	
	/**
	 * 中序遍历
	 * 递归实现，底层是栈的思想
	 */
	public void printMiddle(){
		System.out.print("中序遍历：");
		printMiddle(rootTree);
		System.out.println("");
	}
	
	private void printMiddle(Node node) {
		if(node == null) {
			return;
		}
		
		//先打印左边子节点
		printMiddle(node.leftChild);
		//再打印自己
		if(node.data != null) {
			System.out.print(node.data + ",");
		}
		//最后打印右边子节点
		printMiddle(node.rightChild);
	}
	
	/**
	 * 后序遍历
	 * 递归实现，底层是栈的思想
	 */
	public void printAfter(){
		System.out.print("后序遍历：");
		printAfter(rootTree);
		System.out.println("");
	}
	
	private void printAfter(Node node) {
		if(node == null) {
			return;
		}

		//先打印左右两边的子节点
		printAfter(node.leftChild);
		printAfter(node.rightChild);
		
		//再打印自己
		if(node.data != null) {
			System.out.print(node.data + ",");
		}
	}
	
	/**
	 * 按层次遍历
	 * 先打印顶层，再打印顶部第二层，再打印顶部第三层
	 * 
	 * 核心是利用队列。从上往下遍历到的节点以此放入队列，然后循环取出队列就ok了
	 */
	public void printLayer() {
		System.out.print("按层次遍历：");
		printLayer(rootTree);
		System.out.println("");
	}
	
	private void printLayer(Node node) {
		if(node == null) {
			return;
		}
		ArrayQuene<Node> queue = new ArrayQuene(length);
		
		queue.push(node);
		while(queue.getLength() > 0) {
			Node temp = queue.pull();
			System.out.print(temp.data + ",");
			
			if(temp.leftChild != null) {
				queue.push(temp.leftChild);
			}
			if(temp.rightChild != null) {
				queue.push(temp.rightChild);
			}
		}
	}

	class Node{
		private T data;
		private Node leftChild;//二叉树的左子节点
		private Node rightChild;//二叉树的右子节点
		public Node(T data, Node leftChild, Node rightChild) {
			super();
			this.data = data;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		CustomTree<Integer> tree = new CustomTree<Integer>();
		CustomTree.Node node1 = tree.addLeftChild(null, 1);
		CustomTree.Node node2 = tree.addLeftChild(node1, 2);
		CustomTree.Node node3 = tree.addRightChild(node1, 3);
		tree.addLeftChild(node2, 4);
		tree.addRightChild(node2, 5);
		tree.addLeftChild(node3, 6);
		tree.addRightChild(node3, 7);
		
		tree.printBefore();
		tree.printMiddle();
		tree.printAfter();
		tree.printLayer();
		
	}
}
