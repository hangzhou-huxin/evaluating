package cn.itcast.application.study.common.domain;

import org.apache.ibatis.type.Alias;

@Alias("applyInfo")
public class ApplyInfo {
	
	private Integer id ;
	
	private String	evId ;
	
	private String	name ;
	
	private String	qq ;
	
	private String	career ;
	
	private String	schoolYear ;
	
	private String	profession ;
	
	private Integer educationId ;
	
	private String	educationName ;
	
	private String	memo ;
	
	
	private String  ip ;

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

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	
	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	

	public Integer getEducationId() {
		return educationId;
	}

	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	

	public String getEvId() {
		return evId;
	}

	public void setEvId(String evId) {
		this.evId = evId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}
