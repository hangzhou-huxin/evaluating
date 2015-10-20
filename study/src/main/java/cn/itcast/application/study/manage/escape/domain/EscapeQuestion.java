package cn.itcast.application.study.manage.escape.domain;

import java.util.List;

public class EscapeQuestion {
	
	private Integer id ;
	
	private String	index ;
	
	private String	title ;
	
	private	String	content ;
	
	private Integer categoryId ;
	
	private Integer dimensionId ;
	
	private String	dimensionName ;
	
	private String  dimensionKey ;
	
	private List<EscapeQuestionOption> options; 
	

	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Integer dimensionId) {
		this.dimensionId = dimensionId;
	}

	public List<EscapeQuestionOption> getOptions() {
		return options;
	}

	public void setOptions(List<EscapeQuestionOption> options) {
		this.options = options;
	}

	public String getDimensionKey() {
		return dimensionKey;
	}

	public void setDimensionKey(String dimensionKey) {
		if(dimensionKey == null)
			this.dimensionKey = "";
		else{
			this.dimensionKey = dimensionKey ;
		}
	}

	
	
}
