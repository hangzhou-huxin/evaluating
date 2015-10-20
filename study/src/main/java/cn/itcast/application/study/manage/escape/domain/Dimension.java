package cn.itcast.application.study.manage.escape.domain;

/**
 * 维度
 * @author Administrator
 *
 */
public class Dimension {
	
	private Integer id ;
	
	
	private String	name ;
	
	
	private Integer categoryId ;
	
	
	private String  key ;
	
	
	private Integer scoreValue ;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public Integer getScoreValue() {
		return scoreValue;
	}


	public void setScoreValue(Integer scoreValue) {
		this.scoreValue = scoreValue;
	}
	
	
	
	

}
