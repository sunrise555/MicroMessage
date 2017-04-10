package com.sunny.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunny.service.MantainService;

@SuppressWarnings("serial")
public class DeleteBatchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ʹ��jdbc�������ݿ�
		//����ҳ��������ܲ���ΪUTF-8��ʽ
		req.setCharacterEncoding("UTF-8");
		//��ǰ�˻�ȡ����
		//����checkBox����ʱ��ֻ����յ�ѡ�п��value
		String[] deleteList = req.getParameterValues("operateID");		
		//����service�����ҵ�񷽷�
		MantainService mantainService = new MantainService();
		mantainService.deleteBatch(deleteList);
		//��ת����Ӧҳ��
		req.getRequestDispatcher("/List.action").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}