package com.sunny.testProxy;

import java.lang.reflect.Proxy;

/**
 * ��Ӧ��Mybatis�е�SqlSession�࣬ʵ��getMapper()����
 * @author Administrator
 *
 */
public class MySqlSession {
	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> type) {
		//���ش���ʵ��
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, new MyMapperProxy<T>(type));
	  }
}
