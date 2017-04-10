package com.sunny.testProxy;

public class MyMain {

	public static void main(String[] args) {
		MySqlSession sqlSession = new MySqlSession();
		MyInterface myInterface = sqlSession.getMapper(MyInterface.class);
		String s = myInterface.query();
		System.out.println(s);
	}

}
