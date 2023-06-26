<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기 작성 완료</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberPageAll_style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page_outer"> <!-- 전체 페이지 크기 지정 -->
  <div class="page_inner"> <!--메인 페이지 중앙 정렬, 크기 지정-->
    <h2 id="title">후기 작성 완료</h2>
      <div class="page_login">
        <ul class="button_all" id="page_button_1">
          <li><input type="button" class="button" value="돌아가기" onclick="location.href='${pageContext.request.contextPath}/member/productAfter.do'"></li>
          <li><input type="button" class="button" value="후기 등록 상품 보기" onclick="location.href='${pageContext.request.contextPath}/product/productDetail.do?product_num=${product_num}'"></li>
          <li><input type="button" class="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'"></li>
        </ul>
      </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>