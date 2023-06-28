<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 - 날짜별 매출 </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/content_view_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<hr class="horizon-bar-01">
	
	<div class="main-contents">
		<div class="admin-main-title">상품 판매 조회 날짜 : [${order_date}]</div>
		<div class="admin-sales-head-box">
			<span>매출액 : <fmt:formatNumber value="${product_total_price}"/> 원</span>
			<span>판매량 : <fmt:formatNumber value="${product_sales_quantity}"/> 개</span>
		</div>
		<div class="admin-main-title">날짜별 판매 정보</div>
		
		<div class="admin-product-box">
			<span>날짜</span>
			<span>판매 상품 번호</span>
			<span>판매 상품</span>
			<span>구매자 번호</span>
			<span>구매자 이름</span>
			<span>판매 가격</span>
			<span>판매 갯수</span>
			<span>판매 총액</span>
		</div>
		<c:forEach var="list" items="${list}">
			<div class="admin-product-box">
				<span>${list.order_date_str}</span>
				<span>${list.product_num}</span>
				<span><a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${list.product_num}">${list.order_product_name}</a></span>
				<span>${list.mem_num}</span>
				<span><a href="${pageContext.request.contextPath}/member/adminMemberDetail.do?mem_num=${list.mem_num}">${list.mem_name}</a></span>
				<span><fmt:formatNumber value="${list.order_product_price}"/> 원</span>
				<span>${list.order_product_quantity} 개</span>
				<span><fmt:formatNumber value="${list.order_product_total}"/> 원</span>
			</div>
		</c:forEach>
		
	</div>
		<div class="admin-product-box"></div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>