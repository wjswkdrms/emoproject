<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productForm_style.css">
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<!-- 커닛 -->
	<div class="form-basic">
		<form action="write.do" method="post" enctype="multipart/form-data" id="write_form">
		<h2>상품 등록</h2>
		<ul>
			<li class="dis-in box-half">
				<span class="box-left">카테고리</span>
				<select name="category" required>
					<option value="">카테고리</option>
					<option value="1">농산물</option>
					<option value="2">수산물</option>
					<option value="3">정육</option>
					<option value="4">유제품</option>
					<option value="5">음료</option>
					<option value="6">베이커리</option>
				</select> 
			</li>
			<li class="dis-in box-half">
				<label class="box-left">상품표시여부</label>
				<input type="radio" name="status" value="1" id="status1">미표시
				<input type="radio" name="status" value="2" id="status2">표시
			</li>
			<li>
				<label class="box-left" for="name">상품 이름</label>
				<input class="box-right" type="text" name="name" id="name" >
			</li>
			<li>
				<label class="box-left" for="title">상품 타이틀</label>
				<input class="box-right" type="text" name="title" id="title" >
			</li>
			<li class="dis-in box-half">
				<label class="box-left" for="real_price">원가</label>
				<input type="number" name="real_price" id="real_price" maxlength="5">
			</li>
			<li class="dis-in box-half">
				<label class="box-left" for="price">판매가</label>
				<input type="number" name="price" id="price" maxlength="5">
			</li>
			<li>
				<label class="box-left" for="stock">재고</label>
				<input type="number" name="stock" id="stock" min="0" max="99999">
			</li>
			<li>
				<label class="box-left" for="origin">원산지</label>
				<input type="text" name="origin" id="origin">
			</li>
			<li>
				<label class="box-left" for="photo1">상품 이미지</label>
				<input type="file" name="photo1" id="photo1" accept="image/gif, image/png, image/jpeg">
			</li>
			<li>
				<label class="box-left" for="photo2">상세페이지 이미지</label>
				<input type="file" name="photo2" id="photo2" accept="image/gif, image/png, image/jpeg">
			</li>
			<li>
				<label class="box-left" for="info">상품 설명</label>
				<textarea rows="5" cols="30" name="info" id="info">상품 상세 설명</textarea>
			</li>
			
		</ul>
		<div class="align-center">
			<input type="submit" value="상품 등록">
			<input type="button" value="목록으로 돌아가기" onclick="location.href='#'">
		</div>
		</form>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>