package com.sunny.DAO;

import java.util.HashMap;
import java.util.List;

import com.sunny.bean.Message;

/**
 * message表对应的接口
 * @author sunny
 *
 */
public interface MessageInterface {
	//具体的接口实现类由mybatis来完成，只要匹配了namespace即可
	public List<Message> queryMessageList(Message message);
	public void deleteOne(int id);
	public void deleteBatch(List<Integer> list);
	public int countQueryItemNum(Message message);
	public List<Message> queryMessageListByPage(HashMap<String, Object> map);
	public void insertOne(Message message);
	public void updateOne(Message message);
}
