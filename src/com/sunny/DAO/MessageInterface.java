package com.sunny.DAO;

import java.util.HashMap;
import java.util.List;

import com.sunny.bean.Message;

/**
 * message���Ӧ�Ľӿ�
 * @author sunny
 *
 */
public interface MessageInterface {
	//����Ľӿ�ʵ������mybatis����ɣ�ֻҪƥ����namespace����
	public List<Message> queryMessageList(Message message);
	public void deleteOne(int id);
	public void deleteBatch(List<Integer> list);
	public int countQueryItemNum(Message message);
	public List<Message> queryMessageListByPage(HashMap<String, Object> map);
	public void insertOne(Message message);
	public void updateOne(Message message);
}
