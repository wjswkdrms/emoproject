<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page">
<div class="page_outer"> <!-- 전체 페이지 크기 지정 -->
  <div class="page_inner"> <!--메인 페이지 중앙 정렬, 크기 지정-->
        <h2 id="title">
              회원가입이 완료되었습니다.<br>
          즐거운 쇼핑 되세요.
        </h2>
      <div class="page_button2">
        <div class="page_button_box">
          <input type="button" class="button3" value="로그인" onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
          <input type="button" class="button4" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
        </div>
      </div>
  </div>
</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



