<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 후기 작성</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productAfterWrite.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {
	/*
	let afterCount = $('#after_count').val(); // #afterCount의 값을 정수로 변환
	if (afterCount > 0) {
	    alert('이미 후기가 작성되었습니다.');
	    window.history.back(); // 뒤로가기 수행
	}*/
	
	
	$('#review_form').submit(function() {
		if ($('#review_title').val().trim() == '') {
			alert('제목을 입력하세요');
			$('#review_title').val('').focus();
			return false;
		}
		if ($('#review_content').val().trim() == '') {
			alert('내용을 입력하세요');
			$('#review_content').val('').focus();
			return false;
		}
		if ($('#review_score').val().trim() == '') {
			alert('평점을 입력하세요');
			$('#review_score').val('').focus();
			return false;
		}
		if(!/^[1-9]0?$|^10$/.test($('#review_score').val())){
			alert('평점은 1부터 10 사이의 숫자만 입력 가능합니다.');
			$('#review_score').val('').focus();
			return false;
		}
	});
	 $("input[type='file']").change(function(e){

	      //div 내용 비워주기
	      $('#image_container').empty();
	 });  		
	 
});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<input type="hidden" id ="after_count" value="${afterCount}">
	<div class="whole">
		<div class="container">
			<!-- 내용 시작 -->
    <div class="left-div">

      <div class="page-name">내 정보</div>
      <ul class="menu-box">
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/orderList.do">주문내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/questList.do">문의내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/cart/list.do">장바구니</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/jjimList.do">찜한상품</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/productAfter.do">상품후기</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/myEditForm.do">개인정보 수정</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/memberOutForm.do">회원 탈퇴</a></li>
      </ul>
    </div>
				<div class="right-div">
				<h2>상품 후기 작성</h2>
				<form id="review_form" action="productAfterWrite.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="product_num" id="product_num" value="${product_num}">
				<input type="hidden" name="order_detail_num" id="order_detail_num" value="${order_detail_num}">
					<table class="form-box">
						<tr>
							<td>제목</td>
							<td><input type="text" name="review_title" id="review_title" pattern=".{1,100}" required title="제목은 1 ~ 100자까지 입력 가능"></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="review_content" id="review_content" maxlength="1000"></textarea></td>
						</tr>
						<tr>
							<td>평점 (1~10)</td>
							<td><input type="number" name="review_score" id="review_score" class="review_score" pattern=".{1,2}" required title="1 ~ 10 사이의 상품 평점 입력 필수"  min="1" max="10"></input></td>
						</tr>
												<tr>
							<td>사진첨부</td>
							<td class="row-content">
								<label for="review_photo1">
									<div class="file-button"></div>
								</label>
								<div id="image_container"></div> 
								<input type="file" name="review_photo1" id="review_photo1" 
								accept="image/gif,image/png,image/jpeg" onchange="setThumbnail(event);"> 
								<script type="text/javascript">
									function setThumbnail(event) {
										var reader = new FileReader();

										reader.onload = function(event) {
											var img = document
													.createElement("img");
											img.setAttribute("src",
													event.target.result);
											var div=document.querySelector(
													"div#image_container");
													
											if(div.children.length>0){
												div.firstElementChild.remove();	
											}
											div.appendChild(img);
										};

										reader.readAsDataURL(event.target.files[0]);
									}
								</script>
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