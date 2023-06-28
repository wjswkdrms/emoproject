<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 에러 발생</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="whole">	
<h1 class="align-center">404 ERROR</h1>
<br><br>
<div class="align-center"><b>요청한 페이지는 존재하지 않습니다.</b></div>
<br>
<div class="align-center">주소를 올바르게 입력했는지 확인하시기 바랍니다.</div>
<div class="container">	
</div>
</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html> 