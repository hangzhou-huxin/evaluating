package cn.itcast.application.study.manage.escape.domain;

/**
 * 分值段
 * @author Administrator
 *
 */
public class ScoreSection {
	
	private Integer id ;
	
	
	private Integer upperValue ;
	
	
	private Integer lowerValue ;
	
	
	private String	caption ;
	
	
	private String	description ;
	
	
	private Integer categoryId ;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUpperValue() {
		return upperValue;
	}


	public void setUpperValue(Integer upperValue) {
		this.upperValue = upperValue;
	}


	public Integer getLowerValue() {
		return lowerValue;
	}


	public void setLowerValue(Integer lowerValue) {
		this.lowerValue = lowerValue;
	}


	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	
	
	
	

}
