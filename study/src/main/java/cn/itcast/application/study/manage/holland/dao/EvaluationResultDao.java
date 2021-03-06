package cn.itcast.application.study.manage.holland.dao;

import java.util.List;

import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.manage.holland.dto.EvaluationResultQuery;
import cn.itcast.application.study.utils.Page;

public interface EvaluationResultDao {
	
	
	public void add(EvaluationResult result) ;
	
	public List<EvaluationResult>	findQueryForPage(EvaluationResultQuery query) ;
	
	public Integer findQueryCount(EvaluationResultQuery query) ;
	
	
	public EvaluationResult findByEvalId(String evalId) ;
	
	
	public void delete(Integer id) ;
	
	
	public List<EvaluationResult>	findForApply() ;
	

}
