<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productdetail_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/product-zzim.js"></script>
<script type="text/javascript">
	$(function(){
		
		
		$('#order_quantity').on('keyup mouseup', function() {
			if ($('#order_quantity').val() == '') {
				$('#item_total_txt').text('총 주문 금액 : 0원');
				return;
			}
			if ($('#order_quantity').val() <= 0) {
				$('#order_quantity').val('');
				return;
			}
			/* if (Number($('#product_stock') < $('#order_quantity').val()) {
				alert('수량이 부족합니다.');
				$('#order_quantity').val('');
				$('#item_total_txt').text('총주문 금액 : 0원');
				return;
			} */

			//총주문 금액 표시
			let total = $('#product_price').val() * $('#order_quantity').val();
			$('#product_total_txt').text('총 주문 금액 : ' + total.toLocaleString() + '원');//숫자 세자리 쉼표

		});
			
		//장바구니에 상품을 담기 위해 이벤트 처리
		$('#product_cart').submit(function(event){
			//기본 이벤트 제거
			event.preventDefault();
			
			if($('#order_quantity').val()==''){
				alert('수량을 입력하세요!');
				$('#order_quantity').focus();
				return false;
			}
			
			let form_data = $(this).serialize();
			
			$.ajax({
				url:'../cart/write.do',
				type:'post',
				data:form_data,
				dataType:'json',
				success:function(param){
					if(param.result == 'logout'){
						alert('로그인 후 사용하세요');
					}else if(param.result == 'success'){
						alert('장바구니에 담았습니다.');
						location.href='../cart/list.do';
					}else if(param.result == 'over_quantity'){
						alert('기존에 주문한 상품입니다. 개수를 추가하면 재고가 부족합니다.');
					}else{
						alert('장바구니 담기 오류');
					}
				},
				error:function(){
					alert('네트워크 오류 발생');
				}
			});
		});
		
	});
</script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="page-detail">
		<div class="detail-top">
			<div class="top-img">
				<img src="${pageContext.request.contextPath}/upload/${product.productdetailVO.product_photo2}">
			</div>
			<div class="top-info">
				<div class="pro-title"><h1>${product.productdetailVO.product_title}</h1></div>
				<div class="top-cate pro-cate">
					<span>카테고리 > </span>
					<a href="${pageContext.request.contextPath}/search/searchCategoryMain.do?product_category=${product.product_category}">
						<c:if test="${product.product_category == 1}">농산물</c:if>
						<c:if test="${product.product_category == 2}">수산물</c:if>
						<c:if test="${product.product_category == 3}">정육</c:if>
						<c:if test="${product.product_category == 4}">유제품</c:if>
						<c:if test="${product.product_category == 5}">음료</c:if>
						<c:if test="${product.product_category == 6}">베이커리</c:if>
					</a>
				</div>
				<div>
					<span class="top-per">할인률%</span>
					<span class="top-price">상품판매가격(할인)</span>
					<span class="top-won">원</span>
				</div>
				<div class="b-hr">
					<span class="top-price-no"><fmt:formatNumber value="${product.productdetailVO.product_price}"/>원</span>
				</div>
				
				<div class="box">
					<div class="box-left">원산지</div>
					<div class="box-right">${product.productdetailVO.product_origin}</div>
				</div>
				
				<form id="product_cart">
					<input type="hidden" name="product_num" value="${product.product_num}" id="product_num">
					<input type="hidden" name="product_price" value="${product.productdetailVO.product_price}" id="product_price">
					<input type="hidden" name="item_stock" value="${product.productdetailVO.product_stock}" id="product_stock">
				
					<div class="box">
						<div class="box-left">
							<label for="order_quantity">상품 수량</label>
						</div>
						<div class="box-right">
							<input type="number" name="order_quantity" min="1" max="20" autocomplete="off" id="order_quantity" class="quantity-width">
						</div>
					</div>
					
					<div class="total-price">
						<span id="product_total_txt">총 주문 금액 : 0원</span>
					</div>
					<!-- 찜(좋아요) -->
					<div class="box-button">
						<button class="button-zzim" type="button">
							<img id="output_zzim" data-num="${product.product_num}" src="${pageContext.request.contextPath}/images/zzim_01.png" width="50">
						</button>
						<input type="submit" value="장바구니에 담기">
					</div>
				</form>
			</div>
		</div>
		
		<div class="nav sticky">
			<ul>
				<li><a href="#detail-info"><span class="nav-li">상품 상세정보</span></a></li>
				<li><a href="#detail-review"><span class="nav-li">상품 리뷰</span></a></li>
				<li><a href="#detail-QA"><span class="nav-li">상품 문의</span></a></li>
			</ul>
		</div>
		
		<div id="detail-info" class="div-h">
			상품 상세 설명 자리<br>
			${product.productdetailVO.product_info}
		</div>
		
		<div id="detail-review" class="div-h">
			<div class="review-star">
				리뷰 평균 별점 자리
			</div>
			<div class="review-list">
				상품 리뷰 리스트 자리
			</div>
		</div>
			
		<div id="detail-QA" class="div-h">
			상품 문의 리스트 자리
		</div>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>