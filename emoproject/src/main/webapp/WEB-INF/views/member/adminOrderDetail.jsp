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
						<input type="button" value="목록" onclick="location.href='adminOrderList.do'">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>