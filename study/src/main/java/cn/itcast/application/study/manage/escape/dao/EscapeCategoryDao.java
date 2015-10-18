package cn.itcast.application.study.manage.escape.dao;

import java.util.List;

import cn.itcast.application.study.manage.escape.domain.EscapeCategory;

public interface EscapeCategoryDao {
	
	
	public void add(EscapeCategory escapeCategory) ;
	
	
	public void update(EscapeCategory escapeCategory) ;
	
	
	public EscapeCategory findById(Integer id) ;
	
	
	public List<EscapeCategory> findForList() ;
	
	
	public void delete(Integer id) ;

}
