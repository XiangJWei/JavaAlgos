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
		Node p = firstNode;
		while(p != null) {
			if(p.getNext() == null) {
				p.setNext(node);
				length ++;
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
	
	public boolean checkIfRound() {
		if(length <= 1) {
			return false;
		}
		Node p = firstNode.getNext();
		while(p != null) {
			Node q = p.getNext();
			while (q != null) {
				if(q.getNext() != null && q.getNext() == p) {
					return true;
				}
				
				q = q.getNext();
			}
			p = p.getNext();
		}
		
		return false;
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
		System.out.println("�Ƿ��л���" + list.checkIfRound());
		list.add(list.findByIndex(2));
		System.out.println("�Ƿ��л���" + list.checkIfRound());
	}
}
