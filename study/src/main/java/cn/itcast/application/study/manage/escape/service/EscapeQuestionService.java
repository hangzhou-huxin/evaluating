package cn.itcast.application.study.manage.escape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.manage.escape.dao.EscapeQuestionDao;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;
import cn.itcast.application.study.manage.escape.dto.EscapeQuestionQuery;


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
	
	
	public List<EscapeQuestion> findForPageList(Integer categoryId , Integer pageNo){
		Integer pageSize = 10 ;
		Integer start = (pageNo-1) * pageSize ;
		EscapeQuestionQuery query = new EscapeQuestionQuery() ;
		
		query.setCategoryId(categoryId);
		query.setStart(start);
		query.setLimit(pageSize);
		
		List<EscapeQuestion> list = escapeQuestionDao.findForPageList(query) ;
		return list ;
	}
	
	
	public Integer findForPageListCount( Integer categoryId){
		return  escapeQuestionDao.findForPageListCount(categoryId) ;
	}
	
	


}
