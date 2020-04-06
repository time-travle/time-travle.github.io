/**
 * Project Name: ershuaizhang.github.io
 * File Name: SpringContextUtils
 * Package Name: org.joven.base.utils
 * Date: 2020/2/9 1:18
 * Copyright (c) 2020,All Rights Reserved.
 */
package org.joven.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * CreateBy Notebook
 * Date: 2020/2/9 1:18
 * Version: 1.0
 * Remark: Spring上下文的工具类，当我们需要在一个非Spring管理的类中调用Spring管理的bean
 */
public class SpringContextUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	/**
	 * 根据类型获取bean
	 */
	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
}
