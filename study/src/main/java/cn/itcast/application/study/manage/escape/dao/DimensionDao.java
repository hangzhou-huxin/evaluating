package cn.itcast.application.study.manage.escape.dao;

import java.util.List;

import cn.itcast.application.study.manage.escape.domain.Dimension;


public interface DimensionDao {
	
	public void add(Dimension dimension) ;
	
	
	public void update(Dimension dimension) ;
	
	
	public Dimension findById(Integer id) ;
	
	
	public List<Dimension> findForList(Integer categoryId) ;
	
	
	public void delete(Integer id) ;


}
