package cn.itcast.application.study.manage.escape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.manage.escape.dao.DimensionDao;
import cn.itcast.application.study.manage.escape.dao.TemplateDao;
import cn.itcast.application.study.manage.escape.domain.Dimension;
import cn.itcast.application.study.manage.escape.domain.Template;

@Service
public class TemplateService {
	
	@Autowired
	private TemplateDao templateDao ;
	
	public void save(Template template){
		if(template.getId() != null){
			templateDao.update(template);
		}else{
			templateDao.add(template);
		}
	}
	
	
	public List<Template> findForList(Integer scoreSectionId){
		return templateDao.findForList(scoreSectionId) ;
	}
	
	
	public Template findById(Integer id){
		return templateDao.findById(id) ;
	}
	
	
	public void delete(Integer id){
		templateDao.delete(id);
	}
}
