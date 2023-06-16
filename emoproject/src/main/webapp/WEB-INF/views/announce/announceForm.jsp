<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
	<!-- 내용 시작 -->
	<div class="content-main">
		<div class="left-div">
			<div class="page-name">고객센터</div>
			<ul class="menu-box">
				<li><a class="detail-menu">공지사항<img></a></li>
				<li><a class="detail-menu">자주하는 질문<img></a></li>
				<li><a class="detail-menu">1:1 문의<img></a></li>
			</ul>
		</div>
		<h2>공지사항</h2>
		<form id="announce_form" action="announce.do" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>제목</td>
				<th><input type="text" name="ann_title" id="ann_title" maxlength="100"></th>
			</tr>
			<tr>				
				<td>내용</td>
				<th><textarea rows="5" cols="30" name="content" id="content" maxlength="100"></textarea></th>
			</tr>
			<tr>
				<td><label for="ann_photo1">사진첨부</label></td>
				<th><input type="file" name="ann_photo1" id="ann_photo1" accept="image/gif,image/png,image/jpeg"></th>
			</tr>					
		</table>
			<div>
				<input type="submit" value="등록">
				<input type="button" value="목록" onclick="location.href='#'">
			</div>
		</form>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>