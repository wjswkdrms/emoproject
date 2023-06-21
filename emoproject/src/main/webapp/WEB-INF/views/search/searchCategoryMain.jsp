<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    $('#output').empty();
    sortProducts(1);
    $('#sorted_01').click(function(){
        sortProducts(1);
    });
    
    $('#sorted_02').click(function(){
        sortProducts(2);
    });
    
    $('#sorted_03').click(function(){
        sortProducts(3);
    });
    
    $('#sorted_04').click(function(){
        sortProducts(4);
    });
    
    $('#sorted_05').click(function(){
        sortProducts(5);
    });
});

function sortProducts(sorted_num) {
    let ajaxUrl = '${pageContext.request.contextPath}/search/searchSortedNavigator.do';

    $('#output').empty();
    
    $.ajax({
        url: ajaxUrl,
        type: 'post',
        data: {
            'product_category': $('#cat_num').val(),
            'sorted_navigator_num': sorted_num
        },
        dataType: 'json',
        success: function (param) {
            $(param.list).each(function (index, item) {
                let output = '<div class="product-nums">';
                output += '<a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=' + item.product_num + '">';
                output += '<img src="${pageContext.request.contextPath}/upload/' + item.product_photo1 + '">';
                output += '</a>';
                output += '<span>' + item.product_title + '</span>';
                output += '<p>가격 : ' + item.product_price.toLocaleString() + ' 원</p>';
                output += '</div>';
                $('#output').append(output);
            });
        },
        error: function () {
            alert('실패');
        }
    });
}

</script>
</head>
<body>
	<input type="hidden" id="cat_num" value="${product_category}">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<hr class="horizon-bar-01">
	<div class="main-contents">
		<div class="search-navigator">
			<ul>
				<li id="sorted_01">최신순</li>
				<li id="sorted_02">추천순</li>
				<li id="sorted_03">판매량순</li>
				<li id="sorted_04">높은가격순</li>
				<li id="sorted_05">낮은가격순</li>
			</ul>
		</div>
		<div class="contents-head">${product_category_name}</div>
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
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>