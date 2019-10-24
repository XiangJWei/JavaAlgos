package com.xiangjw.tree;

/**
 * 二叉查找树
 * 二叉查找树要求，在树中的任意一个节点，其左子树中的每个节点的值，都要小于这个节点的值，而右子树节点的值都大于这个节点的值。
 * 如果树的左右两端平衡，那么时间复杂度接近于O(logN),极端情况时都在一端，那么时间复杂度退化为O(n)
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
	
	/**
	 * 添加树节点
	 * @param val
	 */
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
	
	/**
	 * 删除树节点
	 * @param parent
	 * @param target
	 * @param val
	 */
	public void delete(int val) {
		delete(null, root, val);
	}
	
	private void delete(TreeNode parent , TreeNode target , int val) {
		if(target == null) {
			return;
		}
		
		if(target.data == val) {
			if(target.left == null && target.right == null) {
				//没有子节点，直接删除当前节点
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
				//有两个子节点，选取左子节点中最大的一个，换到当前节点
				TreeNode tempParent = target;
				target.data = getMax(tempParent , target.left , true);
				length --;
			}else if(target.left != null){
				//有一个左子节点
				if(parent == null) {
					root = target.left;
				}else if(parent.data > val) {
					parent.left = target.left;
				}else {
					parent.right = target.left;
				}
				length --;
			}else {
				//有一个右子节点
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
	
	/**
	 * 查找树
	 * @param val
	 * @return
	 */
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
	
	/**
	 * 打印树
	 */
	public void print() {
		print(root);
		System.out.println(" length:" + length + " 树的高度：" + getTreeWeight(root));
	}
	
	private void print(TreeNode tree) {
		if(tree == null) {
			return;
		}
		
		print(tree.left);
		System.out.print(tree.data + ",");
		print(tree.right);
	}
	
	/**
	 * 获取树的高度
	 * @param target
	 * @return
	 */
	public int getTreeWeight(TreeNode target) {
		if(target == null) {
			return 0;
		}
		
		int left = getTreeWeight(target.left);
		int right = getTreeWeight(target.right);
		return (left > right) ? (left + 1) : (right + 1);
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
		System.out.print("查找结果：");
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
