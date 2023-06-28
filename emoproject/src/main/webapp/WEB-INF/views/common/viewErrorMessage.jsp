<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외 발생</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="whole">	
<h1 class="align-center">예외 발생</h1>
<br><br>
<div class="align-center"><b>요청 처리 과정에서 예외가 발생했습니다.</b></div>
<br>
<div class="align-center">빠른 시간 내에 문제를 해결하도록 하겠습니다.</div>
에러 타입 : <%= exception.getClass().getName() %><br>
에러 메세지 : <b><%= exception.getMessage() %></b>
<div class="container">	
</div>
</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html> 