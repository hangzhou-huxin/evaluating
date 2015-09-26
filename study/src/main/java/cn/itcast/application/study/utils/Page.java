/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Page.java 59 2012-02-15 11:12:40Z huxin $
 */
package cn.itcast.application.study.utils;

import java.util.List;



/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 
 * 注意所有序号从1开始.
 * 
 * @param <T> Page中记录的类型.
 * 
 * @author calvin
 */
public class Page {
	
	//-- 分页参数 --//
	protected Integer pageNo = 0;
	protected Integer pageSize = -1;
	
	

	

	//-- 构造函数 --//
	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public Page(Integer pageNo, Integer pageSize) {
		
		if(pageSize == null || pageSize.intValue() == 0 ){
			this.pageSize = 20 ;
		}else{
			this.pageSize = pageSize;
		}
	}

	//-- 分页参数访问函数 --//
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;

		if (pageNo == null || pageNo < 0) {
			this.pageNo = 0;
		}
	}

	/**
	 * 返回Page对象自身的setPageNo函数,可用于连续设置。
	 */
	public Page pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * 获得每页的记录数量, 默认为-1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	
}

	


