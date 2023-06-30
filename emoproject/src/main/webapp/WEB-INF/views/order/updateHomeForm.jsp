<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소생성</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		//주문 유효성 체크
		$('#order_form').submit(function(){
			let items = document.querySelectorAll(
					   '.input_style');
			 for(let i=0;i<items.length;i++){
				 
			    if(items[i].value.trim()==''){
					let label = 
						document.querySelector(
					 'label[for="'+items[i].id+'"]');
					alert(label.textContent + ' 항목 필수 입력');
					items[i].value = '';
					items[i].focus();
					return false;
			    }
			}//end of for
		});
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page_outer">	
	<!-- 내용 시작 -->
	<div class="page_inner">
		
		<form id="order_form" class="align-center"
		  action="${pageContext.request.contextPath}/order/userUpdateHome.do" method="post">
			<ul>
				<li class="line">				
					<label for="zipcode">우편번호</label>
					<input type="number" name="receive_post" class="input_style" value="${home.getMem_home_zipcode()}"
					  id="zipcode" maxlength="5" readonly style="background:gray;">
				</li>
				<li class="line">
					<label for="address1">주소</label>
					<input type="text" name="receive_address1" class="input_style" value="${home.getMem_home_address1()}"
					  id="address1" maxlength="30" readonly style="background:gray;">
				</li>
				<li class="line">
					<label for="address2">상세 주소</label>
					<input type="text" name="receive_address2" class="input_style" value="${home.getMem_home_address2()}"
					  id="address2" maxlength="30">
				</li> 
				<li class="line">
					<label for="receive_name">배송지 이름</label>
					<input type="text" name="receive_name" class="input_style" value="${home.getMem_home_name()}"
					  id="receive_name" maxlength="10">
				</li>
				<li class="line">
					<label for="receive_phone">전화번호</label>
					<input type="text" name="receive_phone" class="input_style" value="${home.getMem_home_cell()}"
					  id="receive_phone" maxlength="15">
				</li>
				<li class="line">
				<input type="submit" value="수정" class="button">
				<input type="button" value="홈으로" class="button"
				 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				 </li>
			</ul> 
		</form>
	</div>
	<!-- 내용 끝 -->
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>