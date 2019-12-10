package com.xiangjw.algo.level.middle;

import com.xiangjw.data_structure.array.DynamicArray;

/**
 * 回溯算法应用场景
 * 回溯算法本质上就是枚举，优点在于其类似于摸着石头过河的查找策略，且可以通过剪枝少走冤枉路。它可能适合应用于缺乏规律，或我们还不了解其规律的搜索场景中。
 * 
 * 典型场景： 图的深度优先搜索； 八皇后问题 0-1背包问题
 * 
 * @author xiangjw
 *
 */
public class RecallAlgo {

	private static int count;

	/**
	 * 八皇后问题 有一个 8x8 的棋盘，希望往里放 8 个棋子（皇后），每个棋子所在的行、列、对角线都不能有另一个棋子。找到所有满足这种要求的放棋子方式。
	 */
	public static void printEightQueues() {
		boolean[][] queues = new boolean[8][8];

		printQueue(queues, 0);
	}

	private static void printQueue(boolean[][] queues, int j) {
		if (j >= 8) {
			// 找到一种解法
			count++;
			StringBuffer buffer = new StringBuffer();
			for (int p = 0; p < 8; p++) {
				for (int q = 0; q < 8; q++) {
					if (queues[p][q]) {
						if (buffer.length() == 0) {
							buffer.append(q);
						} else {
							buffer.append(",").append(q);
						}
					}
				}
			}
			System.out.println(buffer.toString());
			return;
		}

		for (int i = 0; i < 8; i++) {
			if (isOk(queues, 8, i, j)) {// 这个点符合条件
				queues[i][j] = true;// 先把这个点预置
				printQueue(queues, j + 1);// 然后找下一个节点
				queues[i][j] = false;// 再把这个点改回来，这里就是回溯的精髓
			}
		}
	}

	private static boolean isOk(boolean[][] queues, int size, int i, int j) {
		for (int item = 0; item < size; item++) {
			if (queues[i][item]) {
				return false;// 竖向有重复的
			}
			if (queues[item][j]) {
				return false;// 横向有重复的
			}

			if (item > 0) {
				if (i - item >= 0 && j - item >= 0 && queues[i - item][j - item]) {
					return false;// 左上对角有重复的
				}
				if (i + item < size && j + item < size && queues[i + item][j + item]) {
					return false;// 右下对角有重复的
				}
				if (i - item >= 0 && j + item < size && queues[i - item][j + item]) {
					return false;// 左下对角有重复的
				}
				if (i + item < size && j - item >= 0 && queues[i + item][j - item]) {
					return false;// 右上对角有重复的
				}
			}
		}

		return true;
	}

	private static int maxWeight;
	private static String maxPlan;

	/**
	 * 0-1 背包问题 有一个背包，背包总的承载重量是 Wkg。现在我们有 n
	 * 个物品，每个物品的重量不等，并且不可分割。我们现在期望选择几件物品，装载到背包中。 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大
	 * 
	 * 利用回溯拆解为：每次考虑其中一个物品放与不放，得到所有可能值里面求最大重量即可。 高端解法是利用动态规划
	 * 时间复杂度是指数级的，想要提高效率，可以采用动态规划解决，见DynamicAlgo。
	 * 
	 * @param weights 每个物品的重量
	 * @param weight  背包可以承受的重量
	 */
	public static void printBags(DynamicArray<Goods> weights, int weight) {
		boolean[] plan = new boolean[weights.getLength()];

		getBag(weights, weight, plan, 0);
	}

	private static void getBag(DynamicArray<Goods> weights, int weight, boolean[] plan, int index) {
		if (index >= weights.getLength()) {
			// 找到一种组合
			int wei = 0;
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < weights.getLength(); i++) {
				if (plan[i]) {
					wei += weights.findByIndex(i).getWeight();
					if (buffer.length() == 0) {
						buffer.append(weights.findByIndex(i).getName());
					} else {
						buffer.append(", ").append(weights.findByIndex(i).getName());
					}
				}
			}
			System.out.println(buffer.toString());
			count++;
			if (wei > maxWeight) {
				maxWeight = wei;
				maxPlan = buffer.toString();
			}
			return;
		}

		// 放这个节点
		plan[index] = true;
		if (isOk(weights, weight, plan)) {
			getBag(weights, weight, plan, index + 1);
		}

		// 不放这个节点
		plan[index] = false;
		if (isOk(weights, weight, plan)) {
			getBag(weights, weight, plan, index + 1);
		}
	}

	private static boolean isOk(DynamicArray<Goods> weights, int weight, boolean[] plan) {
		int wei = 0;
		for (int i = 0; i < weights.getLength(); i++) {
			if (plan[i]) {
				wei += weights.findByIndex(i).getWeight();
			}
		}

		return wei <= weight;
	}

	public static void main(String[] args) {
		long before = System.currentTimeMillis();
		System.out.println("八皇后求解：");
		count = 0;
		printEightQueues();
		System.out.println("八皇后总共有" + count + "种解法，耗时：" + (System.currentTimeMillis() - before) + "ms");

		before = System.currentTimeMillis();
		System.out.println("0-1简单背包求解：");
		maxWeight = 0;
		count = 0;
		DynamicArray<Goods> weights = new DynamicArray<Goods>(5);
		weights.add(new Goods("石头", 20));
		weights.add(new Goods("黄瓜", 5));
		weights.add(new Goods("电脑", 7));
		weights.add(new Goods("浴室柜", 30));
		weights.add(new Goods("手机", 12));

		printBags(weights, 40);
		System.out.println("0-1背包总共有" + count + "种解法，耗时：" + (System.currentTimeMillis() - before) + "ms");
		System.out.println("0-1背包最大解为：" + maxPlan + "，最大重量：" + maxWeight);


	}
}

class Goods {
	private String name;

	private int weight;

	public Goods(String name, int weight) {
		super();
		this.name = name;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Goods [name=" + name + ", weight=" + weight + "]";
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}
}
