package com.xiangjw.graph;

import com.xiangjw.array.CustomArray;
import com.xiangjw.array.DynamicArray;
import com.xiangjw.linkedlist.CustomLinkedList;
import com.xiangjw.queue.ArrayQuene;

/**
 * ����һ������ͼ
 * ������ڼ򵥿����õ�������������洢��
 * �����Ҫ���ߵĲ�ѯЧ�ʣ�����ʹ������ӣ�����ɢ�б�������ȣ�
 * @author Administrator
 *
 */
public class CustomGraph {
	private DynamicArray<CustomLinkedList<Integer>> graph;//�ڽӱ���ʾͼ
	
	public CustomGraph(int size){
		graph = new DynamicArray<CustomLinkedList<Integer>>(size);
		
		for(int i = 0 ; i < size ; i ++) {
			CustomLinkedList<Integer> list = new CustomLinkedList();
			list.addToFirst(i);
			graph.add(list);
		}
	}
	
	/**
	 * ��ͼ���һ����ϵ��
	 * @param s ���ĸ��ڵ�
	 * @param t ���ĸ��ڵ�
	 */
	public void addEdge(int s , int t) {
		if(s >= graph.getLength() || t >= graph.getLength()) {
			return;
		}
		if(!graph.findByIndex(s).isExist(t))
			graph.findByIndex(s).addToLast(t);
		if(!graph.findByIndex(t).isExist(s))
			graph.findByIndex(t).addToLast(s);
	}
	
	
	/**
	 * �����������
	 * @param s ��ʼ��
	 * @param t �յ�
	 */
	public void bfs(int s , int t) {
		ArrayQuene<CustomLinkedList<Integer>> queue = new ArrayQuene<CustomLinkedList<Integer>>(graph.getLength());
		CustomArray visited = new CustomArray(graph.getLength());//��ʼȫ-1,��¼�Ѿ������ʵĶ��������ⱻ�ظ�����
		CustomArray prev = new CustomArray(graph.getLength());//��ʼȫ-1����¼ĳ�ڵ����һ���ڵ�
		
		for(int i = 0 ; i < graph.getLength() ; i ++) {
			visited.add(-1);
			prev.add(-1);
		}
		
		visited.set(s, 1);
		queue.push(graph.findByIndex(s));//��ʼ�������
		boolean isOk = false;
		while(queue.getLength() > 0 && !isOk) {
			CustomLinkedList<Integer> curr = queue.pull();
			
			for(int i = 1 ; i < curr.getLength() ; i ++) {
				Integer temp = curr.get(i);
				
				if(temp.intValue() == t) {
					//�ҵ���
					prev.set(temp, curr.getFirst());
					isOk = true;//�˳�˫��ѭ��
					break;
				}
				
				if(visited.findByIndex(temp) == -1) {
					visited.set(temp, 1);
					prev.set(temp, curr.getFirst());
					queue.push(graph.findByIndex(temp));
				}
			}
		}

		System.out.println("���������������" + s + "��" + t + "��̵�·������");
		printBfs(prev , s , t , t);
		System.out.println("");
	}
	
	/**
	 * �����������
	 * @param s ��ʼ��
	 * @param t �յ�
	 */
	public void dfs(int s , int t) {
		CustomArray visited = new CustomArray(graph.getLength());//��ʼȫ-1,��¼�Ѿ������ʵĶ��������ⱻ�ظ�����
		CustomArray prev = new CustomArray(graph.getLength());//��ʼȫ-1����¼ĳ�ڵ����һ���ڵ�
		
		for(int i = 0 ; i < graph.getLength() ; i ++) {
			visited.add(-1);
			prev.add(-1);
		}
		
		visited.set(s, 1);
		dfs(visited , prev , s , t);
		System.out.println("���������������" + s + "��" + t + "�ҵ���·������");
		printBfs(prev , s , t , t);
		System.out.println("");
	}
	
	private boolean dfs(CustomArray visited , CustomArray prev , int curr , int t) {
		if(curr == t) {
			return true;
		}
		
		
		for(int i = 1 ; i < graph.findByIndex(curr).getLength() ; i ++) {
			int temp = graph.findByIndex(curr).get(i);
			if(temp == t) {
				prev.set(temp, curr);
				return true;
			}
			
			if(visited.findByIndex(temp) == -1) {
				prev.set(temp, curr);//�����һ�εĹ�ϵ
				visited.set(temp, 1);//��ǽڵ��ѷ��ʣ��´��ٵ����Ͳ�������
				
				boolean isOk = dfs(visited , prev , temp , t);
				if(isOk) {
					return true;//���ӵݹ����ҵ�Ŀ��ڵ�ʱ���Ͳ��ٽ��б��ݹ��л�δ��ɵ�ѭ���ˣ�ֱ�ӷ��ء�
				}
			}
		}
		
		return false;
	}
	
	private void printBfs(CustomArray prev , int s , int t , int k) {
		if(prev.findByIndex(t) == -1) {
			System.out.print("�޿ɴ�·��");
			return;
		}
		if(k == s) {
			System.out.print(k + ",");
			return;
		}
		
		printBfs(prev , s , t , prev.findByIndex(k));
		System.out.print(k + ",");
	}
	
	public void print() {
		System.out.println("��ӡͼ����Ϣ����");
		for(int i = 0 ; i < graph.getLength() ; i ++) {
			System.out.print("�ڵ�" + i + ":");
			graph.findByIndex(i).print();
		}
	}
	
	
	public static void main(String[] args) {
		CustomGraph graph = new CustomGraph(8);
		graph.addEdge(0 , 1);
		graph.addEdge(0 , 3);
		graph.addEdge(1 , 2);
		graph.addEdge(1 , 4);
		graph.addEdge(2 , 5);
		graph.addEdge(3 , 4);
		graph.addEdge(4 , 5);
		graph.addEdge(4 , 6);
		graph.addEdge(5 , 7);
		graph.addEdge(6 , 7);
		graph.print();
		
		graph.bfs(0 , 6);
		graph.dfs(0 , 6);
	}
}
