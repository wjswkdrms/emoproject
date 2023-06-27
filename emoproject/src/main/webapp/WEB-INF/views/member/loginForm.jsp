<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
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
<div class="page_outer"> <!-- 전체 페이지 크기 지정 -->
  <div class="page_inner">
    <h2 id="title">로그인</h2>
    <form class="login_form" action="login.do" method="post">
      <div class="page_input">
        <div class="page_input_box">
          <input type="text" name="id" class="input_style" id="id" maxlength="12" autocomplete="off" placeholder="아이디를 입력해주세요">
        </div>
        <div class="page_input_box">
          <input type="password" name="passwd" class="input_style" id="passwd" maxlength="12" placeholder="비밀번호를 입력해주세요">
        </div>
      </div>
      <div class="page_button">
        <div class="page_button_box">
        <input type="button" class="button1" value="아이디 찾기" onclick="location.href='${pageContext.request.contextPath}/member/findIdForm.do'">
        </div>
        <div class="page_button_box">
        <input type="button" class="button2" value="비밀번호 찾기" onclick="location.href='${pageContext.request.contextPath}/member/findPwForm.do'">
        </div>
      </div>
      <div class="page_button2">
        <div class="page_button_box">
          <input type="submit" class="button3" id="login" value="로그인">
        </div>
        <div class="page_button_box">
          <input type="button" class="button4" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/member/registerUserForm.do'">
        </div>
      </div>
    </form>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>