package com.sunny.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunny.service.QueryService;
/**
 * 新增页面对应的servlet
 * @author Administrator
 *
 */
public class AddNewItemServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String command = req.getParameter("newCommand");
		String description = req.getParameter("newDescription");
		String[] contents = req.getParameterValues("newContents");
		//调用service里面的业务方法
		QueryService listService = new QueryService();
		listService.insertBatchByMybatis(command, description, contents);
		req.getRequestDispatcher("/List.action").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
