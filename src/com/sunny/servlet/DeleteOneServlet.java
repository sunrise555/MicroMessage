package com.sunny.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunny.service.MantainService;

/**
 * 单条删除对应的操作层
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class DeleteOneServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//使用jdbc连接数据库
		//设置页面输入接受参数为UTF-8格式
		req.setCharacterEncoding("UTF-8");
		//从前端获取数据
		String id = req.getParameter("id");
		//调用service里面的业务方法
		MantainService mantainService = new MantainService();
		mantainService.deleteOne(id);
		//跳转到相应页面
		req.getRequestDispatcher("/List.action").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}