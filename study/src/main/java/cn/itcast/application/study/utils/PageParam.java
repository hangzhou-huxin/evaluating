package cn.itcast.application.study.utils;

import java.io.Serializable;

public class PageParam implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 页号码,页码从1开始
	 */
	private int pageNumber = 1;
	
	/**
	 * 分页大小
	 */
	private int pageSize = 10;
	
	/**
	 * 排序的多个列,如: username desc
	 */
	private String sortColumns;

	public PageParam() {
		this(1, 10);
	}

	public PageParam(int pageNumber, int pageSize) {
		this(pageNumber, pageSize, null);
	}

	public PageParam(int pageNumber, int pageSize, Object filters) {
		this(pageNumber, pageSize, filters, null);
	}

	public PageParam(int pageNumber, int pageSize, String sortColumns) {
		this(pageNumber, pageSize, null, sortColumns);
	}

	public PageParam(int pageNumber, int pageSize, Object filters,
			String sortColumns) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		// setFilters(filters);
		setSortColumns(sortColumns);
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortColumns() {
		return sortColumns;
	}

	/**
	 * 排序的列,可以同时多列,使用逗号分隔,如 username desc,age asc
	 * 
	 * @return
	 */
	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	/**
	 * 得到数据库的第一条记录号
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return PageUtils.getFirstResult(pageNumber, pageSize);
	}

}
