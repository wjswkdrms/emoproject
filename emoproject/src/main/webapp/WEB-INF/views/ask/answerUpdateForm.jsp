<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 답변</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="whole">
		<div class="container">
			<!-- 내용 시작 -->
				<div class="left-div">
					<div class="page-name">고객센터</div>
					<ul class="menu-box">
						<li><a class="detail-menu" href="${pageContext.request.contextPath}/announce/announce.do">공지사항<img></a></li>
						<li><a class="detail-menu" href="${pageContext.request.contextPath}/faq/faq.do">자주하는 질문<img></a></li>
						<li><a class="detail-menu" href="ask.do">1:1 문의<img></a></li>
					</ul>
				</div>
				<div class="right-div">
					<h2>1:1문의 답변</h2>
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
					</div>	
					<form id="answer_form" action="answerUpdate.do" method="post" enctype="multipart/form-data">
						<input type="hidden" name="mem_num" value="${ask.mem_num}">
						<input type="hidden" name="ask_num" value="${ask.ask_num}">
						<table>
							<tr>
								<th>내용</th>
								<td><textarea rows="5" cols="30" name="answer_content" id="answer_content" maxlength="100">${answer.answer_content}</textarea></td>
							</tr>
							<tr>
								<th><label for="answer_photo">사진첨부</label></th>
								<c:if test="${!empty answer.answer_photo}">
								<div>
									<div>
									<img src="${pageContext.request.contextPath}/upload/${answer.answer_photo}" width="50" height="50" class="photo" data-img="${answer.answer_photo}">
									</div>
									(${answer.answer_photo})이 등록되어 있습니다.
									<input type="button" value="삭제" id="photo_del"><br>
								</div>
								</c:if>
								<td>
									<input type="file" name="answer_photo" id="answer_photo" accept="image/gif,image/png,image/jpeg">
								</td>
							</tr>
						</table>
						<div>
							<input type="submit" value="등록"> <input type="button" value="목록" onclick="location.href='ask.do'">
						</div>
					</form>
				</div>
			<!-- 내용 끝 -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>