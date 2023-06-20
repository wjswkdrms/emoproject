<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:choose>
  <c:when test="${mem_auth == 1}">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
  <div id="center_set">
    <!-- 내용 시작 -->
    <h2 id="main_title">비밀번호 찾기 완료</h2>
    <div class="content-main">
      	<a>비밀번호는 <strong>${mem_passwd}</strong> 입니다.</a>
     </div>
    <div class="align-center">
      <input type="button" class="bt" value="로그인"
             onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
      <input type="button" class="bt" value="아이디 찾기"
             onclick="location.href='${pageContext.request.contextPath}/member/findIdForm.do'">
      <input type="button" class="bt" value="회원가입"
			 onclick="location.href='${pageContext.request.contextPath}/member/registerUserForm.do'">
    </div>
    <!-- 내용 끝 -->
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
  </c:when>
  <c:otherwise>
  <script>
  	alert('해당 정보로 존재하는 비밀번호가 없습니다.');
  	history.go(-1);
  </script>
  </c:otherwise>
</c:choose>  
  
  