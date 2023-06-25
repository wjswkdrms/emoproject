<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
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
		<h2>회원 관리</h2>
		<%-- 검색은 링크 가능하기 때문에 get방식으로 --%>
		<!-- 검색창 시작 -->  
		<form id="search_form" action="adminMemberList.do"
		                          method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>회원번호</option>
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>ID</option>
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
			<input type="button" value="목록" onclick="location.href='adminMemberList.do'">
		</div> 
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 회원정보가 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
			<tr>
				<th>회원번호</th>
				<th>아이디</th>
				<th>누적금액</th>
				<th>등급</th>
			</tr>
			<c:forEach var="member" items="${member}">
			<tr>
				<td>${member.mem_num}</td>
				<td>
					<c:if test="${member.auth>0}">
					<a href="adminMemberDetail.do?mem_num=${member.mem_num}">${member.id}</a>
					</c:if>
					<c:if test="${member.auth==0}">
					${member.id}
					</c:if>
				</td>
				<td>
					<fmt:formatNumber value="${member.order_total_price}"/> 포인트
				</td>
				<td>
					<c:if test="${member.auth==0}">탈퇴</c:if>
					<c:if test="${member.auth==1}">정지</c:if>
					<c:if test="${member.auth==2}">일반</c:if>
					<c:if test="${member.auth==9}">관리</c:if>
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