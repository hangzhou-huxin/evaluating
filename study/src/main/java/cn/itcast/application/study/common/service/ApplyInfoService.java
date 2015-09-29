package cn.itcast.application.study.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.application.study.common.dao.ApplyInfoDao;
import cn.itcast.application.study.common.domain.ApplyInfo;

@Service
public class ApplyInfoService {
	
	@Autowired
	private ApplyInfoDao applyInfoDao ;
	
	
	public void saveApplyInfo(ApplyInfo applyInfo){
		applyInfoDao.add(applyInfo);
	}

}
