package com.xiangjw.linkedlist;


/**
 * ������ת
 * �����л��ļ��
 * �������������ϲ�
 * ɾ���������� n �����
 * ��������м���
 */
public class LinkedListAlgo {
	
	private Node firstNode;
	
	private int length;
	
	/**
	 * ���ڱ��ڵ�������ʼ��
	 * @param preVal  �ڱ��ڵ����ֵ
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
	
	public void add(Node node) {
		//����node�ĳ��ȣ���Ϊnode���ܲ���һ����һ�ڵ㣬Ҳ������һ������
		Node m = node;
		int count = 0;
		while(m != null) {
			count ++;
			m = m .getNext();
		}
		
		//�ٲ���
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
	 * ��ת������
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
	 * ��������Ƿ������������У����ػ�����ڵ�
	 * 
	 * @return
	 */
	public Node checkIfRound() {
		if(length <= 1) {
			return null;
		}
		Node p = firstNode.getNext();//��ָ��
		Node q = firstNode.getNext();//��ָ��
		int i = 0;
		boolean isOk = false;
		while(q != null && q.getNext() != null && q.getNext().getNext() != null) {
			//����л�������ָ���������һ�ο�ָ�볬����ָ��ʱ��Ȼ�����ָ������
			if(i > 0 && p == q) {//�ų���һ�Σ���Ϊ��һ������ָ�����һ��
				isOk = true;
				System.out.println("��" + i + "������");
				break;
			}
			p = p.getNext();
			q = q.getNext().getNext();
			i++;
		}
		
		if(isOk) {
			//p�ص���ʼλ�ã�q�����������㡣
			//������ָ��ͬ�ٶ��ܣ��ٴ���������ǻ������
			//������ڴ�ΪK����һ����������ΪN���򻷵Ĵ�СΪN������P����һ�ε���ڻ���M,��M = N - (N - K) = K
			//���԰�P�ƻ���ʼ�㣬Q��N�㣬����ָ��ͬ�ٶ��ƶ�K�����ͻ���K��������
			p = firstNode.getNext();
			while(q != null && p != null && p.getNext() != null && q.getNext() != null) {
				if(p == q) {
					//�ҵ���ʼ�㣬Ҳ����K��
					return p;
				}
				p = p.getNext();
				q = q.getNext();
			}
		}
		
		return null;
	}
	
	/**
	 * ������������ĺϲ�
	 * @param other
	 */
	public void sortWith(LinkedListAlgo other) {
		Node p = firstNode.getNext();
		Node q = other.firstNode.getNext();
		LinkedListAlgo newList = new LinkedListAlgo(-1);
		
		while(p != null || q != null) {
			if(p == null) {
				//p�Ѿ���������������ֱ��׷��q��ʣ��ڵ㣬ѭ����ֹ
				newList.add(q);
				break;
			}else if(q == null) {
				//q�Ѿ���������������ֱ��׷��p��ʣ��ڵ㣬ѭ����ֹ
				newList.add(p);
				break;
			}else if(p.getData() <= q.getData()){
				//p��q������ֵ�����ԶԱȣ�pС��p��������һ��ѭ��
				newList.add(new Node(p.getData(), null));
				p = p.getNext();
			}else {
				//p��q������ֵ�����ԶԱȣ�qС��q��������һ��ѭ��
				newList.add(new Node(q.getData(), null));
				q = q.getNext();
			}
		}
		
		newList.print();
	}
	
	/**
	 * ɾ���������� n �����
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
			//pָ��������index���ڵ�
			sp ++;
			p = p.getNext();
		}
		
		while(p != null && p.getNext() != null) {
			p = p.getNext();
			q = q.getNext();
		}
		
		//ɾ��q�ڵ�ĺ�һ���ڵ�
		q.setNext(q.getNext().getNext());
		length --;
	}
	
	/**
	 * ��������м���
	 */
	public void printMiddleNode() {
		Node p = firstNode.getNext();
		Node q = firstNode.getNext();
		
		while(q != null) {
			if(q.getNext() == null) {
				//�����������м�ڵ�Ϊһ��
				System.out.println("�м�ڵ�Ϊ��" + p.getData());
				return;
			}else if(q.getNext().getNext() == null) {
				//�����������м�ڵ�Ϊ����
				System.out.println("�м�ڵ�Ϊ��" + p.getData() + " + " + p.getNext().getData());
				return;
			}
			
			p = p.getNext();
			q = q.getNext().getNext();
		}
	}
	
	public void print() {
		System.out.print("Ԫ�ظ���" + length);
		StringBuffer buffer = new StringBuffer("��[");
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
		System.out.println("�Ƿ��л���" + (circle == null ? "ľ��": "�У����ֵΪ" + circle.getData()));
		list.add(6);
		list.print();
		list.add(list.findByIndex(4));
		Node circle2 = list.checkIfRound();
		System.out.println("�Ƿ��л���" + (circle2 == null ? "ľ��": "�У����ֵΪ" + circle2.getData()));
		
		
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
