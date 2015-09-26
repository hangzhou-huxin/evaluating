package cn.itcast.application.study.utils ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class PageResult<T>  {
	
	private List<T> result;

	

	private int totalCount = 0;

    

	public PageResult(List<T> result, int totalCount) {
		super();
		this.result = result;
		this.totalCount = totalCount;
	}



	public List<T> getResult() {
		return result;
	}



	public void setResult(List<T> result) {
		this.result = result;
	}



	public int getTotalCount() {
		return totalCount;
	}



	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


    
	
	
}
