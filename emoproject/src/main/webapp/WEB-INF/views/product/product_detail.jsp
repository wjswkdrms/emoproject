<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 보기</title>
<!--  -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/productdetail_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/product-zzim.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/product-review-list.js"></script>
<script type="text/javascript">
	$(function() {
		
		$('#order_quantity').on(
				'keyup mouseup',
				function() {
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
					} 
					 */
					//총주문 금액 표시
					if ($('product_discount').val() > 0) {
						let total = $('#product_price_sales').val()
								* $('#order_quantity').val();
					} else {
						let total = $('#product_price').val()
								* $('#order_quantity').val();
					}
					$('#product_total_txt').text(
							'총 주문 금액 : ' + total.toLocaleString() + '원');//숫자 세자리 쉼표

				});

		//장바구니에 상품을 담기 위해 이벤트 처리
		$('#product_cart').submit(function(event) {
			//기본 이벤트 제거
			event.preventDefault();

			if ($('#order_quantity').val() == '') {
				alert('수량을 입력하세요!');
				$('#order_quantity').focus();
				return false;
			}

			let form_data = $(this).serialize();

			$.ajax({
				url : '../cart/write.do',
				type : 'post',
				data : form_data,

				dataType : 'json',
				success : function(param) {
					if (param.result == 'logout') {
						alert('로그인 후 사용하세요');
					} else if (param.result == 'success') {
						alert('장바구니에 담았습니다.');
						location.reload();
						//location.href='../cart/list.do';

						/* 헤더에 들어가는 영역 입니다. */
						/*$('#cart_count').text();
						$('#cart_count').text(param.cartCount_ajax);*/

					} else if (param.result == 'over_quantity') {
						alert('기존에 주문한 상품입니다. 개수를 추가하면 재고가 부족합니다.');
					} else {
						alert('장바구니 담기 오류');
					}
				},
				error : function() {
					alert('네트워크 오류 발생');
				}
			});
		});
		$('#btn_Sale').hover(function() {
			$(this).css('background-color', '#cba680')
		}, function() {
			$(this).css('background-color', '#dbb38a')
		});

		$('.button-zzim').hover(function() {
			$(this).css('background-color', '#ebebeb')
		}, function() {
			$(this).css('background-color', '#f5f5f5')
		});

		$('#btn_noSale').on('click', function() {
			alert('해당 상품은 판매 중지 상태입니다.');
		});

		$('#btn_soldout').on('click', function() {
			alert('해당 상품은 품절 입니다.');
		});

	});
</script>
</head>
<body>
	<div>
		<div class="modal">
			<div class="modalBox"></div>
		</div>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<!-- 내용 시작 -->
		<div class="page-detail">
			<c:if test="${product.product_status == 1}">
				<div class="align-center no-Sale">판매 중지 상품 입니다.</div>
			</c:if>
			<div class="detail-top">

				<div class="top-img">
					<img src="${pageContext.request.contextPath}/upload/${product.productdetailVO.product_photo1}">
					<div class="top-img-sub">
						<c:if test="${product.product_status == 1}">
							<img src="${pageContext.request.contextPath}/images/coming_soon.png" class="sold-out-image">
						</c:if>
						<c:if test="${product.product_status==2 && product.productdetailVO.product_stock==0}">
							<img src="${pageContext.request.contextPath}/images/sold_out.png" class="sold-out-image">
						</c:if>
					</div>
				</div>
				<div class="top-info">
					<div class="pro-title">
						<h2 id="pro-title">${product.productdetailVO.product_title}</h2>
					</div>
					<div class="top-cate pro-cate">
						<span>카테고리 > </span> <a
							href="${pageContext.request.contextPath}/search/searchCategoryMain.do?product_category=${product.product_category}">
							<c:if test="${product.product_category == 1}">농산물</c:if> <c:if
								test="${product.product_category == 2}">수산물</c:if> <c:if
								test="${product.product_category == 3}">정육</c:if> <c:if
								test="${product.product_category == 4}">유제품</c:if> <c:if
								test="${product.product_category == 5}">음료</c:if> <c:if
								test="${product.product_category == 6}">베이커리</c:if>
						</a>
					</div>
					<!-- 할인률이 있을 떄 -->
					<c:if test="${product.productdetailVO.product_discount > 0}">
						<div>
							<span class="top-per" id="product_discount">${product.productdetailVO.product_discount}%</span>
							<span class="top-price" id="product_price_sales"><fmt:formatNumber
									value="${product.productdetailVO.product_price_sales}" /></span> <span
								class="top-won">원</span>
						</div>
						<div class="b-hr">
							<span class="top-price-no"><del>
									<fmt:formatNumber
										value="${product.productdetailVO.product_price}" />
									원
								</del></span>
						</div>
					</c:if>
					<!-- 할인률 X -->
					<c:if test="${product.productdetailVO.product_discount == 0}">
						<div>
							<span class="top-price" id="product_price"><fmt:formatNumber
									value="${product.productdetailVO.product_price_sales}" /></span> <span
								class="top-won">원</span>
						</div>
						<div class="b-hr">
							<span class="top-price-no"><fmt:formatNumber
									value="${product.productdetailVO.product_price}" />원</span>
						</div>
					</c:if>

					<div class="box">
						<div class="box-left">원산지</div>
						<div class="box-right">${product.productdetailVO.product_origin}</div>
					</div>

					<form id="product_cart">
						<input type="hidden" name="product_num"
							value="${product.product_num}" id="product_num"> <input
							type="hidden" name="product_price"
							value="${product.productdetailVO.product_price}"
							id="product_price"> <input type="hidden"
							name="item_stock"
							value="${product.productdetailVO.product_stock}"
							id="product_stock"> <input type="hidden"
							name="product_title"
							value="${product.productdetailVO.product_title}"
							id="product_title">

						<div class="box">
							<div class="box-left">
								<label for="order_quantity">상품 수량</label>
							</div>
							<div class="box-right">
								<input type="number" name="order_quantity" value="1" min="1"
									max="20" autocomplete="off" id="order_quantity"
									class="quantity-width">
							</div>
						</div>

						<div class="total-price">
							<span id="product_total_txt">총 주문 금액 : <fmt:formatNumber
									value="${product.productdetailVO.product_price_sales}" />원
							</span>
						</div>
						<!-- 찜(좋아요) -->
						<div class="box-button">
							<button class="button-zzim" type="button" style='cursor: pointer'>
								<img id="output_zzim" data-num="${product.product_num}"
									src="${pageContext.request.contextPath}/images/zzim_01.png"
									width="50">
							</button>


							<c:if test="${product.product_status == 1}">
								<input type="button" id="btn_noSale" value="판매 중지">
							</c:if>

							<c:if
								test="${product.productdetailVO.product_stock <= 0 && product.product_status == 2}">
								<input type="button" id="btn_soldout" value="Sold Out">
							</c:if>

							<c:if
								test="${product.productdetailVO.product_stock > 0 && product.product_status == 2}">
								<input type="submit" id="btn_Sale" value="장바구니에 담기"
									style='cursor: pointer'>
							</c:if>

						</div>
					</form>
				</div>
			</div>

			<div class="nav sticky">
				<ul>
					<li class="li-1"><a href="#detail-info"><span
							class="nav-li">상품설명</span></a></li>
					<li class="li-2"><a href="#context-de"><span
							class="nav-li">상세설명</span></a></li>
					<li class="li-3"><a href="#detail-review"><span
							class="nav-li">상품후기</span></a></li>
				</ul>
			</div>

			<div id="detail-info" class="div-h">
				<!-- 상품 상세 설명 자리<br> 
			${product.productdetailVO.product_info} -->

				<div class="pro-de-img">
					<div class="crop">
						<img class="crop-img"
							src="${pageContext.request.contextPath}/upload/${product.productdetailVO.product_photo1}">
					</div>
				</div>
				<div class="context">
					<h3 class="context-ti">[
						${product.productdetailVO.product_title} ]</h3>
					<p class="de-word">${product.productdetailVO.product_info}</p>
				</div>
				<div id="context-de" class="context-de">
					<h3>상품고시정보</h3>
					<ul class="ul-box">
						<li class="ul-b-left">품목 또는 명칭</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">포장단위별 내용물의 용량(중량), 수량, 크기</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">생산자, 수입품의 경우 수입자를 함께 표기</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">｢농수산물의 원산지 표시 등에 관한 법률｣에 따른 원산지</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">제조연월일, 소비기한 또는 품질유지기한</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">농수산물 – ｢농수산물 품질관리법｣에 따른 유전자변형농수산물 표시,
							지리적 표시</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">축산물 - ｢축산법｣에 따른 등급 표시(1++ 등급 국내산 쇠고기의
							경우 ｢소ㆍ돼지 식육의 표시방법 및 부위 구분기준｣에 따라 근내지방도 정보를 포함하여 표시), ｢가축 및 축산물
							이력관리에 관한 법률｣에 따른 이력관리대상축산물 유무</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">수입 농수축산물 – “수입식품안전관리 특별법에 따른 수입신고를 필함”의
							문구</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">상품구성</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">보관방법 또는 취급방법</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">소비자 안전을 위한 주의사항 (｢식품 등의 표시ㆍ광고에 관한 법률
							시행규칙｣ 제5조 및 [별표 2]에 따른 표시사항을 말함)</li>
						<li class="ul-b-right">상품설명 및 상품이미지 참조</li>
						<li class="ul-b-left">소비자 상담 관련 전화번호</li>
						<li class="ul-b-right">고객센터 (02-1234-5678)</li>

					</ul>
				</div>
			</div>

			<div id="detail-review" class="div-h">
				<div class="review-star">
					<div>
						<h3>평균 별점</h3>
					</div>
					<div class="star-img">
						<img id="star-output" class="star1" src="../images/star_0.png">
						<div class="star-img-y"></div>
					</div>
					<span></span>
				</div>
				<div class="review-list">
					<h3 class="review-ti">상품 후기</h3>
					<c:if test="${count == 0}">
						<div class="no-re align-center">아직 등록된 후기가 없습니다.</div>
					</c:if>
					<c:if test="${count > 0}">
						<div id="output"></div>
						<%-- <div class="review-box">
					<div class="rbox-right" >
						<div class="re-product-ti">${product.productdetailVO.product_title}</div>
					</div>
				</div> --%>
						<div class="paging-button" style="display: none;">
							<input type="button" value="더 많은 후기 보기" style='cursor: pointer'>
						</div>
					</c:if>


				</div>
			</div>

			<!-- <div id="detail-QA" class="div-h">
			상품 문의 리스트 자리
		</div> -->
		</div>
		<!-- 내용 끝 -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
</body>
</html>