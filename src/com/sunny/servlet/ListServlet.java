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
 * �б�ҳ���ʼ������
 * @author sunny
 *
 */
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ʹ��jdbc�������ݿ�
		//����ҳ��������ܲ���ΪUTF-8��ʽ
		req.setCharacterEncoding("UTF-8");
		//��ǰ�˻�ȡ����
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		//��ȡ��ǰ�û���Ҫ��ת��ҳ��
		String currentPageIndex = req.getParameter("currentPageIndex");
		//������ҳ����
		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if(null == currentPageIndex || !pattern.matcher(currentPageIndex).matches()) {
			page.setCurrentPageIndex(1);
		} else {
			page.setCurrentPageIndex(Integer.valueOf(currentPageIndex));
		}
		//����ȡ���ݱ��浽req�У���ǰ��${}����
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		req.setAttribute("page", page);
		//����service�����ҵ�񷽷�
		QueryService listService = new QueryService();
		req.setAttribute("commandList", listService.queryCommandList(null, command, description));
		req.setAttribute("messageList", listService.queryMessageListByPage(command, description, page));
		//��ת����Ӧҳ��
		req.getRequestDispatcher("/WEB-INF/jsps/back/ListShow.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
