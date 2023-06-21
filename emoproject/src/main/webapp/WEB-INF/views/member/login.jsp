<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:choose>
  <c:when test="${auth == 1}">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberPageAll_style.css">
</head>
<body>
<div class="page_outer"> <!-- 전체 페이지 크기 지정 -->
  <div class="page_inner"> <!--메인 페이지 중앙 정렬, 크기 지정-->
    <h2 id="title">회원 정보</h2>
    <h2 id="title_content"><a>정지된 회원 아이디입니다.</a></h2>
      <div class="page_login">
        <ul class="button_all" id="page_button_1">
          <li><input type="button" class="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'"></li>
        </ul>
      </div>
  </div>
</div>
</body>
</html>
  </c:when>
  <%-- auth가 1이 아닌 경우 --%>
  <c:otherwise>
  <script>
  	alert('아이디 또는 비밀번호가 불일치합니다.');
  	history.go(-1);
  </script>
  </c:otherwise>
</c:choose>  
  
  
  
  
  
  
  