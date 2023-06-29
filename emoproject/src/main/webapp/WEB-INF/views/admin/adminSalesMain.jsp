<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 - 매출</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/content_view_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<hr class="horizon-bar-01">
	
	<div class="main-contents">
		<div class="admin-main-title">관리자 매출 페이지 기간 : [2023-06-01 ~ ${today}]</div>
		<div class="admin-sales-head-box">
			<span>총 매출액 : <fmt:formatNumber value="${product_total_price}"/> 원</span>
			<span>총 이익 : <fmt:formatNumber value="${product_total_profit}"/> 원</span>
			<span>총 판매량 : <fmt:formatNumber value="${product_sales_quantity}"/> 개</span>
		</div>
		<div class="admin-main-title">판매 상품 기준</div>
		
		<div class="admin-product-box">
			<span>상품번호</span>
			<span>상품 이름</span>
			<span>판매된 금액 합계</span>
			<span>판매된 상품 갯수</span>
		</div>
		<c:forEach var="list" items="${list}">
			<div class="admin-product-box">
				<span>${list.product_num}</span>
				<a href="${pageContext.request.contextPath}/admin/adminSalesOrders.do?product_num=${list.product_num}"><span>${list.order_product_name}</span></a>
				
				<span><fmt:formatNumber value="${list.product_total_price}"/> 원</span>
				<span>${list.product_sales_quantity} 개</span>
			</div>
		</c:forEach>
		
	</div>
		<div id="unknown_box"></div>
		<div id = "paging-box-main">${page}</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>