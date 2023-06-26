<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productForm_style.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
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
		
		
		$('#write_form').submit(function(){
			//카테고리 선택 여부
			if(!$('#category > option:selected').val()) {
    			alert('카테고리를 지정하세요');
    			return false;
			}
			
			if($('#name').val().trim() == ''){
				alert('상품을 입력하세요');
				$('#name').val('').focus();
				return false;
			}
			if($('#price').val().trim() == ''){
				alert('가격을 입력하세요');
				$('#price').val('').focus();
				return false;
			}
			if($('#stock').val().trim() == ''){
				alert('재고를 입력하세요');
				$('#stock').val('').focus();
				return false;
			}
			if($('#origin').val().trim() == ''){
				alert('원산지를 입력하세요');
				$('#origin').val('').focus();
				return false;
			}
			if($('#photo1').val() == ''){
				alert('상품사진1을 선택하세요');
				$('#photo1').val('').focus();
				return false;
			}
			if($('#photo2').val() == ''){
				alert('상품사진2를 선택하세요');
				$('#photo2').val('').focus();
				return false;
			}
			if($('#info').val().trim() == ''){
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
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<!-- 커닛 -->
	<div class="form-basic">
		<form action="modify.do" method="post" enctype="multipart/form-data" id="modify_form">
		<input type="hidden" name="product_num" value="${product.product_num}">
		<h2>상품 수정</h2>
		<ul>
			<li class="dis-in box-half">
				<span class="box-left">카테고리</span>
				<select id="category" name="category" required>
					<option value="">카테고리</option>
					<option value="1" <c:if test="${product.product_category == 1}">selected</c:if>>농산물</option>
					<option value="2" <c:if test="${product.product_category == 2}">selected</c:if>>수산물</option>
					<option value="3" <c:if test="${product.product_category == 3}">selected</c:if>>정육</option>
					<option value="4" <c:if test="${product.product_category == 4}">selected</c:if>>유제품</option>
					<option value="5" <c:if test="${product.product_category == 5}">selected</c:if>>음료</option>
					<option value="6" <c:if test="${product.product_category == 6}">selected</c:if>>베이커리</option>
				</select> 
			</li>
			
			<li class="dis-in box-half">
				<label class="box-left">상품표시여부</label>
				<input type="radio" name="status" value="1" id="status1" <c:if test="${product.product_status == 1}">checked</c:if>>미표시
				<input type="radio" name="status" value="2" id="status2" <c:if test="${product.product_status == 2}">checked</c:if>>표시
			</li>
			<li>
				<label class="box-left" for="name">상품 이름</label>
				<input class="box-right" type="text" name="name" id="name" value="${product.productdetailVO.product_name}">
			</li>
			<li>
				<label class="box-left" for="title">상품 타이틀</label>
				<input class="box-right" type="text" name="title" id="title" value="${product.productdetailVO.product_title}">
			</li>
			<li class="dis-in box-half">
				<label class="box-left" for="real_price">원가</label>
				<input type="number" name="real_price" id="real_price" maxlength="5" value="${product.productdetailVO.product_real_price}">
			</li>
			<li class="dis-in box-half">
				<label class="box-left" for="price">판매가</label>
				<input type="number" name="price" id="price" maxlength="5" value="${product.productdetailVO.product_price}">
			</li>
			<li><label class="box-left" for="discount">할인률</label> 
						<input type="number" name="discount" id="dsicount" value="0" min="0" max="99">
					</li>
			<li>
				<label class="box-left" for="stock">재고</label>
				<input type="number" name="stock" id="stock" min="0" max="99999" value="${product.productdetailVO.product_stock}">
			</li>
			<li>
				<label class="box-left" for="origin">원산지</label>
				<input type="text" name="origin" id="origin" value="${product.productdetailVO.product_origin}">
			</li>
			<li>
				<label class="box-left" for="photo1">상품 이미지</label>
				<input type="file" name="photo1" id="photo1" accept="image/gif, image/png, image/jpeg">
				<div>(${product.productdetailVO.product_photo1})파일이 등록되어 있습니다.</div>
			</li>
			<li>
				<label class="box-left" for="photo2">상세페이지 이미지</label>
				<input type="file" name="photo2" id="photo2" accept="image/gif, image/png, image/jpeg">
				<div>(${product.productdetailVO.product_photo2})파일이 등록되어 있습니다.</div>
			</li>
			<li>
				<label class="box-left" for="info">상품 설명</label>
				<textarea rows="5" cols="30" name="info" id="info">${product.productdetailVO.product_info}</textarea>
			</li>
			<li id="re_first">
						<label class="box-left"></label>
						<span class="letter-count">300/300</span>					
			</li>
			
		</ul>
		<div class="align-center">
			<input type="submit" value="상품 수정">
			<input type="button" value="목록으로 돌아가기" onclick="location.href='${pageContext.request.contextPath}/product/productlist.do'">
		</div>
		</form>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>