<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문내역 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="whole">
		<div class="container">
			<div class="box">
				<div class="title">
					주문내역 상세
				</div>
				<div class="content-box">
					<div class="content-detail">
						<div class="detail-title">주문번호</div>
						<div class="specific">${detail.order_num}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">결제일</div>
						<div class="specific">${detail.order_date}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">배송상태</div>
						<div class="specific">
							<b>
							<c:if test="${detail.order_status==0}">
								<div class="expire">주문완료
							<input class="small-button orderModify_btn" type="button" value="배송중">
							</div>
							</c:if>
							<c:if test="${detail.order_status==1}">
								<div class="stop">배송중
							<input class="small-button orderModify_btn" type="button" value="배송완료">
							</div>
							</c:if>
							<c:if test="${detail.order_status==2}">
								배송완료
							</c:if>
										 	<script type="text/javascript">
												$(function(){
													$('.orderModify_btn').click(function(){
														let choice=confirm(this.value+' 처리 하시겠습니까?');
														if(choice){
															$.ajax({
																url:'adminOrderModify.do',
																type:'post',
																data:{order_num:${detail.order_num},order_status:${detail.order_status}+1},
																dataType:'json',
																success:function(param){
																	if(param.result == 'logout'){
																		alert('로그인 후 사용하세요');
																	}else if(param.result == 'success'){
																		location.reload();
																	}else if(param.result == 'wrongAccess'){
																		alert('잘못된 접속입니다.');
																	}else{
																		alert('파일 삭제 오류 발생');
																	}														
																},
																error:function(){
																	alert('네트워크 오류 발생');														
																}
															});
														}
													});
												});
											</script>								
							</b>
						</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">아이디</div>
						<div class="specific">${detail.mem_id}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">구매내역</div>
						<div class="specific">${detail.order_product_name}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">소진 포인트</div>
						<div class="specific">
						<fmt:formatNumber value="${detail.order_product_total}"/> 포인트
						</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">우편번호</div>
						<div class="specific">${home.mem_home_zipcode}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">주소</div>
						<div class="specific">${home.mem_home_address1}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">상세주소</div>
						<div class="specific">${home.mem_home_address2}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">전화번호</div>
						<div class="specific">${home.mem_home_cell}</div>
					</div>	
					<div class="button-box">
						<input class="small-button" type="button" value="목록" onclick="location.href='adminOrderList.do'">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>