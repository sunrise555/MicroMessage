package com.sunny.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunny.entity.Page;
import com.sunny.service.QueryService;


/**
 * 列表页面初始化控制
 * @author sunny
 *
 */
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//使用jdbc连接数据库
		//设置页面输入接受参数为UTF-8格式
		req.setCharacterEncoding("UTF-8");
		//从前端获取数据
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		//获取当前用户需要跳转的页数
		String currentPageIndex = req.getParameter("currentPageIndex");
		//创建分页对象
		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if(null == currentPageIndex || !pattern.matcher(currentPageIndex).matches()) {
			page.setCurrentPageIndex(1);
		} else {
			page.setCurrentPageIndex(Integer.valueOf(currentPageIndex));
		}
		//将获取数据保存到req中，供前端${}调用
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		//调用service里面的业务方法
		QueryService listService = new QueryService();
		req.setAttribute("commandList", listService.queryCommandList(null, command, description));
		req.setAttribute("messageList", listService.queryMessageListByPage(command, description, page));
		//跳转到相应页面
		req.getRequestDispatcher("/WEB-INF/jsps/back/ListShow.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
