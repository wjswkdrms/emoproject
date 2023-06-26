<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 주소 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditPageAll_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div>
	<div>
		<table>
			<tr>
				<th>이름</th>
				<th>우편번호</th>
				<th>주소</th>
				<th>상세주소</th>
				<th>전화번호</th>
			</tr>
			<c:forEach var="home" items="${list}">
			<tr>
				<td>${home.getMem_home_name()}</td>
				<td>${home.getMem_home_zipcode()}</td>
				<td>${home.getMem_home_address1()}</td>
				<td>${home.getMem_home_address2()}</td>
				<td>${home.getMem_home_cell()}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>