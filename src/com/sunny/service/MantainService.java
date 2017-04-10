package com.sunny.service;

import java.util.ArrayList;
import java.util.List;

import com.sunny.DAO.CommandDao;
import com.sunny.DAO.MessageDao;

/**
 * ����ά�����ݿ��ҵ���
 * @author Administrator
 *
 */
public class MantainService {
	/**
	 * ����ɾ������
	 * @param id
	 */
	public void deleteOne(String id) {
		if(null!=id && !"".equals(id.trim())) {
			MessageDao listDao = new MessageDao();
			listDao.deleteOne(Integer.valueOf(id));	
			CommandDao commandDao = new CommandDao();
			commandDao.deleteOne(Integer.valueOf(id));
		}
	}
	
	public void deleteBatch(String[] list) {
		if(null!=list && list.length!=0) {
			MessageDao listDao = new MessageDao();
			List<Integer> tempList = new ArrayList<Integer>();
			for (String s : list) {
				tempList.add(Integer.valueOf(s));
			}
			listDao.deleteBatch(tempList);	
		}
	}
}
