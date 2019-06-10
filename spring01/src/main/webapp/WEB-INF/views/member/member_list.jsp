<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
function goView(userid) {
	var filePath = encodeURIComponent(userid);
	location.href="${path}/member/view.do?userid="+filePath;
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>회원목록</h2>
<input type="button" value="회원등록" onclick="location.href='${path}/member/write.do'">
<table border=1 style="width:700px;">
<tr>
	<td>아이디</td>
	<td>이름</td>
	<td>이메일</td>
	<td>가입일자</td>
</tr>
<c:forEach var="row" items="${list}">
	<tr>
		<td>${row.userid}</td>
		<td>
			<a onclick="javascript:goView('${row.userid}');" href="#">${row.name}</a>
		</td>
		<td>${row.email}</td>
		<td>
			<fmt:formatDate  value="${row.join_date}" pattern="yyyy-MM-dd HH:mm:ss" />
		</td>
	</tr>
</c:forEach>
</table>

</body>
</html>