<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<!-- 구글 차트 호출을 위한 js 파일 -->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
// 구글차트 라이브러리 코딩
google.load('visualization', '1', {
	'packages' : ['corechart']
});
// 로딩이 완료되면 drawChart 함수 호출
google.setOnLoadCallback(drawChart);

function drawChart() {
	var jsonData = $.ajax({
		url : "${path}/json/sampleData.json",
		dataType : "json",
		async : false
	}).responseText;
	//console.log(jsonData);
	// 데이터 테이블 생성
	var data = new google.visualization.DataTable(jsonData);
	// 차트를 출력할 div
	// LineChart, ColumnChart, PieChart
	var pie_chart = new google.visualization.PieChart(document.getElementById('pie_chart_div'));
	var line_chart = new google.visualization.LineChart(document.getElementById('line_chart_div'));
	var column_chart = new google.visualization.ColumnChart(document.getElementById('column_chart_div'));
	// 차트객체.draw(데이터 테이블, 옵션)
	pie_chart.draw(data, {
		title : "pie 차트",
		width : 600,
		height :440
	});
	line_chart.draw(data, {
		title : "line 차트",
		curveType : "function",	// 곡선형태 표시
		width : 600,
		height :440
	});
	column_chart.draw(data, {
		title : "column 차트",
		width : 600,
		height :440
	});
}
</script>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>
<!-- 차트 출력 영역 -->
<div id="pie_chart_div"></div>
<div id="line_chart_div"></div>
<div id="column_chart_div"></div>

<!-- 차트 새로고침 버튼 -->
<button id="btn" type="button" onclick="javascript:drawChart();">refresh</button>

</body>
</html>