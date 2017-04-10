package com.sunny.entity;
/**
 * ��ҳ��Ӧ��ʵ����
 * @author Administrator
 *
 */
public class Page {
	/**
	 * ��ǰ�б����ܵ���Ŀ��
	 */
	private int totalItemNum;
	
	/**
	 * ��ǰ��ʾ����ҳ��
	 */
	private int totalPageNum;
	
	/**
	 * ��ǰ��ʾ�ڼ�ҳ
	 */
	private int currentPageIndex;
	/**
	 * ÿҳ��ʾ����Ŀ��
	 */
	private int pageShowItemNum = 5;
	
	/**
	 * ���ݿ���limit�Ĳ�������ʾ�ӵڼ�����ʼȡ
	 */
	private int dbIndex;
	//mysql�в���limit���з�ҳ
	/**
	 * ���ݿ���limit�Ĳ�������ʾһ��ȡ�˶�����
	 */
	private int dbNum;
	
	/**
	 * ���ݵ�ǰ�����еĲ�������ֵ���������������ֵ
	 */
	public void count() {
		//������ҳ��
		int totalItemNumtemp = this.totalItemNum/this.pageShowItemNum;
		int plus  = (this.totalItemNum % this.pageShowItemNum) == 0 ? 0 : 1;
		totalItemNumtemp = totalItemNumtemp + plus;
		if(totalItemNumtemp < 1)
			totalItemNumtemp = 1;
		this.totalPageNum = totalItemNumtemp;
		
		//���õ�ǰҳ��
		//��ҳ��С�ڵ�ǰҳ��ʱ����ǰҳ��������ҳ��
		if(this.totalPageNum < this.currentPageIndex)
			this.currentPageIndex = this.totalPageNum;
		
		//��ǰҳ��С��1ʱ������Ϊ1
		if(this.currentPageIndex < 1)
			this.currentPageIndex = 1;
		
		//����limit����dbindex=0-4,5-9,....
		this.dbIndex = (this.currentPageIndex - 1) * this.pageShowItemNum;
		this.dbNum = this.pageShowItemNum;
		
		
	}

	public int getTotalItemNum() {
		return totalItemNum;
	}

	public void setTotalItemNum(int totalItemNum) {
		this.totalItemNum = totalItemNum;
		this.count();
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public int getPageShowItemNum() {
		return pageShowItemNum;
	}

	public void setPageShowItemNum(int pageShowItemNum) {
		this.pageShowItemNum = pageShowItemNum;
	}

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	public int getDbNum() {
		return dbNum;
	}

	public void setDbNum(int dbNum) {
		this.dbNum = dbNum;
	}
	
	
}
