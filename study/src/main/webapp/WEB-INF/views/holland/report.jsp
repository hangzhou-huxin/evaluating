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
            categories: ['实际型', '调查型', '艺术型', '社会型', '事业型', '常规型'],
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
   <p class="lead" style="font-size:14px;">人们通常倾向选择与自我兴趣类型匹配的职业环境，如具有现实型兴趣的人希望在现实型的职业环境中工作，可以最好地发挥个人的潜能。但职业选择中，个体并非一定要选择与自己兴趣完全对应的职业环境。人们通常倾向选择与自我兴趣类型匹配的职业环境，如具有现实型兴趣的人希望在现实型的职业环境中工作，可以最好地发挥个人的潜能。但职业选择中，个体并非一定要选择与自己兴趣完全对应的职业环境。一则因为个体本身常是多种兴趣类型的综合体，单一类型显著突出的情况不多，因此评价个体的兴趣类型时也时常以其在六大类型中得分居前三位的类型组合而成，组合时根据分数的高低依次排列字母，构成其兴趣组型，如 RCA 、 AIS 等；二则因为影响职业选择的因素是多方面的，不完全依据兴趣类型，还要参照社会的职业需求及获得职业的现实可能性。因此，职业选择时会不断妥协，寻求与相邻职业环境、甚至相隔职业环境，在这种环境中，个体需要逐渐适应工作环境。但如果个体寻找的是相对的职业环境，意味着所进入的是与自我兴趣完全不同的职业环境，则我们工作起来可能难以适应，或者难以做到工作时觉得很快乐，相反，甚至可能会每天工作得很痛苦。</p>
   <h2  class="page-header">测评报告－结果分析</h2>
   <div id="container" class=" container" ></div>　　
   <h2  class="page-header">您属于 ${orderedResult} 型人才</h2>
   <div class="bs-example" data-example-id="simple-dl">
    <dl>
      <dt>特点：</dt>
      <dd>A description list is perfect for defining terms.</dd>
      <dt>职业建议：</dt>
      <dd>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</dd>
      <dd>Donec id elit non mi porta gravida at eget metus.</dd>
    </dl>
   </div>
    <p>Bootstrap makes use of certain HTML elements and CSS properties that require the use of the HTML5 doctype. Include it at the beginning of all your projects.</p>
	<div class="highlight">
		<pre>
			<code class="language-html" data-lang="html">
				<span class="cp"></span>
				<span class="nt"></span>
			</code>
		</pre>
	</div>
	<div class="container">
		<button type="button" class="btn btn-primary btn-lg active"><a href="<%=request.getContextPath()%>/apply.do"><span style="color:black;">申请深入解读戳这里</span></a></button>
	</div>
   
</body>
</html>