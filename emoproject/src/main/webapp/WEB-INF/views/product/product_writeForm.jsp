<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title><!--  -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header_style.css">
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/productForm_style.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		//입력한 글자수 셋팅
		let inputLength = $('#info').val().length;
        let remain = 300 - inputLength;
		remain += '/300';
		
		//문서 객체에 반영
		$('#re_first .letter-count').text(remain);
		
		//textarea에 내용 입력시 글자수 체크
		$(document).on('keyup','textarea',function(){
			//입력한 글자수 구함
			let inputLength = $(this).val().length;
			
			if(inputLength>300){//300자를 넘어선 경우
				$(this).val($(this).val().substring(0,300));
			}else{//300자 이하인 경우
				let remain = 300 - inputLength;
				remain += '/300';
				if($(this).attr('id')=='info'){
					//등록폼 글자수
					$('#re_first .letter-count').text(remain);
				}
			}
		});

		$('#write_form').submit(function() {
			//카테고리 선택 여부
			if (!$('#category > option:selected').val()) {
				alert('카테고리를 지정하세요');
				return false;
			}
			//radio버튼 반드시 하나 선택해야한다, 선택 X인 경우
			if ($('input[type=radio]:checked').length < 1) {//length가 1보다 작을 때 하나도 선택 X일 때
				alert('상품표시여부를 지정하세요');
				return false;
			}
			if ($('#name').val().trim() == '') {
				alert('상품을 입력하세요');
				$('#name').val('').focus();
				return false;
			}
			if ($('#price').val().trim() == '') {
				alert('가격을 입력하세요');
				$('#price').val('').focus();
				return false;
			}
			if ($('#stock').val().trim() == '') {
				alert('재고를 입력하세요');
				$('#stock').val('').focus();
				return false;
			}
			if ($('#origin').val().trim() == '') {
				alert('원산지를 입력하세요');
				$('#origin').val('').focus();
				return false;
			}
			if ($('#photo1').val() == '') {
				alert('상품사진1을 선택하세요');
				$('#photo1').val('').focus();
				return false;
			}
			if ($('#photo2').val() == '') {
				alert('상품사진2를 선택하세요');
				$('#photo2').val('').focus();
				return false;
			}
			if ($('#info').val().trim() == '') {
				alert('상품설명을 입력하세요');
				$('#info').val('').focus();
				return false;
			}
		});

	});
</script>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<!-- 내용 시작 -->
		<!-- 커닛 -->
		<div class="form-basic">
			<form action="write.do" method="post" enctype="multipart/form-data"
				id="write_form">
				<h2>상품 등록</h2>
				<ul>
					<li class="dis-in box-half"><span class="box-left">카테고리</span>
						<select id="category" name="category" required>
							<option value="">카테고리</option>
							<option value="1">농산물</option>
							<option value="2">수산물</option>
							<option value="3">정육</option>
							<option value="4">유제품</option>
							<option value="5">음료</option>
							<option value="6">베이커리</option>
					</select></li>
					<li class="dis-in box-half"><label class="box-left">상품표시여부</label>
						<input type="radio" name="status" value="1" id="status1">미표시
						<input type="radio" name="status" value="2" id="status2">표시
					</li>
					<li><label class="box-left" for="name">상품 이름</label> <input
						class="box-right" type="text" name="name" id="name"></li>
					<li><label class="box-left" for="title">상품 타이틀</label> <input
						class="box-right" type="text" name="title" id="title"></li>
					<li class="dis-in box-half"><label class="box-left"
						for="real_price">원가</label> <input type="number" name="real_price"
						id="real_price" maxlength="5"></li>
					<li class="dis-in box-half"><label class="box-left"
						for="price">판매가</label> <input type="number" name="price"
						id="price" maxlength="5"></li>
					<li><label class="box-left" for="discount">할인률</label> 
						<input type="number" name="discount" id="dsicount" value="0" min="0" max="99">
					</li>
					<li><label class="box-left" for="stock">재고</label> <input
						type="number" name="stock" id="stock" min="0" max="99999">
					</li>
					<li><label class="box-left" for="origin">원산지</label> <input
						type="text" name="origin" id="origin"></li>
					<li><label class="box-left" for="photo1">상품 이미지</label> <input
						type="file" name="photo1" id="photo1"
						accept="image/gif, image/png, image/jpeg"></li>
					<li><label class="box-left" for="photo2">상세페이지 이미지</label> <input
						type="file" name="photo2" id="photo2"
						accept="image/gif, image/png, image/jpeg"></li>
					<li>
						<label class="box-left" for="info">상품 설명</label> 
						<textarea rows="5" cols="30" name="info" id="info">상품 상세 설명</textarea></li>
					<li id="re_first">
						<label class="box-left"></label>
						<span class="letter-count">300/300</span>					
					</li>
				</ul>
				
				<!-- asda -->
				<div class="align-center">
					<input type="submit" value="상품 등록"> <input type="button"
						value="목록으로 돌아가기"
						onclick="location.href='${pageContext.request.contextPath}/product/productlist.do'">
				</div>
			</form>
		</div>
		<!-- 내용 끝 -->
	</div>
</body>
</html>