package cn.itcast.application.study.evaluation.utils;

import org.junit.Test;

public class NumberUtilTest {
	
	
	@Test
	public void test(){
		for(int i=0;i<100;i++){
			int num = RandomUtil.getNumber(10) ;
			System.out.println(num);
		}
		
	}

}
