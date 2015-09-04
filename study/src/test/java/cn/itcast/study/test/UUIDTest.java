package cn.itcast.study.test;

import java.util.UUID;

import org.junit.Test;

public class UUIDTest {
	
	@Test
	public void test(){
		
		UUID uuid = java.util.UUID.randomUUID() ;
		System.out.println(uuid.toString()) ;
	}

}
