package cn.itcast.application.study.manage.holland.domain;

import org.apache.ibatis.type.Alias;

@Alias("evaluationResult")
public class EvaluationResult {
	
	private Integer id ;
	
	private String	evaluationId ;
	
	private String	name ;
	
	private String	content ;
	
	private String	type ;
	
	private String	qq	 ;
	
	private String	createDate ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(String evaluationId) {
		this.evaluationId = evaluationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
	

}
