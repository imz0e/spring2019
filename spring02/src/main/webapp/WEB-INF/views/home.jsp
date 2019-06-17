<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
<%@ include file="include/header.jsp" %>
</head>
<body>
<%@ include file="include/menu.jsp" %>
<c:if test="${sessionScope.userId != null}">
	<h2>${sessionScope.name}( ${sessionScope.userId} ) 님의 방문을 환영합니다.</h2>
</c:if>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<!-- 배포 디렉토리 확인 -->
<%= application.getRealPath("/") %>
<!-- C:\z0boot\spring02\src\main\webapp\  -->
</body>
</html>
