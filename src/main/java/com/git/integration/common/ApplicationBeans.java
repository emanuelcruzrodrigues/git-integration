package com.git.integration.common;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBeans implements BeanFactoryAware{
	
	private static BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		ApplicationBeans.beanFactory = beanFactory;
	}
	
	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	public static <T> Map<String, T> getBeansOfType(Class<T> clazz){
		Map<String, T> result = ((ListableBeanFactory)beanFactory).getBeansOfType(clazz);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		return (T) beanFactory.getBean(name);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz){
		return (T) beanFactory.getBean(clazz);
	}

}
