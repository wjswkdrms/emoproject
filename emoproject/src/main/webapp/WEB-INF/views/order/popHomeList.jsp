<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 주소 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditPageAll_style.css"> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#modify_address').submit(function(){
			opener.document.getElementById("pInput").value = document.getElementById("cInput").value
		});
	});
</script>
</head>
<body>

<div class="page_outer">
	<div class="page_inner">
		<form id="modify_address">
		<div class="align-right">
		<input type="submit" class="button" value="선택">
		</div>
		<table class="address-table">
			<tr>
				<th class="checkbox">선택</th>
				<th class="name">이름</th>
				<th class="zipcode">우편번호</th>
				<th class="address1">주소</th>
				<th class="address2">상세주소</th>
				<th class="cell">전화번호</th>
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
				<td><input type="radio" name="home_num" id="home_num" value="${home}"></td>
				<td>${home.getMem_home_name()}</td>
				<td>${home.getMem_home_zipcode()}</td>
				<td>${home.getMem_home_address1()}</td>
				<td>${home.getMem_home_address2()}</td>
				<td>${home.getMem_home_cell()}</td>
			</tr>
			</c:forEach>
		</table>
		
		</form>
	</div>
</div>
</body>
</html>