package com.xujiahui.oom;

import java.lang.reflect.Method;

import org.junit.experimental.runners.Enclosed;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


/**
 * jdk1.8中不再会出现  OOM：PermGen space
 * 
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author xujiahui
 * 2019年6月14日下午9:51:43
 */
public class JavaMethodAreaOOM {

	public static void main(String[] args) {
		while(true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {			
				@Override
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(obj, args);
				}
			});
			enhancer.create();
		}
	}
	
	static class OOMObject {
		
	}

}
