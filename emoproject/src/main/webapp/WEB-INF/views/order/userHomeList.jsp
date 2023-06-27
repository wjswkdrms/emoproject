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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page_outer">
	<div class="page_inner">
		<form action="${pageContext.request.contextPath}/order/userAddressUpdate.do">
		<table class="address-table">
			<tr>
				<th>선택</th>
				<th>이름</th>
				<th>우편번호</th>
				<th>주소</th>
				<th>상세주소</th>
				<th>전화번호</th>
			</tr>
			<tr class="now-address">
				<td>현재 주소</td>
				<td></td>
				<td>${user.getZipcode()}</td>
				<td>${user.getAddress1()}</td>
				<td>${user.getAddress2()}</td>
				<td>${user.getCell()}</td>		
			</tr>
			<c:forEach var="home" items="${list}">
			<tr>
				<td><input type="radio" name="home_num" id="home_num" value="${home.getMem_home_num()}"></td>
				<td>${home.getMem_home_name()}</td>
				<td>${home.getMem_home_zipcode()}</td>
				<td>${home.getMem_home_address1()}</td>
				<td>${home.getMem_home_address2()}</td>
				<td>${home.getMem_home_cell()}</td>
				<td><input type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/order/userUpdateHomeForm.do?home_num=${home.getMem_home_num()}'"></td>
			</tr>
			</c:forEach>
		</table>
		<input type="submit" value="내 주소를 해당 주소로 변경">
		<input type="button" value="추가" onclick="location.href='${pageContext.request.contextPath}/order/userInsertHomeForm.do'">
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>