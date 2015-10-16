package cn.itcast.application.study.manage.escape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.manage.escape.dao.EscapeQuestionOptionDao;
import cn.itcast.application.study.manage.escape.domain.EscapeQuestionOption;


@Service
public class EscapeQuestionOptionService {

	@Autowired
	private EscapeQuestionOptionDao escapeQuestionOptionDao ;
	
	public void save(EscapeQuestionOption escapeQuestionOption){
		if(escapeQuestionOption.getId() != null){
			escapeQuestionOptionDao.update(escapeQuestionOption);
		}else{
			escapeQuestionOptionDao.add(escapeQuestionOption);
		}
	}
	
	
	public List<EscapeQuestionOption> findForList(){
		return escapeQuestionOptionDao.findForList() ;
	}
}
