package com.xujiahui.oom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试
 * 1、一个线程报OOM时,另一个线程还会正常工作
 * 2、n++操作的线程安全性,加了volatile保证内存可见也没用，无法保证原子性
 * 3、AtomicInteger的线程安全性
 * @author xujiahui
 * 2019年6月27日下午5:40:44
 */
public class TwoThreadTest {
	
	public static List<byte[]> list = new ArrayList<>();
	
	public static AtomicInteger ai = new AtomicInteger(0);
	
	public static volatile int n = 0;

	public static void main(String[] args) {
		
		testInteger();
		testAtomicInteger();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(n);
		System.out.println(ai.get());
		
	}
	
	/**
	 * 一个线程报OOM时。另一个线程还会正常工作
	 */
	public static void twoThreadOOM() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println(new Date().toString());
					list.add(new byte[1024 * 1024 * 1]);
					try {	
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}				
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					System.out.println(new Date().toString());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}				
			}
		}).start();		
	}
	
	public static void testInteger() {
		CountRunnable countRunnable = new CountRunnable();		
		for (int i = 0; i < 100; i++) {
			new Thread(countRunnable).start();
		}
	}
	
	public static void testAtomicInteger() {
		AtomicCountRunnable atomicCountRunnable = new AtomicCountRunnable();
		for (int i = 0; i < 100; i++) {
			new Thread(atomicCountRunnable).start();
		}
	}
	
	//int自增
	static class CountRunnable implements Runnable {		
		private void count() {
			for (int i = 0; i < 100; i++) {
				n++;
			}
		}
		@Override
		public void run() {
			count();
		}
	}
	
	//AtomicIntger自增
	static class AtomicCountRunnable implements Runnable{
		
		private void count() {
			for (int i = 0; i < 100; i++) {
				ai.incrementAndGet();
			}
		}

		@Override
		public void run() {
			count();
		}
		
	}
	
}





