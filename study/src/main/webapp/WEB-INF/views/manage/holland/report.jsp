<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">

<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/highcharts/highcharts.js"></script>
<script src="<%=request.getContextPath()%>/highcharts/highcharts-more.js"></script>
<script src="<%=request.getContextPath()%>/highcharts/modules/exporting.js"></script>
<script type="text/javascript">
$(document).ready(function(){

    $('#container').highcharts({

        chart: {
            polar: true,
            type: 'line'
        },

        title: {
            text: ' ',
            x: -100
        },

        pane: {
            size: '90%'
        },

        xAxis: {
            categories: ['R:实际型', 'I:调查型', 'A:艺术型', 'S:社会型', 'E:事业型', 'C:常规型'],
            tickmarkPlacement: 'on',
            lineWidth: 0
        },

        yAxis: {
            gridLineInterpolation: 'polygon',
            lineWidth: 0,
            min: 0
        },

        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '得分',
            data: [${result['r']}, ${result['i']}, ${result['a']}, ${result['s']}, ${result['e']}, ${result['c']}],
            pointPlacement: 'on'
        }]

    });
});
</script>
</head>
<body>
   <h2 id="overview" class="page-header">测评报告－前言</h2>
   <p class="lead" style="font-size:14px;">人们通常倾向选择与自我兴趣类型匹配的职业环境，如具有现实型兴趣的人希望在现实型的职业环境中工作，可以最好地发挥个人的潜能。人们通常倾向选择与自我兴趣类型匹配的职业环境，如具有现实型兴趣的人希望在现实型的职业环境中工作，可以最好地发挥个人的潜能。但职业选择中，个体并非一定要选择与自己兴趣完全对应的职业环境。职业选择时会不断妥协，寻求与相邻职业环境、甚至相隔职业环境，在这种环境中，个体需要逐渐适应工作环境。但如果个体寻找的是相对的职业环境，意味着所进入的是与自我兴趣完全不同的职业环境，则我们工作起来可能难以适应，或者难以做到工作时觉得很快乐，相反，甚至可能会每天工作得很痛苦。</p>
   <h3  class="page-header">您属于 ${orderedResult} 型人才</h3>
   <div class="bs-example" data-example-id="simple-dl">
    <dl>
      <dt>【共同特点】</dt>
      <dd id="common-feature">${commonFeature}</dd>
      <dt>【性格特点】</dt>
      <dd id="character-feature">${characterFeature}</dd>
    </dl>
   </div>
    
   <h3  class="page-header">测评报告－结果分析</h3>
<div id="container" class="container" ></div>　
    
       <table class="table table-bordered">
          <caption>&nbsp;</caption>
          <thead>
             <tr>
                <th>类型</th>
                <th>得分</th>
                <th>总分</th>
                <th>类型说明</th>
             </tr>
          </thead>
          <tbody>
             <tr>
                <td>R：实际型</td>
                <td>${result['r']}</td>
                <td>130</td>
                <td>【共同特点】愿意使用工具从事操作性工作，动手能力强，做事手脚灵活，动作协调。偏好于具体任务，不善言辞，做事保守，较为谦虚。缺乏社交能力，通常喜欢独立做事。<br>
【性格特点】感觉迟钝、不讲究、谦逊的。踏实稳重、诚实可靠。</td>
             </tr>
             <tr>
                <td>A：艺术型</td>
                <td>${result['a']}</td>
                <td>130</td>
                <td>【共同特点】喜欢以各种艺术形式的创作来表现自己的才能，实现自身的价值；具有特殊艺术才能和个性。<br>
【性格特点】 乐于创造新颖的、与众不同的艺术成果，渴望表现自己的个性</td>
             </tr>
             <tr>
                <td>I：调研型</td>
                <td>${result['i']}</td>
                <td>130</td>
                <td>【共同特点】思想家而非实干家，抽象思维能力强，求知欲强，肯动脑，善思考，不愿动手。喜欢独立的和富有创造性的工作。知识渊博，有学识才能，不善于领导他人。考虑问题理性，做事喜欢精确，喜欢逻辑分析和推理，不断探讨未知的领域。<br>
【性格特点】坚持性强，有韧性，喜欢钻研。为人好奇，独立性强。注：工作中调研兴趣强的人做事较为坚持，有韧性，善始善终。</td>
             </tr>
             <tr>
               <td>S：社会型</td>
               <td>${result['s']}</td>
               <td>130</td>
               <td>【共同特点】喜欢与人交往、不断结交新的朋友、善言谈、愿意教导别人。关心社会问题、渴望发挥自己的社会作用。寻求广泛的人际关系，比较看重社会义务和社会道德。<br>
【性格特点】为人友好、热情、善解人意、乐于助人。</td>
             </tr>
             <tr>
               <td>E：事业型</td>
               <td>${result['e']}</td>
               <td>130</td>
               <td>【共同特点】追求权力、权威和物质财富，具有领导才能。喜欢竞争、敢冒风险、有野心/抱负。为人务实，习惯以利益得失、权利、地位、金钱等来衡量做事的价值，做事有较强的目的性。<br>
【性格特点】善辩、精力旺盛、独断、乐观、自信、好交际、机敏、有支配愿望。</td>
             </tr>
             <tr>
                <td>C：常规型</td>
                <td>${result['c']}</td>
                <td>130</td>
                <td>【共同特点】尊重权威和规章制度，喜欢按计划办事，细心、有条理，习惯接受他人的指挥和领导，自己不谋求领导职务。喜欢关注实际和细节情况，通常较为谨慎和保守，缺乏创造性，不喜欢冒险和竞争，富有自我牺牲精神。<br>
【性格特点】有责任心、依赖性强、高效率、稳重踏实、细致、有耐心。</td>
             </tr>
          </tbody>
       </table>
    
    
</body>
</html>