<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- action에서 error가 1이면 입력 오류, 아니면 오류가 없음 -->    
<c:choose>
  <c:when test="${error != 1}">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page">
<div class="page_outer"> <!-- 전체 페이지 크기 지정 -->
  <div class="page_inner"> <!--메인 페이지 중앙 정렬, 크기 지정-->
    <h2 id="title">개인정보 수정 완료</h2>
      <div class="page_button2">
        <div class="page_button_box">
          <input type="button" class="button3" value="확인" onclick="location.href='${pageContext.request.contextPath}/member/myEditForm.do'">
          <input type="button" class="button4" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
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
  	alert('수정하려는 정보에 * 이 들어갈 수 없습니다.');
  	history.go(-1);
  </script>
  </c:otherwise>
</c:choose>  