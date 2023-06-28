<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header 시작 -->
<!--  -->
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
    function performSearch() {
        let searchText = $('#search-text-box').val();
        
        // 검색 페이지로 이동
        window.location.href = '../search/searchCategoryMain.do?searchText=' + encodeURIComponent(searchText);
    }

    $('#search-icon').click(function(){
        performSearch();
    });

    $('#search-text-box').keypress(function(event) {
        if (event.which === 13) {  // Enter 키의 keyCode는 13입니다.
            performSearch();
        }
    });
    
    /*
    let user_id = $('#id_check_tag').val();
	if(user_id.length>0) {
    	$('#add_cart_count').append(cart_output);
    	
    	$.ajax({
	        url: '${pageContext.request.contextPath}/cart/cartCount.do',
	        type: 'post',
	        dataType: 'json',
	        success: function (param) {
	        	alert('ajax 성공');
	            let cart_output = '<span id="cart_count">'+param.cartCount_ajax+'</span>';
	            $('#add_cart_count').append(cart_output);
	        },
	        error: function () {
	            alert('실패');
	        }
	    });
    };
    */
    
    let user_id_exsist = $('#id_check_tag').val();
    if(user_id_exsist.length>0) {
    	$.ajax({
    		url:'../cart/cartCount.do',
    		type:'post',
    		dataType:'json',
    		success:function(param){
    			$('#cart_count').text()
    			let count_output = '<span id ="cart_count">'+param.cartCount+'</span>';
    			$('#cart-icon').append(count_output);
    				
    				
    				/* 헤더에 들어가는 영역 입니다. */
    				/*$('#cart_count').text();
    				$('#cart_count').text(param.cartCount_ajax);*/
    				
    				
    			
    		},
    		error:function(){
    			alert('네트워크 오류 발생');
    		}
    	});
    }
    
});






</script>
<div id="unknown_box"></div>
<div class="main-head" id="header_docker">
	<div class="top-box">
	<input type="hidden" id="id_check_tag" value="${user_id}">
		<ul>
			<c:if test="${user_id != null }">
				<li>
				<strong>${user_id}</strong>님 환영합니다. 
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
				</li>
			</c:if>
			<!-- 쿠폰 기능 추가 후 활성화 예정. css 추가 필요
			<c:if test="${count_coupon != 0}">
				<li>
					<a href="${pageContext.request.contextPath}/member/coupon.do">
					<img id="notify" src="${pageContext.request.contextPath}/images/notify.png">
					</a>
				</li>
			</c:if>
			 -->
			
			<c:if test="${user_id != null }">
			<!-- 
				<li>
					<a href="${pageContext.request.contextPath}/member/pwdCheckForm.do">회원 탈퇴 - 비밀번호 확인</a>
				</li>
			 -->
			 	<c:if test="${user_auth <= 2}">
			 	<li>
			 		
					<a href="${pageContext.request.contextPath}/member/orderList.do">내 정보</a>
				</li>
				</c:if>
				<c:if test="${user_auth == 9 && !empty user_num}">
				<li>
					<a href="${pageContext.request.contextPath}/member/adminPage.do">관리자 페이지</a>
				</li>
				</c:if>
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
			<c:if test="${user_auth == 9 && !empty user_num}">
				<li>
					<a href="${pageContext.request.contextPath}/product/productlist.do">상품등록</a>
				</li>
			</c:if>
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
				<a href="${pageContext.request.contextPath}/member/jjimList.do"><img src="${pageContext.request.contextPath}/images/emo_icon_fav01.png"></a>

				<a href="${pageContext.request.contextPath}/cart/list.do" id="cart-icon">
					<img src="${pageContext.request.contextPath}/images/emo_icon_cart01.png">
					<!-- ajax 처리 필요 -->
					<!--  <span id ="cart_count">${cartCount}</span>
					 ajax 처리 -->
				</a>
				<a href="${pageContext.request.contextPath}/order/userHomeList.do"><img src="${pageContext.request.contextPath}/images/emo_icon_home01.png"></a>
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