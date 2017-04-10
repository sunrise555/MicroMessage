package com.sunny.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sunny.bean.Message;
import com.sunny.db.DataBaseAccess;

/**
 * Message表操作层
 * @author sunny
 *
 */
public class MessageDao {
//	public List<Message> queryMessageList(String command, String description) {
		
/**
 * 下面是在Dao层连接数据库
 */
//		List<Message> messageList = new ArrayList<Message>();
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message", "root", "");
//			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,DESCRIPTION,CONTENT from message where 1=1");
//			List<String> paraList = new ArrayList<String>();
//			//拼接查询的sql语句
//			if(null!=command && !"".equals(command.trim())) {
//				sql.append(" and COMMAND=?");
//				paraList.add(command);
//			}
//			if(null!=description && !"".equals(description.trim())) {
//				sql.append(" and DESCRIPTION like '%' ? '%'");
//				paraList.add(description);
//			}
			//使用拦截器实现分页功能，应该在这个位置就开始拦截
	//		PreparedStatement pst = conn.prepareStatement(sql.toString());
//			//为第几个问号赋值
//			for (int i = 0; i < paraList.size(); i++) {
//				pst.setString(i + 1, paraList.get(i));
//			}
//			ResultSet result = pst.executeQuery();
//			
//			while(result.next()) {
//				Message message = new Message();
//				message.setId(result.getString("ID"));
//				message.setCommand(result.getString("COMMAND"));
//				message.setDescription(result.getString("DESCRIPTION"));
//				message.setContent(result.getString("CONTENT"));
//				messageList.add(message);
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (SQLException e) {
//			// TODO: handle exception
//		}
//		return messageList;
//	}
/**
 * 下面使用Mybatis的sqlSession来操作数据库
 * 功能：查询列表
 * 》使用Mybatis的limit来实现分页功能
 */
	public List<Message> queryMessageList(Message message) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			//存在的问题，下面方法中传的参数只要是object类型的，编译都会通过，但执行会出错
			//实际上我们只需要Message类型的参数，如何对传递的参数加约束，使用接口式编程，参数类型规范化
			//这样如果传递的参数类型不匹配，编译时就会报错
//			messageList = sqlSession.selectList("Message.queryMessageList", message);
			/**
			 * 使用sqlSession的getMapper()获取接口实现类
			 */
			MessageInterface messageInterface = sqlSession.getMapper(MessageInterface.class);
			messageList = messageInterface.queryMessageList(message);
		} catch (IOException e) {
			// TODO: handle exception
		}finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
		return messageList;
	}
	/**
	 * 分页查询消息列表
	 * 》使用拦截器实现分页功能
	 * @param map
	 * @return
	 */
	public List<Message> queryMessageListByPage(HashMap<String, Object> map) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			MessageInterface messageInterface = sqlSession.getMapper(MessageInterface.class);
			messageList = messageInterface.queryMessageListByPage(map);
		} catch (IOException e) {
			// TODO: handle exception
		}finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
		return messageList;
	}
/**
 * 删除单条记录
 * @param id -- 删除记录对应的id
 */
	public void deleteOne(int id) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			MessageInterface messageInterface = sqlSession.getMapper(MessageInterface.class);
			messageInterface.deleteOne(id);
			//对数据库进行增删改操作时，需要手动提交，进行事务操作，更新数据库
			sqlSession.commit();
		} catch (IOException e) {
			// TODO: handle exception
		}finally {
			if(sqlSession!=null)
				sqlSession.close();
		}

	}
	
	public void deleteBatch(List<Integer> list) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			MessageInterface messageInterface = sqlSession.getMapper(MessageInterface.class);
			messageInterface.deleteBatch(list);
			//对数据库进行增删改操作时，需要手动提交，进行事务操作，更新数据库
			sqlSession.commit();
		} catch (IOException e) {
			
		}finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
	}	
	/**
	 * 获取查询结果的条数
	 * @return
	 */
	public int countQueryItemNum(Message message) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			MessageInterface messageInterface = sqlSession.getMapper(MessageInterface.class);
			result = messageInterface.countQueryItemNum(message);
		} catch (IOException e) {
			
		}finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
		return result;
	}
	
	/**
	 * 向message表中插入一条记录
	 * @param message
	 */
	public void insertOne(Message message) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			MessageInterface messageInterface = sqlSession.getMapper(MessageInterface.class);
			messageInterface.insertOne(message);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
	}
	/**
	 * 根据id更新对应的记录
	 * @param message
	 */
	public void updateOne(Message message) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			MessageInterface messageInterface = sqlSession.getMapper(MessageInterface.class);
			messageInterface.updateOne(message);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
	}
}
