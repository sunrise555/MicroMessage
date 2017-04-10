package com.sunny.entity;
/**
 * 分页对应的实体类
 * @author Administrator
 *
 */
public class Page {
	/**
	 * 当前列表类总的条目数
	 */
	private int totalItemNum;
	
	/**
	 * 当前显示的总页数
	 */
	private int totalPageNum;
	
	/**
	 * 当前显示第几页
	 */
	private int currentPageIndex;
	/**
	 * 每页显示的条目数
	 */
	private int pageShowItemNum = 5;
	
	/**
	 * 数据库中limit的参数，表示从第几条开始取
	 */
	private int dbIndex;
	//mysql中采用limit进行分页
	/**
	 * 数据苦衷limit的参数，表示一共取了多少条
	 */
	private int dbNum;
	
	/**
	 * 根据当前对象中的部分属性值计算其他相关属性值
	 */
	public void count() {
		//计算总页数
		int totalItemNumtemp = this.totalItemNum/this.pageShowItemNum;
		int plus  = (this.totalItemNum % this.pageShowItemNum) == 0 ? 0 : 1;
		totalItemNumtemp = totalItemNumtemp + plus;
		if(totalItemNumtemp < 1)
			totalItemNumtemp = 1;
		this.totalPageNum = totalItemNumtemp;
		
		//设置当前页数
		//总页数小于当前页数时，当前页数等于总页数
		if(this.totalPageNum < this.currentPageIndex)
			this.currentPageIndex = this.totalPageNum;
		
		//当前页数小于1时，设置为1
		if(this.currentPageIndex < 1)
			this.currentPageIndex = 1;
		
		//设置limit参数dbindex=0-4,5-9,....
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
