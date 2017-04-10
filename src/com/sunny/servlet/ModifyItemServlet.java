package com.sunny.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunny.service.QueryService;

/**
 * 修改页面对应的servlet
 * @author Administrator
 *
 */
public class ModifyItemServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//获取选中item的ID
		String id = req.getParameter("id");
		String command = req.getParameter("newCommand");
		String description = req.getParameter("newDescription");
		String[] contents = req.getParameterValues("newContents");
		QueryService listService = new QueryService();
		listService.modifyItem(id, command, description, contents);
		req.getRequestDispatcher("/List.action").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
