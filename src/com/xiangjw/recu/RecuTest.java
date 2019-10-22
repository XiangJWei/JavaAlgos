package com.xiangjw.recu;

import java.util.HashMap;

/**
 * �ݹ�����㷨
 * �ݹ������Ȼ����Ч�����ǣ��ݹ����Ҳ�кܶ�׶ˡ����磬��ջ������ظ����㡢�������ú�ʱ�ࡢ�ռ临�Ӷȸߵ�
 * @author Administrator
 *
 */
public class RecuTest {
	
	private HashMap<Integer, Long> cache;//�����ظ�����
	
	private int currDept;
	
	/**
	 * �ݹ鷽ʽʵ��
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
			throw new IllegalArgumentException("���鳬��");
		}
		
		long result = getStepNum(n - 1) + getStepNum(n - 2);
		cache.put(n, result);
		
		return result;
	}
	
	/**
	 * �ǵݹ鷽ʽʵ��
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
	
	public static void printWays(int arr[] , int length , int k) {
		if(k == 1) {
			for(int i = 0 ; i < length ; i++) {
				System.out.print(arr[i] + ",");
			}
			System.out.println("");
			return;
		}
		
		for(int i = 0 ; i < k ; i++) {
			//��a[i]��a[k-1]����
			int temp = arr[i];
			arr[i] = arr[k-1];
			arr[k-1] = temp;
			
			//���k-1�������ٽ���������
			printWays(arr, length, k - 1);
			
			//������֮��һ��Ҫ�ٰѻ�����Ԫ�ظĻ���
			temp = arr[i];
			arr[i] = arr[k-1];
			arr[k-1] = temp;
		}
	}
	
	public static int getWays(int arr[] , int length , int k) {
		if(k == 1) {
			return 1;
		}
		
		int sum = 0;
		for(int i = 0 ; i < k ; i++) {
			//��a[i]��a[k-1]����
			int temp = arr[i];
			arr[i] = arr[k-1];
			arr[k-1] = temp;
			
			//���k-1�������ٽ���������
			sum += getWays(arr, length, k - 1);
			
			//������֮��һ��Ҫ�ٰѻ�����Ԫ�ظĻ���
			temp = arr[i];
			arr[i] = arr[k-1];
			arr[k-1] = temp;
		}
		
		return sum;
	}
	
	public static void main(String []args) {
		long before = System.currentTimeMillis();
		System.out.println("��̨�ף�ÿ�ο�����һ���������������ж������߷���   �ݹ鷽ʽ--->" + new RecuTest().getTotalSteps(80));
		System.out.println("��ʱ+" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		System.out.println("��̨�ף�ÿ�ο�����һ���������������ж������߷���   �ǵݹ鷽ʽ--->" + new RecuTest().getStepsWithoutRecu(80));
		System.out.println("��ʱ+" + (System.currentTimeMillis() - before) + "ms");
		
		int[] arr = {1 , 3 , 5 , 7};
		System.out.println("����" + getWays(arr , arr.length , arr.length) + "�����");
		printWays(arr , arr.length , arr.length);
	}
}
