package com.xiangjw.stack;

public class Brower {

	//ʱ�̱���backStack��ջ��Ϊ��ǰ���ʵ�ҳ��
	private ArrayStack<String> backStack;//���˵�����ջ
	
	private ArrayStack<String> frontStack;//ǰ��������ջ
	
	public Brower() {
		backStack = new ArrayStack<String>(10);
		frontStack = new ArrayStack<String>(10);
	}
	
	public void open(String url) {
		System.out.println("����--->" + url);
		backStack.push(url);
	}
	
	/**
	 * ����
	 */
	public void back() {
		if(backStack.getLength() <= 1) {
			System.out.println("���޿���");
			return;
		}
		
		String info = backStack.pull();
		System.out.println("���˷���--->" + backStack.get());
		frontStack.push(info);
	}
	
	/**
	 * ǰ��
	 */
	public void forward() {
		if(frontStack.getLength() == 0) {
			System.out.println("���޿ɽ�");
			return;
		}
		
		String info = frontStack.pull();
		System.out.println("ǰ������--->" + info);
		backStack.push(info);
	}
	
	public static void main(String[] args) {
		Brower brower = new Brower();
		brower.open("www.baidu.com");
		brower.open("www.360.com");
		brower.open("www.jd.com");
		brower.back();
		brower.back();
		brower.back();
		brower.back();
		brower.forward();
		brower.forward();
		brower.forward();
	}
}
