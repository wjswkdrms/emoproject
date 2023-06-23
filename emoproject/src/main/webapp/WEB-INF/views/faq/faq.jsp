<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주하는 질문</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript">

</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<!-- 내용 시작 -->
	<div class="whole">
		<div class="container">
			<div class="left-div">
				<div class="page-name">고객센터</div>
				<ul class="menu-box">
					<li><a class="detail-menu" href="${pageContext.request.contextPath}/announce/announce.do">공지사항<img></a></li>
					<li><a class="detail-menu" href="faq.do">자주하는 질문<img></a></li>
					<li><a class="detail-menu" href="${pageContext.request.contextPath}/ask/ask.do">1:1 문의<img></a></li>
				</ul>
			</div>
			<div class="right-div">
				
				<div class="list-name">
					<h2>자주하는 질문</h2>
				</div>
				
	 			<c:if test="${count==0}"> 
	 			<div class="list">
					<div class="list-num">번호</div>
					<div class="list-category">카테고리</div>
					<div class="list-title">제목</div>
				</div>
	 			<div>
					표시할 게시물이 없습니다.
				</div> 
	 			</c:if>
	 			<c:if test="${count>0}">
				<div class="list">
					<div class="list-num">번호</div>
					<div class="list-category">카테고리</div>
					<div class="list-name">제목</div>
				</div>
				
				<c:forEach var="faq" items="${list}">
				<ul class="list">
					<li><div class="list-num">${faq.faq_num}</div></li>
					<li>
						<div  class="list-category">
						<c:if test="${faq.faq_category==1}">
							회원
						</c:if>
						<c:if test="${faq.faq_category==2}">
							이벤트/쿠폰
						</c:if>
						<c:if test="${faq.faq_category==3}">
							서비스 이용
						</c:if>
						<c:if test="${faq.faq_category==4}">
							시스템 오류
						</c:if>
						</div>
					</li>
					<li>
						<div class="list-name">${faq.faq_title}</div>
						<div>
							<img src="${pageContext.request.contextPath}/images/arw_reply.png">
							${faq.faq_content}
						</div>
					</li>
					<li>
						<c:if test="${!empty user_num && user_auth==9}">
						<div class="button-box">
							<input type="button" value="수정" onclick="location.href='faqUpdateForm.do?faq_num=${faq.faq_num}'">
						</div> 
						</c:if>
					</li>
				</ul>
				</c:forEach>
	 			</c:if> 
				<c:if test="${!empty user_num && user_auth==9}">
				<div class="button-box">
					<input type="button" value="글쓰기" onclick="location.href='faqForm.do'">
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