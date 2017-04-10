package com.sunny.testProxy;

import java.lang.reflect.Proxy;

/**
 * 对应于Mybatis中的SqlSession类，实现getMapper()方法
 * @author Administrator
 *
 */
public class MySqlSession {
	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> type) {
		//返回代理实例
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, new MyMapperProxy<T>(type));
	  }
}
