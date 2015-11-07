package cn.itcast.application.study.evaluation.escape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.common.dao.ApplyInfoDao;
import cn.itcast.application.study.common.domain.ApplyInfo;
import cn.itcast.application.study.common.dto.ProcessInfoDto;
import cn.itcast.application.study.evaluation.escape.dao.EscapeApplyInfoDao;
import cn.itcast.application.study.evaluation.escape.domain.EscapeApplyInfo;
import cn.itcast.application.study.manage.escape.dto.ApplyInfoQuery;

@Service
public class EscapeApplyInfoService {
	
	@Autowired
	private EscapeApplyInfoDao applyInfoDao ;
	
	
	public void saveApplyInfo(EscapeApplyInfo applyInfo){
		applyInfoDao.add(applyInfo);
	}
	
	
	
	public List<EscapeApplyInfo> findByCategoryId(Integer categoryId , Integer start , Integer limit){
		ApplyInfoQuery query = new ApplyInfoQuery() ;
		query.setCategoryId(categoryId);
		query.setStart(start);
		query.setLimit(limit);
		
		return applyInfoDao.findByCategoryId(query) ;
	}
	
	
	public void saveProcessInfo(Integer applyId , String processInfo){
		ProcessInfoDto dto = new ProcessInfoDto() ;
		dto.setId(applyId);
		dto.setProcessInfo(processInfo);
		applyInfoDao.saveProcessInfo(dto);
	}

}
