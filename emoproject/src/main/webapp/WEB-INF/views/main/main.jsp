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
	<div class="main-contents">
		<div class="contents-head">전체 상품 보기</div>
		<c:forEach var="product" items="${productList}">
		<div class="product-nums"> <!-- 수정 필요 -->
		<a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${product.product_num}">
		<img src="${pageContext.request.contextPath}/upload/${product.product_photo1}" width="350px" height="350px">
		<span>${product.product_title}</span>
		<p>가격 : ${product.product_price} 원</p>
		</a>
		</div>
		</c:forEach>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
