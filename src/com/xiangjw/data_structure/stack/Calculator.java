package com.xiangjw.data_structure.stack;

import java.text.DecimalFormat;

/**
 * 简易计算器
 * @author xiangjw
 *
 */
public class Calculator {
	
	private String info;
	
	private ArrayStack<Double> nums;//存储每个计算的数
	private ArrayStack<Character> opers;//存储每个计算符号
	
	private static final String OPERATOR = "+-*/";
	private static final String LEFT_SUB = "(";
	private static final String RIGHT_SUB = ")";
	
	public Calculator(String info) {
		this.info = info;
		nums = new ArrayStack<Double>(10);
		opers = new ArrayStack<Character>(10);
	}
	
	/**
	 * 括号开闭是否合规
	 * @return
	 */
	public boolean isSubsOk() {
		ArrayStack<Character> subs = new ArrayStack<Character>(10);//借助栈检测括号开闭是否正常
		for(int i = 0 ; i < info.length() ; i ++) {
			Character item = info.charAt(i);
			if(LEFT_SUB.contains(item.toString())) {
				subs.push(item);
			}else if(RIGHT_SUB.contains(item.toString())) {
				Character before = subs.pull();
				if(before == null || !isCouple(before , item)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean isCouple(Character left , Character right) {
		if(left.equals('(') && right.equals(')')) {
			return true;
		}
		
		
		return false;
	}
	
	public double cal() {
		if(!isSubsOk()) {
			System.out.println("符号错误");
			return 0;
		}
		
		StringBuffer buffer = new StringBuffer();
		for(int i = 0 ; i < info.length() ; i ++) {
			Character item = info.charAt(i);
			//正常表达式item存在几种情况：左括号、右括号、加减乘除符号、数字、小数点，分别做不同的处理
			
			if(item.equals(' ')) {
				continue;//忽略空格
			}
			
			if(item.equals('(')) {
				//左括号直接压栈
				opers.push(item);
			}else if(item.equals(')')) {
				//碰到右括号，要结算到左括号了
				if(buffer.length() > 0) {
					try {
						Double val = Double.valueOf(buffer.toString());
						System.out.println("找到数字 >>> " + val);
						buffer = new StringBuffer();
						
						//先处理数字
						doNum(val);
					}catch(Exception e) {
						System.out.println("数据存在错误");
						return 0;
					}
				}else {
					System.out.println("数据存在错误");
					return 0;
				}
				
				doCalWithSubs();
			}else if(OPERATOR.contains(item.toString())) {
				//碰到运算符号
				if(i == 0) {
					System.out.println("第一位必须是数字");
					return 0;
				}
				if(i == info.length() - 1) {
					System.out.println("最后一位必须是数字");
					return 0;
				}
				//找到符号
				if(buffer.length() > 0) {
					
					try {
						Double val = Double.valueOf(buffer.toString());
						System.out.println("找到数字 >>> " + val);
						buffer = new StringBuffer();
						
						//先处理数字
						doNum(val);
					}catch(Exception e) {
						System.out.println("数据存在错误");
						return 0;
					}
				}
				
				System.out.println("找到符号 >>> " + item);
				//再处理符号
				doOpera(item);
			}else {
				//组装数字
				buffer.append(item);//这里不做运算，只做数字组装比如依次读到1 2 3 . 4五个字符，最终组装成数字123.4
				
				if( i == info.length() - 1) {
					//最后一个数字来了，直接组装加运算最终结果
					try {
						Double val = Double.valueOf(buffer.toString());
						System.out.println("找到数字 >>> " + val);
						//处理最后一个数字
						doNum(val);
						return lastCal();
					}catch(Exception e) {
						System.out.println("数据存在错误");
						return 0;
					}
				}
			}
		}
		
		return 0;
	}
	
	/**
	 * 进行带括号的运算
	 */
	private void doCalWithSubs() {
		while(opers.get() != null && !opers.get().equals('(')) {
			Character op = opers.pull();
			Double num2 = nums.pull();
			Double num1 = nums.pull();
			Double result = doCal(num1 , num2 , op);
			System.out.println("计算" + num1 + op + num2 + "=" + result);
			nums.push(result);
		}
		
		//左括号取出来丢掉
		opers.pull();
	}
	
	/**
	 * 数字压栈逻辑
	 * @param item
	 */
	private void doNum(Double item) {
		nums.push(item);
	}
	
	/**
	 * 符号压栈逻辑
	 * 如果来的是大优先级运算符，则只压栈
	 * 否则，就先计算前面的结果，再压栈结果
	 * @param item
	 */
	private void doOpera(char item) {
		if(opers.getLength() > 0 && isOperaBigger(opers.get() , item)) {
			//来了个大优先级运算符，压栈
			opers.push(item);
		}else {
			//来了个平级或小级运算符，计算栈中之前的数值..算完再压栈
			while(opers.get() != null && !opers.get().equals('(')) {
				Character op = opers.pull();
				Double num2 = nums.pull();
				Double num1 = nums.pull();
				Double result = doCal(num1 , num2 , op);
				System.out.println("计算" + num1 + op + num2 + "=" + result);
				nums.push(result);
			}
			
			opers.push(item);
		}
	}
	
	/**
	 * 遍历到最后一个数字后的最终运算
	 * @return
	 */
	private Double lastCal() {
		while(opers.get() != null && !opers.get().equals('(')) {
			Character item = opers.pull();
			Double num2 = nums.pull();
			Double num1 = nums.pull();
			Double result = doCal(num1 , num2 , item);
			System.out.println("计算" + num1 + item + num2 + "=" + result);
			nums.push(result);
		}
		
		return nums.pull();
	}
	
	/**
	 * 进行具体的算术运算，返回运算结果
	 * @param num1
	 * @param num2
	 * @param item
	 * @return
	 */
	private Double doCal(Double num1 , Double num2 , Character item) {
		switch (item) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		case '/':
			return num1 / num2;
		}
		
		return null;
	}
	
	/**
	 * 两个计算符号比较优先级
	 * @param before
	 * @param after
	 * @return
	 */
	public boolean isOperaBigger(char before , char after) {
		if((after == '*' || after == '/') && (before == '+' || before == '-')) {
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.00");
		String info = "(3+5.2)*8-6*3";
		Calculator cal = new Calculator(info);
		System.out.println(info + " = " + df.format(cal.cal()));
	}
}
