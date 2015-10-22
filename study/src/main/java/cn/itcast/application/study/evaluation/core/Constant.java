package cn.itcast.application.study.evaluation.core;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	public final static String EVALUATION_ID_PARAM_NAME = "evId" ;
	
	public final static Long EXPIRE_MINUTES = 40l ;
	
	public final static String PREVIOUS_STEP_PARAM_NAME = "previous" ;
	
	public final static String NEXT_STEP_PARAM_NAME = "next" ;
	
	public final static String USER_NAME_PARAM_NAME = "username" ;
	
	public final static String QQ_PARAM_NAME	= "qq" ;
	
	public final static Map<Integer,String> educationMap = new HashMap<Integer,String>(){
		{
            put(1, "初中");
            put(2, "高中");
            put(3, "中专");
            put(4, "大专");
            put(5, "本科");
            put(6, "硕士");
            put(7, "博士");
        }
	};  
	
	
	public final static Map<String,String> commonFeatureMap = new HashMap<String,String>(){
		{
			put("r","愿意使用工具从事操作性工作，动手能力强，做事手脚灵活，动作协调。偏好于具体任务，不善言辞，做事保守，较为谦虚。缺乏社交能力，通常喜欢独立做事。") ;
			put("a","喜欢以各种艺术形式的创作来表现自己的才能，实现自身的价值；具有特殊艺术才能和个性。") ;
			put("i","思想家而非实干家，抽象思维能力强，求知欲强，肯动脑，善思考，不愿动手。喜欢独立的和富有创造性的工作。知识渊博，有学识才能，不善于领导他人。考虑问题理性，做事喜欢精确，喜欢逻辑分析和推理，不断探讨未知的领域。") ;
			put("s","喜欢与人交往、不断结交新的朋友、善言谈、愿意教导别人。关心社会问题、渴望发挥自己的社会作用。寻求广泛的人际关系，比较看重社会义务和社会道德。") ;
			put("e","追求权力、权威和物质财富，具有领导才能。喜欢竞争、敢冒风险、有野心/抱负。为人务实，习惯以利益得失、权利、地位、金钱等来衡量做事的价值，做事有较强的目的性。") ;
			put("c","尊重权威和规章制度，喜欢按计划办事，细心、有条理，习惯接受他人的指挥和领导，自己不谋求领导职务。喜欢关注实际和细节情况，通常较为谨慎和保守，缺乏创造性，不喜欢冒险和竞争，富有自我牺牲精神。") ;
		}
	} ;
	
	public final static Map<String,String> characterFeatureMap = new HashMap<String,String>(){
		{
			put("r","感觉迟钝、不讲究、谦逊的。踏实稳重、诚实可靠。") ;
			put("a","乐于创造新颖的、与众不同的艺术成果，渴望表现自己的个性") ;
			put("i","坚持性强，有韧性，喜欢钻研。为人好奇，独立性强。注：工作中调研兴趣强的人做事较为坚持，有韧性，善始善终。") ;
			put("s","为人友好、热情、善解人意、乐于助人。") ;
			put("e","善辩、精力旺盛、独断、乐观、自信、好交际、机敏、有支配愿望。") ;
			put("c","有责任心、依赖性强、高效率、稳重踏实、细致、有耐心。") ;
		}
	} ;
	
	
	public final static String ESCAPE_PREFIX = "es_" ;
	
	
	public final static String ESCAPE_TOTAL_SCORE = "__escape_total_score__" ;
	
	
	public final static String[] TEMPLATE_VARS = new String[]{"totalScore" } ;

}
