package cn.itcast.application.study.manage.escape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.manage.escape.dao.DimensionDao;
import cn.itcast.application.study.manage.escape.domain.Dimension;

@Service
public class DimensionService {
	
	@Autowired
	private DimensionDao dimensionDao ;
	
	public void save(Dimension dimension){
		if(dimension.getId() != null){
			dimensionDao.update(dimension);
		}else{
			dimensionDao.add(dimension);
		}
	}
	
	
	public List<Dimension> findForList(Integer categoryId){
		return dimensionDao.findForList(categoryId) ;
	}
	
	
	public Dimension findById(Integer id){
		return dimensionDao.findById(id) ;
	}
	
	
	public void delete(Integer id){
		dimensionDao.delete(id);
	}
}
