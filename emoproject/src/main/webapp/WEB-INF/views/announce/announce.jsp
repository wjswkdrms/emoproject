<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<!-- 내용 시작 -->
	<div class="whole">
		<div class="container">
			<div class="left-div">
				<div class="page-name">고객센터</div>
				<ul class="menu-box">
					<li><a class="detail-menu" href="announce.do">공지사항<img></a></li>
					<li><a class="detail-menu" href="${pageContext.request.contextPath}/faq/faq.do">자주하는 질문<img></a></li>
					<li><a class="detail-menu" href="${pageContext.request.contextPath}/ask/ask.do">1:1 문의<img></a></li>
				</ul>
			</div>
			<div class="right-div">
				
				<div class="list-name">
					<h2>공지사항</h2>
				</div>
				
	 			<c:if test="${count==0}"> 
	 			<div class="list">
					<div class="list-num">번호</div>
					<div class="list-name2" style="text-align:center;">제목</div>
					<div class="list-num">작성자</div>
					<div class="list-category">작성일</div>
				</div>
	 			<div>
					표시할 게시물이 없습니다.
				</div> 
	 			</c:if>
	 			<c:if test="${count>0}">
				<div class="list">
					<div class="list-num">번호</div>
					<div class="list-name2" style="text-align:center;">제목</div>
					<div class="list-num">작성자</div>
					<div class="list-category">작성일</div>
				</div>
				
				<c:forEach var="announce" items="${list}">
				<ul class="list">
					<li class="list-num">
						${announce.ann_num}
					</li>
					<li class="list-content">
						<a href="announceDetail.do?ann_num=${announce.ann_num}">${announce.ann_title}</a>
					</li>
					<li class="list-num">${announce.mem_id}</li>
					<li class="list-category">${announce.ann_date}</li>
				</ul>
				</c:forEach>
	 			</c:if> 
				<c:if test="${!empty user_num && user_auth==9}">
				<div class="button-box">
					<input type="button" class="small-button" value="글쓰기" onclick="location.href='announceForm.do'">
				</div> 
				</c:if>
				<div class="align-center">${page}</div>  
			</div>
		</div>
		<!-- 내용 끝 -->
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>