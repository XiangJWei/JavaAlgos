package com.xiangjw.algo.level.middle;

import com.xiangjw.data_structure.array.DynamicArray;

public class DynamicAlgo {
	
	/**
	 * 0-1 背包问题 有一个背包，背包总的承载重量是 Wkg。现在我们有 n
	 * 个物品，每个物品的重量不等，并且不可分割。我们现在期望选择几件物品，装载到背包中。 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大
	 * 
	 * 本例采用动态规划解决，也可使用回溯见RecallAlgo。
	 * 利用一个缓存cache二维数组，记录每层可以达到的不同状态。根据第i层的情况就可以再分析i+1层的结果，依次处理
	 * 把整个求解过程分为 n 个阶段，每个阶段会决策一个物品是否放到背包中。每个物品决策（放入或者不放入背包）完之后，背包中的物品的重量会有多种情况
	 * 
	 * @param weights 每个物品的重量
	 * @param weight  背包可以承受的重量
	 */
	public static void printBags(DynamicArray<Goods> weights, int weight) {
		boolean [][]cache = new boolean[weights.getLength()][weight + 1];//i表示当前考察的第几个物品，j表示当前方案累计的前i个物品总重量
		//第一个，有两种情况，不放就是0，放就是第一个物品重量（当然前提是不大于背包容量）
		cache[0][0] = true;
		if(weights.findByIndex(0).getWeight() <= weight) {
			cache[0][weights.findByIndex(0).getWeight()] = true;
		}
		
		for(int i = 1 ; i < weights.getLength() ; i ++) {
			for(int j = 0 ; j < cache[i].length; j ++) {
				//第i层不放
				if(cache[i - 1][j]) {
					cache[i][j] = true;
				}
				
				//第i层放
				int currWeight = weights.findByIndex(i).getWeight();
				if(cache[i - 1][j] && j + currWeight <= weight) {
					cache[i][j + currWeight] = true;
				}
			}
		}
		
		//打印出二维数组
		for(int i = 0 ; i < cache.length ; i ++) {
			for(int j = 0 ; j < cache[i].length; j ++) {
				System.out.print((cache[i][j] ? 1 : 0) + ",");
			}
			System.out.println("");
		}
		
		//反推放了哪些东西
		int j = weight;
		for(int i = cache.length - 1 ; i >= 0 ; i --) {
			while(!cache[i][j] && i == cache.length - 1) {
				j--;
			}
			
			if(i == cache.length - 1) {
				System.out.println("规划出的最大重量为：" + j);
			}
			
			if(i == 0 && j == weights.findByIndex(i).getWeight()) {
				System.out.println("放了" + weights.findByIndex(i).getName() + "，其重量为" + weights.findByIndex(i).getWeight());
			}else if(j >= weights.findByIndex(i).getWeight() 
					&& cache[i - 1][j - weights.findByIndex(i).getWeight()]) {
				System.out.println("放了" + weights.findByIndex(i).getName() + "，其重量为" + weights.findByIndex(i).getWeight());
				j = j - weights.findByIndex(i).getWeight();
			}
		}
	}
	
	public static void main(String []args) {
		long before = System.currentTimeMillis();
		System.out.println("0-1简单背包求解：");
		DynamicArray<Goods> weights = new DynamicArray<Goods>(5);
		weights.add(new Goods("石头", 20));
		weights.add(new Goods("黄瓜", 5));
		weights.add(new Goods("电脑", 7));
		weights.add(new Goods("浴室柜", 30));
		weights.add(new Goods("手机", 12));

		printBags(weights, 40);
		System.out.println("0-1背包耗时：" + (System.currentTimeMillis() - before) + "ms");
	}
}
