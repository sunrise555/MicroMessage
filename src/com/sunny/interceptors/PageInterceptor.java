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
 * 分页拦截器对应的类
 * @author Administrator
 *
 */
//将此类完成后，需要在configuration.xml文件中注册该拦截器<plugin>
//以下注解参数type--需要拦截的类的对象   method--拦截的方法名  args--方法对应的参数
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//invocation中包含了我们需要的原始sql语句以及配置信息
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		/**
		 * 但是，我们拦截的对象是对的，不能保证拦截的对象的动作也是对的，
		 * 因为很多sql语句都可以触发prepare方法，这是我们需要通过sql语句的id来判断对象是否需要分页功能
		 * id=queryMessageListByPage,我们设定以“ByPage”结尾的sql都需要分页功能
		*/
		//metaObject封装的是反射功能,获取
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
				SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		//通过反射取到MappedStatement对象,getValue()中的参数写法与ognl格式相同
		//上面的StateHandler是先通过RoutingHandler访问，在通过delegate访问到mappedStatement
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		//获取sql语句对应的id
		String sql_id = mappedStatement.getId();
		//判断，利用正则表达式判断以ByPage结尾的id对应的sql语句是需要分页功能的
		if (sql_id.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			//获取原始的sql语句
			String sql = boundSql.getSql();
			//改造sql，添加分页功能
			//查询总条数的sql语句
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
		//交回主权
		return invocation.proceed();
	}
	//plugin方法是判断传入的target是否是需要拦截的对象，根据拦截器的注解来判断
	//如果是，则返回有代理权的对象，这个对象继续执行intercept()方法，对sql进行修改
	//如果不是，则返回无代理权的兑现个，且不执行intercept()方法
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
