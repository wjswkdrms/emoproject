<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditPageAll_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberOutButton8_style.css">
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
  <div class="page_left">
    <ul>
      <li><a href="${pageContext.request.contextPath}/member/memberEditForm.do"><input type="submit" class="button_left" id="button_left_1" value="주문내역"></a></li>
      <li><a href=""><input type="submit" class="button_left" id="button_left_2" value="문의내역"></a></li>
      <li><a href=""><input type="submit" class="button_left" id="button_left_3" value="장바구니"></a></li>
      <li><a href=""><input type="submit" class="button_left" id="button_left_4" value="찜한상품"></a></li>
      <li><a href=""><input type="submit" class="button_left" id="button_left_5" value="상품후기"></a></li>
      <li><a href=""><input type="submit" class="button_left" id="button_left_6" value="쿠폰함"></a></li>
      <li><a href=""><input type="submit" class="button_left" id="button_left_7" value="개인정보수정"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/memberOutForm.do"><input type="submit" class="button_left" value="회원탈퇴"></a></li>
    </ul>
  </div>
    <div class="page_inner"> <!--메인 페이지 중앙 정렬, 크기 지정-->
      <h2 id="title">아이디 및 비밀번호 확인</h2>
        <form id="login_form" action="login.do" method="post">
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
		              <label for="passwd">비밀번호</label>
		            </div>
	            <div class="input_box">
	              <input type="password" name="passwd" class="input_style" id="passwd" maxlength="12">
	            </div>
	          </div>
	        </div>
	      </div>
	      <div class="page_login">
	        <ul class="button_all" id="page_button_4">
	          <li><input type="submit" class="button" value="로그인"></li>
	          <li><input type="button" class="button" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/member/memberOut.do'"></li>
	        </ul>
	      </div>
      </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>