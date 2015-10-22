package cn.itcast.application.study.manage.escape.dao;

import java.util.List;

import cn.itcast.application.study.manage.escape.domain.Dimension;
import cn.itcast.application.study.manage.escape.domain.Template;


public interface TemplateDao {
	
	public void add(Template template) ;
	
	
	public void update(Template template) ;
	
	
	public Template findById(Integer id) ;
	
	
	public List<Template> findForList(Integer scoreSectionId) ;
	
	
	public void delete(Integer id) ;


}
