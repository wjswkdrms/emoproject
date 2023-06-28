<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 - 상품별 매출 </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/content_view_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<hr class="horizon-bar-01">
	
	<div class="main-contents">
		<div class="admin-main-title">관리자 매출 페이지 기간 : [2023-06-01 ~ ${today}]</div>
		
		<div class="admin-main-title">상품별 판매 정보</div>
		
		<div class="admin-product-box">
			<span>상품 번호</span>
			<span>상품 이름</span>
			<span>구매자 번호</span>
			<span>구매자 이름</span>
			<span>구매한 가격</span>
			<span>구매가 갯수</span>
			<span>최종 구매 가격</span>
			<span>구매 날짜</span>
		</div>
		<c:forEach var="list" items="${list}">
			<div class="admin-product-box">
				<span>${list.product_num}</span>
				<span>${list.order_product_name}</span>
				<span>${list.mem_num}</span>
				<span><a href="${pageContext.request.contextPath}/member/adminMemberDetail.do?mem_num=${list.mem_num}">${list.mem_name}</a></span>
				<span><fmt:formatNumber value="${list.order_product_price}"/> 원</span>
				<span>${list.order_product_quantity} 개</span>
				<span><fmt:formatNumber value="${list.order_product_total}"/> 원</span>
				<span>${list.order_date}</span>
				
			</div>
		</c:forEach>
		
	</div>
		<div class="admin-product-box"></div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>