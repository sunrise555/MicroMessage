package com.sunny.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * �����Ӵ����ݿ�Ĳ�
 * ���ܣ���ȡsqlSession���󣬴��ݸ�Dao��
 * @author sunny
 *
 */
public class DataBaseAccess {
	public SqlSession getSqlSession() throws IOException {
		//ͨ�������ļ���ȡ���ݿ�������Ϣ
		//��ȡ�����ļ�����·���Ǵ�src��ʼ�����
		Reader reader = Resources.getResourceAsReader("com/sunny/config/Configuration.xml");
		//ͨ��������Ϣ����sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//ͨ��sqlSessionFactory�򿪻Ự
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
