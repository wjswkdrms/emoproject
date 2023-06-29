<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#memberOut_form').submit(function(){
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
<div class="page">
  <div class="page_outer"> <!-- 전체 페이지 크기 지정 -->
    <div class="page_inner">
      <h2 id="title">회원 탈퇴</h2>

      <form class="memberOut_form" action="memberOut.do" method="post">
        <div class="page_input">
          <div class="page_input_box">
            <input type="text" name="id" class="input_style" id="id" maxlength="12" autocomplete="off" placeholder="아이디를 입력해주세요">
          </div>
          <div class="page_input_box">
            <input type="password" name="passwd" class="input_style" id="passwd" maxlength="12" placeholder="비밀번호를 입력해주세요">
          </div>
        </div>

        <div class="page_button2">
          <div class="page_button_box">
            <input type="submit" class="button3" id="login" value="탈퇴하기">
          </div>
          <div class="page_button_box">
            <input type="button" class="button4" value="취소" onclick="location.href='${pageContext.request.contextPath}/member/orderList.do'">
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>