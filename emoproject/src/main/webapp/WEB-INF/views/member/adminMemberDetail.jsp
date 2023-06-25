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
							<c:if test="${detail.auth==0}">탈퇴</c:if>
							<c:if test="${detail.auth==1}">정지</c:if>
							<c:if test="${detail.auth==2}">일반</c:if>
							<c:if test="${detail.auth==9}">관리</c:if>
						</div>
						<div class="specific">
						<input type="button" value="정지" onclick="location.href='adminMemberStop.do?mem_num=${detail.mem_num}'">
						<input type="button" value="탈퇴" onclick="location.href='adminMemberExpire.do?mem_num=${detail.mem_num}'">
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
							<input type="button" value="포인트 입금" onclick="location.href='adminGivePointForm.do?mem_num=${detail.mem_num}'">
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
						<input type="button" value="목록" onclick="location.href='adminMemberList.do'">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>