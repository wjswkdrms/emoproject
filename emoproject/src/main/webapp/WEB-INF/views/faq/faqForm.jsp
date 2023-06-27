<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>faq 글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#faq_form').submit(function(){
		if($('#faq_title').val().trim() == ''){
			alert('제목을 입력하세요');
			$('#faq_title').val('').focus();
			return false;
		}
		if($('#faq_content').val().trim() == ''){
			alert('내용을 입력하세요');
			$('#faq_content').val('').focus();
			return false;
		}
		if(!$('#faq_category > option:selected').val()) {
			alert('카테고리를 지정하세요');
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
						<li><a class="detail-menu" href="faq.do">자주하는 질문<img></a></li>
						<li><a class="detail-menu" href="${pageContext.request.contextPath}/ask/ask.do">1:1 문의<img></a></li>
					</ul>
				</div>
				<div class="right-div">
				<h2>자주하는 질문 글쓰기</h2>
				<form id="faq_form" action="faqWrite.do" method="post">
					<table class="form-box">
						<tr>
							<td class="row-title">제목</td>
							<th class="row-content"><input class="text-title" type="text" name="faq_title" id="faq_title" ></th>
						</tr>
						<tr>
							<td class="row-title">카테고리</td>
							<th class="row-content">
								<select id="faq_category" name="faq_category" required>
									<option value="">카테고리</option>
									<option value="1">회원</option>
									<option value="2">이벤트</option>
									<option value="3">서비스 이용</option>
									<option value="4">시스템 오류</option>
								</select> 
							</th>
						</tr>
						<tr>
							<td class="row-title">내용</td>
							<th class="row-content"><textarea rows="5" cols="30" name="faq_content" id="faq_content" maxlength="1000"></textarea></th>
						</tr>
					</table>
					<div class="button-box">
						<input class="button" type="submit" value="등록"> <input class="button" type="button" value="목록" onclick="location.href='faq.do'">
					</div>
				</form>
				</div>
			<!-- 내용 끝 -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>