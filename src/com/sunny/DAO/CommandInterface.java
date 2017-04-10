package com.sunny.DAO;

import java.util.List;

import com.sunny.bean.Command;

/**
 * 指令表对应的接口
 * @author sunny_hbqq
 *
 */
public interface CommandInterface {
	public void insertOne(Command command);
	public List<Command> queryCommandList(Command command);
	public int countCommandNum();
	public Command getCommandByName(String name);
	public void deleteOne(int id);
	public void updateOne(Command command);
}
