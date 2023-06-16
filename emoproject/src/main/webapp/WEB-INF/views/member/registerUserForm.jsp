<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/promotion_style.css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
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
					<input type="text" name="cell"
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
					<label for="address2">나머지 주소</label>
					<input type="text" name="address2"
					  id="address2" maxlength="30">
				</li>
				<li>
					<label for="birth">생년월일</label>
					<input type="text" name="birth"
					  id="birth" maxlength="30">
				</li>
				<li>
					<label for="gender">성별</label>
					<input type="text" name="gender"
					  id="gender" maxlength="30">
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
</html>