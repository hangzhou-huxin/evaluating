package cn.itcast.application.study.evaluation.core.entity;

/**
 * 评估项（评估维度）
 * @author Administrator
 *
 */
public class EvaluationItem {
	
	
	private String prefix ;
	
	private String name ;
	
	private Integer value ;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	

}
