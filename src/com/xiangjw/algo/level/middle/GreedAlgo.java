package com.xiangjw.algo.level.middle;

import com.xiangjw.data_structure.array.DynamicArray;

/**
 * 贪心算法相关应用场景
 * 有明确的限制和期望，每次选取单位贡献值最大的数据，这样选出的总期望最高。
 * 哪些场景可以用贪心算法，是不好定义的，建议多举例验证。
 * 典型的，如果前面的选择，会影响到后面的选择期望，那么不适用贪心算法。
 * 
 * 场景一：放豆子
 * 
 * @author xiangjw
 *
 */
public class GreedAlgo {

	/**
	 * 假设我们有一个可以容纳 100kg 物品的背包，可以装各种物品。
	 * 我们有以下 5 种豆子，每种豆子的总量和总价值都各不相同。
	 * 为了让背包中所装物品的总价值最大，我们如何选择在背包中装哪些豆子？每种豆子又该装多少呢？
	 */
	public static void printBeans(int packetWeight , DynamicArray<Beans> beans) {
		//先把豆子按照单价进行排序，从小到大
		for(int i = 0 ; i < beans.getLength() ; i ++) {
			for(int j = 0 ; j < beans.getLength() - i - 1 ; j ++) {
				if(beans.findByIndex(j).compareTo(beans.findByIndex(j + 1)) > 0) {
					Beans temp = beans.findByIndex(j);
					beans.update(j, beans.findByIndex(j + 1));
					beans.update(j + 1, temp);
				}
			}
		}
		
		beans.print();
		getBeans(packetWeight , beans);
	}
	
	public static void getBeans(int packetWeight , DynamicArray<Beans> beans) {
		if(beans.getLength() == 0 || packetWeight == 0) {
			return;
		}
		
		int lastOne = beans.getLength() - 1;
		Beans item = beans.findByIndex(lastOne);
		if(item.getWeight() >= packetWeight) {
			System.out.println("放入" + item.getName() + packetWeight  + "kg");
			return;
		}
		
		System.out.println("放入" + item.getName() + item.getWeight()  + "kg");
		beans.remove(lastOne);
		getBeans(packetWeight - item.getWeight(), beans);
	}
	
	public static void main(String[] args) {
		DynamicArray<Beans> beans = new DynamicArray<Beans>(5);
		beans.add(new Beans("黄豆" , 100 , 100));
		beans.add(new Beans("绿豆" , 30 , 90));
		beans.add(new Beans("红豆" , 60 , 120));
		beans.add(new Beans("黑豆" , 20 , 80));
		beans.add(new Beans("青豆" , 50 , 75));
		
		printBeans(100 , beans);
	}
}

class Beans implements Comparable<Beans>{
	private String name;
	private int weight;
	private int totalValue;
	
	public Beans(String name , int weight, int totalValue) {
		super();
		this.name = name;
		this.weight = weight;
		this.totalValue = totalValue;
	}
	
	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public int getTotalValue() {
		return totalValue;
	}



	@Override
	public String toString() {
		return "Beans [name=" + name + ", weight=" + weight + ", totalValue=" + totalValue + "]";
	}



	@Override
	public int compareTo(Beans o) {
		// TODO Auto-generated method stub
		float val = totalValue * 1.0f / weight - o.totalValue * 1.0f / o.weight;
		if(val > 0) {
			return 1;
		}else if(val < 0) {
			return -1;
		}
		
		return 0;
	}
}