<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 입력 완료</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>1234</h2>
		<div class="result-display">
			<div class="align-center">
				1234
				<p>
				<input type="button" value="홈으로"
				 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



