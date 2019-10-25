package com.xiangjw.data_structure.linkedlist;


/**
 * 链表几个常用算法
 * 这里定义的链表首节点是个空节点，也就是无意义的哨兵节点，主要用处是便于避免一些遍历的边界特殊处理
 * 
 * 单链表反转
 * 链表中环的检测
 * 两个有序的链表合并
 * 删除链表倒数第 n 个结点
 * 求链表的中间结点
 */
public class LinkedListAlgo {
	
	private Node firstNode;
	private int length;
	
	/**
	 * 带哨兵节点的链表初始化
	 * @param preVal  哨兵节点给的值
	 */
	public LinkedListAlgo(int preVal) {
		firstNode = new Node(preVal, null);
		length = 0;
	}
	
	public void add(int val) {
		Node p = firstNode;
		while(p != null) {
			if(p.getNext() == null) {
				Node node = new Node(val, null);
				p.setNext(node);
				length ++;
				return;
			}
			p = p .getNext();
		}
	}
	
	/**
	 * 往链表尾部添加固定节点
	 * 注意这个节点本身也可能是一个多节点的链表
	 * @param node
	 */
	public void add(Node node) {
		//先找node的长度，因为待添加节点node可能不是一个单一节点，也可能是一个链表
		Node m = node;
		int count = 0;
		while(m != null) {
			count ++;
			m = m .getNext();
		}
		
		//再插入
		Node p = firstNode;
		while(p != null) {
			if(p.getNext() == null) {
				p.setNext(node);
				length += count;
				return;
			}
			p = p .getNext();
		}
	}
	
	/**
	 * 删除指定元素值的节点
	 * @param val
	 */
	public void del(int val) {
		Node p = firstNode;
		while(p != null) {
			if(p.getNext().getData() == val) {
				p.setNext(p.getNext().getNext());
				length --;
				return;
			}
			p = p.getNext();
		}
	}
	
	/**
	 * 获取指定位置的节点
	 * @param index
	 * @return
	 */
	public Node findByIndex(int index) {
		int i = 0 ; 
		Node p = firstNode.getNext();
		while(p != null) {
			if(index == i) {
				return p;
			}
			i++;
			p = p.getNext();
		}
		
		return null;
	}

	/**
	 * 反转，倒叙
	 * 定义一个空链表
	 * 在从前往后遍历原链表时，同时插入遍历元素到新链表的头节点，保证顺序刚好相反，再遍历新链表即倒叙
	 */
	public void roundOver() {
		Node newNode = new Node(firstNode.getData(), null);
		Node p = firstNode.getNext();
		Node q = newNode;
		while(p != null) {
			q.setNext(new Node(p.getData(), q.getNext()));
			
			p = p .getNext();
		}
		
		newNode.print();
	}
	
	/**
	 * 检测链表是否包含环，如果有，返回环的入口点
	 * 通过快慢指针，如果快慢指针开始后有相遇，那必然就有环。
	 * 
	 * @return
	 */
	public Node checkIfRound() {
		if(length <= 1) {
			return null;
		}
		Node p = firstNode.getNext();//慢指针
		Node q = firstNode.getNext();//快指针
		int i = 0;
		boolean isOk = false;
		while(q != null && q.getNext() != null && q.getNext().getNext() != null) {
			//如果有环，那慢指针进环后，下一次快指针超过慢指针时必然会和慢指针相遇
			if(i > 0 && p == q) {//排除第一次，因为第一次两个指针就在一起
				isOk = true;
				System.out.println("第" + i + "步相遇");
				break;
			}
			p = p.getNext();
			q = q.getNext().getNext();
			i++;
		}
		
		if(isOk) {
			//p回到起始位置，q还是在相遇点。
			//这是两指针同速度跑，再次相遇点就是环的入口
			//假设入口处为K，第一次两点相遇为N，则环的大小为N，假设P离下一次到入口还有M,则M = N - (N - K) = K
			//所以把P移回起始点，Q在N点，两个指针同速度移动K步，就会在K点相遇。
			p = firstNode.getNext();
			while(q != null && p != null && p.getNext() != null && q.getNext() != null) {
				if(p == q) {
					//找到起始点，也就是K点
					return p;
				}
				p = p.getNext();
				q = q.getNext();
			}
		}
		
		return null;
	}
	
	/**
	 * 两个有序链表的合并
	 * 定义两个指针分别对两个链表，从前往后依次找最值拷贝到新链表，待遍历完两个链表，新链表就是有序的。
	 * @param other
	 */
	public void sortWith(LinkedListAlgo other) {
		Node p = firstNode.getNext();
		Node q = other.firstNode.getNext();
		LinkedListAlgo newList = new LinkedListAlgo(-1);
		
		while(p != null || q != null) {
			if(p == null) {
				//p已经遍历结束，所以直接追加q的剩余节点，循环终止
				newList.add(q);
				break;
			}else if(q == null) {
				//q已经遍历结束，所以直接追加p的剩余节点，循环终止
				newList.add(p);
				break;
			}else if(p.getData() <= q.getData()){
				//p、q都还有值，所以对比，p小放p，进入下一次循环
				newList.add(new Node(p.getData(), null));
				p = p.getNext();
			}else {
				//p、q都还有值，所以对比，q小放q，进入下一次循环
				newList.add(new Node(q.getData(), null));
				q = q.getNext();
			}
		}
		
		newList.print();
	}
	
	/**
	 * 删除链表倒数第 n 个结点
	 * 同样用快慢指针的思想，两个指针，p快p先走n步，然后pq再同步前进，当p到末尾时，q就到了倒数第n个节点
	 * @param index
	 */
	public void deleteLastIndexOf(int index) {
		if(index <= 0) {
			return;
		}
		if(index > length) {
			return;
		}
		
		Node p = firstNode.getNext();
		Node q = firstNode;
		
		int sp = 0;
		while(p != null && sp < index - 1) {
			//p指向正数第index个节点
			sp ++;
			p = p.getNext();
		}
		
		while(p != null && p.getNext() != null) {
			p = p.getNext();
			q = q.getNext();
		}
		
		//删除q节点的后一个节点
		q.setNext(q.getNext().getNext());
		length --;
	}
	
	/**
	 * 找链表的中间结点
	 * 快慢指针，一个每次走一步，一个每次走两步，这样两步指针到链表尾时一步指针就到中间了
	 */
	public void printMiddleNode() {
		Node p = firstNode.getNext();
		Node q = firstNode.getNext();
		
		while(q != null) {
			if(q.getNext() == null) {
				//基数行数，中间节点为一个
				System.out.println("中间节点为：" + p.getData());
				return;
			}else if(q.getNext().getNext() == null) {
				//基数行数，中间节点为两个
				System.out.println("中间节点为：" + p.getData() + " + " + p.getNext().getData());
				return;
			}
			
			p = p.getNext();
			q = q.getNext().getNext();
		}
	}
	
	public void print() {
		System.out.print("元素个数" + length);
		StringBuffer buffer = new StringBuffer("，[");
		Node p = firstNode.getNext();
		while (p != null) {
			buffer.append(p.getData()).append(",");
			p = p.getNext();
		}
		buffer.append("]");
		
		System.out.println(buffer.toString());
	}
	
	private class Node{
		private int data;
		private Node next;
		
		public Node(int data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		
		public void print() {
			StringBuffer buffer = new StringBuffer("[");
			Node p = this;
			while (p != null) {
				buffer.append(p.getData()).append(",");
				p = p.getNext();
			}
			buffer.append("]");
			
			System.out.println(buffer.toString());
		}
	}
	
	public static void main(String []args) {
		LinkedListAlgo list = new LinkedListAlgo(-1);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.print();
		
		list.roundOver();
		Node circle = list.checkIfRound();
		System.out.println("是否有环：" + (circle == null ? "木有": "有，入口值为" + circle.getData()));
		list.add(6);
		list.print();
		list.add(list.findByIndex(4));
		Node circle2 = list.checkIfRound();
		System.out.println("是否有环：" + (circle2 == null ? "木有": "有，入口值为" + circle2.getData()));
		
		
		LinkedListAlgo list2 = new LinkedListAlgo(-1);
		list2.add(1);
		list2.add(3);
		list2.add(10);
		list2.add(18);
		list2.add(20);
		list2.print();
		
		LinkedListAlgo list3 = new LinkedListAlgo(-1);
		list3.add(2);
		list3.add(11);
		list3.add(19);
		list3.add(25);
		list3.add(30);
		list3.print();
		
		list2.sortWith(list3);
		
		list3.printMiddleNode();
		list3.deleteLastIndexOf(5);
		list3.print();
		list3.printMiddleNode();
		list3.deleteLastIndexOf(3);
		list3.print();
		list3.printMiddleNode();
	}
}
