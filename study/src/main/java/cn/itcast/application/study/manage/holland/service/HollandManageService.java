package cn.itcast.application.study.manage.holland.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.manage.holland.dao.EvaluationResultDao;
import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.utils.Page;
import cn.itcast.application.study.utils.PageResult;

@Service
public class HollandManageService {
	
	@Autowired
	private  EvaluationResultDao evaluationResultDao ;
	
	public PageResult<EvaluationResult> findList(Integer start , Integer limit){
		Page page = new Page(start,limit) ;
		int totalCount = evaluationResultDao.findAllCount() ;
		List<EvaluationResult> list = evaluationResultDao.findAllForPage(page) ;
		PageResult<EvaluationResult> result = new PageResult<EvaluationResult>(list,totalCount) ;
		return result ; 
	}

}
