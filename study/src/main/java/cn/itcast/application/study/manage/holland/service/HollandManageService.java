package cn.itcast.application.study.manage.holland.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.manage.holland.dao.EvaluationResultDao;
import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.manage.holland.dto.EvaluationResultQuery;
import cn.itcast.application.study.utils.Page;
import cn.itcast.application.study.utils.PageResult;

@Service
public class HollandManageService {
	
	@Autowired
	private  EvaluationResultDao evaluationResultDao ;
	
	public PageResult<EvaluationResult> findList(EvaluationResultQuery query){
		//Page page = new Page(start,limit) ;
		int totalCount = evaluationResultDao.findQueryCount(query) ;
		List<EvaluationResult> list = evaluationResultDao.findQueryForPage(query) ;
		PageResult<EvaluationResult> result = new PageResult<EvaluationResult>(list,totalCount) ;
		return result ; 
	}
	
	
	public List<EvaluationResult> findListForApply(){
		return evaluationResultDao.findForApply() ;
	}
	
	
	
	
	public void saveResult( EvaluationResult result ){
		evaluationResultDao.add(result);
	}
	
	public EvaluationResult findByEvalId( String evalId	){
		return evaluationResultDao.findByEvalId(evalId) ;
	}
	
	public void deleteResult( Integer id){
		evaluationResultDao.delete(id);
	}

}
