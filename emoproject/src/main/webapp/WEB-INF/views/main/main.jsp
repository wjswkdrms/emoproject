<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이모마켓 | 메인화면</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/content_view_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		//초기값 설정
		let promotion_img_num = 1;
		
		// 5초마다 promotion_img_num을 1씩 증가시키기
		function incrementPromotionImgNum() {
			$('#pro-0' + promotion_img_num).removeClass('active');
			promotion_img_num++;

			if (promotion_img_num > 6) {
				promotion_img_num = 1;
			}

			$('#pro-0' + promotion_img_num).addClass('active');
		}

		setInterval(incrementPromotionImgNum, 4000);
    	// 증가 시키기 끝
    	
    	//클릭 시 작동
    	$('.arrow-right').click(function(){
    		$('#pro-0'+promotion_img_num).removeClass('active');
    		promotion_img_num++;
    		
    		if(promotion_img_num >6) {
    			promotion_img_num = 1;
    		} 
    		
    		
    		$('#pro-0'+promotion_img_num).addClass('active');
    	});
    	
    	$('.arrow-left').click(function(){
    		$('#pro-0'+promotion_img_num).removeClass('active');
    		promotion_img_num--;
    		
    		if(promotion_img_num <1){
				promotion_img_num = 6;
			}
    		
    		$('#pro-0'+promotion_img_num).addClass('active');
    	});
    	
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<jsp:include page="/WEB-INF/views/common/adver.jsp"/>
	<jsp:include page="/WEB-INF/views/common/navigator.jsp"/>
	<div class="main-contents">
		
		<div class="contents-head">전체 상품 보기</div>
		<span id="total_count">총 상품 : ${count}건</span>
		<c:forEach var="product" items="${productList}">
			<div class="product-nums">
				<c:if test="${product.product_status == 2 && product.product_stock == 0}">
					<a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${product.product_num}">
						<img src="${pageContext.request.contextPath}/upload/${product.product_photo1}" width="350px" height="350px">
						<img src="${pageContext.request.contextPath}/images/sold_out.png" class="sold-out-image">
					</a>
				</c:if>
			
				<c:if test="${product.product_status == 2 && product.product_stock > 0}">
					<a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${product.product_num}">
						<img src="${pageContext.request.contextPath}/upload/${product.product_photo1}" width="350px" height="350px">
					</a>
				</c:if>
				
				<c:if test="${product.product_status == 1 }">
					<a style="pointer-events: none;">
						<img src="${pageContext.request.contextPath}/upload/${product.product_photo1}" width="350px" height="350px">
						<img src="${pageContext.request.contextPath}/images/coming_soon.png" class="sold-out-image">
					</a>
				</c:if>
				<div class="product_result">
					<span>${product.product_title}</span>
					 
					<c:if test="${product.product_discount>0}">
						<p class="product_discount">할인률 : ${product.product_discount}%
							<strike class="product_price">가격 : <fmt:formatNumber value="${product.product_price}"/> 원</strike>
						</p>
					</c:if>	 
					<p class="product_price_sales">가격 : <fmt:formatNumber value="${product.product_price_sales}"/> 원</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id = "paging-box-main">${page}</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
