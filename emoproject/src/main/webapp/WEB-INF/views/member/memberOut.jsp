<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:choose>
  <c:when test="${check == 1}">
<script>
	alert('탈퇴 완료!');
	location.href='${pageContext.request.contextPath}/main/main.do';
</script>
  </c:when>
  <%-- auth가 1이 아닌 경우 --%>
  <c:otherwise>
  <script>
  	alert('아이디 또는 비밀번호가 불일치합니다.');
  	history.go(-1);
  </script>
  </c:otherwise>
</c:choose>  