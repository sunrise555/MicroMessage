package com.sunny.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sunny.bean.Command;
import com.sunny.db.DataBaseAccess;

/**
 * Command表的操作层
 * @author sunny
 *
 */
public class CommandDao {
	/**
	 * 根据指令的名称、描述查询列表
	 * @param name 指令名称
	 * @param description 指令描述
	 * @return 查询到的指令
	 */
	public List<Command> queryCommandList(String id, String name, String description) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		List<Command> commandList = new ArrayList<Command>();
		SqlSession sqlSession = null;
		Command command = new Command();
		command.setId(id);
		command.setName(name);
		command.setDescription(description);
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			CommandInterface commandInterface = sqlSession.getMapper(CommandInterface.class);
			commandList = commandInterface.queryCommandList(command);
		} catch (IOException e) {
			// TODO: handle exception
		}finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
		return commandList;
	}
	/**
	 * 向指令表中插入一条记录
	 */
	public void insertOne(Command command) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			CommandInterface commandInterface = sqlSession.getMapper(CommandInterface.class);
			commandInterface.insertOne(command);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
	}
	
	public int countCommandNum() {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			CommandInterface commandInterface = sqlSession.getMapper(CommandInterface.class);
			result = commandInterface.countCommandNum();
		} catch (IOException e) {
			
		}finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
		return result;
	}
	/**
	 * 根据指令名称，返回查询的结果
	 * @param name
	 * @return
	 */
	public Command getCommandByName(String name) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		Command command = new Command();
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			CommandInterface commandInterface = sqlSession.getMapper(CommandInterface.class);
			command = commandInterface.getCommandByName(name);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
		return command;
	}
	
	public void deleteOne(int id) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			CommandInterface commandInterface = sqlSession.getMapper(CommandInterface.class);
			commandInterface.deleteOne(id);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
	}
	/**
	 * 更新指令表中的一条记录
	 * @param command
	 */
	public void updateOne(Command command) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			CommandInterface commandInterface = sqlSession.getMapper(CommandInterface.class);
			commandInterface.updateOne(command);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
	}
}

