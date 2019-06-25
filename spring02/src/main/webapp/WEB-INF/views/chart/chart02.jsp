<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="https://www.google.com/jsapi" type="text/javascript"></script>
<script type="text/javascript">
google.load('visualization', '1', {
	'packages' : ['corechart']
});
google.setOnLoadCallback(drawChart);
function drawChart() {
	var jsonData = $.ajax({
		url : "${path}/chart/cart_money_list.do",
		dataType : "json",
		async : false
	}).responseText;
	var data = new google.visualization.DataTable(jsonData);
	var chart = new google.visualization.LineChart(document.getElementById("chart_div"));
	chart.draw(data, {
		title : "장바구니 통계",
// 		curveType : "function",
		width : 600,
		height : 440
	});
}
</script>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>
<div id="chart_div"></div>
<button id="btn" type="button" onclick="javascript:drawChart();">refresh</button>
</body>
</html>