<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이모마켓 | 검색</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/content_view_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	let pageSize = 12;//화면에 보여줄 레코드 수
	let pageBlock = 10;//페이지 표시 단위
	let currentPage;//현재 보고 있는 화면
	let sorted_num;
	
    $('#output').empty();
    sortProducts(1);
    $('#sorted_01').click(function(){
    	sorted_num = 1;
        sortProducts();
    });
    
    $('#sorted_02').click(function(){
    	sorted_num = 2;
        sortProducts();
    });
    
    $('#sorted_03').click(function(){
    	sorted_num = 3;
        sortProducts();
    });
    
    $('#sorted_04').click(function(){
    	sorted_num = 4;
        sortProducts();
    });
    
    $('#sorted_05').click(function(){
    	sorted_num = 5;
        sortProducts();
    });
    
    $('#sorted_06').click(function(){
    	sorted_num = 6;
        sortProducts();
    });
    
  //페이지 설정
	function setPage(totalCount){
	  	console.log('페이지 처리 시작');
	  	console.log('개수:'+totalCount);
		//ul 태그 초기화
		$('.paging-btn').empty();
		if(totalCount == 0){
			return;
		}
		
		var totalPage = Math.ceil(totalCount/pageSize);
		
		if(currentPage == undefined || currentPage == ''){
			currentPage = 1;
		}
		//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정
		if(currentPage > totalPage){
			currentPage = totalPage;
		}
		
		//시작 페이지와 마지막 페이지 값을 구하기
		var startPage = 
			Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;
		var endPage = startPage + pageBlock - 1;
		
		//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if(endPage > totalPage){
			endPage = totalPage;
		}
		
		var add='';
		if(startPage>pageBlock){
			add += '<li data-page='+(startPage-1)+'>&lt;</li>';
		}
		
		for(var i=startPage;i<=endPage;i++){
			add += '<li data-page='+i+'>'+i+'</li>';
		}
		if(endPage < totalPage){
			add += '<li data-page='+(startPage+pageBlock)+'>&gt;</li>';;
		}
		//ul 태그에 생성한 li를 추가
		$('.paging-btn').append(add);
	}  
  //페이지 버튼 이벤트 연결
	$(document).on('click','.paging-btn li',function(){
		//페이지 번호를 읽어들임
		currentPage = $(this).attr('data-page');
		//목록 호출
		sortProducts();
	});
  
	function sortProducts() {
	    let ajaxUrl = '${pageContext.request.contextPath}/search/searchSortedNavigator.do';

	    $('#output').empty();
	    
	    $.ajax({
	        url: ajaxUrl,
	        type: 'post',
	        data: {
	            'product_category': $('#cat_num').val(),
	            'sorted_navigator_num': sorted_num,
	            'searchText': $('#sch_name').val(),
	            'pageSize':pageSize,
	            'pageNum':currentPage,
	            'pageBlock':pageBlock
	        },
	        dataType: 'json',
	        success: function (param) {
	        	$('#search_count').text('검색에 대한 결과 '+param.count+' 건');
	            $(param.list).each(function (index, item) {
	                let output = '<div class="product-nums">';
	                if(item.product_status == 2) {
	                	output += '<a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=' + item.product_num + '">';
	                	output += '<img src="${pageContext.request.contextPath}/upload/' + item.product_photo1 + '">';
	                	output += '</a>';
	                } else if(item.product_status <2) {
	                	output += '<a style="pointer-events: none;">';
	                	output += '<img src="${pageContext.request.contextPath}/upload/' + item.product_photo1 + '">';
	                	output += '<img src="${pageContext.request.contextPath}/images/sold_out.jpg" class="sold-out-image">';
	                	output += '</a>';
	                }
	                output += '<div class="product_result">';
	                output += '<span>' + item.product_title + '</span>';
	                if(item.product_discount > 0) {
	                output += '<p class="product_discount">할인률 : ' + item.product_discount + '% <strike class="product_price">가격 : ' + item.product_price.toLocaleString() + '원</strike></p>';
	                
	                }
	                output += '<p class="product_price_sales">가격 : ' + item.product_price_sales.toLocaleString() + ' 원</p>';
	                output += '</div>';
	                output += '</div>';
	                $('#output').append(output);
	            });
	            /*
	            $(param.page).each(function (index.item){
	            	$('#paging-class').append(item);
	            }); 
	            */
	          //페이지 설정
	    		setPage(param.count);
	                
	        },
	        error: function () {
	            alert('실패');
	        }
	    });
	}
});


</script>
</head>
<body>
	<input type="hidden" id="cat_num" value="${product_category}">
	<input type="hidden" id="sch_name" value="${product_category_name}">
	<jsp:include page="/WEB-INF/views/common/navigator.jsp"/>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<hr class="horizon-bar-01">
	<div class="main-contents">
		<div class="search-navigator">
			<ul>
				<li id="sorted_01">최신순</li>
				<li id="sorted_02">실시간 찜순</li>
				<li id="sorted_03">판매량순</li>
				<li id="sorted_04">높은가격순</li>
				<li id="sorted_05">낮은가격순</li>
				<li id="sorted_06">높은할인순</li>
			</ul>
		</div>
		
		<div class="contents-head">"${product_category_name}"<span> 에 대한 검색 결과</span></div>
		<div id="search_count"></div>
		<div id = "output" class="main-contents"></div>
		<!-- 최초 방문시에 나옴 -->
			<!--  
			<c:if test="${firstVisited == firstVisited}">
			<c:forEach var="list" items="${categoryList}" >
			<div class="product-nums">
				<a href="productDetail.do?product_num=${list.product_num}"><img src="${pageContext.request.contextPath}/upload/${list.product_photo1}"></a>
				<span>${list.product_title}</span>
				<p>가격 : <fmt:formatNumber value="${list.product_price}" /> 원</p>
			</div>
			</c:forEach>
			</c:if> 
			-->
			
			
			<!-- 카테고리 눌렀을 때  -->
		<div id = "paging-box"><ul class="paging-btn"></ul></div>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>