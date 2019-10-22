package com.xiangjw.tree;

/**
 * ���������
 * ���������Ҫ�������е�����һ���ڵ㣬���������е�ÿ���ڵ��ֵ����ҪС������ڵ��ֵ�����������ڵ��ֵ����������ڵ��ֵ��
 * @author Administrator
 *
 */
public class BinarySearchTree {
	
	private TreeNode root;
	private int length;
	
	public BinarySearchTree() {
		this.root = null;
		this.length = 0;
	}
	
	public void add(int val) {
		if(root == null) {
			root = new TreeNode(val, null, null);
			length ++;
			return;
		}
		
		addTo(root , val);
	}
	
	public void addTo(TreeNode parent , int val) {
		if(val < parent.data) {
			if(parent.left == null) {
				parent.left = new TreeNode(val, null, null);
				length ++;
			}else {
				addTo(parent.left , val);
			}
		}else {
			if(parent.right == null) {
				parent.right = new TreeNode(val, null, null);
				length ++;
			}else {
				addTo(parent.right , val);
			}
		}
	}
	
	public void delete(int val) {
		delete(null, root, val);
	}
	
	private void delete(TreeNode parent , TreeNode target , int val) {
		if(target == null) {
			return;
		}
		
		if(target.data == val) {
			if(target.left == null && target.right == null) {
				//û���ӽڵ㣬ֱ��ɾ����ǰ�ڵ�
				if(parent == null) {
					root = null;
					length --;
					return;
				}
				
				if(parent.data > val) {
					parent.left = null;
				}else {
					parent.right = null;
				}
				length --;
			}else if(target.left != null && target.right != null) {
				//�������ӽڵ㣬ѡȡ���ӽڵ�������һ����������ǰ�ڵ�
				TreeNode tempParent = target;
				target.data = getMax(tempParent , target.left , true);
				length --;
			}else if(target.left != null){
				//��һ�����ӽڵ�
				if(parent == null) {
					root = target.left;
				}else if(parent.data > val) {
					parent.left = target.left;
				}else {
					parent.right = target.left;
				}
				length --;
			}else {
				//��һ�����ӽڵ�
				if(parent == null) {
					root = target.right;
				}else if(parent.data > val) {
					parent.left = target.right;
				}else {
					parent.right = target.right;
				}
				length --;
			}

			if(target.right != null) {
				delete(getNextParent(parent , val) , target.right , val);
			}
		}else if(target.data > val) {
			delete(getNextParent(parent , val) , target.left , val);
		}else {
			delete(getNextParent(parent , val) , target.right , val);
		}
	}
	
	private int getMax(TreeNode parent , TreeNode target , boolean isLeft) {
		if(target == null) {
			return -1;
		}
		if(target.right == null) {
			if(isLeft) {
				parent.left = target.left;
			}else {
				parent.right = target.left;
			}
			
			return target.data;
		}
		
		return getMax(target , target.right , false);
		
	}
	
	private TreeNode getNextParent(TreeNode parent , int val) {
		if(parent == null) {
			return root;
		}
		
		if(parent.data > val) {
			return parent.left;
		}else {
			return parent.right;
		}
	}
	
	public TreeNode[] find(int val) {
		TreeNode[] arr = new TreeNode[length];
		find(arr , root , val);
		return arr;
	}
	
	private void find(TreeNode[] arr , TreeNode target , int val) {
		if(target == null) {
			return;
		}
		if(target.data == val) {
			addFind(arr , target);

			if(target.right != null) {
				find(arr , target.right , val);
			}
		}else if(target.data > val) {
			find(arr , target.left , val);
		}else {
			find(arr , target.right , val);
		}
	}
	
	private void addFind(TreeNode[] arr , TreeNode node) {
		for(int i = 0 ; i < arr.length ; i ++) {
			if(arr[i] == null) {
				arr[i] = node;
				break;
			}
		}
	}
	
	private void print() {
		print(root);
		System.out.println(" length:" + length);
	}
	
	private void print(TreeNode tree) {
		if(tree == null) {
			return;
		}
		
		print(tree.left);
		System.out.print(tree.data + ",");
		print(tree.right);
	}
	

	private class TreeNode{
		private int data;
		private TreeNode left;
		private TreeNode right;
		public TreeNode(int data, TreeNode left, TreeNode right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.add(5);tree.print();
		tree.add(2);tree.print();
		tree.add(3);tree.print();
		tree.add(7);tree.print();
		tree.add(8);tree.print();
		tree.add(5);tree.print();
		tree.add(9);tree.print();
		tree.add(6);tree.print();
		tree.add(4);tree.print();
		
		TreeNode[] searchResult = tree.find(5);
		System.out.print("���ҽ����");
		for(int i = 0 ; i < searchResult.length ; i ++) {
			if(searchResult[i] != null) {
				System.out.print(searchResult[i].data + ",");
			}
		}
		System.out.println("");
		
		tree.delete(4);tree.print();
		tree.delete(5);tree.print();
		tree.delete(3);tree.print();
		tree.delete(8);tree.print();
	}
}
