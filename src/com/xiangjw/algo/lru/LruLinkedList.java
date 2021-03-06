package com.xiangjw.algo.lru;

/**
 * 利用链表实现lru算法
 * 添加元素时，如果该值已存在，就挪到最新；如果不存在且数组没满，就直接插到最新；如果不存在但数组满了，就删除最旧插入最新；
 * 获取元素时，如果存在就挪到最新并返回。否则返回空
 * 
 * @author xiangjw
 *
 * @param <T>
 */
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
	 * 从缓存中获取元素
	 * @param info
	 * @return
	 */
	public Node find(T info) {
		Node pre = null;
		Node p = firstNode;
		while(p != null) {
			if(p.getNext() != null && info.equals(p.getNext().getData())) {
				pre = p;
			}
			p = p.getNext();
		}
		
		if(pre != null) {
			//存在且不在第一位,直接挪动元素到第一位
			Node target = pre.getNext();
			pre.setNext(target.getNext());
			target.setNext(firstNode);
			firstNode = target;
			
			return target;
		}
		
		return null;
	}
	
	/**
	 * 缓存中增加元素
	 * @param info
	 */
	public void put(T info) {
		if(info == null) {
			throw new IllegalArgumentException("Value cannot be null");
		}
		
		//元素已在队列且第一位，无需再任何处理
		if(firstNode != null && info.equals(firstNode.getData())) {
			return;
		}
		
		Node curr = find(info);
		if(curr == null){
			//不存在
			if(length == sizeOfList) {
				//链表长度已达上限，删除最末位
				if(sizeOfList > 1) {
					//找到链表倒数第二位，使之变为倒数第一位
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
					//链表容量总共就一位，直接清零。
					firstNode = null;
					length = 0;
				}
			}
				
			//链表长度还没到上限，直接插入
			Node target = new Node(info, firstNode);
			firstNode = target;
			length ++;
		}
	}
	
	/**
	 * 打印缓存链表信息
	 */
	public void print() {
		System.out.print("占用空间:" + sizeOfList + "，元素个数" + length);
		StringBuffer buffer = new StringBuffer("，[");
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
		for(int i = 0 ; i < 10000 ; i ++) {
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
		
		System.out.println("耗时：" + (System.currentTimeMillis() - before) + "ms");
	}
}

