<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header 시작 -->
<!--  -->
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
    $('#search-icon').click(function(){
		let searchText = $('#search-text-box').val();
        
        // 검색 페이지로 이동
        window.location.href = '../search/searchCategoryMain.do?searchText=' + encodeURIComponent(searchText);
    });
});

</script>
<div class="main-head">
	<div class="top-box">
		<ul>
			<c:if test="${user_id != null }">
			<!--${user_id } 로그인 중<br /> -->
				<li>
					<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
				</li>
			</c:if>
			
			<c:if test="${user_id != null }">
			<!-- 
				<li>
					<a href="${pageContext.request.contextPath}/member/pwdCheckForm.do">회원 탈퇴 - 비밀번호 확인</a>
				</li>
			 -->
			 	<li>
					<a href="${pageContext.request.contextPath}/member/memberEditForm.do">내 정보</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/member/pwdCheckForm.do">회원 탈퇴(임시)</a>
				</li>
			</c:if>
			
			<c:if test="${user_id == null }">
				<li>
					<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
				</li>
			</c:if>
			
			<c:if test="${user_id == null }">
				<li>
					<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
				</li>
			</c:if>
			

			
			<li>
				<a href="${pageContext.request.contextPath}/announce/announce.do">고객센터</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/product/productlist.do">상품등록</a>
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
				
					<input type="text" id="search-text-box" class="search-text-box">
					
					<img src="${pageContext.request.contextPath}/images/emo_icon_search01.svg" id="search-icon">
				
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
				<a href="${pageContext.request.contextPath}/search/searchCategoryMain.do?product_category=1" id="nongsan" class="nongsan">농산물</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/search/searchCategoryMain.do?product_category=2" id="fish" class="fish">수산물</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/search/searchCategoryMain.do?product_category=3" id="meats" class="meats">정육</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/search/searchCategoryMain.do?product_category=4" id="milk" class="milk">유제품</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/search/searchCategoryMain.do?product_category=5" id="drink" class="drink">음료</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/search/searchCategoryMain.do?product_category=6" id="bakery" class="bakery">베이커리</a>
			</li>
		</ul>
	</div>
	
</div>
<!-- header 끝 -->



