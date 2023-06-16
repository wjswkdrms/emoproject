<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
</head>
<body>
<div class="container">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>공지사항</h2>
		<form id="announce_form" action="announce.do" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="ann_title">제목</label>
					<input type="text" name="ann_title" id="ann_title" maxlength="100">
				</li>
				<li>
					<label for="ann_content">내용</label>
					<textarea rows="5" cols="30" name="content" id="content" maxlength="100"></textarea>
				</li>
				<li>
					<label for="ann_photo1">사진첨부</label>
					<input type="file" name="ann_photo1" id="ann_photo1" accept="image/gif,image/png,image/jpeg">
				</li>
			</ul>
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