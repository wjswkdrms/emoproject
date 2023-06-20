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
					<li><a class="detail-menu">공지사항<img></a></li>
					<li><a class="detail-menu">자주하는 질문<img></a></li>
					<li><a class="detail-menu">1:1 문의<img></a></li>
				</ul>
			</div>
			<div class="right-div">
				
				<div class="list-name">
					<h2>1:1 문의</h2>
				</div>
				
<%-- 	 			<c:if test="${count==0}"> 
	 			<div class="list">
					<div>번호</div>
					<div>제목</div>
					<div>작성자</div>
					<div>작성일</div>
				</div>
	 			<div>
					표시할 게시물이 없습니다.
				</div> 
	 			</c:if> --%>
<%-- 	 			<c:if test="${count>0}"> --%>
				<div class="list">
					<div>답변상태</div>
					<div>번호</div>
					<div>제목</div>
					<div>작성자</div>
					<div>작성일</div>
				</div>
				
				<c:forEach var="ask" items="${list}">
				<ul class="list">
					<li>
						<div class="no-answer">
						<c:if test="${ask.ask_status<1}">
							답변 대기
						</c:if>
						</div>
						<div class="answer">
						<c:if test="${ask.ask_status==1}">
							답변 완료
						</c:if>
						</div>
					</li>
					<li>${ask.ask_num}</li>
					<li><a href="askDetail.do?ask_num=${ask.ask_num}">${ask.ask_title}</a></li>
					<li>${ask.mem_id}</li>
					<li>${ask.ask_date}</li>
					
				</ul>
				</c:forEach>
<%-- 	 			</c:if>  --%>
				<c:if test="${!empty user_num && user_auth==2}">
				<input type="button" value="글쓰기" onclick="location.href='askForm.do'"> 
				</c:if>
				<div>${page}</div>  
			</div>
		</div>
		<!-- 내용 끝 -->
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>