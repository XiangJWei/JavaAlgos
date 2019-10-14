package com.xiangjw.sort;

public class SortTest {

	public static void sortByPop(int[] a , String m) {
		if(a == null || a.length < 2) {
			System.out.println("长度不够");
			return;
		}
		
		for(int p = 0 ; p < a.length - 1 ; p++) {
			boolean isSwaped = false;
			for(int q = p + 1 ; q < a.length ; q ++) {
				if(a[p] > a[q]) {
					int temp = a[p];
					a[p] = a[q];
					a[q] = temp;
					isSwaped = true;
				}
			}
			
			if(!isSwaped) {
				//某一次
				return;
			}
		}
		
		m = "ok";
	}
	
	public static void print(int[] arr , String m) {
		System.out.print("数组元素个数" + arr.length);
		StringBuffer buffer = new StringBuffer("，[");
		for(int i = 0 ; i < arr.length ; i ++) {
			if(i == 0) {
				buffer.append(arr[i]);
			}else {
				buffer.append(",").append(arr[i]);
			}
		}
		buffer.append("]").append(m);
		
		System.out.println(buffer.toString());
	}
	
	public static void main(String[] args) {
		int[] a = {1, 2, 7 , 3, 5, 2, 10};
		String m = "init";
		print(a , m);
		sortByPop(a , m);
		print(a , m);
	}
}
