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
 * 业务逻辑层，直接访问Dao层，调用Dao层的方法完成业务逻辑
 * @author Administrator
 *
 */
public class QueryService {
	/**
	 * 
	 * @author Administrator
	 * @create_time 2016年12月17日 下午9:05:43
	 * @modify_time 2016年12月17日 下午9:05:43 
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
	 * 根据查询条件分页显示查询结果
	 * 》采用mysql的limit实现分页功能
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
	 * 根据查询条件分页显示查询结果
	 * 》使用Mybatis的拦截器来实现分页功能
	 * @param command 指令名称
	 * @param description 指令描述
	 * @param page 分页信息
	 * @return
	 */
	public List<Message> queryMessageListByPage(String command, String description, Page page) {
		MessageDao listDao = new MessageDao();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		//先通过数据库查询，获取查询后结果的条数，对应totalItemNum
		int totalItemNum = listDao.countQueryItemNum(message);
		page.setTotalItemNum(totalItemNum);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("page", page);
		return listDao.queryMessageListByPage(map);
	}
	/**
	 * 根据用户输入的id查询
	 * @param id
	 * @return
	 */
	public String queryById(String id) {
		CommandDao listDao = new CommandDao();
		List<Command> list;
		list = listDao.queryCommandList(id, null, null);
		if(list.size()>0) {
			List<CommandContent> contentList = list.get(0).getContentList();
			//表示随机返回0到contentList.size()之间的整数，包含0不包含contentList.size()；左闭右开
			int index = new Random().nextInt(contentList.size());
			return list.get(0).getDescription()+"<br/>"+contentList.get(index).getContent();
		}
		return DefaultInformationUtil.NO_MATCHING_ID;
	}
	/**
	 * 根据用户指令名称查询内容
	 * @param name 指令名称
	 * @return
	 */
	public String queryByCommand(String name) {
		CommandDao listDao = new CommandDao();
		List<Command> list;
		list = listDao.queryCommandList(null, name,null);
		if(list.size()>0) {
			List<CommandContent> contentList = list.get(0).getContentList();
			//表示随机返回0到contentList.size()之间的整数，包含0不包含contentList.size()；左闭右开
			int index = new Random().nextInt(contentList.size());
			return list.get(0).getDescription()+"<br/>"+contentList.get(index).getContent();
		}
		return DefaultInformationUtil.NO_MATCHING_CONTENT;
	}
	/**
	 * 根据用户输入内容查询结果
	 * @param content
	 * @return
	 */
	public String queryByInputContent(String content) {
			//如果回复帮助，则显示所有指令及描述
		if(DefaultInformationUtil.HELP_COMMAND.equals(content)) {
			MessageDao listDao = new MessageDao();
			List<Message> list;
			//无条件查询
			list = listDao.queryMessageList(null);
			//拼接指令列表
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				//1.查看[精彩内容]
				if(i!=0)
					result.append("<br/>");
				result.append(list.get(i).getId() + "." +list.get(i).getCommand() + "[" + list.get(i).getDescription() + "]");
			}
			result.append("<br/>回复" +DefaultInformationUtil.HOME_PAGE+ "返回主页面");
			return result.toString();
		}else if(DefaultInformationUtil.HOME_PAGE.equals(content)){
			//如果回复99，就返回欢迎页
			return DefaultInformationUtil.WELCOME_PAGE;
		}else if(isInteger(content)){
			return queryById(content);
		}else {
			return queryByCommand(content);
		}
	}
	
	/**
	 * 判断字符串是不是整数
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
//	 * 通过指令名称获取指令的id
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
	 * 新增页面进行插入数据
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
		//如果表中包含新添加的指令，则只对指令内容表进行插入
		//否则需要对指令表以及信息表进行插入
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
			//设置新纪录的id
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
	 * @create_time 2016年12月17日 下午8:14:59
	 * @modify_time 2016年12月17日 下午8:14:59 
	 * @param id
	 * @param command
	 * @param description
	 * @param contents
	 */
	public void modifyItem(String id,String command_name,String description,String[] contents) {
		CommandContentDao commandContentDao = new CommandContentDao();
		//先将所有该指令的内容全部删除，之后再进行插入
		//该做发适用于记录数较少的时候
		commandContentDao.deleteByCommandID(id);
		//当前内容的条数
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
		
		//想指令内容表中插入新数据
		commandContentDao.insertBatchByMybatis(contentList);
	}
}
