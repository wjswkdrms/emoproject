<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		
		<div class="main-content">
			<div class="search-category-title">
				${product_category_name.product_category_name}
			</div>
			
			<div class="product-category-contents">
					
			</div>
		</div>
		
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
		