package com.sunny.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sunny.bean.Command;
import com.sunny.db.DataBaseAccess;

/**
 * Command��Ĳ�����
 * @author sunny
 *
 */
public class CommandDao {
	/**
	 * ����ָ������ơ�������ѯ�б�
	 * @param name ָ������
	 * @param description ָ������
	 * @return ��ѯ����ָ��
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
	 * ��ָ����в���һ����¼
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
	 * ����ָ�����ƣ����ز�ѯ�Ľ��
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
	 * ����ָ����е�һ����¼
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

