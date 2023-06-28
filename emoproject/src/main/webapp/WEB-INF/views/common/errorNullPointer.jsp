<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Null 에러 발생</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="whole">	
<h1 class="align-center">Null ERROR</h1>
<br><br>
<div class="align-center"><b>Null</b></div>
<br>
<div class="align-center">서비스 처리 과정에서 NULL 예외가 발생했습니다.</div>
<div class="container">	
</div>
</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html> 