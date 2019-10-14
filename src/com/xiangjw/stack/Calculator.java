package com.xiangjw.stack;

/**
 * ���׼�����
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
	 * ���ſ����Ƿ�Ϲ�
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
			System.out.println("���Ŵ���");
			return 0;
		}
		
		StringBuffer buffer = new StringBuffer();
		for(int i = 0 ; i < info.length() ; i ++) {
			Character item = info.charAt(i);
			if(item.equals(' ')) {
				continue;//���Կո�
			}
			
			if(item.equals('(')) {
				//������ֱ��ѹջ
				opers.push(item);
			}else if(item.equals(')')) {
				//���������ţ�Ҫ���㵽��������
				if(buffer.length() > 0) {
					try {
						Double val = Double.valueOf(buffer.toString());
						System.out.println("�ҵ����� >>> " + val);
						buffer = new StringBuffer();
						
						//�ȴ�������
						doNum(val);
					}catch(Exception e) {
						System.out.println("���ݴ��ڴ���");
						return 0;
					}
				}else {
					System.out.println("���ݴ��ڴ���");
					return 0;
				}
				
				doCalWithSubs();
			}else if(OPERATOR.contains(item.toString())) {
				if(i == 0) {
					System.out.println("��һλ����������");
					return 0;
				}
				if(i == info.length() - 1) {
					System.out.println("���һλ����������");
					return 0;
				}
				//�ҵ�����
				if(buffer.length() > 0) {
					
					try {
						Double val = Double.valueOf(buffer.toString());
						System.out.println("�ҵ����� >>> " + val);
						buffer = new StringBuffer();
						
						//�ȴ�������
						doNum(val);
					}catch(Exception e) {
						System.out.println("���ݴ��ڴ���");
						return 0;
					}
				}
				
				System.out.println("�ҵ����� >>> " + item);
				//�ٴ������
				doOpera(item);
			}else {
				//��װ����
				buffer.append(item);
				
				if( i == info.length() - 1) {
					try {
						Double val = Double.valueOf(buffer.toString());
						System.out.println("�ҵ����� >>> " + val);
						//�������һ������
						doNum(val);
						return lastCal();
					}catch(Exception e) {
						System.out.println("���ݴ��ڴ���");
						return 0;
					}
				}
			}
		}
		
		return 0;
	}
	
	//���Ž���
	private void doCalWithSubs() {
		while(opers.get() != null && !opers.get().equals('(')) {
			Character op = opers.pull();
			Double num2 = nums.pull();
			Double num1 = nums.pull();
			Double result = doCal(num1 , num2 , op);
			System.out.println("����" + num1 + op + num2 + "=" + result);
			nums.push(result);
		}
		
		//������ȡ��������
		opers.pull();
	}
	
	private void doNum(Double item) {
		nums.push(item);
	}
	
	private void doOpera(char item) {
		if(opers.getLength() > 0 && isOperaBigger(opers.get() , item)) {
			//���˸������ȼ��������ѹջ
			opers.push(item);
		}else {
			//���˸�ƽ����С�������������ջ��֮ǰ����ֵ..������ѹջ
			while(opers.get() != null && !opers.get().equals('(')) {
				Character op = opers.pull();
				Double num2 = nums.pull();
				Double num1 = nums.pull();
				Double result = doCal(num1 , num2 , op);
				System.out.println("����" + num1 + op + num2 + "=" + result);
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
			System.out.println("����" + num1 + item + num2 + "=" + result);
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
		System.out.println("��������" + cal.cal());
	}
}
