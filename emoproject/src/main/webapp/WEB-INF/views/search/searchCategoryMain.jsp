<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이모마켓 | 검색</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="search-contents">
		<div class="search-category-title">${product_category_name}</div>
		<div class="search-navigator">
			<ul>
				<li><a href="${pageContext.request.contextPath}/search/searchNew.do">최신순</a></li>
				<li><a href="${pageContext.request.contextPath}/search/searchFav.do">추천순</a></li>
				<li><a href="${pageContext.request.contextPath}/search/searchOrderCount.do">판매량순</a></li>
				<li><a href="${pageContext.request.contextPath}/search/searchPriceMax.do">높은가격순</a></li>
				<li><a href="${pageContext.request.contextPath}/search/searchPriceMin.do">낮은가격순</a></li>
			</ul>
		</div>
		<div class="product-category-contents">
			<c:forEach var="list" items="${categoryList}" >
			<div id="${list.product_num}">
				<img src="${list.product_photo1}">
				<span>${list.product_title}</span>
				<b>가격 : <fmt:formatNumber value="${list.product_price}" /> 원</b>
			</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
