package com.xiangjw.string;

import java.util.Random;

public class StringCompare {

	public static int findByBF(String parent , String child) {
		if(parent == null || child == null || parent.length() < child.length()) {
			return -1;
		}
		
		char[] a = parent.toCharArray();
		char[] b = child.toCharArray();
		
		for(int i = 0 ; i <= a.length - b.length ; i ++ ) {
			boolean isOk = true;
			for(int j = 0 ; j < b.length ; j ++) {
				if(a[i + j] != b[j]) {
					isOk = false;
					break;
				}
			}
			
			if(isOk) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * 假设两个字符串内容都是小写字母
	 * @param parent
	 * @param child
	 * @return
	 */
	public static int findByRK(String parent , String child) {
		if(parent == null || child == null || parent.length() < child.length()) {
			return -1;
		}
		
		char[] a = parent.toCharArray();
		char[] b = child.toCharArray();
		
		int[] arr = new int[child.length() + 1];
		arr[0] = 1; 
		for(int i = 1 ; i <= child.length() ; i ++) {
			arr[i] = arr[i - 1] * 26;//存放26、26²、26三次方....
		}
		
		int hashOfChild = 0;
		int[] hashs = new int[a.length - b.length + 1];
		for(int i = 0 ; i < b.length ; i ++) {
			hashOfChild += (b[i] - 'a') * arr[i];//计算子串的hash值
			hashs[0] = hashs[0] + (a[i] - 'a') * arr[i];////计算第一个父串的hash值
		}
		
		if(hashs[0] == hashOfChild) {
			return 0;
		}
		
		//计算父串中其他hash值
		//并随时比较，若和子串hash值相等，则直接返回
		for(int i = 1 ; i <= a.length - b.length ; i ++) {
			hashs[i] = (hashs[i - 1] - (a[i - 1] - 'a')) / 26 + (a[i + b.length - 1] - 'a') * arr[b.length - 1];
			//这里每一位的hash值和前一位的hash值是有关系的，拆解一下就知道了。
			
			if(hashs[i] == hashOfChild) {
				return i;
			}
		}
		
		
		return -1;
	}
	
	public static int findByBM(String parent , String child) {
		if(parent == null || child == null || parent.length() < child.length()) {
			return -1;
		}
		
		char[] a = parent.toCharArray();
		char[] b = child.toCharArray();
		
		//两个好后缀的数组
		int[] suffix = new int[b.length];//子串中，存储对应长度的尾串在其他位置也出现的最大下标（公共后缀子串）
		
		//先针对子串做一个类似散列表，存储的是每个字符在子串中对应的最大下标位置，子串中不存在的就是-1
		int[] table = new int[256];
		for(int i = 0 ; i < 256 ; i ++) {
			table[i] = -1;
		}
		for(int i = 0 ; i < b.length ; i ++) {
			table[(int)b[i]] = i;//若一个字符在子串出现多次，以最大的下标为准
			
			//初始化默认值
			suffix[i] = -1;
		}
		
		//再对子串做两个好后缀的数组
		for(int i = 0 ; i < b.length - 1; i ++) {
			//i表示从i开始，i和串尾对比，i-1和串尾倒数第二对比。
			int j = i;
			int num = 1;
			while(j >= 0 && b[j] == b.length - num) {
				suffix[num++] = j --;
			}
		}
		
		//子串开始动起来了
		int i = 0;
		while(i <= a.length - b.length) {
			int val = 1;
			for(int j = b.length - 1 ; j >= 0 ; j --) {
				int badMoveSteps = 0;
				int goodMoveSteps = 0;
				if(a[i + j] != b[j]) {//找到坏字符
					int indexOfBad = table[(int)a[i + j]];
					badMoveSteps = b.length - indexOfBad - 1;//也可能会是负数
					
					if(j < b.length - 1) {
						//存在好后缀
						int goodSuffixNum = b.length - 1 - j;
						if(suffix[goodSuffixNum] >= 0) {
							//存在好后缀的完全匹配
							goodMoveSteps = b.length - goodSuffixNum - suffix[goodSuffixNum];
						}else {
							for(int k = goodSuffixNum - 1 ; k > 0 ; k --) {
								if(suffix[k] == 0) {
									goodMoveSteps = b.length - k;
								}
							}
						}
					}
					
					val = (badMoveSteps > goodMoveSteps) ? badMoveSteps : goodMoveSteps;
					break;
				}else if(j == 0){
					//全部匹配
					return i;
				}
			}
			
			i += val > 0 ? val : 1;
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		String parent = "abcdefghijklmn";
		String child = "hij";
		System.out.println("BF算法从“" + parent + "”中查找“" + child + "”查找\n结果为：" + findByBF(parent, child));
		System.out.println("RK算法从“" + parent + "”中查找“" + child + "”查找\n结果为：" + findByRK(parent, child));
		System.out.println("BM算法从“" + parent + "”中查找“" + child + "”查找\n结果为：" + findByBM(parent, child));
		
		Random random = new Random();
		int size = 100 , length = 10000;
		char[][] parents = new char[size][];
		char[][] children = new char[size][];
		for(int i = 0 ; i < size ; i ++) {
			int randomChildSize = random.nextInt(length / 100);
			while(randomChildSize == 0) {
				randomChildSize = random.nextInt(length / 100);
			}
			parents[i] = new char[length];
			children[i] = new char[randomChildSize];
			for(int j = 0 ; j < length ; j ++) {
				parents[i][j] = (char)(97 + random.nextInt(26));
				if(j < randomChildSize) {
					children[i][j] = (char)(97 + random.nextInt(26));
				}
			}
		}
		
		long before = System.currentTimeMillis();
		int successCount = 0;
		for(int i = 0 ; i < size ; i ++) {
			int res = findByBF(new String(parents[i]), new String(children[i]));
			if(res >= 0) {
				System.out.println("父串：" + new String(parents[i]).substring(res , res + children[i].length));
				System.out.println("子串：" + new String(children[i]));
				System.out.println("匹配到下标：" + res);
				successCount ++;
			}
		}
		System.out.println("BF算法从" + size + "个数组，每个数组" + length + "个元素中搜索子串，耗时" + (System.currentTimeMillis() - before) + "ms，成功匹配数量:" + successCount);
		
		before = System.currentTimeMillis();
		successCount = 0;
		for(int i = 0 ; i < size ; i ++) {
			int res = findByRK(new String(parents[i]), new String(children[i]));
			if(res >= 0) {
				System.out.println("父串：" + new String(parents[i]).substring(res , res + children[i].length));
				System.out.println("子串：" + new String(children[i]));
				System.out.println("匹配到下标：" + res);
				successCount ++;
			}
		}
		System.out.println("RK算法从" + size + "个数组，每个数组" + length + "个元素中搜索子串，耗时" + (System.currentTimeMillis() - before) + "ms，成功匹配数量:" + successCount);
		
		before = System.currentTimeMillis();
		successCount = 0;
		for(int i = 0 ; i < size ; i ++) {
			int res = findByBM(new String(parents[i]), new String(children[i]));
			if(res >= 0) {
				System.out.println("父串：" + new String(parents[i]).substring(res , res + children[i].length));
				System.out.println("子串：" + new String(children[i]));
				System.out.println("匹配到下标：" + res);
				successCount ++;
			}
		}
		System.out.println("BM算法从" + size + "个数组，每个数组" + length + "个元素中搜索子串，耗时" + (System.currentTimeMillis() - before) + "ms，成功匹配数量:" + successCount);
	}
}
