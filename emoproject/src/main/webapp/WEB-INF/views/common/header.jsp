<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header 시작 -->
<div class="main-head">
	<div class="top-box">
		<ul>
			<li>
				<a href="#">로그인</a>
			</li>
			<li>
				<a href="#">회원가입</a>
			</li>
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



