<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/announce_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<!-- 내용 시작 -->
	<div>
		<div class="container">
			<div class="left-div">
				<div class="page-name">고객센터</div>
				<ul class="menu-box">
					<li><a class="detail-menu">공지사항<img></a></li>
					<li><a class="detail-menu">자주하는 질문<img></a></li>
					<li><a class="detail-menu">1:1 문의<img></a></li>
				</ul>
			</div>
			<div class="right-div">
				
				<div class="list-name">
					<h2>공지사항</h2>
				</div>
	<%-- 			<c:if test="${count==0}"> --%>
	<!-- 			<div>
					표시할 게시물이 없습니다.
				</div> -->
	<%-- 			</c:if> --%>
	<%-- 			<c:if test="${count>0}"> --%>
				<div class="list">
					<div>번호</div>
					<div>제목</div>
					<div>작성자</div>
					<div>작성일</div>
				</div>
				<%-- <c:forEach> --%>
				<ul class="list">
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
				<input type="button" value="글쓰기" onclick="location.href='announceForm.do'"> 
				<%-- </c:forEach> --%>
	<%-- 			</c:if> --%>
	<%-- 			<div>${page}</div> --%>
			</div>
		</div>
		<!-- 내용 끝 -->
	</div>
</body>
</html>