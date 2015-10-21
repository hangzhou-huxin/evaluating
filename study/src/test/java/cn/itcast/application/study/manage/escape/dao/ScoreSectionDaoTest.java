package cn.itcast.application.study.manage.escape.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itcast.application.study.manage.escape.domain.EscapeQuestion;
import cn.itcast.application.study.manage.escape.domain.ScoreSection;
import cn.itcast.application.study.manage.escape.dto.EscapeQuestionQuery;

public class ScoreSectionDaoTest {
	
	@Test
	public void testAdd(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-escape-manage.xml"}) ;
		ScoreSectionDao scoreSectionDao = context.getBean(ScoreSectionDao.class) ;
		Integer categoryId = 1 ;
		ScoreSection scoreSection = new ScoreSection() ;
		scoreSection.setCaption("分值段1");
		scoreSection.setLowerValue(10);
		scoreSection.setUpperValue(100);
		
		scoreSectionDao.add(scoreSection);
		
		
	}

}
