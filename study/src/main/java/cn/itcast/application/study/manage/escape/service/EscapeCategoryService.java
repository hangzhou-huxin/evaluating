package cn.itcast.application.study.manage.escape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.manage.escape.dao.EscapeCategoryDao;
import cn.itcast.application.study.manage.escape.domain.EscapeCategory;

@Service
public class EscapeCategoryService {
	
	@Autowired
	private EscapeCategoryDao escapeCategoryDao ;
	
	public void save(EscapeCategory escapeCategory){
		if(escapeCategory.getId() != null){
			escapeCategoryDao.update(escapeCategory);
		}else{
			escapeCategoryDao.add(escapeCategory);
		}
	}
	
	
	public List<EscapeCategory> findForList(){
		return escapeCategoryDao.findForList() ;
	}
	
	
	public EscapeCategory findById(Integer id){
		return escapeCategoryDao.findById(id) ;
	}
	
	
	public void delete(Integer id){
		escapeCategoryDao.delete(id);
	}

}
