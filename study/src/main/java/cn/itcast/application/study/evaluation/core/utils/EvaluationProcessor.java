package cn.itcast.application.study.evaluation.core.utils;

import cn.itcast.application.study.evaluation.core.entity.EvaluationReport;
import cn.itcast.application.study.evaluation.core.entity.EvaluationResult;
import cn.itcast.application.study.evaluation.core.entity.EvaluationStrategy;

/**
 * 评估处理器
 * @author Administrator
 *
 */
public interface EvaluationProcessor {
	
	/**
	 * 根据评估结果和评估策略，得到评估报告
	 * @param evaluationResult
	 * @param strategy
	 * @return
	 */
	public EvaluationReport parse(EvaluationResult evaluationResult , EvaluationStrategy strategy) ;

}
