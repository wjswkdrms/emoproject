<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header 시작 -->
<div class="main-head">
	<div class="top-box">
		<ul>
			<c:if test="${!empty user_num && user_auth == 9}">
			<li>
				<a href="${pageContext.request.contextPath}/member/memberList.do">회원관리</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/item/list.do">상품관리</a>
			</li>
			</c:if>
			<c:if test="${!empty user_num && user_auth == 2}">
			<li>
				<a href="${pageContext.request.contextPath}/cart/list.do">장바구니</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
			</li>
			</c:if>
			<c:if test="${empty user_num}">
			<li>
				<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
			</li>
			</c:if>
			<c:if test="${!empty user_num}">
			<li class="menu-logout">
				[<span>${user_id}</span>]
				<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
			</li>
			</c:if>
			<li>
				<a href="#">고객센터</a>
			</li>
		</ul>
	</div>
	<div class="mid-box">
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}/main/main.do"><img src="${pageContext.request.contextPath}/images/emo-logo.png"></a>
			</li>
			<li>
				<label for="search">상품검색창</label>
				<input type="text" id="search" class="search">
			</li>
			<li>
				<a href="#">찜</a>
			</li>
			<li>
				<a href="#">장바구니</a>
			</li>
			<li>
				<a href="#">배송지</a>
			</li>
		</ul>
	</div>
	<div class="category-box">
		<ul>
			<li>
				<a href="#">농산물</a>
			</li>
			<li>
				<a href="#">수산물</a>
			</li>
			<li>
				<a href="#">정육</a>
			</li>
			<li>
				<a href="#">유제품</a>
			</li>
			<li>
				<a href="#">음료</a>
			</li>
			<li>
				<a href="#">베이커리</a>
			</li>
		</ul>
	</div>
	
</div>
<!-- header 끝 -->



