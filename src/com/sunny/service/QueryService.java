package com.sunny.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.sunny.DAO.CommandContentDao;
import com.sunny.DAO.CommandDao;
import com.sunny.DAO.MessageDao;
import com.sunny.bean.Command;
import com.sunny.bean.CommandContent;
import com.sunny.bean.Message;
import com.sunny.entity.Page;
import com.sunny.util.DefaultInformationUtil;

/**
 * ҵ���߼��㣬ֱ�ӷ���Dao�㣬����Dao��ķ������ҵ���߼�
 * @author Administrator
 *
 */
public class QueryService {
	/**
	 * 
	 * @author Administrator
	 * @create_time 2016��12��17�� ����9:05:43
	 * @modify_time 2016��12��17�� ����9:05:43 
	 * @param id
	 * @param name
	 * @param description
	 * @return
	 */
	public List<Command> queryCommandList(String id, String name, String description) {
		CommandDao commandDao = new CommandDao();
		return commandDao.queryCommandList(id, name, description);
	}
	/**
	 * ���ݲ�ѯ������ҳ��ʾ��ѯ���
	 * ������mysql��limitʵ�ַ�ҳ����
	 * @param command 
	 * @param description
	 * @param page
	 * @return
	 */
	public List<Message> queryMessageList(String command, String description) {
		MessageDao listDao = new MessageDao();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		return listDao.queryMessageList(message);
	}
	/**
	 * ���ݲ�ѯ������ҳ��ʾ��ѯ���
	 * ��ʹ��Mybatis����������ʵ�ַ�ҳ����
	 * @param command ָ������
	 * @param description ָ������
	 * @param page ��ҳ��Ϣ
	 * @return
	 */
	public List<Message> queryMessageListByPage(String command, String description, Page page) {
		MessageDao listDao = new MessageDao();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		//��ͨ�����ݿ��ѯ����ȡ��ѯ��������������ӦtotalItemNum
		int totalItemNum = listDao.countQueryItemNum(message);
		page.setTotalItemNum(totalItemNum);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("page", page);
		return listDao.queryMessageListByPage(map);
	}
	/**
	 * �����û������id��ѯ
	 * @param id
	 * @return
	 */
	public String queryById(String id) {
		CommandDao listDao = new CommandDao();
		List<Command> list;
		list = listDao.queryCommandList(id, null, null);
		if(list.size()>0) {
			List<CommandContent> contentList = list.get(0).getContentList();
			//��ʾ�������0��contentList.size()֮�������������0������contentList.size()������ҿ�
			int index = new Random().nextInt(contentList.size());
			return list.get(0).getDescription()+"<br/>"+contentList.get(index).getContent();
		}
		return DefaultInformationUtil.NO_MATCHING_ID;
	}
	/**
	 * �����û�ָ�����Ʋ�ѯ����
	 * @param name ָ������
	 * @return
	 */
	public String queryByCommand(String name) {
		CommandDao listDao = new CommandDao();
		List<Command> list;
		list = listDao.queryCommandList(null, name,null);
		if(list.size()>0) {
			List<CommandContent> contentList = list.get(0).getContentList();
			//��ʾ�������0��contentList.size()֮�������������0������contentList.size()������ҿ�
			int index = new Random().nextInt(contentList.size());
			return list.get(0).getDescription()+"<br/>"+contentList.get(index).getContent();
		}
		return DefaultInformationUtil.NO_MATCHING_CONTENT;
	}
	/**
	 * �����û��������ݲ�ѯ���
	 * @param content
	 * @return
	 */
	public String queryByInputContent(String content) {
			//����ظ�����������ʾ����ָ�����
		if(DefaultInformationUtil.HELP_COMMAND.equals(content)) {
			MessageDao listDao = new MessageDao();
			List<Message> list;
			//��������ѯ
			list = listDao.queryMessageList(null);
			//ƴ��ָ���б�
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				//1.�鿴[��������]
				if(i!=0)
					result.append("<br/>");
				result.append(list.get(i).getId() + "." +list.get(i).getCommand() + "[" + list.get(i).getDescription() + "]");
			}
			result.append("<br/>�ظ�" +DefaultInformationUtil.HOME_PAGE+ "������ҳ��");
			return result.toString();
		}else if(DefaultInformationUtil.HOME_PAGE.equals(content)){
			//����ظ�99���ͷ��ػ�ӭҳ
			return DefaultInformationUtil.WELCOME_PAGE;
		}else if(isInteger(content)){
			return queryById(content);
		}else {
			return queryByCommand(content);
		}
	}
	
	/**
	 * �ж��ַ����ǲ�������
	 * @param value
	 * @return
	 */
	private static boolean isInteger(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	     } catch (NumberFormatException e) {  
	         return false;  
	     }  
	 } 
//	/**
//	 * ͨ��ָ�����ƻ�ȡָ���id
//	 * @param commandName
//	 * @return
//	 */
//	public String queryCommandId(String commandName) {
//		CommandDao listDao = new CommandDao();
//		List<Command> list;
//		list = listDao.queryCommandList(null, commandName,null);
//		if(list.isEmpty())
//			return null;
//		return list.get(0).getId();
//	}
	/**
	 * ����ҳ����в�������
	 * @param command
	 * @param contents
	 */
	public void insertBatchByMybatis(String commandName, String description, String[] contents) {
		Command old_command = new Command();
		CommandDao commandDao = new CommandDao();
		CommandContentDao commandContentDao = new CommandContentDao();
		old_command = commandDao.getCommandByName(commandName);
		if(null!=old_command){
		String command_id = old_command.getId();
		//������а�������ӵ�ָ���ֻ��ָ�����ݱ���в���
		//������Ҫ��ָ����Լ���Ϣ����в���
		int commandContent_ID = commandContentDao.countCommandContentNum() + 1;
		List<CommandContent> contentList = new ArrayList<CommandContent>();
		for (String content : contents) {
			CommandContent commandContent = new CommandContent();
			commandContent.setId(Integer.toString(commandContent_ID));
			commandContent.setContent(content);
			commandContent.setCommand_id(command_id);
			contentList.add(commandContent);
			commandContent_ID += 1;
		}		
		commandContentDao.insertBatchByMybatis(contentList);
		} else {
			//�����¼�¼��id
			int id = commandDao.countCommandNum() + 1;
			int commandContent_ID = commandContentDao.countCommandContentNum() + 1;
			List<CommandContent> contentList = new ArrayList<CommandContent>();
			for (String content : contents) {
				CommandContent commandContent = new CommandContent();
				commandContent.setId(Integer.toString(commandContent_ID));
				commandContent.setContent(content);
				commandContent.setCommand_id(Integer.toString(id));
				contentList.add(commandContent);
				commandContent_ID += 1;
			}
			Command command = new Command();
			command.setId(Integer.toString(id));
			command.setName(commandName);
			command.setDescription(description);
			command.setContentList(contentList);
			commandDao.insertOne(command);
			commandContentDao.insertBatchByMybatis(contentList);
			MessageDao messageDao = new MessageDao();
			Message message = new Message();
			message.setId(Integer.toString(id));
			message.setCommand(commandName);
			message.setDescription(description);
			message.setContent(contents[0]);
			//message.setContentList(contents);
			messageDao.insertOne(message);
		}
	}
	/**
	 * 
	 * @author Administrator
	 * @create_time 2016��12��17�� ����8:14:59
	 * @modify_time 2016��12��17�� ����8:14:59 
	 * @param id
	 * @param command
	 * @param description
	 * @param contents
	 */
	public void modifyItem(String id,String command_name,String description,String[] contents) {
		CommandContentDao commandContentDao = new CommandContentDao();
		//�Ƚ����и�ָ�������ȫ��ɾ����֮���ٽ��в���
		//�����������ڼ�¼�����ٵ�ʱ��
		commandContentDao.deleteByCommandID(id);
		//��ǰ���ݵ�����
		int commandContent_ID = commandContentDao.countCommandContentNum() + 1;
		List<CommandContent> contentList = new ArrayList<CommandContent>();
		for (String content : contents) {
			CommandContent commandContent = new CommandContent();
			commandContent.setId(Integer.toString(commandContent_ID));
			commandContent.setCommand_id(id);
			commandContent.setContent(content);
			commandContent_ID += 1;
		}
		Message message = new Message();
		message.setId(id);
		message.setCommand(command_name);
		message.setContent(contents[0]);
		message.setDescription(description);
		//message.setContentList(contents);
		MessageDao messageDao = new MessageDao();
		messageDao.updateOne(message);
		
		Command command = new Command();
		command.setId(id);
		command.setName(command_name);
		command.setDescription(description);
		command.setContentList(contentList);
		CommandDao CommandDao = new CommandDao();
		CommandDao.updateOne(command);
		
		//��ָ�����ݱ��в���������
		commandContentDao.insertBatchByMybatis(contentList);
	}
}
