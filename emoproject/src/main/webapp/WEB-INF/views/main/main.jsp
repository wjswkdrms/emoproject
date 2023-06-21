<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이모마켓 | 메인화면</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/promotion_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/content_view_style.css">


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
