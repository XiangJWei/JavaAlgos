package com.xiangjw.data_structure.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.xiangjw.data_structure.array.CustomArray;
import com.xiangjw.data_structure.array.DynamicArray;
import com.xiangjw.data_structure.linkedlist.CustomLinkedList;
import com.xiangjw.data_structure.queue.ArrayQuene;


/**
 * 这是一个无向图
 * 这里出于简单考虑用的数组加链表来存储。
 * 如果需要更高的查询效率，可以使用数组加其他高效结构（跳表、散列表、红黑树等）来存储
 * @author xiangjw
 *
 */
public class CustomGraph {
	//外层数组表示图的每个节点
	//内层链表表示这个节点到链表中每个节点都存在边
	private DynamicArray<CustomLinkedList<Integer>> graph;//邻接表，表示图
	
	
	public CustomGraph(int size){
		graph = new DynamicArray<CustomLinkedList<Integer>>(size);
		
		for(int i = 0 ; i < size ; i ++) {
			CustomLinkedList<Integer> list = new CustomLinkedList();
			list.addToFirst(i);//链表的第一个元素直接放本节点
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
		if(s == t) {
			return;
		}
		if(!graph.findByIndex(s).isExist(t))
			graph.findByIndex(s).addToLast(t);
		if(!graph.findByIndex(t).isExist(s))
			graph.findByIndex(t).addToLast(s);
	}
	
	
	/**
	 * 广度优先搜索
	 * 找到的是最短路径
	 * 借助队列实现
	 * @param s 起始点
	 * @param t 终点
	 */
	public CustomArray bfs(int s , int t) {
		ArrayQuene<CustomLinkedList<Integer>> queue = new ArrayQuene<CustomLinkedList<Integer>>(graph.getLength());
		CustomArray visited = new CustomArray(graph.getLength());//初始全-1,记录已经被访问的订单，避免被重复访问
		CustomArray prev = new CustomArray(graph.getLength());//初始全-1，记录某节点的上一个节点
		
		for(int i = 0 ; i < graph.getLength() ; i ++) {
			visited.add(-1);
			prev.add(-1);
		}
		
		visited.update(s, 1);
		queue.push(graph.findByIndex(s));//起始点入队列
		boolean isOk = false;
		while(queue.getLength() > 0 && !isOk) {
			CustomLinkedList<Integer> curr = queue.pull();
			
			for(int i = 1 ; i < curr.getLength() ; i ++) {
				Integer temp = curr.get(i);
				
				if(temp.intValue() == t) {
					//找到啦
					prev.update(temp, curr.getFirst());
					isOk = true;//退出双重循环
					break;
				}
				
				if(visited.findByIndex(temp) == -1) {//只探访还未到达过得节点
					visited.update(temp, 1);
					prev.update(temp, curr.getFirst());
					queue.push(graph.findByIndex(temp));
				}
			}
		}

		return prev;

	}
	
	/**
	 * 深度优先搜索
	 * 找到的不一定是最短路径
	 * 借助递归实现
	 * @param s 起始点
	 * @param t 终点
	 */
	public CustomArray dfs(int s , int t) {
		CustomArray visited = new CustomArray(graph.getLength());//初始全-1,记录已经被访问的订单，避免被重复访问
		CustomArray prev = new CustomArray(graph.getLength());//初始全-1，记录某节点的上一个节点
		
		for(int i = 0 ; i < graph.getLength() ; i ++) {
			visited.add(-1);
			prev.add(-1);
		}
		
		visited.update(s, 1);
		dfs(visited , prev , s , t);
		return prev;

	}
	
	private boolean dfs(CustomArray visited , CustomArray prev , int curr , int t) {
		if(curr == t) {
			return true;
		}
		
		
		for(int i = 1 ; i < graph.findByIndex(curr).getLength() ; i ++) {
			int temp = graph.findByIndex(curr).get(i);
			if(temp == t) {
				prev.update(temp, curr);
				return true;
			}
			
			if(visited.findByIndex(temp) == -1) {
				prev.update(temp, curr);//标记上一任的关系
				visited.update(temp, 1);//标记节点已访问，下次再到它就不进来了
				
				boolean isOk = dfs(visited , prev , temp , t);
				if(isOk) {
					return true;//当子递归里找到目标节点时，就不再进行本递归中还未完成的循环了，直接返回。
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 已经根据深度或者广度优先找到路径，递归打印路径
	 * @param prev
	 * @param s
	 * @param t
	 * @param k
	 */
	public static void printWay(CustomArray prev , int s , int t , int k) {
		if(prev.findByIndex(t) == -1) {
			System.out.print("无可达路径");
			return;
		}
		if(k == s) {
			System.out.print(k + ",");
			return;
		}
		
		printWay(prev , s , t , prev.findByIndex(k));
		System.out.print(k + ",");
	}
	
	/**
	 * 打印图的信息
	 */
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
		
		int s = 0;
		int t = 6;
		CustomArray prev = graph.bfs(s , t);
		System.out.println("广度优先搜索，求" + s + "到" + t + "最短的路径如下");
		printWay(prev , s , t , t);
		System.out.println("");
		
		prev = graph.dfs(s , t);
		System.out.println("深度优先搜索，求" + s + "到" + t + "找到的路径如下");
		printWay(prev , s , t , t);
		System.out.println("");
		
		int size = 1000 , length = 10000;
		List<CustomGraph> graphs = new ArrayList<CustomGraph>();
		Random random = new Random();
		for(int i = 0 ; i < size ; i ++ ) {
			CustomGraph graphItem = new CustomGraph(length);
			
			for(int j = 0 ; j < random.nextInt(length) ; j ++) {
				graph.addEdge(random.nextInt(length) , random.nextInt(length));
			}
			graphs.add(graphItem);
		}
		
		s = random.nextInt(length);
		t = random.nextInt(length);
		while(s == t) {
			t = random.nextInt(length);
		}
		long before = System.currentTimeMillis();
		for(int i = 0 ; i < size ; i ++ ) {
			graphs.get(i).bfs(s, t);
		}
		System.out.println("图的广度优先搜索，一个图共有" + length + "个节点，从" + s + "查找到" + t + "的路径，依次遍历" + size + "个图耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < size ; i ++ ) {
			graphs.get(i).dfs(s, t);
		}
		System.out.println("图的深度优先搜索，一个图共有" + length + "个节点，从" + s + "查找到" + t + "的路径，依次遍历" + size + "个图耗时" + (System.currentTimeMillis() - before) + "ms");
	}
}
