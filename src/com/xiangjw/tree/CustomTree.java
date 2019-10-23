package com.xiangjw.tree;

import com.xiangjw.queue.ArrayQuene;

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
	
	public Node addLeftChild(Node parent, T val) {
		Node newNode = new Node(val, null, null);
		if(parent != null) {
			parent.leftChild = newNode;
		}else {
			rootTree = newNode;
		}
		
		length++;
		return newNode;
	}
	
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
		if(node.data != null) {
			System.out.print(node.data + ",");
		}
		
		printBefore(node.leftChild);
		printBefore(node.rightChild);
	}
	
	/**
	 * 中序遍历
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
		printMiddle(node.leftChild);
		if(node.data != null) {
			System.out.print(node.data + ",");
		}
		printMiddle(node.rightChild);
	}
	
	/**
	 * 后序遍历
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

		printAfter(node.leftChild);
		printAfter(node.rightChild);
		
		if(node.data != null) {
			System.out.print(node.data + ",");
		}
	}
	
	/**
	 * 按层次遍历
	 */
	public void printLayer() {
		System.out.print("按层次遍历：");
		printLayer(rootTree);
		System.out.println("");
	}
	
	private void printLayer( Node node) {
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
		private Node leftChild;
		private Node rightChild;
		public Node(T data, Node leftChild, Node rightChild) {
			super();
			this.data = data;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}
	
	public static void main(String[] args) {
		CustomTree<Integer> tree = new CustomTree<Integer>();
		CustomTree.Node node1 = tree.addLeftChild(null, 1);
		CustomTree.Node node2 = tree.addLeftChild(node1, 2);
		CustomTree.Node node3 = tree.addRightChild(node1, 3);
		CustomTree.Node node4 = tree.addLeftChild(node2, 4);
		CustomTree.Node node5 = tree.addRightChild(node2, 5);
		CustomTree.Node node6 = tree.addLeftChild(node3, 6);
		CustomTree.Node node7 = tree.addRightChild(node3, 7);
		
		tree.printBefore();
		tree.printMiddle();
		tree.printAfter();
		tree.printLayer();
		
	}
}
