package cn.itcast.application.study.manage.holland.domain;

import org.apache.ibatis.type.Alias;

@Alias("evaluationResult")
public class EvaluationResult {
	
	private Integer id ;
	
	private String	evaluationId ;
	
	private String	name ;
	
	private String	content ;
	
	private String	talentsType ;
	
	private String	qq	 ;
	
	private Integer rValue ;
	
	private Integer sValue ;
	
	private Integer iValue ;
	
	private Integer eValue ;
	
	private Integer aValue ;
	
	private Integer cValue ;
	
	private String ip ;
	
	
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

	

	public String getTalentsType() {
		return talentsType;
	}

	public void setTalentsType(String talentsType) {
		this.talentsType = talentsType;
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

	public Integer getrValue() {
		return rValue;
	}

	public void setrValue(Integer rValue) {
		this.rValue = rValue;
	}

	public Integer getsValue() {
		return sValue;
	}

	public void setsValue(Integer sValue) {
		this.sValue = sValue;
	}

	public Integer getiValue() {
		return iValue;
	}

	public void setiValue(Integer iValue) {
		this.iValue = iValue;
	}

	public Integer geteValue() {
		return eValue;
	}

	public void seteValue(Integer eValue) {
		this.eValue = eValue;
	}

	public Integer getaValue() {
		return aValue;
	}

	public void setaValue(Integer aValue) {
		this.aValue = aValue;
	}

	public Integer getcValue() {
		return cValue;
	}

	public void setcValue(Integer cValue) {
		this.cValue = cValue;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	

}
