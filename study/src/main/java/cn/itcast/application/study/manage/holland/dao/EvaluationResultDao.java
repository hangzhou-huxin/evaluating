package cn.itcast.application.study.manage.holland.dao;

import java.util.List;

import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.utils.Page;

public interface EvaluationResultDao {
	
	
	public void add(EvaluationResult result) ;
	
	public List<EvaluationResult>	findAllForPage(Page page) ;
	
	public Integer findAllCount() ;
	

}
