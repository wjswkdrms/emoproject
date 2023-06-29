<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안내</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/eesamsaoh.css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="whole">
		<h2 class="align-center">안내</h2><br><br>
			<div class="align-center">
				<c:if test="${!empty accessMsg}">
					${accessMsg}
				</c:if>
				<c:if test="${empty accessMsg}">
					잘못된 접속입니다.
				</c:if>
				<p>
				<c:if test="${!empty accessUrl}">
				<input class="small-button" type="button" value="이동"
				  onclick="location.href='${accessUrl}'">
				</c:if>
				<c:if test="${empty accessUrl}">
				<input class="small-button" type="button" value="이동"
				 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				</c:if>
			</div>
	</div>
				<p>				<p>	
	<!-- 내용 끝 -->
</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
</body>
</html>



