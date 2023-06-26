<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header 시작 -->
<style>

.promotion-box{
	
	width: 1600px; /* width 나중에 설정*/
	height:370px;
	margin: 0 auto;
	/*display:flex;
	justify-content: center;
	align-items: center;
	*/
	overflow:hidden;
	margin-top:10px;
	position: relative;
	
}

.promotion-box img {
	display:none;
	object-fit: cover;
	
	
}
.promotion-box img.active{
	position:absolute;
	top:50%; 
	left:50%; 
	transform:translate(-50%, -50%);
	display:block;
	
}


.arrow-right {
    width: 40px;
    height: 40px;
	position:absolute;
    top:50%; 
    transform:translateY(-50%);
    margin-right : 100px;
    cursor: pointer;
    z-index: 2;
    right: 10px;
    
    
}


.arrow-right img {
	display:block;
	width:40px;
	height:40px;
}


.arrow-left {
    width: 40px;
    height: 40px;
	position:absolute;
    top:50%; 
    transform:translateY(-50%);
    margin-left : 100px;
    cursor: pointer;
    z-index: 2;
    left:10px;
}

.arrow-left img {
    display: block;
    width: 40px;
    height: 40px;
    transform: rotate(180deg); /* 이미지를 180도 회전 */
}

</style>

<div class="promotion-box" id="promotion_docker">
		<div class="arrow-right">
			<img src="${pageContext.request.contextPath}/images/emo_icon_right01.svg">
		</div>
		<div class="arrow-left">
			<img src="${pageContext.request.contextPath}/images/emo_icon_right01.svg">
		</div>
		<a href="#"><img id="pro-01" src="${pageContext.request.contextPath}/images/promotion-01.jpg" class="active"></a>
		<a href="#"><img id="pro-02" src="${pageContext.request.contextPath}/images/promotion-02.jpg"></a>
		<a href="#"><img id="pro-03" src="${pageContext.request.contextPath}/images/promotion-03.jpg"></a>
		<a href="#"><img id="pro-04" src="${pageContext.request.contextPath}/images/promotion-04.jpg"></a>
		<a href="#"><img id="pro-05" src="${pageContext.request.contextPath}/images/promotion-05.jpg"></a>
		<a href="#"><img id="pro-06" src="${pageContext.request.contextPath}/images/promotion-06.jpg"></a>
</div>


