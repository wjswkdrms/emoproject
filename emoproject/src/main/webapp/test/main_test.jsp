<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이모마켓 | 메인화면</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style_test.css">

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	

	<p>contents</p>
	<ul>
		<li><a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a></li>
		<li><a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a></li>

	</ul>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>



