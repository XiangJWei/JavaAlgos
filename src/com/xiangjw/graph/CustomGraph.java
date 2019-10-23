package com.xiangjw.graph;

import com.xiangjw.array.CustomArray;
import com.xiangjw.array.DynamicArray;
import com.xiangjw.linkedlist.CustomLinkedList;
import com.xiangjw.queue.ArrayQuene;

/**
 * 这是一个无向图
 * 这里出于简单考虑用的数组加链表来存储。
 * 如果需要更高的查询效率，可以使用数组加（跳表、散列表、红黑树等）
 * @author Administrator
 *
 */
public class CustomGraph {
	private DynamicArray<CustomLinkedList<Integer>> graph;//邻接表，表示图
	
	public CustomGraph(int size){
		graph = new DynamicArray<CustomLinkedList<Integer>>(size);
		
		for(int i = 0 ; i < size ; i ++) {
			CustomLinkedList<Integer> list = new CustomLinkedList();
			list.addToFirst(i);
			graph.add(list);
		}
	}
	
	/**
	 * 给图添加一条关系边
	 * @param s 从哪个节点
	 * @param t 到哪个节点
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
	 * 广度优先搜索
	 * @param s 起始点
	 * @param t 终点
	 */
	public void bfs(int s , int t) {
		ArrayQuene<CustomLinkedList<Integer>> queue = new ArrayQuene<CustomLinkedList<Integer>>(graph.getLength());
		CustomArray visited = new CustomArray(graph.getLength());//初始全-1,记录已经被访问的订单，避免被重复访问
		CustomArray prev = new CustomArray(graph.getLength());//初始全-1，记录某节点的上一个节点
		
		for(int i = 0 ; i < graph.getLength() ; i ++) {
			visited.add(-1);
			prev.add(-1);
		}
		
		visited.set(s, 1);
		queue.push(graph.findByIndex(s));//起始点入队列
		boolean isOk = false;
		while(queue.getLength() > 0 && !isOk) {
			CustomLinkedList<Integer> curr = queue.pull();
			
			for(int i = 1 ; i < curr.getLength() ; i ++) {
				Integer temp = curr.get(i);
				
				if(temp.intValue() == t) {
					//找到啦
					prev.set(temp, curr.getFirst());
					isOk = true;//退出双重循环
					break;
				}
				
				if(visited.findByIndex(temp) == -1) {
					visited.set(temp, 1);
					prev.set(temp, curr.getFirst());
					queue.push(graph.findByIndex(temp));
				}
			}
		}

		System.out.println("广度优先搜索，求" + s + "到" + t + "最短的路径如下");
		printBfs(prev , s , t , t);
		System.out.println("");
	}
	
	private void printBfs(CustomArray prev , int s , int t , int k) {
		if(k == s) {
			System.out.print(k + ",");
			return;
		}
		
		printBfs(prev , s , t , prev.findByIndex(k));
		System.out.print(k + ",");
	}
	
	public void print() {
		System.out.println("打印图的信息如下");
		for(int i = 0 ; i < graph.getLength() ; i ++) {
			System.out.print("节点" + i + ":");
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
	}
}
