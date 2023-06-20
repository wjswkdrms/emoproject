<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberPageAll_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		//0:중복 체크 미실시, id 중복
		//1:id 미중복
		let idChecked = 0;
		
		//아이디 중복 체크
		$('#id_check').click(function(){
			
			if(!/^[A-Za-z0-9]{4,12}$/.test(
					            $('#id').val())){
				alert('영문 또는 숫자 사용, 최소 4자 ~ 최대 12자를 사용하세요');
				$('#id').val('');
				$('#id').focus();
				return false;
			}
			
			//서버와의 통신
			$.ajax({
				url:'checkDuplicatedId.do',
				type:'post',
				data:{id:$('#id').val()},
				dataType:'json',
				success:function(param){
					if(param.result == 'idNotFound'){
						//id 미중복
						idChecked = 1;
						$('#message_id').css('color','#000000')
						                .text('등록 가능 ID');
					}else if(param.result == 'idDuplicated'){
						//id 중복
						idChecked = 0;
						$('#message_id').css('color','red')
						                .text('중복된 ID');
						$('#id').val('').focus();
					}else{
						idChecked = 0;
						alert('아이디 중복 체크 오류 발생');
					}
				},
				error:function(){
					idChecked = 0;
					alert('네트워크 오류 발생');
				}
			});
			
		});//end of click
		
		//아이디 중복 안내 메시지 초기화 및 아이디
		//중복 값 초기화
		$('#register_form #id').keydown(function(){
			idChecked = 0;
			$('#message_id').text('');
		});//end of keydown

	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page_outer_register"> <!-- 전체 페이지 크기 지정 -->
  <div class="page_inner_register"> <!--메인 페이지 중앙 정렬, 크기 지정-->
    <h2 id="title">회원가입</h2>
    <form id="register_form" action="registerUser.do" method="post">
      <div class="page_login">
        <div class="input_idpw_outer" id="input_id">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="id">아이디</label>
            </div>
            <div class="input_box">
              <input type="text" name="id" id="id" class="input_style" maxlength="12" autocomplete="off">
              <input type="button" class="input_style" value="id중복체크" id="id_check">
              <span id="message_id"></span>
            </div>
          </div>
        </div>
        <div class="text_announce">
          <div class="input-notice"><strong>* 영문 또는 숫자(4자~12자)</strong></div>
        </div>
        <div class="input_idpw_outer" id="input_name">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="name">이름</label>
            </div>
            <div class="input_box">
              <input type="text" class="input_style" name="name" id="name" maxlength="10">
            </div>
          </div>
        </div>
        <div class="input_idpw_outer" id="input_passwd">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="passwd">비밀번호</label>
            </div>
            <div class="input_box">
              <input type="password" class="input_style" name="passwd" id="passwd" maxlength="12">
            </div>
          </div>
        </div>
        <div class="text_announce">
          <div class="input-notice"><strong>* 영문 또는 숫자(4자~12자)</strong></div>
        </div>
        <div class="input_idpw_outer" id="input_cell">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="cell">전화번호</label>
            </div>
            <div class="input_box">
              <input type="text" name="cell" class="input_style" id="cell" maxlength="15">
            </div>
          </div>
        </div>
        <div class="input_idpw_outer" id="input_email">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="email">이메일</label>
            </div>
            <div class="input_box">
              <input type="email" name="email" class="input_style" id="email" maxlength="50">
              <input type="button" class="input_style" value="eamil중복체크" id="email_check">
            </div>
          </div>
        </div>
        <div class="input_idpw_outer" id="input_zipcode">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="zipcode">우편번호</label>
            </div>
            <div class="input_box">
              <input type="text" name="zipcode" class="input_style" id="zipcode" maxlength="5">
              <input type="button" value="우편번호 찾기" class="input_style" onclick="execDaumPostcode()">
            </div>
          </div>
        </div>
        <div class="input_idpw_outer" id="input_address1">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="address1">주소</label>
            </div>
            <div class="input_box">
              <input type="text" name="address1" class="input_style" id="address1" maxlength="30">
            </div>
          </div>
        </div>
        <div class="input_idpw_outer" id="input_address2">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="address2">상세 주소</label>
            </div>
            <div class="input_box">
              <input type="text" name="address2" class="input_style" id="address2" maxlength="30">
            </div>
          </div>
        </div>
        <div class="input_idpw_outer" id="input_birth">
          <div class="input_idpw_inner">
            <div class="input_title">
              <label for="birth">생년월일</label>
            </div>
            <div class="input_box">
              <input type="text" name="birth" class="input_style" id="birth" maxlength="6">
            </div>
          </div>
        </div>
        <div class="text_announce">
          <div class="input-notice"><strong>ex) 961221</strong></div>
        </div>
        <div class="input_idpw_outer" id="input_gender">
          <div class="input_idpw_inner">
            <div class="input_title_radio">
              <label for="gender">성별</label>
              <span class="gender_span">남자</span>
              <input type="radio" name="gender"
                     id="gender" class="gender" value="1">
              <span class="gender_span">여자</span>
              <input type="radio" name="gender"
                     id="gender" class="gender" value="2">
            </div>
          </div>
        </div>

      </div>
      <div class="page_login">
        <ul class="button_all" id="page_button_4">
          <li><input type="submit" class="button" value="확인"></li>
          <li><input type="button" class="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'"></li>
        </ul>
      </div>
    </form>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>