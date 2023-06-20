<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#login_form').submit(function(){
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
<div class="page-main">
  <div id="center_set">
    <!-- 내용 시작 -->
    <h2 id="main_title">아이디 찾기</h2>
    <div class="content-main">
      <form id="login_form" action="findId.do"
            method="post">
        <ul>
          <li>
            <label for="name">이름</label>
            <input type="text" class="input_style" name="name"
                   id="name" maxlength="30">
          </li>
          <li>
            <label for="email">이메일</label>
            <input type="email" name="email" class="input_style"
                   id="email" maxlength="50">
          </li>
        </ul>
        <div class="align-center">
          <input type="submit" class="bt" value="확인">
          <input type="button" class="bt" value="취소"
                 onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
        </div>
      </form>
    </div>
    <!-- 내용 끝 -->
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



