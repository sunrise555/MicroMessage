package com.sunny.interceptors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.sunny.entity.Page;



/**
 * ��ҳ��������Ӧ����
 * @author Administrator
 *
 */
//��������ɺ���Ҫ��configuration.xml�ļ���ע���������<plugin>
//����ע�����type--��Ҫ���ص���Ķ���   method--���صķ�����  args--������Ӧ�Ĳ���
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//invocation�а�����������Ҫ��ԭʼsql����Լ�������Ϣ
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		/**
		 * ���ǣ��������صĶ����ǶԵģ����ܱ�֤���صĶ���Ķ���Ҳ�ǶԵģ�
		 * ��Ϊ�ܶ�sql��䶼���Դ���prepare����������������Ҫͨ��sql����id���ж϶����Ƿ���Ҫ��ҳ����
		 * id=queryMessageListByPage,�����趨�ԡ�ByPage����β��sql����Ҫ��ҳ����
		*/
		//metaObject��װ���Ƿ��书��,��ȡ
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
				SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		//ͨ������ȡ��MappedStatement����,getValue()�еĲ���д����ognl��ʽ��ͬ
		//�����StateHandler����ͨ��RoutingHandler���ʣ���ͨ��delegate���ʵ�mappedStatement
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		//��ȡsql����Ӧ��id
		String sql_id = mappedStatement.getId();
		//�жϣ�����������ʽ�ж���ByPage��β��id��Ӧ��sql�������Ҫ��ҳ���ܵ�
		if (sql_id.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			//��ȡԭʼ��sql���
			String sql = boundSql.getSql();
			//����sql����ӷ�ҳ����
			//��ѯ��������sql���
			String countSql = "select count(*) from (" + sql + ")a";		                          
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement countStatement  = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet resultSet = countStatement.executeQuery();
			
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = (HashMap<String, Object>) boundSql.getParameterObject();
			Page page = (Page) map.get("page");
			if (resultSet.next()) {
				page.setTotalItemNum(resultSet.getInt(1));
			}
			String page_sql = sql + " limit " + page.getDbIndex() + "," + page.getDbNum();
			metaObject.setValue("delegate.boundSql.sql", page_sql);
		}
		//������Ȩ
		return invocation.proceed();
	}
	//plugin�������жϴ����target�Ƿ�����Ҫ���صĶ��󣬸�����������ע�����ж�
	//����ǣ��򷵻��д���Ȩ�Ķ�������������ִ��intercept()��������sql�����޸�
	//������ǣ��򷵻��޴���Ȩ�Ķ��ָ����Ҳ�ִ��intercept()����
	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
