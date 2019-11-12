/**
 * 
 */
package com.xiangjw.data_structure.tree;

/**
 * Trie树，每个节点存一个字符，子节点不限数量。依次实现搜索引擎中根据输入的字符快速检索出相关关键词。
 * 适合查找前缀匹配的字符串，以及根据输入进行自动补全；不适合精确匹配的查找，构造压力大切内存消耗多
 * 但它有局限性，字符集不能过多，最好前缀重合较多不然浪费很多内存
 * 图例中设定只存a-z 26个字母，所以子节点的引用采用26大小的数组表示。
 * 
 * @author xiangjw
 *
 */
public class TrieTree {

	private Node firstNode;
	
	public TrieTree() {
		firstNode = new Node('/');//顶节点不放具体值
	}
	
	public void add(String data) {
		if(data == null || data.length() == 0) {
			return;
		}
		Node p = firstNode;
		for(int i = 0 ; i < data.length() ; i ++) {
			int currIndex = data.charAt(i) - 'a';
			Node temp = p.children[currIndex];
			if(temp == null) {
				p.children[currIndex] = new Node(data.charAt(i));
			}
			
			p = p.children[currIndex];
		}
		p.haveThisOne = true;
	}
	
	public void printWhenInput(String data) {
		Node p = firstNode;
		if(data == null || data.length() == 0) {
			System.out.println("无输入，关联结果如下：");
			print("", firstNode);
			return;
		}
		
		for(int i = 0 ; i < data.length() ; i ++) {
			int currIndex = data.charAt(i) - 'a';
			Node temp = p.children[currIndex];
			if(temp != null) {
				p = p.children[currIndex];
			}else {
				p = null;
			}
		}
		
		System.out.println("输入：" + data + "，关联结果如下：");
		if(p != null) {
			print(data, p);
		}else {
			System.out.println("无结果");
		}
	}
	
	private void print(String data , Node node) {
		boolean isNoChild = true;
		for(int i = 0 ; i < node.children.length ; i ++) {
			if(node.children[i] != null) {
				isNoChild = false;
				print(data + node.children[i].value, node.children[i]);
			}
		}
		
		if(isNoChild || node.haveThisOne) {
			System.out.println(data);
		}
	}
	
	class Node{
		private boolean haveThisOne;//避免部分被省略，比如he和hello，不处理的话he会失效
		private char value;
		private Node[] children;
		
		public Node(char val) {
			this.value = val;
			this.children = new Node[26];
			this.haveThisOne = false;
		}
	}
	
	public static void main(String[] args) {
		TrieTree tree = new TrieTree();
		tree.add("hello");
		tree.add("hi");
		tree.add("why");
		tree.add("she");
		tree.add("he");
		tree.add("me");
		tree.add("is");
		tree.add("mom");
		tree.add("grand");
		tree.add("great");
		
		tree.printWhenInput(null);
		tree.printWhenInput("h");
		tree.printWhenInput("he");
		tree.printWhenInput("hey");
	}
}
