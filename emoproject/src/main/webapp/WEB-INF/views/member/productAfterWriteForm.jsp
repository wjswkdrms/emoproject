<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 후기 작성</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="whole">
		<div class="container">
			<!-- 내용 시작 -->
				<div class="right-div">
				<h2>상품 후기 작성</h2>
				<form id="ask_form" action="productAfterWrite.do" method="post" enctype="multipart/form-data">
					<input type="hidden" value="${ask.mem_num}">
					<table>
						<tr>
							<th>제목</th>
							<td><input type="text" name="review_title" id="review_title" maxlength="100"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea rows="5" cols="30" name="review_content" id="review_content" maxlength="1000"></textarea></td>
						</tr>
						<tr>
							<th>사진</th>
							<td><textarea rows="5" cols="30" name="review_photo1" id="review_photo1" maxlength="100"></textarea></td>
						</tr>
						<tr>
							<th>평점 (1~10)</th>
							<td><textarea rows="5" cols="30" name="review_content" id="review_content" maxlength="2"></textarea></td>
						</tr>
						<tr>
							<th><label for="ask_photo1">사진첨부</label></th>
							<td>
								<input type="file" name="ask_photo1" id="ask_photo1" accept="image/gif,image/png,image/jpeg">
							</td>
						</tr>
					</table>
					<div>
						<input type="submit" value="등록"> <input type="button" value="목록" onclick="location.href='productAfter.do'">
					</div>
				</form>
				</div>
			<!-- 내용 끝 -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>