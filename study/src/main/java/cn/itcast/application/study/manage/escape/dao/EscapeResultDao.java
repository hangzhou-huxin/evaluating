package cn.itcast.application.study.manage.escape.dao;

import java.util.List;

import cn.itcast.application.study.manage.escape.domain.Dimension;
import cn.itcast.application.study.manage.escape.domain.EscapeResult;
import cn.itcast.application.study.manage.escape.dto.EscapeResultQuery;


public interface EscapeResultDao {
	
	public void add(EscapeResult escapeResult) ;
	
	
	//public void update(EscapeResult escapeResult) ;
	
	
	public EscapeResult findById(Integer id) ;
	
	
	public List<EscapeResult> findForList(Integer categoryId) ;
	
	
	public void delete(Integer id) ;
	
	
	public List<EscapeResult> findForPageList(EscapeResultQuery query) ;
	
	
	public Integer findForPageListCount(EscapeResultQuery query) ;


}
