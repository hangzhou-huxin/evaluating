package cn.itcast.application.study.manage.holland.dto;

public class EvaluationResultQuery {
	
	private String startdt ;
	
	private String evalId ;
	
	private String enddt  ;
	
	private String qq ;
	
	private Boolean apply ;
	
	private Boolean process ;
	
	private String name ;
	
	private Integer start ;
	
	private Integer limit ;

	public String getStartdt() {
		return startdt;
	}

	public void setStartdt(String startdt) {
		this.startdt = startdt;
	}

	public String getEvalId() {
		return evalId;
	}

	public void setEvalId(String evalId) {
		this.evalId = evalId;
	}

	public String getEnddt() {
		return enddt;
	}

	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Boolean getApply() {
		return apply;
	}

	public void setApply(Boolean apply) {
		this.apply = apply;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	

	public Boolean getProcess() {
		return process;
	}

	public void setProcess(Boolean process) {
		this.process = process;
	}

	@Override
	public String toString() {
		return "EvaluationResultQuery [startdt=" + startdt + ", evalId="
				+ evalId + ", enddt=" + enddt + ", qq=" + qq + ", apply="
				+ apply + ", name=" + name + "]";
	}
	
	
	

}
