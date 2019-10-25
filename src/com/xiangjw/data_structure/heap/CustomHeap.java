package com.xiangjw.data_structure.heap;

/**
 * 
 * 堆是一种特殊二叉树。两个要求：满足满二叉树（N-1层全满，第N层全靠左），每个节点都比左右子节点大于等于或者小于等于
 * 因为是满二叉树所以堆可以用数组来存，堆首存a[1]，依次存下去，那么节点N的左子节点就是a[N*2],右子节点就是a[N*2 + 1]
 * 本例是个大顶堆（每个节点都比左右子节点大）
 * 每次插入删除时保证依然满足堆的条件，就是堆化的过程。
 * 
 * 
 * 堆最擅长大量数据下的几种场景：
 * 1.优先级队列（合并有序的小文件、高性能定时器）
 * 2.求topN，不需要全遍历来排序找值。
 * 3.动态数据求中位数，构造一个大顶堆和一个小顶堆，同时控制大顶堆的数据小于小顶堆，以及两个堆要数量相差<=1
 * 
 * @author xiangjw
 *
 */
public class CustomHeap {

	private int[] arr;//a[0]不放元素，便于计算父子节点
	private int length;
	
	public CustomHeap(int size) {
		this.arr = new int[size];
		this.length = 0;
	}
	
	/**
	 * 添加元素，并堆化
	 * 先加到堆尾，然后依次往上进行比对，完成堆化
	 * @param val
	 */
	public void add(int val) {
		int index = length + 1;
		if(index == arr.length) {
			System.out.println("容量已满");
			return;
		}
		
		arr[index] = val;
		
		
		for(int i = index ; i >= 2 ; i = i / 2) {
			int temp = arr[i / 2];
			if(temp < arr[i]) {//互换值
				arr[i / 2] = arr[i];
				arr[i] = temp;
			}else {
				break;
			}
		}
		
		length ++;
	}
	
	/**
	 * 删除堆顶元素，并堆化
	 * 删除堆顶，然后依次从上往下比对，完成堆化
	 */
	public void delTop() {
		if(length == 0) {
			return;
		}
		
		if(length  == 1) {
			arr[length --] = 0;
			return;
		}
		
		arr[1] = arr[length];
		int i = 1;
		while(i * 2 <= length) {
			int tempLeft = arr[i * 2];
			int tempRight = Integer.MIN_VALUE;
			if(i * 2 + 1 <= length) {
				tempRight = arr[i * 2 + 1];
			}
			int index = tempLeft > tempRight ? (i * 2) : (i * 2 + 1);//获取当前节点的子节点中较大的那个
			if(arr[i] < arr[index]) {//互换值
				int temp = arr[i];
				arr[i] = arr[index];
				arr[index] = temp;
			}else {
				break;
			}
			
			i = index;
		}
		length --;
	}
	
	/**
	 * 打印堆
	 */
	public void print() {
		System.out.print("堆的大小：" + length + "-----");
		StringBuffer buffer = new StringBuffer();
		for(int i = 1 ; i <= length ; i ++) {
			if(i != 1) {
				buffer.append(",");
			}
			buffer.append(arr[i]);
		}
		
		System.out.println(buffer.toString());
	}
	
	public static void main(String[] args) {
		CustomHeap heap = new CustomHeap(10);
		heap.add(15);heap.print();
		heap.add(10);heap.print();
		heap.add(3);heap.print();
		heap.add(50);heap.print();
		heap.add(7);heap.print();
		heap.add(1);heap.print();
		heap.add(25);heap.print();
		heap.add(100);heap.print();
		heap.delTop();heap.print();
		heap.delTop();heap.print();
		heap.delTop();heap.print();
		heap.delTop();heap.print();
		heap.delTop();heap.print();
	}
}
