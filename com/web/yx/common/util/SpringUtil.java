package com.web.yx.common.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class SpringUtil implements ApplicationContextAware{
	
	private static ApplicationContext context;
	
	private static CountDownLatch init =new CountDownLatch(1);
	
	public static Object getBean(String beanName){
		if(context==null){
			try {
				init.await(30,TimeUnit.SECONDS);
			} catch (InterruptedException e) {
			}
		}
		return context.getBean(beanName);
	}
	@SuppressWarnings({ "unchecked"})
	public static <T> T getBean(String beanName,Class<T> beanClass){
		if(context==null){
			try {
				init.await(30,TimeUnit.SECONDS);
			} catch (InterruptedException e) {
			}
		}
		return (T)context.getBean(beanName, beanClass);
	}
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}

	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context= applicationContext;
		init.countDown();
	}
}
