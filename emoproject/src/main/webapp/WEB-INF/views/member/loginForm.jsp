<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#login_form').submit(function(){
			if($('#id').val().trim()==''){
				alert('아이디를 입력하세요');
				$('#id').val('').focus();
				return false;
			}
			if($('#passwd').val().trim()==''){
				alert('비밀번호를 입력하세요');
				$('#passwd').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
  <div id="center_set">
    <!-- 내용 시작 -->
    <h2 id="main_title">로그인</h2>
    <div class="content-main">
      <form id="login_form" action="login.do"
            method="post">
        <ul>
          <li>
            <label for="id">아이디</label>
            <input type="text" name="id" class="input_style"
                   id="id" maxlength="12"
                   autocomplete="off">
          </li>
          <li>
            <label for="passwd">비밀번호</label>
            <input type="password" name="passwd" class="input_style"
                   id="passwd" maxlength="12">
          </li>
        </ul>
        <div class="align-center">
          <input type="submit" class="bt" value="로그인">
          <input type="button" class="bt" value="홈으로"
                 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
        </div>
      </form>
    </div>
    <!-- 내용 끝 -->
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



