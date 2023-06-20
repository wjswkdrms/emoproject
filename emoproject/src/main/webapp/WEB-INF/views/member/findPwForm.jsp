<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberPageAll_style.css">
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
		});
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page_outer"> <!-- 전체 페이지 크기 지정 -->
  <div class="page_inner"> <!--메인 페이지 중앙 정렬, 크기 지정-->
    <h2 id="title">비밀번호 찾기</h2>
    <form id="findPw_form" action="findPw.do" method="post">
      <div class="page_login">
        <div class="input_idpw_outer" id="input_id">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="id">아이디</label>
            </div>
            <div class="input_box">
              <input type="text" name="id" class="input_style" id="id" maxlength="12" autocomplete="off">
            </div>
          </div>
        </div>
        <div class="input_idpw_outer" id="input_pw">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="name">이름</label>
            </div>
            <div class="input_box">
              <input type="text" class="input_style" name="name" id="name" maxlength="30">
            </div>
          </div>
        </div>
        <div class="input_idpw_outer" id="input_email">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="name">이메일</label>
            </div>
            <div class="input_box">
              <input type="email" name="email" class="input_style" id="email" maxlength="50">
            </div>
          </div>
        </div>
      </div>
      <div class="page_login">
        <ul class="button_all" id="page_button_4">
          <li><input type="submit" class="button" value="확인"></li>
          <li><input type="button" class="button" value="취소" onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'"></li>
        </ul>
      </div>
    </form>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



