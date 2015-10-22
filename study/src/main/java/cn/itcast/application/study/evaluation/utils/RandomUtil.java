package cn.itcast.application.study.evaluation.utils;

import java.util.Random;

public class RandomUtil {
	
	
	public static int getNumber(int max){
		
		int min=0;
        Random random = new Random();

        int number = random.nextInt(max)%(max-min+1) + min ;
        return number ;
	}

}
