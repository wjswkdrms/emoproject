<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEdit_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
  <div id="center_set">
    <!-- 내용 시작 -->
    <ul>
	    <li>주문 내역</li>
	    <li>문의 내역</li>
	    <li>찜한 상품</li>
	    <li>상품 후기</li>
	    <li>쿠폰함</li>
	    <li>개인정보 수정</li>
	    <li>회원 탈퇴</li>
    </ul>
    <!-- 내용 끝 -->
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



