<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	//장바구니 상품 삭제 이벤트 연결
	$('.cart-del').on('click',function(){
		$.ajax({
			url:'deleteCart.do',
			type:'post',
			data:{cart_num:$(this).attr('data-cartnum')},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'success'){
					alert('선택하신 상품을 삭제했습니다.');
					location.href='list.do';
				}else{
					alert('장바구니 상품 삭제 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//장바구니 상품 수량 변경 이벤트 연결
	$('.cart-modify').on('click',function(){
		let input_quantity = 
			$(this).parent().find(
				'input[name="order_quantity"]');
		if(input_quantity.val()==''){
			alert('수량을 입력하세요');
			input_quantity.focus();
			return;
		}
		if(input_quantity.val()<1){
			alert('상품의 최소 수량은 1입니다.');
			                   //태그에 명시한 변경전 value 값을 읽어옴
			input_quantity.val(input_quantity.attr('value'));
			return;                   
		}
		
		//서버와 통신
		$.ajax({
			url:'modifyCart.do',
			type:'post',
			data:{cart_num:$(this).attr('data-cartnum'),item_num:$(this).attr('data-itemnum'),order_quantity:input_quantity.val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'noSale'){
					alert('판매가 중지되었습니다.');
					location.href='list.do';
				}else if(param.result == 'noQuantity'){
					alert('상품의 수량이 부족합니다.');
					location.href='list.do';
				}else if(param.result == 'success'){
					alert('상품의 개수가 수정되었습니다.');
					location.href='list.do';
				}else{
					alert('장바구니 상품 개수 수정 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
	});
	
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>[${user_id}]님의 장바구니</h2>
		<c:if test="${empty list}">
		<div class="result-display">
			장바구니에 담은 상품이 없습니다.
		</div>	
		</c:if>
		<c:if test="${!empty list}">
		<form id="cart_order" 
		  action="${pageContext.request.contextPath}/order/orderForm.do" method="post">
		
			<table>
				<c:forEach var="cart" items="${list}">
				<tr>
					<td>
						<input type="checkbox">
					</td>
					<td>
						<img src="${pageContext.request.contextPath}/upload/${cart.product.product_photo1}" width="80">
					</td>
					<td>
						${cart.product.product_title}
					</td>
					<td class="align-center">						
							<input type="number" name="order_quantity"
							  min="1" max="${cart.product.product_stock}"
							  autocomplete="off" 
							  value="${cart.cart_quantity}">
							<br>
							<input type="button" value="변경" 
							 class="cart-modify" 
							 data-cartnum="${cart.mem_cart_num}"
							 data-itemnum="${cart.product_num}">
					</td>
					<td>
						${cart.product.product_price*cart.cart_quantity}
					</td>
				</tr>
				</c:forEach>
			</table>
			<div >
				
				<div>
					<span>보유 포인트</span>
					<span>${point}원</span>
				</div>
				<div>
					<span>최종금액</span>
					<span>${all_total}원</span>
				</div>
				<hr size="1" noshade="noshade" width="100%">
				<div>
					<span>결제시 포인트 잔액</span>
					<span>${point-all_total}원</span>
				</div>
				<input type="button" value="결제">
			</div>
		</form>
		
		</c:if>
	</div>

</body>
</html>