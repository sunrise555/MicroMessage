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
 * Message�������
 * @author sunny
 *
 */
public class MessageDao {
//	public List<Message> queryMessageList(String command, String description) {
		
/**
 * ��������Dao���������ݿ�
 */
//		List<Message> messageList = new ArrayList<Message>();
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message", "root", "");
//			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,DESCRIPTION,CONTENT from message where 1=1");
//			List<String> paraList = new ArrayList<String>();
//			//ƴ�Ӳ�ѯ��sql���
//			if(null!=command && !"".equals(command.trim())) {
//				sql.append(" and COMMAND=?");
//				paraList.add(command);
//			}
//			if(null!=description && !"".equals(description.trim())) {
//				sql.append(" and DESCRIPTION like '%' ? '%'");
//				paraList.add(description);
//			}
			//ʹ��������ʵ�ַ�ҳ���ܣ�Ӧ�������λ�þͿ�ʼ����
	//		PreparedStatement pst = conn.prepareStatement(sql.toString());
//			//Ϊ�ڼ����ʺŸ�ֵ
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
 * ����ʹ��Mybatis��sqlSession���������ݿ�
 * ���ܣ���ѯ�б�
 * ��ʹ��Mybatis��limit��ʵ�ַ�ҳ����
 */
	public List<Message> queryMessageList(Message message) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			//���ڵ����⣬���淽���д��Ĳ���ֻҪ��object���͵ģ����붼��ͨ������ִ�л����
			//ʵ��������ֻ��ҪMessage���͵Ĳ�������ζԴ��ݵĲ�����Լ����ʹ�ýӿ�ʽ��̣��������͹淶��
			//����������ݵĲ������Ͳ�ƥ�䣬����ʱ�ͻᱨ��
//			messageList = sqlSession.selectList("Message.queryMessageList", message);
			/**
			 * ʹ��sqlSession��getMapper()��ȡ�ӿ�ʵ����
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
	 * ��ҳ��ѯ��Ϣ�б�
	 * ��ʹ��������ʵ�ַ�ҳ����
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
 * ɾ��������¼
 * @param id -- ɾ����¼��Ӧ��id
 */
	public void deleteOne(int id) {
		DataBaseAccess dataBaseAccess = new DataBaseAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dataBaseAccess.getSqlSession();
			MessageInterface messageInterface = sqlSession.getMapper(MessageInterface.class);
			messageInterface.deleteOne(id);
			//�����ݿ������ɾ�Ĳ���ʱ����Ҫ�ֶ��ύ����������������������ݿ�
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
			//�����ݿ������ɾ�Ĳ���ʱ����Ҫ�ֶ��ύ����������������������ݿ�
			sqlSession.commit();
		} catch (IOException e) {
			
		}finally {
			if(sqlSession!=null)
				sqlSession.close();
		}
	}	
	/**
	 * ��ȡ��ѯ���������
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
	 * ��message���в���һ����¼
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
	 * ����id���¶�Ӧ�ļ�¼
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
