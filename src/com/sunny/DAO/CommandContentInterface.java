package com.sunny.DAO;

import java.util.List;

import com.sunny.bean.CommandContent;

/**
 * command_content���Ӧ�Ľӿ�
 * @author Administrator
 *
 */
public interface CommandContentInterface {
	public void insertOne(CommandContent commandContent);
	public void insertBatch(List<CommandContent> commandContent);
	public int countCommandContentNum();
	public void deleteByCommandID(String command_id);
}
