<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/findPw.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#findPw_form').submit(function(){
			if($('#id').val().trim()==''){
				alert('아이디를 입력하세요');
				$('#id').val('').focus();
				return false;
			}
			if($('#name').val().trim()==''){
				alert('이름을 입력하세요');
				$('#name').val('').focus();
				return false;
			}
			if($('#email').val().trim()==''){
				alert('이메일을 입력하세요');
				$('#email').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z가-힣]+$/.test($('#name').val())){
				alert('이름은 영문자와 한글만 입력 가능합니다.');
				$('#name').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9.@]+$/.test($('#email').val())){
				alert('이메일은 영문자와 숫자만 입력 가능합니다.');
				$('#email').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page">
  <div class="page_outer"> <!-- 전체 페이지 크기 지정 -->
    <div class="page_inner">
      <h2 id="title">비밀번호 찾기</h2>
        <form id="findPw_form" action="findPw.do" method="post">
        <div class="page_input">
          <div class="page_input_box">
              <input type="text" name="id" class="input_style" id="id" maxlength="12" autocomplete="off" placeholder="아이디를 입력해주세요">
          </div>
          <div class="page_input_box">
              <input type="text" class="input_style" name="name" id="name" maxlength="30" placeholder="이름을 입력해주세요">
          </div>
          <div class="page_input_box">
              <input type="email" name="email" class="input_style" id="email" maxlength="50" placeholder="이메일을 입력해주세요">
          </div>
        </div>
        <div class="page_button2">
          <div class="page_button_box">
          <input type="submit" class="button3" value="확인">
          </div>
          <div class="page_button_box">
          <input type="button" class="button4" value="취소" onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



