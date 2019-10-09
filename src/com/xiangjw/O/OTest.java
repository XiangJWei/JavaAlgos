package com.xiangjw.O;

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
			for(int j = 1 ; j <= n ; j *= 2) {
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
