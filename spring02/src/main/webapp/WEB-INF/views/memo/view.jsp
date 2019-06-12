<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(document).ready(function() {
	// 수정
	$("#btnUpdate").click(function() {
		document.form1.action="${path}/memo/update/${dto.idx}";
		document.form1.submit();
	});
	// 삭제
	$("#btnDelete").click(function() {
		if(confirm("삭제하시겠습니까?")) {
			location.href="${path}/memo/delete/${dto.idx}";
		}
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>메모 보기</h2>
<form method="post" name="form1">
<table border="1" style="width:500px;">
	<tr>
		<td>번호</td>
		<td>${dto.idx}</td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input name="writer" value="${dto.writer}"></td>
	</tr>
	<tr>
		<td>메모</td>
		<td><input name="memo" value="${dto.memo}" size="50"></td>
	</tr>
	<tr align="center">
		<td colspan="2">
			<input type="hidden" name="idx" value="${dto.idx}">
			<button type="button" id="btnUpdate">수정</button>
			<button type="button" id="btnDelete">삭제</button>
		</td>
	</tr>
</table>
</form>

</body>
</html>