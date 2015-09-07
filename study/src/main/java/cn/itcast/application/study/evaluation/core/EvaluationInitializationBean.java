package cn.itcast.application.study.evaluation.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.huxin.utils.redis.RedisOperationsDao ;

public class EvaluationInitializationBean implements InitializingBean,ApplicationContextAware{

	private ApplicationContext context ;
	public void afterPropertiesSet() throws Exception {
		EvaluationUtils.stringOperationDao = (RedisOperationsDao)context.getBean("stringOperations") ;
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context ;
	}

}
