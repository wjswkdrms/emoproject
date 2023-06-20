<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>[${user_id}]님의 장바구니</h2>
		<c:if test="${empty list}">
		<div class="result-display">
			장바구니에 담은 상품이 없습니다.
		</div>	
		</c:if>
		<c:if test="${!empty list}">
		<form id="cart_order" 
		  action="${pageContext.request.contextPath}/order/orderForm.do" method="post">
			<table>
				<tr>
					<th>상품이름</th>
					<th>이미지</th>
					<th>주문수량</th>
					<th>가격</th>
				</tr>
				<c:forEach var="cart" items="${list}">
				<tr>
					<td>
						${cart.product.product_title}
					</td>
					<td>
						<img src="${pageContext.request.contextPath}/upload/${cart.product.product_photo1}" width="80">
					</td>
					<td>
						${cart.cart_quantity}
					</td>
					<td>
						${cart.product.product_price}
					</td>
				</tr>
				</c:forEach>
			</table>
		</form>
		</c:if>
	</div>
		
임시)<a href="${pageContext.request.contextPath}/cart/writeForm.do">상품등록</a>
</body>
</html>