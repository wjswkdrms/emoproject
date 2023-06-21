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
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function (){
		$("#faq_write").click(function (){
	  	$(".faq_writeform").toggle();
	  });
	});

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
					<li><a class="detail-menu" href="announce.do">공지사항<img></a></li>
					<li><a class="detail-menu" href="${pageContext.request.contextPath}/faq/faq.do">자주하는 질문<img></a></li>
					<li><a class="detail-menu" href="${pageContext.request.contextPath}/ask/ask.do">1:1 문의<img></a></li>
				</ul>
			</div>
			<div class="right-div">
				
				<div class="list-name">
					<h2>자주하는 질문</h2>
				</div>
				
				<!-- faq 목록 출력 시작 -->
				<div class="list">
					<div class="faq_writeform">
						<form id="re_form">
						<input type="hidden" name="mem_num" value="${faq.mem_num}" id="mem_num">
							<textarea rows="3" cols="100" name="re_content" id="re_content" class="rep-content"
							<c:if test="${empty user_num||user_auth<9}">disabled="disabled"</c:if>><c:if test="${empty user_num}">관리자만 작성할 수 있습니다.</c:if>faq작성</textarea>
							<c:if test="${!empty user_num&&user_auth==9}">
							<div id="re_second" class="button-box">
								<input type="submit" value="전송">
							</div>
							</c:if>
						</form>
					</div>
					<div class="paging-button" style="display:none;">
						<input type="button" value="다음글 보기">
					</div>
					<!-- faq 목록 출력 끝 -->
				</div>
				<div class="align-center">${page}</div>  
				<c:if test="${!empty user_num && user_auth==9}">
				<div class="button-box">
					<button id="faq_write">글쓰기</button>
				</div> 
				</c:if>
			</div>
		</div>
		<!-- 내용 끝 -->
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>