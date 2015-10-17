package cn.itcast.application.study.manage.escape.dao;

import java.util.List;

import cn.itcast.application.study.manage.escape.domain.EscapeQuestionOption;



public interface EscapeQuestionOptionDao {
	
	
	public void add(EscapeQuestionOption escapeQuestionOption) ;
	
	
	public void update(EscapeQuestionOption escapeQuestionOption) ;
	
	
	public EscapeQuestionOption findById(Integer id) ;
	
	
	public List<EscapeQuestionOption> findForList(Integer questionId) ;
	
	
	public void delete(Integer id) ;

}
