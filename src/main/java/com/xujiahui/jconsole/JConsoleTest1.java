package com.xujiahui.jconsole;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码清单4-8  JConsole监视代码
 * -Xms 100m   -Xmx 100m    -XX:UseSerialGC
 * @author xujiahui
 * 2019年7月11日下午2:10:04
 */
public class JConsoleTest1 {
	
	static class OOMObject {
		public byte[] placeholder = new byte[64*1024];
	}
	
	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<>();
		
		for (int i = 0; i < num; i++) {
			//稍作延时，令监视曲线的变化更加明显
			Thread.sleep(50);
			list.add(new OOMObject());
		}
//		System.gc();
	}

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(1000 * 10);
		fillHeap(1000);
		System.gc();
		System.out.println("结束");
		Thread.sleep(1000 * 200);
	}

}

