<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="whole">
		<div class="container">
			<div class="box">
				<div class="title">
					1:1 문의
				</div>
				<div class="content-box">
					<div class="content-detail">
						<div class="detail-title">제목</div>
						<div class="specific">${ask.ask_title}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">작성자</div>
						<div class="specific">${ask.mem_id}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">작성일</div>
						<div class="specific">${ask.ask_date}</div>
					</div>	
					<div class="main-content">
						<c:if test="${!empty ask.ask_photo1}">
						<div><img src=${pageContext.request.contextPath}/upload/${ask.ask_photo1}></div>
						<p><br>
						</c:if>
						<div>${ask.ask_content}</div>
					</div>	
				<!-- 답변 시작 -->
					<c:if test="${!empty answer.answer_num}">
					<div class="content-detail">
						<div class="detail-title">관리자</div>
						<div class="specific">${answer.answer_date}</div>
					</div>
					<div class="main-content">						
							<c:if test="${!empty answer.answer_photo}">
							<div><img src=${pageContext.request.contextPath}/upload/${answer.answer_photo}></div>
							<p><br>
							</c:if>
							<div>${answer.answer_content}</div>
					</div>
					</c:if>	
					<div class="button-box">
						<c:if test="${!empty user_num&&user_num==ask.mem_num}">
						<input class="small-button" type="button" value="수정" onclick="location.href='askUpdateForm.do?ask_num=${ask.ask_num}'">
						<input class="small-button" type="button" value="삭제" id="del_ask">
										 	<script type="text/javascript">
												$(function(){
													$('#del_ask').click(function(){
														let choice=confirm('이 문의글을 삭제 하시겠습니까?');
														if(choice){
															$.ajax({
																url:'askDelete.do',
																type:'post',
																data:{ask_num:${ask.ask_num}},
																dataType:'json',
																success:function(param){
																	if(param.result == 'logout'){
																		alert('로그인 후 사용하세요');
																	}else if(param.result == 'success'){
																		alert('글 삭제 완료');
																		location.replace('ask.do');																		
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
													});
												});
											</script>							
						</c:if>
					</div>
					<div class="button-box">
						<c:if test="${!empty user_num&&user_auth==9}">
						<form action="answerUpdateForm.do" method="post">
							<input type="hidden" name="ask_num" value="${ask.ask_num}" >
							<c:if test="${!empty answer.answer_num}">
								<input class="small-button" type="submit" value="답변 수정">
							</c:if>
						</form>
						<form action="answerForm.do" method="post">
							<input type="hidden" name="ask_num" value="${ask.ask_num}" >
							<c:if test="${empty answer.answer_num}">
								<input class="small-button" type="submit" value="글쓰기" >
							</c:if>
						</form>
						</c:if>
					</div>
					<div class="button-box">
						<input class="button" type="button" value="목록" onclick="location.href='ask.do'">
					</div>
				<!-- 답변 끝 -->
				</div>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>