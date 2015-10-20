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
import cn.itcast.application.study.manage.escape.dto.EscapeQuestionQuery;

public class EscapeQuestionDaoTest {
	
	@Test
	public void testFindForList(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-escape-manage.xml"}) ;
		EscapeQuestionDao escapeQuestionDao = context.getBean(EscapeQuestionDao.class) ;
		Integer categoryId = 1 ;
		
		EscapeQuestionQuery query = new EscapeQuestionQuery() ;
		query.setCategoryId(categoryId);
		query.setStart(0);
		query.setLimit(10);
		List<EscapeQuestion> questionList = escapeQuestionDao.findForPageList(categoryId) ;
		for(EscapeQuestion  question: questionList){
			List optionList = question.getOptions() ;
			System.out.println("option:" + optionList.size());
		}
		
		//生成json串
		JsonGenerator jsonGenerator = null;
	    ObjectMapper objectMapper =  new ObjectMapper();
	    OutputStream out = null ;
	    String json = null ;
	    try {
	    	try {
	    		
	    		String jsonStr = "" ;
	    		out = new ByteArrayOutputStream() ;
	    		jsonGenerator = objectMapper.getFactory().createGenerator(out, JsonEncoding.UTF8) ;
	    		jsonGenerator.writeObject(questionList); 
	    		json = out.toString() ;
	    	}finally{
	        	if (jsonGenerator != null) {
	                jsonGenerator.flush();
	            }
	            if (!jsonGenerator.isClosed()) {
	                jsonGenerator.close();
	            }
	            if( out != null){
	            	out.close();
	            }
	            jsonGenerator = null;
	            objectMapper = null;
	           
	        }
        }catch(Exception e){
   		 
        } 
		System.out.println(json);
	}

}
