package com.sunny.testProxy;
import java.lang.reflect.*;

public class MyMapperProxy<T> implements InvocationHandler{
	  private final Class<T> mapperInterface;

	  public MyMapperProxy(Class<T> mapperInterface) {
	    this.mapperInterface = mapperInterface;

	  }
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String s = mapperInterface.getSimpleName()+ "." + method.getName() + "()";
		return s;
	}

}
