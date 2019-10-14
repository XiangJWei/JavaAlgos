package com.xiangjw.stack;

public class Brower {

	//时刻保持backStack的栈顶为当前访问的页面
	private ArrayStack<String> backStack;//后退的数据栈
	
	private ArrayStack<String> frontStack;//前进的数据栈
	
	public Brower() {
		backStack = new ArrayStack<String>(10);
		frontStack = new ArrayStack<String>(10);
	}
	
	public void open(String url) {
		System.out.println("访问--->" + url);
		backStack.push(url);
	}
	
	/**
	 * 后退
	 */
	public void back() {
		if(backStack.getLength() <= 1) {
			System.out.println("退无可退");
			return;
		}
		
		String info = backStack.pull();
		System.out.println("回退访问--->" + backStack.get());
		frontStack.push(info);
	}
	
	/**
	 * 前进
	 */
	public void forward() {
		if(frontStack.getLength() == 0) {
			System.out.println("进无可进");
			return;
		}
		
		String info = frontStack.pull();
		System.out.println("前进访问--->" + info);
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
