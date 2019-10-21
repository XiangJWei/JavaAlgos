package com.xiangjw.tree;

public class CustomTree<T> {
	
	private Node rootTree;
	private int length;
	
	public CustomTree() {
		this.rootTree = new Node(null, null, null);
		this.length = 0;
	}
	
	public void printBefore(Node node) {
		if(node == null) {
			return;
		}
		if(node.data != null) {
			System.out.println(node);
		}
		
		printBefore(node.leftChild);
		printBefore(node.rightChild);
	}
	
	public void printMiddle(Node node) {
		if(node == null) {
			return;
		}
		printMiddle(node.leftChild);
		if(node.data != null) {
			System.out.println(node);
		}
		printMiddle(node.rightChild);
	}
	
	public void printAfter(Node node) {
		if(node == null) {
			return;
		}

		printAfter(node.leftChild);
		printAfter(node.rightChild);
		
		if(node.data != null) {
			System.out.println(node);
		}
	}
	

	private class Node{
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
}
