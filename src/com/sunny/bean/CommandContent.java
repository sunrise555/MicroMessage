package com.sunny.bean;
/**
 * ��ָ�����ݱ��Ӧ��ʵ����
 * @author sunny
 *
 */
public class CommandContent {
	/**
	 * ָ�����ݵ�����
	 */
	private String id;
	/**
	 * ָ������
	 */
	private String content;
	/**
	 * ָ��ID
	 */
	private String command_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommand_id() {
		return command_id;
	}
	public void setCommand_id(String command_id) {
		this.command_id = command_id;
	}
	
}
