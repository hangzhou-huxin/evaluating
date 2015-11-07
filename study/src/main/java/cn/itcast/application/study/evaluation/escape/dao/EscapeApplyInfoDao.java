package cn.itcast.application.study.evaluation.escape.dao;

import java.util.List;

import cn.itcast.application.study.common.domain.ApplyInfo;
import cn.itcast.application.study.common.dto.ProcessInfoDto;
import cn.itcast.application.study.evaluation.escape.domain.EscapeApplyInfo;
import cn.itcast.application.study.manage.escape.dto.ApplyInfoQuery;

public interface EscapeApplyInfoDao {
	
	public void add(EscapeApplyInfo applyInfo) ;
	
	
	public void delete(Integer id) ;
	
	
	public List<EscapeApplyInfo> findByCategoryId(ApplyInfoQuery query) ;
	
	
	public void saveProcessInfo(ProcessInfoDto processInfoDto) ;

}
