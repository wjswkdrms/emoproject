<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문내역 관리</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
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
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="whole">
		<div class="container">
			<div class="box">	
				<div class="title">
					주문내역 관리
				</div>
				<%-- 검색은 링크 가능하기 때문에 get방식으로 --%>
				<!-- 검색창 시작 -->
				<div class="content-box">  
				<form id="search_form" action="adminOrderList.do"
				                          method="get">
					<div class="button-box">
						<ul class="main-content">
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
								<input class="small-button" type="submit" value="검색">
							</li>
							<li>
								<input class="small-button" type="button" value="목록" onclick="location.href='adminOrderList.do'">
							</li>
						</ul>      
					</div>                    
				</form>
				<!-- 검색창 끝 -->
				<c:if test="${count == 0}">
				<div class="row-content">
					표시할 회원정보가 없습니다.
				</div>
				</c:if>
				<c:if test="${count > 0}">
				<table class="form-box">
					<tr>
						<th class="row-title"><b>주문번호</b></th>
						<th class="row-title"><b>회원번호</b></th>
						<th class="row-title"><b>아이디</b></th>
						<th class="row-title"><b>배송상태</b></th>
					</tr>
					<c:forEach var="order" items="${order}">
					<tr>
						<td class="row-title">${order.order_num}</td>
						<td class="row-title">${order.mem_num}</td>
						<td class="row-title">
							<a href="adminOrderDetail.do?order_num=${order.order_num}" data-hidden="${order.mem_num}">${order.id}</a>
						</td>
						<td class="row-title">
							<b>
							<c:if test="${order.order_status==0}">
								<div class="expire">주문완료</div>
							</c:if>
							<c:if test="${order.order_status==1}">
								<div class="stop">배송중</div>
							</c:if>
							<c:if test="${order.order_status==2}">
								배송완료
							</c:if>
							</b>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="align-center">${page}</div>
				</c:if>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
	</div>
			<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>