<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.reply.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="whole">
		<div class="container">
			<div class="box">
				<div class="title">
					1:1 문의
				</div>
				<div class="content-box">
					<div class="content-detail">
						<div class="detail-title">제목</div>
						<div class="specific">${ask.ask_title}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">작성자</div>
						<div class="specific">${ask.mem_id}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">작성일</div>
						<div class="specific">${ask.ask_date}</div>
					</div>	
					<div class="main-content">
						<div><img src=${pageContext.request.contextPath}/upload/${ask.ask_photo1}></div>
						<div>${ask.ask_content}</div>
					</div>	
					<div class="button-box">
						<c:if test="${!empty user_num&&user_num==ask.mem_num}">
						<input type="button" value="수정" onclick="location.href='askUpdateForm.do?ask_num=${ask.ask_num}'">
						</c:if>
						<input type="button" value="목록" onclick="location.href='ask.do'">
					</div>
				</div>
				<!-- 댓글 시작 -->
				<div id="reply_div">
					<c:if test="${!empty user_num&&user_auth==9}">
					<form id="re_form" method="post" enctype="multipart/form-data">
						<input type="hidden" name="ask_num" value="${ask.ask_num}" id="ask_num">
						<textarea rows="3" cols="50" name="re_content" id="re_content" class="rep-content"></textarea>
						<div id="re_first">
							<span class="letter-count">333/333</span>
						</div>
						<div id="re_second" class="button-box">
							<input type="submit" value="전송">
						</div>
					</form>
					</c:if>
				</div>
				<!-- 댓글 목록 출력 시작 -->
				<div id="output"></div>
				<div class="paging-button" style="display:none;">
					<input type="button" value="다음글 보기">
				</div>
				<div id="loading" style="display:none;">
					<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
				</div>
				<!-- 댓글 목록 출력 끝 -->
				<!-- 댓글 끝 -->
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>