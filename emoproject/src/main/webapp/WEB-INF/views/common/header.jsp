<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header 시작 -->
<div class="main-head">
	<div class="top-box">
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/announce/announce.do">고객센터</a>
			</li>
			<li>
				<a href="#">상품등록</a>
			</li>
		</ul>
	</div>
	<div class="mid-box">
		<ul>
			<li class="main-logo">
				<a href="${pageContext.request.contextPath}/main/main.do"><img src="${pageContext.request.contextPath}/images/emo_main_logo.png"></a>
			</li>
			<li class="li-search">
				<div id="search-product">
					<input type="text" id="search" class="search">
					<img src="${pageContext.request.contextPath}/images/emo_icon_search01.svg">
				</div>
			</li>
			<li class="icons-menu">
				<a href="#"><img src="${pageContext.request.contextPath}/images/emo_icon_fav01.png"></a>
				<a href="${pageContext.request.contextPath}/cart/list.do"><img src="${pageContext.request.contextPath}/images/emo_icon_cart01.png"></a>
				<a href="#"><img src="${pageContext.request.contextPath}/images/emo_icon_home01.png"></a>
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



