package cn.itcast.application.study.manage.escape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.manage.escape.dao.EscapeQuestionDao;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;


@Service
public class EscapeQuestionService {
	
	@Autowired
	private EscapeQuestionDao escapeQuestionDao ;
	
	public void save(EscapeQuestion escapeQuestion){
		if(escapeQuestion.getId() != null){
			escapeQuestionDao.update(escapeQuestion);
		}else{
			escapeQuestionDao.add(escapeQuestion);
		}
	}
	
	
	public List<EscapeQuestion> findForList(){
		return escapeQuestionDao.findForList() ;
	}
	
	
	public void delete(Integer id){
		escapeQuestionDao.delete(id);
	}


}
