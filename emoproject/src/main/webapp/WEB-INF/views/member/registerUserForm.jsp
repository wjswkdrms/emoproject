<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/promotion_style.css">
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
<div class="page-main">
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>회원가입</h2>
		<form id="register_form" 
		  action="registerUser.do" method="post">
			<ul>
				<li>
					<label for="id">아이디</label>
					<input type="text" name="id"
					   id="id" maxlength="12"
					   autocomplete="off">
					<input type="button" 
					 value="id중복체크" id="id_check">
					<span id="message_id"></span>
					<div class="form-notice">*영문 또는 숫자(4자~12자)</div>    
				</li>
				<li>
					<label for="name">이름</label>
					<input type="text" name="name"
					  id="name" maxlength="10">
				</li>
				<li>
					<label for="passwd">비밀번호</label>
					<input type="password" name="passwd"
					  id="passwd" maxlength="12">
				</li>
				<li>
					<label for="cell">전화번호</label>
					<input type="tel" name="cell"
					  id="cell" maxlength="15">
				</li>
				<li>
					<label for="email">이메일</label>
					<input type="email" name="email"
					  id="email" maxlength="50">
				</li>
				<li>
					<label for="zipcode">우편번호</label>
					<input type="text" name="zipcode"
					  id="zipcode" maxlength="5">
					<input type="button" value="우편번호 찾기"
					 onclick="execDaumPostcode()">  
				</li>
				<li>
					<label for="address1">주소</label>
					<input type="text" name="address1"
					  id="address1" maxlength="30">
				</li>
				<li>
					<label for="address2">상세 주소</label>
					<input type="text" name="address2"
					  id="address2" maxlength="30">
				</li>
				<li>
					<label for="birth">생년월일</label>
					<input type="text" name="birth"
					  id="birth" maxlength="6">
				</li>
				<li>
					<label for="gender">성별</label>
					남자
					<input type="radio" name="gender"
					  id="gender">
				    여자
					<input type="radio" name="gender"
					  id="gender">
				</li>
				
			</ul> 
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="홈으로"
				 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div> 
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>