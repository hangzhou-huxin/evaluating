package cn.itcast.application.study.manage.escape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.manage.escape.dao.DimensionDao;
import cn.itcast.application.study.manage.escape.dao.ScoreSectionDao;
import cn.itcast.application.study.manage.escape.domain.Dimension;
import cn.itcast.application.study.manage.escape.domain.ScoreSection;

@Service
public class ScoreSectionService {
	
	@Autowired
	private ScoreSectionDao scoreSectionDao ;
	
	public void save(ScoreSection scoreSection){
		if(scoreSection.getId() != null){
			scoreSectionDao.update(scoreSection);
		}else{
			scoreSectionDao.add(scoreSection);
		}
	}
	
	
	public List<ScoreSection> findForList(Integer categoryId){
		return scoreSectionDao.findForList(categoryId) ;
	}
	
	
	public ScoreSection findById(Integer id){
		return scoreSectionDao.findById(id) ;
	}
	
	
	public void delete(Integer id){
		scoreSectionDao.delete(id);
	}
}
