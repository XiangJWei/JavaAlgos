package com.xiangjw.sort;

import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

public class SortTest {

	/**
	 * 冒泡排序
	 * 相邻两个元素比较，决定是否互换
	 * 最好情况：O(n) 最坏情况O(n²)  平均情况O(n²)
	 * @param a
	 * @param m
	 */
	public static void sortByPop(int[] a , int length) {
		if(a == null || length < 2) {
			System.out.println("长度不够");
			return;
		}
		
		for(int p = 0 ; p < length - 1 ; p++) {
			boolean isSwaped = false;
			for(int q = 0 ; q < length - p - 1 ; q ++) {
				if(a[q] > a[q + 1]) {
					int temp = a[q + 1];
					a[q + 1] = a[q];
					a[q] = temp;
					isSwaped = true;
				}
			}
			
			if(!isSwaped) {
				//某一次没有发生任何顺序调换，说明全数组已经顺序ok，终止
				return;
			}
		}
		
	}
	
	/**
	 * 插入排序
	 * 假设数组左端有序，右端无序。每次从右端拿出一个元素，往左端对比插入
	 * 最好情况：O(n) 最坏情况O(n²)  平均情况O(n²)
	 * @param a
	 * @param length
	 */
	public static void sortByInsert(int[] a , int length) {
		if(a == null || a.length < 2) {
			System.out.println("长度不够");
			return;
		}
		
		for(int i = 1 ; i < length ; i ++) {
			int temp = a[i];//右端第一个元素
			int j = i - 1;//左端最后元素开始往左依次对比，如果左端大则后移，否则就已经找到元素在左端中的顺序位置。
			for(; j >= 0 ; j--) {
				if(temp < a[j]) {
					a[j + 1] = a[j];//数据移动
				}else{
					break;//找到对应位置，马上结束循环直接插入
				}
			}
			
			a[j + 1] = temp;//插入数据
		}
	}
	
	/**
	 * 选择排序
	 * 假设数组左端有序，右端无序。每次从右端选择出最大的元素，插入左端的末尾即可
	 * 不稳定，存在跨位互换
	 * 最好情况：O(n²) 最坏情况O(n²)  平均情况O(n²)
	 * @param a
	 * @param length
	 */
	public static void sortBySel(int[] a , int length) {
		if(a == null || a.length < 2) {
			System.out.println("长度不够");
			return;
		}
		
		for(int i = 0 ; i < length - 1 ; i ++) {
			int minVal = i;
			for(int j = i + 1 ;  j < length ; j ++) {
				if(a[j] < a[minVal]) {
					minVal = j;
				}
			}
			
			int temp = a[i];
			a[i] = a[minVal];
			a[minVal] = temp;
		}
	}
	
	/**
	 * 归并排序
	 * 把N位的数组拆分成0~N/2和N/2~N的小问题，递归下去
	 * 也就是先把两个拆分数组分别排序，然后再组合两个数组进行排序
	 * 很稳定，时间复杂度O(nlogN)
	 * 但是会占用额外的内存空间，空间复杂度O(n)
	 * 由下至上，先处理子问题，再拼接两个子问题
	 * 
	 * @param a
	 * @param start
	 * @param length
	 */
	public static void sortByMerge(int[] a , int start , int length) {
		if(length == 1) {
			return;
		}
		
		//把数组分成两部分
		//先对前半部分排序，再对后半部分排序
		sortByMerge(a , start , length / 2);
		sortByMerge(a , start + length / 2 , length - length / 2);
		
		//最后归并前半部分和后半部分，组成一个排序完成的数组。
		//借用一个临时数组
		int tempArr[] = new int[length];
		int i = start , j = start + length / 2;
		int index = 0;
		while(i < start + length / 2 && j < start + length) {
			if(a[i] <= a[j]) {//谁小先放谁
				tempArr[index++] = a[i++];
			}else {
				tempArr[index++] = a[j++];
			}
		}
		
		//两部分数组可能一个对比完了，另一个还没完，就把没完的那一部分直接追加到临时数组
		if(i < start + length / 2) {
			for(;index < length ; index ++) {
				tempArr[index] = a[i++];
			}
		}else if(j < start + length) {
			for(;index < length ; index ++) {
				tempArr[index] = a[j++];
			}
		}
		
		//拷贝临时数组排序好的数据到原数组，完成本轮数组的排序工作
		for(index = 0;index < length ; index ++) {
			a[start++] = tempArr[index];
		}
	}
	
	/**
	 * 快速排序
	 * 也属于分治算法，每次取最后一个节点作为比较点，其他元素和它比，比它小就放它左边，比它大就放右边。
	 * 然后再依次排序左边半部分和右边半部分，达到全局有序
	 * 非稳定的排序算法，但它属于原地排序，基本不需要额外的内存空间
	 * 由上至下，先处理本身，再处理两个子问题
	 * 大部分情况下的时间复杂度都可以做到 O(nlogn)，只有在极端情况下，才会退化到 O(n²)，比如数组极端有序或者极端无序时
	 * 
	 * @param a
	 * @param start
	 * @param length
	 */
	public static void sortByQuick(int[] a , int start , int length) {
		if(length <= 1) {
			return;
		}
		
		if(start < 0) {
			return;
		}
		
		//每次取最后一个节点作为比较点，其他元素和它比，比它小就放它左边，比它大就放右边
		int end = start + length - 1;
		int temp = a[end];
		int middleIndex = start;
		for(int index = start; index < end  ; index ++) {
			if(a[index] <= temp) {
				if(index == middleIndex) {
					middleIndex ++;
				}else if(a[index] != a[middleIndex]) {
					int swap = a[index];
					a[index] = a[middleIndex];
					a[middleIndex ++] = swap;
				}
			}
		}
		//循环结束，把比较点放在中间位置区，原有位置元素移到最后
		if(end != middleIndex && a[end] != a[middleIndex]) {
			a[end] = a[middleIndex];
			a[middleIndex] = temp;
		}
		
		//分别处理比较点两边的子序列
		sortByQuick(a , start , middleIndex - start);
		sortByQuick(a , middleIndex + 1 , end - middleIndex);
	}
	
	
	/**
	 * 堆排序
	 * @param arr
	 * @param length
	 */
	public static void sortByHeap(int arr[] , int length) {
		if(length <= 1) {
			return;
		}
		
		//建堆
		//从第一个非叶子节点开始往前遍历。
		//如果当前节点比子节点最大值还大，就往下交换，从上往下堆化。
		//依次完成所有非叶子节点的遍历后，就形成了一个堆
		//这一块的时间复杂度是O(n)
		int index = length / 2 - 1;//从第一个非叶子节点开始
		while(index >= 0) {
			int tempIndex = index;
			while(tempIndex * 2 + 1 < length) {
				int leftChild = arr[tempIndex * 2 + 1];
				int rightChild = tempIndex * 2 + 2 == length ? Integer.MIN_VALUE : arr[tempIndex * 2 + 2];//如果没有右子节点，就置为最小值
				int maxIndex = leftChild > rightChild ? (tempIndex * 2 + 1) : (tempIndex * 2 + 2);//左子节点和右子节点，最的值大
				if(arr[tempIndex] < arr[maxIndex]) {
					int temp = arr[tempIndex];
					arr[tempIndex] = arr[maxIndex];
					arr[maxIndex] = temp;
				}else {
					break;
				}
				
				tempIndex = maxIndex;
			}
			
			index --;
		}
		
		//排序
		//堆顶就是最大元素，排序过程就是每次移除堆顶最大值，然后重新堆化，再移除堆顶...最后移除完成即全部有序了
		//这部分时间复杂度O(nlogN)
		for(int i = length - 1 ; i > 0 ; i --) {
			//堆顶和堆尾互换
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			
			//从0到i-1进行堆化，从上到下
			int j = 0;
			while(j * 2 + 1 < i) {
				int left = arr[2 * j + 1];//左边子节点
				int right = 2 * j + 2 == i ? Integer.MIN_VALUE : arr[2 * j + 2];//右边子节点或最小值
				int maxIndex = left > right ? (2 * j + 1) : (2 * j + 2);
				
				if(arr[j] < arr[maxIndex]) {
					int temp2 = arr[j];
					arr[j] = arr[maxIndex];
					arr[maxIndex] = temp2;
				}else {
					break;
				}
				
				j = maxIndex;
			}
		}
	}
	
	/**
	 * 获取第K小的元素值，时间复杂度O(n)
	 * 数组中存在重复值时就不适用了
	 * @param a
	 * @param start
	 * @param length
	 * @param k
	 * @return
	 */
	public static int getKth(int[] a , int start , int length , int k) {
		if(start < 0) {
			return -1;
		}
		if(length <= 1) {
			return a[start];
		}
		
		int end = start + length - 1;
		int middleIndex = start;
		int temp = a[end];
		for(int i = start ; i < end ; i ++) {
			if(a[i] < temp) {
				int swap = a[i];
				a[i] = a[middleIndex];
				a[middleIndex ++] = swap;
			}
		}
		
		a[end] = a[middleIndex];
		a[middleIndex] = temp;
		
		if(k == middleIndex + 1) {
			return a[middleIndex];
		}else if(k < middleIndex + 1) {
			return getKth(a, start, middleIndex - start, k);
		}else {
			return getKth(a, middleIndex + 1 , end - middleIndex, k);
		}
	}
	
	/**
	 * 	桶排序
	 * 按照值的范围均分为多个区间桶，每个桶里才单独进行快排。这样组成的桶的列表就是有序的
	 * 对数据要求较高
	 * 时间复杂度O(n)
	 * @param arr
	 * @param length
	 */
	public static void sortByBucket(int[] arr , int length) {
		//先找到元素的最小值和最大值
		int minVal = Integer.MAX_VALUE;
		int maxVal = Integer.MIN_VALUE;
		
		for(int i = 0 ; i < length ; i ++) {
			if(arr[i] > maxVal) {
				maxVal = arr[i];
			}
			if(arr[i] < minVal) {
				minVal = arr[i];
			}
		}
		
		//分桶,这里没有固定分法，主要还是看最大差值和数组元素个数来均衡衡量桶怎么分。
		int valScope = maxVal - minVal;
		int bucketNum;//桶的数量
		if(valScope > 1000000) {
			bucketNum = 10000;
		}else if(valScope > 10000) {
			bucketNum = 1000;
		}else if(valScope > 100) {
			bucketNum = 100;
		}else if(valScope > 10) {
			bucketNum = 10;
		}else {
			bucketNum = 1;
		}
		
		int bucketSize = valScope / bucketNum;//单个桶的默认大小，不够再扩容
		
		int [][]bucketArr = new int[bucketNum][];//存放每个桶的各自数据
		int []everyBucketLength = new int[bucketNum];//存放每个桶分别存储的数据量，length
		//把所有数据分装到每一个桶里面去
		for(int i = 0 ; i < length ; i ++) {
			int bucketIndex = (arr[i] - minVal) / bucketSize;
			bucketIndex = bucketIndex >= bucketNum ? bucketNum - 1 : bucketIndex;
			
			if(bucketArr[bucketIndex] == null) {
				bucketArr[bucketIndex] = new int[bucketSize];//初始化
			}
			
			//某个桶已经满了，要扩容
			if(bucketArr[bucketIndex].length == everyBucketLength[bucketIndex]) {
				bucketArr[bucketIndex] = resize(bucketArr[bucketIndex] , bucketArr[bucketIndex].length * 2);
			}
			
			int newLength = everyBucketLength[bucketIndex];
			bucketArr[bucketIndex][newLength] = arr[i];
			everyBucketLength[bucketIndex] = newLength + 1;
		}
		
		int arrIndex = 0;
		for(int j = 0 ; j < bucketNum ; j ++) {
			if(everyBucketLength[j] > 0) {
				//接下来对每个桶分别进行排序
				sortByQuick(bucketArr[j], 0, everyBucketLength[j]);
				
				//排序结果直接赋值到原数组里去，这样原数组就有序了
				for(int k = 0 ; k < everyBucketLength[j] ; k ++) {
					arr[arrIndex++] = bucketArr[j][k];
				}
			}
		}
	}
	
	private static int[] resize(int []arr , int newLength) {
		int[] newArr = new int[newLength];
		System.arraycopy(arr, 0, newArr, 0, arr.length);
		return newArr;
	}
	
	/**
	 * 计数排序
	 * 和桶排序类似，适用于值范围很小的情况，每个值给一个桶，桶里放这个值和前值出现的次数和。
	 * 再分析这个桶得到每个元素应该在的位置
	 * 时间复杂度O(n)
	 * @param arr
	 * @param length
	 */
	public static void sortByCounting(int[] arr , int length) {
		//先找到元素的最小值和最大值
		int minVal = Integer.MAX_VALUE;
		int maxVal = Integer.MIN_VALUE;
		for(int i = 0 ; i < length ; i ++) {
			if(arr[i] > maxVal) {
				maxVal = arr[i];
			}
			if(arr[i] < minVal) {
				minVal = arr[i];
			}
		}
		
		//区间范围内每个int值定义一个桶，当然一个桶里面只表示一个值，记录这个值在数组中的次数（包括前值的次数）
		int valScope = maxVal - minVal + 1;
		int[] bucketArr = new int[valScope];//存放每个值对应的次数（包括前值）
		for(int i = 0 ; i < valScope ; i ++) {
			for(int j = 0 ; j < length ; j ++) {
				if(arr[j] == i + minVal) {
					bucketArr[i] ++;
				}
			}
			
			//加上前值的次数
			if(i < valScope - 1) {
				bucketArr[i + 1] = bucketArr[i];
			}
		}
		
		int[] temp = new int[length];
		for(int i = length - 1 ; i >= 0 ; i --) {
			int bucketIndex = arr[i] - minVal;
			int numVal = bucketArr[bucketIndex];
			temp[numVal - 1] = arr[i];
			bucketArr[bucketIndex] = numVal - 1;
		}
		
		System.arraycopy(temp, 0, arr, 0, length);
	}
	
	/**
	 * 基数排序
	 * 适用于电话号码排序这种定长排序，先按最后一位排序，再按倒数第二位排序，...最终按第一位排序，得到的就是有序的
	 * 时间复杂度O(n)
	 * @param arr
	 * @param length
	 */
	public static void sortByRadix(int[] arr , int length) {
		int maxVal = Integer.MIN_VALUE;
		for(int i = 0 ; i < length ; i ++) {
			if(arr[i] > maxVal) {
				maxVal = arr[i];
			}
		}
		
		int num = 1;
		while(num <= maxVal) {
			sortRadixFromCounting(arr , length , num);
			num = num * 10;
		}
	}
	
	public static void sortRadixFromCounting(int[] arr , int length ,int num) {
		int[] bucket = new int[10];
		//计数排序的思想
		for(int i = 0 ; i < bucket.length ; i ++) {
			for(int j = 0 ; j < length ; j ++) {
				int currNum = arr[j] % (num * 10) / num;//获取数目个位、十位...的具体值
				if(currNum == i) {
					bucket[i] = bucket[i] + 1;
				}
			}
			
			if(i < bucket.length - 1) {
				bucket[i + 1] = bucket[i];
			}
		}
		
		int[] temp = new int[length];
		int index = 0;
		for(int k = length - 1 ; k >= 0 ; k --) {
			int currNum = arr[k] % (num * 10) / num;//获取数目个位、十位...的具体值
			int newIndex = bucket[currNum] - 1;
			temp[newIndex] = arr[k];
			bucket[currNum] = newIndex;
		}
		
		System.arraycopy(temp, 0, arr, 0, length);
	}
	
	public static void print(int[] arr , int length) {
		System.out.print("数组占用空间:" + arr.length + "，数组元素个数" + length);
		StringBuffer buffer = new StringBuffer("，[");
		for(int i = 0 ; i < arr.length ; i ++) {
			if(i == 0) {
				buffer.append(arr[i]);
			}else {
				buffer.append(",").append(arr[i]);
			}
		}
		buffer.append("]");
		
		System.out.println(buffer.toString());
	}
	
	public static int[] getTestArr(int length) {
		int[] testArr = new int[length];
		for(int m = 0; m < length ; m ++) {
			testArr[m] = (int)((Math.random() * 9 + 1) * 100000);//生成一个六位数的随机整数
		}
		
		return testArr;
	}
	
	public static void main(String[] args) {
		int[] a = {1, 2, 7 , 3, 5, 2, 10};
		int length = 7;
		print(a , length);
		sortByPop(a , length);
		print(a , length);
		
		int[] a1 = {1, 2, 7 , 3, 5, 2, 10};
		sortByInsert(a1 , length);
		print(a1 , length);
		
		int[] a2 = {1, 2, 7 , 3, 5, 2, 10};
		sortBySel(a2 , length);
		print(a2 , length);
		
		int[] a3 = {1, 2, 7 , 3, 5, 2, 10};
		sortByMerge(a3 , 0 , length);
		print(a3 , length);
		
		int[] a4 = {1, 2, 7 , 3, 5, 2, 10};
		sortByQuick(a4 , 0 , length);
		print(a4 , length);
		
		int[] a9 = {1, 2, 7 , 3, 5, 2, 10};
		sortByHeap(a9 , length);
		print(a9 , length);
		
		
		int[] a5 = {1, 2, 7 , 3, 5, 8, 10};
		int k = 4;
		System.out.println("获取到第" + k + "小的元素为：" + getKth(a5 , 0 , length , k));
		print(a5 , length);
		
		length = 20;
		int[] a6 = new int[length];
		Random random =new Random();
		for(int i = 0; i < length ; i ++) {
			a6[i] = random.nextInt(10000);
		}
		sortByBucket(a6 , length);
		print(a6 , length);
		
		length = 20;
		int[] a7 = new int[length];
		for(int i = 0; i < length ; i ++) {
			a7[i] = random.nextInt(100);
		}
		sortByCounting(a7 , length);
		print(a7 , length);
		
		int[] a8 = new int[length];
		for(int i = 0; i < length ; i ++) {
			a8[i] = (int)((Math.random() * 9 + 1) * 100000);//生成一个六位数的随机整数
		}
		sortByRadix(a8 , length);
		print(a8 , length);
		
		
		//性能对比测试
		int num = 100 , size = 1000;
		System.out.println("随机生成" + num + "个数组，每个数组" + size + "个元素，统计耗时情况如下：");
		long before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByPop(testArr, size);
		}
		System.out.println("冒泡排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByInsert(testArr, size);
		}
		System.out.println("插入排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortBySel(testArr, size);
		}
		System.out.println("选择排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByMerge(testArr, 0, size);
		}
		System.out.println("归并排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		

		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByQuick(testArr, 0, size);
		}
		System.out.println("快速排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByHeap(testArr, size);
		}
		System.out.println("堆排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByBucket(testArr , size);
		}
		System.out.println("桶排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByCounting(testArr , size);
		}
		System.out.println("计数排序，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		for(int i = 0 ; i < num ; i ++) {
			int[] testArr = getTestArr(size);
			sortByRadix(testArr , size);
		}
		System.out.println("基数排序，耗时" + (System.currentTimeMillis() - before) + "ms");
	}
}
