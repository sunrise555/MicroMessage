package com.sunny.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunny.service.MantainService;

/**
 * ����ɾ����Ӧ�Ĳ�����
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class DeleteOneServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ʹ��jdbc�������ݿ�
		//����ҳ��������ܲ���ΪUTF-8��ʽ
		req.setCharacterEncoding("UTF-8");
		//��ǰ�˻�ȡ����
		String id = req.getParameter("id");
		//����service�����ҵ�񷽷�
		MantainService mantainService = new MantainService();
		mantainService.deleteOne(id);
		//��ת����Ӧҳ��
		req.getRequestDispatcher("/List.action").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}