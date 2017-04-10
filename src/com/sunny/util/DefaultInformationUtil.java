package com.sunny.util;
/**
 * 定义默认处理信息的工具类
 * @author Administrator
 *
 */
public interface DefaultInformationUtil {
	/**
	 * 当指令没有匹配的自动回复内容时，用此内容代替。
	 */
	public static final String NO_MATCHING_CONTENT = "客官，你没按套路出牌……我听不懂你在说什么哎！";
	public static final String HELP_COMMAND = "帮助";
	public static final String NO_MATCHING_ID = "客官，请输入正确的数字哟";
	public static final String HOME_PAGE = "99";
	public static final String WELCOME_PAGE = "hello，想知道点什么吗？"+"<br/>回复[帮助]获取所有查询主题";
}
