<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문내역 관리</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#search_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>주문내역 관리</h2>
		<%-- 검색은 링크 가능하기 때문에 get방식으로 --%>
		<!-- 검색창 시작 -->  
		<form id="search_form" action="adminOrderList.do"
		                          method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>주문번호</option>
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>회원번호</option>
						<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>아이디</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" 
					   name="keyword" id="keyword"
					                    value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>                          
		</form>
		<!-- 검색창 끝 -->
		<div class="button-box">
			<input type="button" value="목록" onclick="location.href='adminOrderList.do'">
		</div> 
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 회원정보가 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
			<tr>
				<th>주문번호</th>
				<th>회원번호</th>
				<th>아이디</th>
				<th>배송상태</th>
			</tr>
			<c:forEach var="order" items="${order}">
			<tr>
				<td>${order.order_num}</td>
				<td>${order.mem_num}</td>
				<td>
					<a href="adminOrderDetail.do?order_num=${order.order_num}" data-hidden="${order.mem_num}">${order.id}</a>
				</td>
				<td>
					<c:if test="${order.order_status==0}">
						주문완료
					</c:if>
					<c:if test="${order.order_status==1}">
						배송중
					</c:if>
					<c:if test="${order.order_status==2}">
						배송완료
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${page}</div>
		</c:if>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>