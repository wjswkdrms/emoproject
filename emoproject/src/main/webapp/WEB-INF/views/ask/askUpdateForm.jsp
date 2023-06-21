<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 수정</title>
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
						<li><a class="detail-menu">자주하는 질문<img></a></li>
						<li><a class="detail-menu" href="ask.do">1:1 문의<img></a></li>
					</ul>
				</div>
				<div class="right-div">
				<h2>1:1문의</h2>
				<form id="ask_form" action="askUpdate.do" method="post" enctype="multipart/form-data">
					<input type="hidden" name="mem_num" value="${ask.mem_num}">
					<input type="hidden" name="ask_num" value="${ask.ask_num}">
					<table>
						<tr>
							<th>제목</th>
							<td><input type="text" name="ask_title" value="${ask.ask_title}" id="ask_title" maxlength="100"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea rows="5" cols="30" name="ask_content" id="ask_content" maxlength="100">${ask.ask_content}</textarea></td>
						</tr>
						<tr>
							<th><label for="ask_photo1">사진첨부</label></th>
							<td>
								<c:if test="${!empty ask.ask_photo1}">
								<div>
									(${ask.ask_photo1})이 등록되어 있습니다.
									<input type="button" value="삭제" id="photo_del"><br>
								</div>
<!-- 								<script type="text/javascript">
									$(function(){
										$('#photo_del').click(function(){
											let choice=confirm('삭제하시겠습니까?');
											if(choice){
												
											}
										})
									});
								</script> -->
								</c:if>
								<input type="file" name="ask_photo1" id="ask_photo1" accept="image/gif,image/png,image/jpeg">
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