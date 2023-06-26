<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="whole">
		<div class="container">
			<div class="box">
				<div class="title">
					관리자 페이지
				</div>
				<div class="content-box">
					<ul class="main-content">
						<li class="icon-detail">
							<a href="${pageContext.request.contextPath}/product/productlist.do"><span class="icon1"></span>상품 등록</a>
						</li>
						<li class="icon-detail">
							<a href="${pageContext.request.contextPath}/member/adminMemberList.do"><span class="icon2"></span>회원 관리</a>
						</li>
						<li class="icon-detail">
							<a><span class="icon3"></span>매출 확인</a>
						</li>
						<li class="icon-detail">
							<a href="${pageContext.request.contextPath}/announce/announce.do"><span class="icon4"></span>고객 센터</a>
						</li>
						<li class="icon-detail">
							<a href="${pageContext.request.contextPath}/member/adminOrderList.do"><span class="icon5"></span>주문 관리</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>