package com.sunny.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sunny.bean.CommandContent;
import com.sunny.db.DataBaseAccess;

/**
 * CommandContent表的操作层
 * @author Administrator
 *
 */
public class CommandContentDao {
	public void insertBatchByJDBC(List<CommandContent> contentList) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message", "root", "");
			String sql = "INSERT INTO micro_message.command_content(CONTENT, COMMAND_ID) VALUES (?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			for (CommandContent content : contentList) {
				statement.setString(1, content.getContent());
				statement.setString(2, content.getCommand_id());
				statement.addBatch();
			}
			statement.executeBatch();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertBatchByMybatis(List<CommandContent> contentList) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			CommandContentInterface commandContentInterface  = sqlSession.getMapper(CommandContentInterface.class);
			commandContentInterface.insertBatch(contentList);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
	}
	
	public int countCommandContentNum() {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			CommandContentInterface commandContentInterface = sqlSession.getMapper(CommandContentInterface.class);
			result = commandContentInterface.countCommandContentNum();
		} catch (IOException e) {
			
		}finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
		return result;
	}
	/**
	 * 根据指令id批量删除指令内容
	 * @param command_id
	 */
	public void deleteByCommandID(String command_id) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			CommandContentInterface commandContentInterface = sqlSession.getMapper(CommandContentInterface.class);
			commandContentInterface.deleteByCommandID(command_id);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
	}
}
