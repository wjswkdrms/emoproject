<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:choose>
  <c:when test="${mem_auth == 1}">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/findPw.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page">
	<div class="page_outer"> <!-- 전체 페이지 크기 지정 -->
	  <div class="page_inner"> <!--메인 페이지 중앙 정렬, 크기 지정-->
    <h2 id="title">아이디 찾기 완료</h2>
    <h2 id="title">아이디는 <strong>${mem_id}</strong> 입니다.</h2>
	     <div class="page_button2">
	       <div class="page_button_box">
          <input type="button" class="button3" value="로그인" onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
          <input type="button" class="button3" value="비밀번호 찾기" onclick="location.href='${pageContext.request.contextPath}/member/findPwForm.do'">
          <input type="button" class="button3" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/member/registerUserFor.do'">
        </div>
      </div>
  </div>
</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
  </c:when>
  <c:otherwise>
  <script>
  	alert('해당 정보로 가입된 아이디가 없습니다.');
  	history.go(-1);
  </script>
  </c:otherwise>
</c:choose>  
  
  