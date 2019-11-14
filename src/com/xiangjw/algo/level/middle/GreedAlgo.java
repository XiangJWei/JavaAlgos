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
 * 场景三：钱币找零
 * 场景四：区间覆盖
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
	
	/**
	 * 钱币找零
	 * 假设我们有 1 元、2 元、5 元、10 元、20 元、50 元、100 元这些面额的纸币，它们的张数分别是 c1、c2、c5、c10、c20、c50、c100。我们现在要用这些钱来支付 K 元，最少要用多少张纸币呢？
	 * 
	 * @param moneys 自己有多少钱
	 * @param totalMoney 要花多少钱
	 */
	public static void spendMoney(DynamicArray<Money> moneys , int totalMoney) {
		//先把自己的钱按面值从小到大排序
		for(int i = 0 ; i < moneys.getLength() ; i ++) {
			for(int j = 0 ; j < moneys.getLength() - i - 1 ; j ++) {
				if(moneys.findByIndex(j).getValue() > moneys.findByIndex(j + 1).getValue()) {
					Money temp = moneys.findByIndex(j);
					moneys.update(j, moneys.findByIndex(j + 1));
					moneys.update(j + 1, temp);
				}
			}
		}
		
		moneys.print();
		spend(moneys, totalMoney);
	}
	
	private static void spend(DynamicArray<Money> moneys , int totalMoney) {
		int index = moneys.getLength() - 1;
		while(index >= 0 && totalMoney > 0) {
			Money item = moneys.findByIndex(index);
			if(totalMoney >= item.getValue()) {//这个面值可以用
				int needNum = totalMoney / item.getValue();
				if(item.getCount() > needNum) {
					System.out.println("拿出" + needNum + "张" + item.getValue() + "元");
					totalMoney -= needNum * item.getValue();
					item.setCount(item.getCount() - needNum);
				}else {
					System.out.println("拿出" + item.getCount() + "张" + item.getValue() + "元");
					totalMoney -= item.getCount() * item.getValue();
					item.setCount(0);
				}
			}
			
			index --;
		}
		
		if(totalMoney > 0) {
			System.out.println("钱不够啊亲");
		}
	}
	
	/**
	 * 假设我们有 n 个区间，区间的起始端点和结束端点分别是 [l1, r1]，[l2, r2]，[l3, r3]，……，[ln, rn]。我们从这 n 个区间中选出一部分区间，这部分区间满足两两不相交（端点相交的情况不算相交），最多能选出多少个区间呢？
	 * 
	 * @param regions
	 */
	public static void getUnOverRegions(DynamicArray<Region> regions) {
		//先把这些区间按照起始点从小到大排序
		for(int i = 0 ; i < regions.getLength() ; i ++) {
			for(int j = 0 ; j < regions.getLength() - i - 1 ; j ++) {
				if(regions.findByIndex(j).getStart() > regions.findByIndex(j + 1).getStart()) {
					Region temp = regions.findByIndex(j);
					regions.update(j, regions.findByIndex(j + 1));
					regions.update(j + 1, temp);
				}
			}
		}
		
		regions.print();
		getRegion(regions , regions.findByIndex(0).getStart());//第一个节点的起始节点其实就是所有元素的最小值
	}
	
	public static void getRegion(DynamicArray<Region> regions , int startValue) {
		Region item = null;//本轮找到的满足不重合条件的右值最小的
		for(int i = 0 ; i < regions.getLength() ; i ++) {
			if(regions.findByIndex(i).getStart() >= startValue) {//找到大于等于startValue的，就是满足不重合条件的
				if(item == null) {
					item = regions.findByIndex(i);
				}else if(item.getEnd() > regions.findByIndex(i).getEnd()) {
					item = regions.findByIndex(i);
				}
			}
		}
		
		if(item != null) {
			System.out.println("选出区间[" + item.getStart() + "," + item.getEnd() + "]");
			getRegion(regions , item.getEnd());//接着找大于等于当前右值找下一个节点
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
		
		DynamicArray<Money> moneys = new DynamicArray<Money>(5);
		moneys.add(new Money(100, 3));
		moneys.add(new Money(50, 5));
		moneys.add(new Money(20, 7));
		moneys.add(new Money(10, 4));
		moneys.add(new Money(5, 3));
		moneys.add(new Money(2, 20));
		moneys.add(new Money(1, 100));
		spendMoney(moneys, 568);
		
		DynamicArray<Region> regions = new DynamicArray<Region>(5);
		regions.add(new Region(6, 8));
		regions.add(new Region(2, 4));
		regions.add(new Region(3, 5));
		regions.add(new Region(1, 5));
		regions.add(new Region(5, 9));
		regions.add(new Region(8, 10));
		getUnOverRegions(regions);
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

class Money{
	private int value;//面值
	private int count;//数量

	public Money(int value, int count) {
		super();
		this.value = value;
		this.count = count;
	}

	public int getValue() {
		return value;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Money [value=" + value + ", count=" + count + "]";
	}
}

class Region{
	private int start;//区间起点
	
	private int end;//区间终点

	public Region(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return "Region [start=" + start + ", end=" + end + "]";
	}
	
}