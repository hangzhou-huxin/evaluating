package cn.itcast.application.study.manage.escape.dao;

import java.util.List;

import cn.itcast.application.study.manage.escape.domain.ScoreSection;


public interface ScoreSectionDao {
	
    public void add(ScoreSection scoreSection) ;
	
	
	public void update(ScoreSection scoreSection) ;
	
	
	public ScoreSection findById(Integer id) ;
	
	
	public List<ScoreSection> findForList(Integer categoryId) ;
	
	
	public void delete(Integer id) ;

}
