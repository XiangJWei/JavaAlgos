package com.xiangjw.lru;

public class LruLinkedList<T> {
	
	private Node firstNode;
	
	private int length;
	
	private int sizeOfList;
	
	public LruLinkedList(int size) {
		this.length = 0;
		this.sizeOfList = size;
		this.firstNode = null;
	}
	
	/**
	 * Ѱ��ĳԪ�ص���һ�ڵ㣬������ڵĻ�
	 * ƽ��ʱ�临�Ӷ�O(n)
	 * @param info
	 * @return
	 */
	private Node findPreNode(T info) {
		Node p = firstNode;
		while(p != null) {
			if(p.getNext() != null && info.equals(p.getNext().getData())) {
				return p;
			}
			p = p.getNext();
		}
		
		return null;
	}
	
	public void put(T info) {
		if(info == null) {
			throw new IllegalArgumentException("Value cannot be null");
		}
		
		//Ԫ�����ڶ����ҵ�һλ���������κδ���
		if(firstNode != null && info.equals(firstNode.getData())) {
			return;
		}
		
		Node pre = findPreNode(info);
		if(pre != null) {
			//�����Ҳ��ڵ�һλ,ֱ��Ų��Ԫ�ص���һλ
			Node target = pre.getNext();
			pre.setNext(target.getNext());
			target.setNext(firstNode);
			firstNode = target;
		}else {
			//������
			if(length == sizeOfList) {
				//�������Ѵ����ޣ�ɾ����ĩλ
				if(sizeOfList > 1) {
					//�ҵ��������ڶ�λ��ʹ֮��Ϊ������һλ
					Node p = firstNode;
					while(p != null) {
						if(p.getNext() != null && p.getNext().getNext() == null) {
							break;
						}
						p = p.getNext();
					}
					
					if(p != null) {
						p.setNext(null);
						length --;
					}
				}else {
					//���������ܹ���һλ��ֱ�����㡣
					firstNode = null;
					length = 0;
				}
			}
				
			//�����Ȼ�û�����ޣ�ֱ�Ӳ���
			Node target = new Node(info, firstNode);
			firstNode = target;
			length ++;
		}
	}
	
	public void print() {
		System.out.print("ռ�ÿռ�:" + sizeOfList + "��Ԫ�ظ���" + length);
		StringBuffer buffer = new StringBuffer("��[");
		Node p = firstNode;
		while (p != null) {
			buffer.append(p.getData() == null ? "null" : p.getData().toString()).append(",");
			p = p.getNext();
		}
		buffer.append("]");
		
		System.out.println(buffer.toString());
	}

	public class Node{
		private T data;
		private Node next;
		
		@Override
		public String toString() {
			return "Node [data=" + (data == null ? "null" : data.toString()) + ", next=" + (next == null ? "null" : next.toString()) + "]";
		}

		public Node(T data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	public static void main(String[] args) {
		long before = System.currentTimeMillis();
		LruLinkedList<String> cache = new LruLinkedList<String>(1000);
		for(int i = 0 ; i < 1000000 ; i ++) {
		cache.put("abc" + i);
		cache.put("123" + i);
		cache.put("33" + i);
		cache.put("43" + i);
		cache.put("123" + i);
		cache.put("3243" + i);
		cache.put("2342" + i);
		cache.put("12312" + i);
		cache.put("123" + i);
		cache.put("12312" + i);
		cache.put("12312" + i);
		cache.put("sds" + i);
		}
		cache.print();
		
		System.out.println("��ʱ��" + (System.currentTimeMillis() - before) + "ms");
	}
}

