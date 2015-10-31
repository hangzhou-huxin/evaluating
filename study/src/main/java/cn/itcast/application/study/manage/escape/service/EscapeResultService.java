package cn.itcast.application.study.manage.escape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import cn.itcast.application.study.manage.escape.dao.DimensionDao;
import cn.itcast.application.study.manage.escape.dao.EscapeResultDao;
import cn.itcast.application.study.manage.escape.domain.Dimension;
import cn.itcast.application.study.manage.escape.domain.EscapeResult;
import cn.itcast.application.study.manage.escape.dto.EscapeResultQuery;
import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.utils.PageResult;

@Service
public class EscapeResultService {
	
	@Autowired
	private EscapeResultDao escapeResultDao ;
	
	public void save(EscapeResult escapeResult){
		
		escapeResultDao.add(escapeResult);
	}
	
	
	public List<EscapeResult> findForList(Integer categoryId){
		return escapeResultDao.findForList(categoryId) ;
	}
	
	
	public EscapeResult findById(Integer id){
		return escapeResultDao.findById(id) ;
	}
	
	
	public EscapeResult findByEvalId(String evalId){
		return escapeResultDao.findByEvalId(evalId) ;
	}
	
	
	
	public void delete(Integer id){
		escapeResultDao.delete(id);
	}
	
	public PageResult<EscapeResult> findPageList(EscapeResultQuery query){
		//Page page = new Page(start,limit) ;
		int totalCount = escapeResultDao.findForPageListCount(query) ;
		List<EscapeResult> list = escapeResultDao.findForPageList(query) ;
		PageResult<EscapeResult> result = new PageResult<EscapeResult>(list,totalCount) ;
		return result ; 
	}
	
}
