package com.xujiahui.visualVM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 代码清单4-11   BTrace跟踪演示
 * @author xujiahui
 * 2019年7月14日下午1:29:44
 */
public class BTraceTest {
	
	public int add(int a, int b) {
		return a+b;
	}

	public static void main(String[] args) throws IOException {
		BTraceTest test = new BTraceTest();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 10; i++) {
			
			reader.readLine();
			
			int a = (int)Math.round(Math.random() * 1000);
			int b = (int)Math.round(Math.random() * 1000);
			System.out.println(test.add(a, b));
			
		}

	}

}
