package cn.itcast.application.study.manage.escape.dao;

import java.util.List;

import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;



public interface EscapeQuestionDao {

	public void add(EscapeQuestion escapeQuestion) ;
	
	
	public void update(EscapeQuestion escapeQuestion) ;
	
	
	public EscapeQuestion findById(Integer id) ;
	
	
	public List<EscapeQuestion> findForList() ;
	
	
	public void delete(Integer id) ;
}
