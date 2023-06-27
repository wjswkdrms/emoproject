<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
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
	<div class="whole">
		<div class="container">
			<div class="box">
			<!-- 내용 시작 -->
				<div class="title">
					회원 관리
				</div>
				<div class="content-box">			
					<%-- 검색은 링크 가능하기 때문에 get방식으로 --%>
					<!-- 검색창 시작 -->
					<form id="search_form" action="adminMemberList.do"
					                          method="get">
						<div class="button-box">					                          
								<ul class="main-content">
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
											<input class="small-button" type="submit" value="검색">
										</li>
										<li>
											<input class="small-button" type="button" value="목록" onclick="location.href='adminMemberList.do'">
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
							<th class="row-title"><b>회원번호</b></th>
							<th class="row-title"><b>아이디</b></th>
							<th class="row-title"><b>누적금액</b></th>
							<th class="row-title"><b>등급</b></th>
						</tr>
						<c:forEach var="member" items="${member}">
						<tr>
							<td class="row-title">${member.mem_num}</td>
							<td class="row-title">
								<c:if test="${member.auth>0}">
								<a href="adminMemberDetail.do?mem_num=${member.mem_num}">${member.id}</a>
								</c:if>
								<c:if test="${member.auth==0}">
								${member.id}
								</c:if>
							</td>
							<td class="row-title">
								<fmt:formatNumber value="${member.order_total_price}"/> 포인트
							</td>
							<td class="row-title">
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
				<!-- 내용 끝 -->
				</div>
			</div>
		</div>
	</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>