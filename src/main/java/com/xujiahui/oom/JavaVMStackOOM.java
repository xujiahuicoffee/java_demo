package com.xujiahui.oom;

/**
 * 该代码可能会导致操作系统假死，运行需谨慎！！！
 * VM Args: -Xss2M         栈容量
 * @author xujiahui
 * 2019年6月14日下午9:29:03
 */
public class JavaVMStackOOM {

	private void dontStop() {
		while (true) {
		}
	}
	
	public void stackLeakByThread() {
		while(true) {
			Thread thread = new Thread(new Runnable() {			
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}
	
	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}

}
