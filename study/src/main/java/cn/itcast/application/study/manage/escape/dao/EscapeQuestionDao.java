package cn.itcast.application.study.manage.escape.dao;

import java.util.List;

import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;
import cn.itcast.application.study.manage.escape.dto.EscapeQuestionQuery;



public interface EscapeQuestionDao {

	public void add(EscapeQuestion escapeQuestion) ;
	
	
	public void update(EscapeQuestion escapeQuestion) ;
	
	
	public EscapeQuestion findById(Integer id) ;
	
	
	public List<EscapeQuestion> findForList(Integer categoryId) ;
	
	
	public void delete(Integer id) ;
	
	
	public List<EscapeQuestion> findForPageList(EscapeQuestionQuery query) ;
	
	
	public Integer findForPageListCount(Integer categoryId) ;
}
