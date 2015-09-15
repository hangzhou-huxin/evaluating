package cn.itcast.study.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.junit.Test;

public class OrderedMapTest {
	
	@Test
	public void test(){

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("d", 2);
		map.put("c", 1);
		map.put("b", 1);
		map.put("a", 3);

		List<Map.Entry<String, Integer>> infoIds =
		    new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

		//排序前
		for (int i = 0; i < infoIds.size(); i++) {
		    String id = infoIds.get(i).toString();
		    //System.out.println(id);
		}
		//d 2
		//c 1
		//b 1
		//a 3

		//排序
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        //return (o2.getValue() - o1.getValue()); 
		        return (o1.getValue()).compareTo(o2.getValue());
		    }
		}); 

		//排序后
		for (int i = 0; i < infoIds.size(); i++) {
		    String id = infoIds.get(i).getKey();
		    System.out.println(id);
		}
	}
	
	@Test
	public void test2(){
		StringBuilder orderedResult = new StringBuilder() ;
		Map<String,Integer> map = new HashMap<String,Integer>() ;
		map.put("R", 34) ;
		map.put("E", 34) ;
		map.put("S", 342) ;
		map.put("C", 89) ;
		map.put("I", 34) ;
		map.put("A", 78) ;
		List<Entry<String,Integer>> list = new ArrayList<Entry<String,Integer>>(map.entrySet()) ;
		
		Collections.sort(list, new Comparator<Entry<String,Integer>>() {   
		    public int compare(Entry<String,Integer> o1, Entry<String,Integer> o2) {      
		        //return (o2.getValue() - o1.getValue());
		    	if( o2.getValue() - o1.getValue() != 0){
		    		return o2.getValue().intValue() - o1.getValue().intValue() ;
		    	}else{
		    		return o2.getKey().compareTo(o1.getKey())  ;
		    	}
		        
		    }
		});
		
		//排序后
		for (int i = 0; i < list.size(); i++) {
		    String id = list.get(i).getKey();
		    System.out.println(id);
		}
	}

}
