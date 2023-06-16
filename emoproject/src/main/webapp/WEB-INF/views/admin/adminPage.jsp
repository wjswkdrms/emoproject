<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div>
		<div>
			<div class="page-name">고객센터</div>
		</div>
		<div>
			<ul>
				<li>
					<a><span></span>상품 등록</a>
				</li>
				<li>
					<a><span></span>회원 관리</a>
				</li>
				<li>
					<a><span></span>매출 확인</a>
				</li>
				<li>
					<a><span></span>쿠폰 관리</a>
				</li>
				<li>
					<a><span></span>고객 센터</a>
				</li>
				<li>
					<a><span></span>주문 관리</a>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>