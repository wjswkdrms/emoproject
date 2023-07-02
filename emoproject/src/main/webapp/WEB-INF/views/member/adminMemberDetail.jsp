<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="whole">
		<div class="container">
			<div class="box">
				<div class="title">
					회원 상세
				</div>
				<div class="content-box">
					<div class="content-detail">
						<div class="detail-title">회원번호</div>
						<div class="specific">${detail.mem_num}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">회원상태</div>
						<div class="specific">
							<c:if test="${detail.auth==0}"><div class="expire"><b>탈퇴</b></div></c:if>
							<c:if test="${detail.auth==1}"><div class="stop"><b>정지</b></div></c:if>
							<c:if test="${detail.auth==2}"><b>일반</b></c:if>
							<c:if test="${detail.auth==9}"><div class="answer">관리</div></c:if>
						</div>
						<div class="specific">
							<c:if test="${detail.auth==1}">
								<input class="small-button" type="button" value="일반" onclick="location.href='adminMemberNor.do?mem_num=${detail.mem_num}'">
							</c:if>
							<c:if test="${detail.auth==0}">
								<input class="small-button" type="button" value="일반" onclick="location.href='adminMemberNor.do?mem_num=${detail.mem_num}'">
							</c:if>
							<c:if test="${detail.auth==2}">
								<input class="small-button" type="button" value="정지" onclick="location.href='adminMemberStop.do?mem_num=${detail.mem_num}'">
								<input id="expire_btn" class="small-button" type="button" value="탈퇴">
							</c:if>
										 	<script type="text/javascript">
												$(function(){
													$('#expire_btn').click(function(){
														let choice=confirm('탈퇴처리 하시겠습니까?');
														if(choice){
															$.ajax({
																url:'adminMemberExpire.do',
																type:'post',
																data:{mem_num:${detail.mem_num}},
																dataType:'json',
																success:function(param){
																	if(param.result == 'logout'){
																		alert('로그인 후 사용하세요');
																	}else if(param.result == 'success'){
																		location.reload();
																	}else if(param.result == 'wrongAccess'){
																		alert('잘못된 접속입니다.');
																	}else{
																		alert('파일 삭제 오류 발생');
																	}														
																},
																error:function(){
																	alert('네트워크 오류 발생');														
																}
															});
														}
													})
												});
											</script>							
						</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">아이디</div>
						<div class="specific">${detail.id}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">비밀번호</div>
						<div class="specific">${detail.passwd}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">우편번호</div>
						<div class="specific">${detail.zipcode}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">주소</div>
						<div class="specific">${detail.address1}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">상세주소</div>
						<div class="specific">${detail.address2}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">전화번호</div>
						<div class="specific">${detail.cell}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">성별</div>
						<div class="specific">${detail.gender}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">생년월일</div>
						<div class="specific">${detail.birth}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">보유포인트</div>
						<div class="specific">
							<fmt:formatNumber value="${detail.point}"/> 포인트
						</div>
						<div class="specific">
							<c:if test="${detail.auth==2}">
								<input class="small-button" type="button" value="포인트 입금" onclick="location.href='adminGivePointForm.do?mem_num=${detail.mem_num}'">
							</c:if>
						</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">누적소진포인트</div>
						<div class="specific">
							<fmt:formatNumber value="${detail.order_total_price}"/> 포인트
						</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">가입일</div>
						<div class="specific">${detail.reg_date}</div>
					</div>	
					<div class="button-box">
						<input class="small-button" type="button" value="목록" onclick="location.href='adminMemberList.do'">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>