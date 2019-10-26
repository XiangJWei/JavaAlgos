package com.xiangjw.algo.recu;

import java.util.HashMap;

/**
 * 递归相关算法
 * 递归与栈的思想密不可分
 * 递归代码虽然简洁高效，但是，递归代码也有很多弊端。比如，堆栈溢出、重复计算、函数调用耗时多、空间复杂度高等
 * 
 * 场景一：走台阶，每次可以走一步或者两步，共有多少种走法
 * 场景二：给定一个字符数组，打印出所有可能得排列组合。
 * 
 * @author xiangjw
 *
 */
public class RecuTest {
	
	private HashMap<Integer, Long> cache;//避免重复运算
	
	private int currDept;//限制递归层级，避免递归调用过深时栈溢出
	
	/**
	 * 递归方式实现走台阶
	 * @param total
	 * @return
	 */
	public long getTotalSteps(int total) {
		this.cache = new HashMap<Integer, Long>();
		this.currDept = 0;
		
		return getStepNum(total);
	}
	
	private long getStepNum(int n) {
		if(n == 1) {
			return 1;
		}
		if(n == 2) {
			return 2;
		}
		
		if(cache.containsKey(n)) {
			return cache.get(n);
		}
		
		if(++ currDept > 100) {
			throw new IllegalArgumentException("数组超限");
		}
		
		long result = getStepNum(n - 1) + getStepNum(n - 2);
		cache.put(n, result);
		
		return result;
	}
	
	/**
	 * 非递归方式实现走台阶
	 * 
	 * @param n
	 * @return
	 */
	public long getStepsWithoutRecu(int n) {
		if(n == 1) {
			return 1;
		}
		if(n == 2) {
			return 2;
		}
		
		long steps = 0;
		long lastOne = 2;
		long lastTwo = 1;
		for(int i = 3 ; i <= n ; i ++) {
			steps = lastOne + lastTwo;
			lastTwo = lastOne;
			lastOne = steps;
		}
		
		return steps;
	}
	
	/**
	 * 给定一个字符数组，打印出所有可能得排列组合
	 * @param arr
	 * @param length
	 * @param k
	 */
	public static void printWays(int arr[] , int length , int k) {
		if(k == 1) {
			for(int i = 0 ; i < length ; i++) {
				System.out.print(arr[i] + ",");
			}
			System.out.println("");
			return;
		}
		
		for(int i = 0 ; i < k ; i++) {
			//把a[i]和a[k-1]互换
			int temp = arr[i];
			arr[i] = arr[k-1];
			arr[k-1] = temp;
			
			//针对k-1的数组再进行子排列
			printWays(arr, length, k - 1);
			
			//子排列之后一定要再把互换的元素改回来
			temp = arr[i];
			arr[i] = arr[k-1];
			arr[k-1] = temp;
		}
	}
	
	/**
	 * 给定一个数组，判断有多少种排列组合方式
	 * @param arr
	 * @param length
	 * @param k
	 * @return
	 */
	public static int getWays(int arr[] , int length , int k) {
		if(k == 1) {
			return 1;
		}
		
		int sum = 0;
		for(int i = 0 ; i < k ; i++) {
			//把a[i]和a[k-1]互换
			int temp = arr[i];
			arr[i] = arr[k-1];
			arr[k-1] = temp;
			
			//针对k-1的数组再进行子排列
			sum += getWays(arr, length, k - 1);
			
			//子排列之后一定要再把互换的元素改回来
			temp = arr[i];
			arr[i] = arr[k-1];
			arr[k-1] = temp;
		}
		
		return sum;
	}
	
	public static void main(String []args) {
		long before = System.currentTimeMillis();
		System.out.println("走台阶，每次可以走一步或者两步，共有多少种走法？   递归方式--->" + new RecuTest().getTotalSteps(80));
		System.out.println("耗时+" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		System.out.println("走台阶，每次可以走一步或者两步，共有多少种走法？   非递归方式--->" + new RecuTest().getStepsWithoutRecu(80));
		System.out.println("耗时+" + (System.currentTimeMillis() - before) + "ms");
		
		int[] arr = {1 , 3 , 5 , 7};
		System.out.println("共有" + getWays(arr , arr.length , arr.length) + "种组合");
		printWays(arr , arr.length , arr.length);
	}
}
