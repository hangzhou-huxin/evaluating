package cn.itcast.application.study.manage.escape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.itcast.application.study.manage.escape.dao.EscapeQuestionDao;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;
import cn.itcast.application.study.manage.escape.domain.EscapeResult;
import cn.itcast.application.study.manage.escape.dto.EscapeQuestionQuery;
import cn.itcast.application.study.manage.holland.domain.EvaluationResult;
import cn.itcast.application.study.manage.holland.dto.EvaluationResultQuery;
import cn.itcast.application.study.utils.PageResult;


@Service
public class EscapeQuestionService {
	
	@Autowired
	private EscapeQuestionDao escapeQuestionDao ;
	
	public void save(EscapeQuestion escapeQuestion){
		if(escapeQuestion.getId() != null){
			escapeQuestionDao.update(escapeQuestion);
		}else{
			escapeQuestionDao.add(escapeQuestion);
		}
	}
	
	
	public List<EscapeQuestion> findForList(Integer categoryId){
		return escapeQuestionDao.findForList(categoryId) ;
	}
	
	
	public void delete(Integer id){
		escapeQuestionDao.delete(id);
	}
	
	
	public List<EscapeQuestion> findForListWithOptions(Integer categoryId ){
		
		List<EscapeQuestion> list = escapeQuestionDao.findForPageList(categoryId) ;
		return list ;
	}
	
	
	public Integer findForPageListCount( Integer categoryId){
		return  escapeQuestionDao.findForPageListCount(categoryId) ;
	}
	
	
	
	


}
