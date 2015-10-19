package cn.itcast.application.study.manage.escape.dto;

public class EscapeQuestionQuery {
	
	
	private Integer categoryId ;
	
	
	private Integer start ;
	
	
	private Integer limit ;


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public Integer getStart() {
		return start;
	}


	public void setStart(Integer start) {
		this.start = start;
	}


	public Integer getLimit() {
		return limit;
	}


	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	

}
