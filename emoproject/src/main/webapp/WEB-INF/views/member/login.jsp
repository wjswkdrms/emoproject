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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page">
	<div class="page_outer"> <!-- 전체 페이지 크기 지정 -->
	  <div class="page_inner"> <!--메인 페이지 중앙 정렬, 크기 지정-->
	    <h2 id="title">정지된 회원 아이디입니다.</h2>
	     <div class="page_button2">
	       <div class="page_button_box">
	         <input type="button" class="button3" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	       </div>
	    </div>
	  </div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
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
  
  
  
  
  
  
  