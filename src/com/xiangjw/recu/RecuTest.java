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
	
	public static void main(String []args) {
		long before = System.currentTimeMillis();
		System.out.println("��̨�ף�ÿ�ο�����һ���������������ж������߷���   �ݹ鷽ʽ--->" + new RecuTest().getTotalSteps(80));
		System.out.println("��ʱ+" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		System.out.println("��̨�ף�ÿ�ο�����һ���������������ж������߷���   �ǵݹ鷽ʽ--->" + new RecuTest().getStepsWithoutRecu(80));
		System.out.println("��ʱ+" + (System.currentTimeMillis() - before) + "ms");
		
	}
}
