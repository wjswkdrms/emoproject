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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#answer_form').submit(function(){
		if($('#answer_content').val().trim() == ''){
			alert('내용을 입력하세요');
			$('#answer_content').val('').focus();
			return false;
		}
	});
	
});

</script>
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
					<form id="answer_form" action="answerWrite.do" method="post" enctype="multipart/form-data">
						<input type="hidden" name="mem_num" value="${ask.mem_num}">
						<input type="hidden" name="ask_num" value="${ask.ask_num}">
						<table class="form-box">
							<tr>
								<th class="row-title">내용</th>
								<td class="row-content"><textarea rows="5" cols="30" name="answer_content" id="answer_content" maxlength="100"></textarea></td>
							</tr>
							<tr>
								<th class="row-title"><label for="answer_photo">사진첨부</label></th>
								<td class="row-content">
									<label  for="answer_photo">
										<div class="file-button">
										</div>
									</label>								
									<input type="file" name="answer_photo" id="answer_photo" accept="image/gif,image/png,image/jpeg">
								</td>
							</tr>
						</table>
						<div class="button-box">
							<input class="button" type="submit" value="등록"> <input class="button" type="button" value="목록" onclick="location.href='askDetail.do?ask_num=${ask.ask_num}'">
						</div>
					</form>
				</div>
			<!-- 내용 끝 -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>