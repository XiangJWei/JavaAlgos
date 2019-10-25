package com.xiangjw.java_base.data;

/**
 * java 数据测试
 * @author xiangjw
 *
 */
public class DataTest{

	public static void main(String []args) {
		//打印各类型的占用空间大小及最大值和最小值
		System.out.println("Byte：占用" + (Byte.SIZE / 8) + "字节，最小值：" + Byte.MIN_VALUE  + ",最大值：" + Byte.MAX_VALUE);
		System.out.println("Char：占用" + (Character.SIZE / 8) + "字节");
		System.out.println("Short：占用" + (Short.SIZE / 8) + "字节，最小值：" + Short.MIN_VALUE  + ",最大值：" + Short.MAX_VALUE);
		System.out.println("Integer：占用" + (Integer.SIZE / 8) + "字节，最小值：" + Integer.MIN_VALUE  + ",最大值：" + Integer.MAX_VALUE);
		System.out.println("Double：占用" + (Double.SIZE / 8) + "字节，最小值：" + Double.MIN_VALUE  + ",最大值：" + Double.MAX_VALUE);
		System.out.println("Float：占用" + (Float.SIZE / 8) + "字节，最小值：" + Float.MIN_VALUE  + ",最大值：" + Float.MAX_VALUE);
		System.out.println("Double：占用" + (Double.SIZE / 8) + "字节，最小值：" + Double.MIN_VALUE  + ",最大值：" + Double.MAX_VALUE + "\n");
		
		//对比逻辑乘2运算和位乘2运算的时间效率
		long times = 1000000000;
		long before = System.currentTimeMillis();
		long m = 1;
		for(int i = 0 ; i < times ; i ++) {
			m *= 2;
		}
		System.out.println("逻辑运算乘法" + times + "次，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		long m2 = 1;
		for(int i = 0 ; i < times ; i ++) {
			m2 <<= 1;
		}
		System.out.println("位运算乘法" + times + "次，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		//对比基础int和Integer封装类运算的时间效率
		before = System.currentTimeMillis();
		int num = 1;
		for(int i = 0 ; i < times ; i ++) {
			num ++;
		}
		System.out.println("基础int运算" + times + "次，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		before = System.currentTimeMillis();
		Integer num2 = 1;
		for(int i = 0 ; i < times ; i ++) {
			num2 ++;
		}
		System.out.println("Integer封装类拆装箱运算" + times + "次，耗时" + (System.currentTimeMillis() - before) + "ms");
		
		
	}
}
