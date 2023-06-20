<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록 상품 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div>
		<h2>등록 상품 목록</h2>
		<!-- 검색창 시작 -->
		<!-- 검색창 끝 -->
		<div>
			<input type="button" value="상품등록" onclick="location.href='writeForm.do'">
			<input type="button" value="목록" onclick="location.href='productlist.do'"> 
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">  
		</div>
		
		<c:if test="${count == 0}">	<!-- 등록된 상품 0개(미표시 포함) -->
		<div class="result-display">
			표시할 상품이 없습니다.
		</div>
		</c:if>
		
		<c:if test="${count > 0}">
		<table>
			<tr>
				<th>번호</th>
				<th>상품 사진</th>
				<th>상품명</th>
				<th>가격</th>
				<th>재고수</th>
				<!--  <th>등록일</th> -->
				<th>상태</th>
			</tr>
			<c:forEach var="product" items="${list}">
			<tr>
				<td>${product.product_num}</td>
				<td><img src="${pageContext.request.contextPath}/upload/${product.productdetailVO.product_photo1}" width="70px" height="80px"></td>
				<td><a href="modifyForm.do?product_num=${product.product_num}">${product.productdetailVO.product_name}</a></td>
				<td><fmt:formatNumber value="${product.productdetailVO.product_price}"/>원</td>
				<td><fmt:formatNumber value="${product.productdetailVO.product_stock}"/></td>
				<!-- <td>${item.reg_date}</td>  -->
				<td>
					<c:if test="${product.product_status == 1}">미표시</c:if>
					<c:if test="${product.product_status == 2}">표시</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
	<!-- 내용 끝 -->
</div>

</body>
</html>