package com.xiangjw.stack;

/**
 * 简易计算器
 * @author Administrator
 *
 */
public class Calculator {
	
	private String info;
	
	private ArrayStack<Double> nums;
	private ArrayStack<Character> opers;
	
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
		ArrayStack<Character> subs = new ArrayStack<Character>(10);
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
		if(left.equals('[') && right.equals(']')) {
			return true;
		}
		if(left.equals('{') && right.equals('}')) {
			return true;
		}
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
				buffer.append(item);
				
				if( i == info.length() - 1) {
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
	
	//括号结算
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
	
	private void doNum(Double item) {
		nums.push(item);
	}
	
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
	
	public boolean isOperaBigger(char before , char after) {
		if((after == '*' || after == '/') && (before == '+' || before == '-')) {
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Calculator cal = new Calculator("(3+5)*8-6");
		System.out.println("运算结果：" + cal.cal());
	}
}
