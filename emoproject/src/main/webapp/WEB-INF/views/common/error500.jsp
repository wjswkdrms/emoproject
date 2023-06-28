<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 에러 발생</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="whole">	
<h1 class="align-center">500 ERROR</h1>
<br><br>
<div class="align-center"><b>페이지 호출시 오류가 발생했습니다.</b></div>
<br>
<div class="align-center">잠시 후에 다시 사용하시기 바랍니다.</div>
<div class="container">	
</div>
</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html> 