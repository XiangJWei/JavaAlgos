package com.xiangjw.algo.level.middle;

import java.util.Arrays;

import com.xiangjw.data_structure.array.DynamicArray;

/**
 * 贪心算法相关应用场景
 * 有明确的限制和期望，每次选取单位贡献值最大的数据，这样选出的总期望最高。
 * 哪些场景可以用贪心算法，是不好定义的，建议多举例验证。
 * 典型的，如果前面的选择，会影响到后面的选择期望，那么不适用贪心算法。
 * 
 * 场景一：放豆子
 * 场景二：分糖果
 * 
 * @author xiangjw
 *
 */
public class GreedAlgo {

	/**
	 * 假设我们有一个可以容纳 100kg 物品的背包，可以装各种物品。
	 * 我们有以下 5 种豆子，每种豆子的总量和总价值都各不相同。
	 * 为了让背包中所装物品的总价值最大，我们如何选择在背包中装哪些豆子？每种豆子又该装多少呢？
	 * 
	 * packetWeight包可以装的重量
	 * beans 可以装的豆子清单
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
	
	private static void getBeans(int packetWeight , DynamicArray<Beans> beans) {
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
	
	/**
	 * 有 m 个糖果和 n 个孩子。我们现在要把糖果分给这些孩子吃，但是糖果少，孩子多（m<n），所以糖果只能分配给一部分孩子
	 * 每个糖果的大小不等，这 m 个糖果的大小分别是 s1，s2，s3，……，sm。除此之外，每个孩子对糖果大小的需求也是不一样的，只有糖果的大小大于等于孩子的对糖果大小的需求的时候，孩子才得到满足。假设这 n 个孩子对糖果大小的需求分别是 g1，g2，g3，……，gn。
	 * 如何分配糖果，能尽可能满足最多数量的孩子？
	 * 
	 * @param candy 每个糖果的大小
	 * @param children 每个小孩对糖果的需求大小
	 */
	public static void giveCandy(DynamicArray<Integer> candy , DynamicArray<Children> children) {
		//先对孩子们的糖果需求进行从大到小排序
		for(int i = 0 ; i < children.getLength() ; i ++) {
			for(int j = 0 ; j < children.getLength() - i - 1 ; j ++) {
				if(children.findByIndex(j).getNeed() < children.findByIndex(j + 1).getNeed()) {
					Children temp = children.findByIndex(j);
					children.update(j, children.findByIndex(j + 1));
					children.update(j + 1, temp);
				}
			}
		}
		
		//再对糖果的大小进行从小到大排序
		for(int i = 0 ; i < candy.getLength() ; i ++) {
			for(int j = 0 ; j < candy.getLength() - i - 1 ; j ++) {
				if(candy.findByIndex(j).intValue() > candy.findByIndex(j + 1)) {
					int temp = candy.findByIndex(j);
					candy.update(j, candy.findByIndex(j + 1));
					candy.update(j + 1, temp);
				}
			}
		}
		
		children.print();
		candy.print();
		giveCandys(candy , children);
	}
	
	private static void giveCandys(DynamicArray<Integer> candy , DynamicArray<Children> children) {
		if(candy.getLength() == 0 || children.getLength() == 0) {
			return;
		}
		
		int minIndex = children.getLength() - 1;
		Children minChild = children.findByIndex(minIndex);
		for(int i = 0 ; i < candy.getLength() ; i ++) {//找到需求糖果最小的那个小孩
			if(candy.findByIndex(i).intValue() >= minChild.getNeed()) {//找到最小的能满足这个小孩的糖果
				System.out.println("分给" + minChild + candy.findByIndex(i) + "大小的糖果");
				
				//去除这个分的糖果盒小孩，然后继续分其他糖果
				candy.remove(i);
				children.remove(minIndex);
				giveCandys(candy , children);
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		DynamicArray<Beans> beans = new DynamicArray<Beans>(5);
		beans.add(new Beans("黄豆" , 100 , 100));
		beans.add(new Beans("绿豆" , 30 , 90));
		beans.add(new Beans("红豆" , 60 , 120));
		beans.add(new Beans("黑豆" , 20 , 80));
		beans.add(new Beans("青豆" , 50 , 75));
		printBeans(100 , beans);
		
		DynamicArray<Integer> candy = new DynamicArray<Integer>(5);
		DynamicArray<Children> children = new DynamicArray<Children>(5);
		candy.add(1);
		candy.add(2);
		candy.add(1);
		candy.add(5);
		candy.add(3);
		candy.add(4);
		candy.add(3);
		candy.add(3);
		children.add(new Children("张三", 4));
		children.add(new Children("李四", 3));
		children.add(new Children("王五", 2));
		children.add(new Children("赵六", 4));
		giveCandy(candy, children);
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

class Children{
	private String name;//小孩姓名
	private int need;//小孩的糖果大小需求
	public Children(String name, int need) {
		super();
		this.name = name;
		this.need = need;
	}
	public String getName() {
		return name;
	}
	public int getNeed() {
		return need;
	}
	@Override
	public String toString() {
		return "Children [name=" + name + ", need=" + need + "]";
	}
	
}