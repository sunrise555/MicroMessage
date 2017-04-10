package com.sunny.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 真正接触数据库的层
 * 功能：获取sqlSession对象，传递给Dao层
 * @author sunny
 *
 */
public class DataBaseAccess {
	public SqlSession getSqlSession() throws IOException {
		//通过配置文件获取数据库连接信息
		//获取配置文件，该路径是从src开始算起的
		Reader reader = Resources.getResourceAsReader("com/sunny/config/Configuration.xml");
		//通过配置信息构建sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//通过sqlSessionFactory打开会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
