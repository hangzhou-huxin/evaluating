package cn.itcast.application.study.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.common.dao.ApplyInfoDao;
import cn.itcast.application.study.common.domain.ApplyInfo;
import cn.itcast.application.study.common.dto.ProcessInfoDto;

@Service
public class ApplyInfoService {
	
	@Autowired
	private ApplyInfoDao applyInfoDao ;
	
	
	public void saveApplyInfo(ApplyInfo applyInfo){
		applyInfoDao.add(applyInfo);
	}
	
	
	
	public ApplyInfo findById(Integer id){
		return applyInfoDao.findById(id) ;
	}
	
	
	public void saveProcessInfo(Integer applyId , String processInfo){
		ProcessInfoDto dto = new ProcessInfoDto() ;
		dto.setId(applyId);
		dto.setProcessInfo(processInfo);
		applyInfoDao.saveProcessInfo(dto);
	}

}
