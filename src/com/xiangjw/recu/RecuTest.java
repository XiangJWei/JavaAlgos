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
	
	public static void main(String []args) {
		long before = System.currentTimeMillis();
		System.out.println("��̨�ף�ÿ�ο�����һ���������������ж������߷���   --->" + new RecuTest().getTotalSteps(50));
		System.out.println("��ʱ+" + (System.currentTimeMillis() - before) + "ms");
	}
}
