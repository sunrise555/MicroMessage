package com.sunny.DAO;

import java.util.List;

import com.sunny.bean.CommandContent;

/**
 * command_content表对应的接口
 * @author Administrator
 *
 */
public interface CommandContentInterface {
	public void insertOne(CommandContent commandContent);
	public void insertBatch(List<CommandContent> commandContent);
	public int countCommandContentNum();
	public void deleteByCommandID(String command_id);
}
