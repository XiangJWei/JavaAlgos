package com.xiangjw.data;

import com.xiangjw.array.ArrayTest;

enum UserType{
	Student,
	teacher,
	parents
}

public class DataTest extends ArrayTest{

	public static void main(String []args) {
		System.out.println("Byte：" + Byte.SIZE + "," + Byte.MIN_VALUE  + "," + Byte.MAX_VALUE);
		System.out.println("Char：" + Character.SIZE + "," + Character.MIN_VALUE  + "," + Character.MAX_VALUE);
		System.out.println("Short：" + Short.SIZE + "," + Short.MIN_VALUE  + "," + Short.MAX_VALUE);
		System.out.println("Integer：" + Integer.SIZE + "," + Integer.MIN_VALUE  + "," + Integer.MAX_VALUE);
		System.out.println("Double：" + Double.SIZE + "," + Double.MIN_VALUE  + "," + Double.MAX_VALUE);
		System.out.println("Float：" + Float.SIZE + "," + Float.MIN_VALUE  + "," + Float.MAX_VALUE);
		System.out.println("Double：" + Double.SIZE + "," + Double.MIN_VALUE  + "," + Double.MAX_VALUE);
		
		switch("abc") {
		case "abc":
			System.out.println("abc");
			break;
		case "def":
			System.out.println("def");
			break;
		default:
			System.out.println("default");
			break;
		}
		
		
		String a = new String("abc");
		System.out.println(System.identityHashCode(a));
		a.concat(" hehe");
		System.out.println(System.identityHashCode(a));
		a += " oo";
		System.out.println(System.identityHashCode(a));
		
		System.out.println(UserType.parents);
		
		
		//
		long before = System.currentTimeMillis();
		int m = 1;
		for(int i = 0 ; i < 1000000000 ; i ++) {
			m *= 2;
		}
		
		System.out.println((System.currentTimeMillis() - before) + "ms , " + m);
		
		long before2 = System.currentTimeMillis();
		int m2 = 1;
		for(int i = 0 ; i < 1000000000 ; i ++) {
			m2 <<= 1;
		}
		
		System.out.println((System.currentTimeMillis() - before2) + "ms , " + m2);
		
	}
}
