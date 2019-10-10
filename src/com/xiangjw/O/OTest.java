package com.xiangjw.O;

/**
 * 各种时间复杂度的情况分析
 * */
public class OTest {
	
	//时间复杂度：O(1)
	public static long cal1(int n) {
		long sum = 0;
		for(int i = 1 ; i <= 100 ; i ++) {
			sum += i + n;
		}
		
		return sum;
	}
	
	//时间复杂度：O(n)
	public static long cal2(int n) {
		long sum = 0;
		for(int i = 1 ; i <= n ; i ++) {
			sum += i;
		}
		
		return sum;
	}
	
	//时间复杂度：O(n²)
	public static long cal3(int n) {
		long sum = 0;
		for(int i = 1 ; i <= n ; i ++) {
			for(int j = 1 ; j <= n ; j ++) {
				sum += i + j;
			}
		}
		
		return sum;
	}
	
	//时间复杂度：O(logn)
	public static long cal4(int n) {
		long sum = 0;
		for(int i = 1 ; i <= n ; i *= 2){
			sum += i;
		}
		
		return sum;
	}
	
	//时间复杂度：O(nlogn)
	public static long cal5(int n) {
		long sum = 0;
		for(int i = 1 ; i <= n ; i ++) {
			for(int j = 1 ; j <= n ; j = j * 2) {
				sum += i + j;
			}
		}
		
		return sum;
	}
	
	//时间复杂度：O(m+n)
	public static long cal6(int m , int n) {
		long sum = 0;
		for(int i = 1 ; i <= m ; i ++) {
			sum += i;
		}
		
		for(int j = 1 ; j <= n ; j ++) {
			sum += j;
		}
		
		return sum;
	}
	
	//时间复杂度：O(m*n)
	public static long cal7(int m , int n) {
		long sum = 0;
		for(int i = 1 ; i <= m ; i ++) {
			for(int j = 1 ; j <= n ; j ++) {
				sum += i + j;
			}
		}
				
		return sum;
	}
	
	//arr.length即表示n
	//最好情况时间复杂度（数组里第一个值就是a）：O(1)
	//最坏情况时间复杂度（数组里没有a）：O(n)
	//平均情况时间复杂度(每种情况的循环次数*每种情况的发生概率)
	//		a不在数组中的概率为1/2，a在数组中各个位置的概率就为1/2n
	//		1/2n + 2 / 2n + ... + n/2n + n/2 = (3n+1)/4 = O(n)
	public static int query(int[] arr , int a) {
		for(int i = 0; i < arr.length ; i ++) {
			if(arr[i] == a) {
				return i;
			}
		}
		
		return -1;
	}
	
	private static int[] arr = new int[100];
	private static int count = 0;
	
	//arr.length即表示n
	//这个方法的作用是，当数组没满时直接插入到最小的空闲位置，当数组已满时就遍历出所有元素之和并清空数组，和放第一个，插入值放第二个。
	//这个方法的特点是：大部分情况下时间复杂度都是O(1)，只有个别情况时间复杂度为O(n)；
	//		而且有频率规律，一次O(n)后紧接着是n-1次O(1)
	//这种情况使用平均情况来分析不合适，应该使用摊还分析法计算均摊时间复杂度。
	//把耗时多的那次操作均摊到接下来的n-1次耗时少的操作上，均摊下来，这一组连续的操作的均摊时间复杂度就是 O(1)
	public static void insert(int val) {
		if(count == arr.length) {
			int sum = 0;
			for(int i = 0 ; i < arr.length ;i ++) {
				sum += arr[i];
				arr[i] = 0;
			}
			
			arr[0] = sum;
			count = 1;
		}
		
		arr[count++] = val;
	}
	
	public static void main(String []args) {
		int n = 1000000;
		int n2 = 100000;
		{
			long before = System.currentTimeMillis();
			long sum = cal1(n);
			long after = System.currentTimeMillis();
			System.out.println("cal1---" + sum + " with times ---" + (after - before) / 1000.0f);
		}
		{
			long before = System.currentTimeMillis();
			long sum = cal2(n);
			long after = System.currentTimeMillis();
			System.out.println("cal2---" + sum + " with times ---" + (after - before) / 1000.0f);
		}
		{
			long before = System.currentTimeMillis();
			long sum = cal3(n2);
			long after = System.currentTimeMillis();
			System.out.println("cal3---" + sum + " with times ---" + (after - before) / 1000.0f);
		}
		{
			long before = System.currentTimeMillis();
			long sum = cal4(n);
			long after = System.currentTimeMillis();
			System.out.println("cal4---" + sum + " with times ---" + (after - before) / 1000.0f);
		}
		{
			long before = System.currentTimeMillis();
			long sum = cal5(n);
			long after = System.currentTimeMillis();
			System.out.println("cal5---" + sum + " with times ---" + (after - before) / 1000.0f);
		}
		{
			long before = System.currentTimeMillis();
			long sum = cal6(n , n);
			long after = System.currentTimeMillis();
			System.out.println("cal6---" + sum + " with times ---" + (after - before) / 1000.0f);
		}
		{
			long before = System.currentTimeMillis();
			long sum = cal7(n2 , n2);
			long after = System.currentTimeMillis();
			System.out.println("cal7---" + sum + " with times ---" + (after - before) / 1000.0f);
		}
	}
}
