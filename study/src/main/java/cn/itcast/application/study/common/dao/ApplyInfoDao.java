package cn.itcast.application.study.common.dao;

import cn.itcast.application.study.common.domain.ApplyInfo;
import cn.itcast.application.study.common.dto.ProcessInfoDto;

public interface ApplyInfoDao {
	
	public void add(ApplyInfo applyInfo) ;
	
	
	public void delete(Integer id) ;
	
	
	public ApplyInfo findById(Integer id) ;
	
	
	public void saveProcessInfo(ProcessInfoDto processInfoDto) ;

}
