<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포인트 부여</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#point_form').submit(function(){
		if($('#point_value').val().trim() == ''){
			alert('값을 입력하세요');
			$('#point_value').val('').focus();
			return false;
		}
		if(!/^[0-9]{1,8}$/.test(
	        $('#point_value').val())){
			alert('숫자 사용, 최소 1자 ~ 최대 8자를 사용하세요');
			$('#point_value').val('');
			$('#point_value').focus();
			return false;
		}
	});
	
});

</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="whole">
		<div class="container">
			<div class="box">
				<div class="title">
					포인트 부여
				</div>
				<div class="content-box">
					<form id="point_form" method="post" action="adminGivePoint.do">
						<input type="hidden" name="mem_num" value="${member.mem_num}">
						<div class="content-detail">
							<div class="detail-title">회원번호</div>
							<div class="specific">${member.mem_num}</div>
						</div>	
						<div class="content-detail">
							<div class="detail-title">회원상태</div>
							<div class="specific">
								<c:if test="${member.auth==0}">탈퇴</c:if>
								<c:if test="${member.auth==1}">정지</c:if>
								<c:if test="${member.auth==2}">일반</c:if>
								<c:if test="${member.auth==9}">관리</c:if>
							</div>
						</div>	
						<div class="content-detail">
							<div class="detail-title">아이디</div>
							<div class="specific">${detail.id}</div>
						</div>	
						<div class="content-detail">
							<div class="detail-title">보유포인트</div>
							<div class="specific">
								<fmt:formatNumber value="${member.point}"/> 포인트
							</div>
						</div>
						<div class="content-detail">
							<div class="detail-title">부여포인트</div>
							<div class="specific">
								<input type="text" name="give-point" id="point_value">
							</div>
						</div>
						<div class="button-box">
							<input class="button" type="submit" value="확인">
							<input class="button" type="button" value="목록" onclick="location.href='adminMemberList.do'">
						</div>
					</form>	
				</div>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>