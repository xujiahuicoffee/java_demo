package com.xujiahui.oom;

/**
 * VM Args: -Xss128k
 * @author xujiahui
 * 2019年6月14日下午9:08:19
 */
public class JavaVMStackSOF {
	
	private int stackLength = 1;
	
	public void stackLeak() {
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();		
		} catch (Throwable e) {
			System.out.println("stack length:" + oom.stackLength);
			throw e;
		}

	}

}
