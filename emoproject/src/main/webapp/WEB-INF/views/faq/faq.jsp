<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주하는 질문</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript">
	function collapse(element) {
	    var before = document.getElementsByClassName("active")[0]               // 기존에 활성화된 버튼
	    if (before && document.getElementsByClassName("active")[0] != element) {  // 자신 이외에 이미 활성화된 버튼이 있으면
	        before.nextElementSibling.style.maxHeight = null;   // 기존에 펼쳐진 내용 접고
	        before.classList.remove("active");                  // 버튼 비활성화
	    }
	    element.classList.toggle("active");         // 활성화 여부 toggle
	
	    var content = element.nextElementSibling;
	    if (content.style.maxHeight != 0) {         // 버튼 다음 요소가 펼쳐져 있으면
	        content.style.maxHeight = null;         // 접기
	    } else {
	        content.style.maxHeight = content.scrollHeight + "px";  // 접혀있는 경우 펼치기
	    }
	}
	

</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<!-- 내용 시작 -->
	<div class="whole">
		<div class="container">
			<div class="left-div">
				<div class="page-name">고객센터</div>
				<ul class="menu-box">
					<li><a class="detail-menu" href="${pageContext.request.contextPath}/announce/announce.do">공지사항<img></a></li>
					<li><a class="detail-menu" href="faq.do">자주하는 질문<img></a></li>
					<li><a class="detail-menu" href="${pageContext.request.contextPath}/ask/ask.do">1:1 문의<img></a></li>
				</ul>
			</div>
			<div class="right-div">
				
				<div class="list-name">
					<h2>자주하는 질문</h2>
				</div>
				
					<form id="search_form" action="faq.do"
					                          method="get">
						<div class="button-box">					                          
								<ul class="main-content">
										<li>
											<select class="select" name="keyfield" onchange="this.form.submit();">
												<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>회원</option>
												<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>이벤트</option>
												<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>서비스 이용</option>
												<option value="4" <c:if test="${param.keyfield==4}">selected</c:if>>시스템 오류</option>
											</select>
										</li>
								</ul>
						</div>                          
					</form>                          
	 			<c:if test="${count==0}"> 
	 			<div class="list">
					<div class="list-num">번호</div>
					<div class="list-category">카테고리</div>
					<div class="list-title">제목</div>
				</div>
	 			<div>
					표시할 게시물이 없습니다.
				</div> 
	 			</c:if>
	 			<c:if test="${count>0}">
				<div class="list">
					<div class="list-num">번호</div>
					<div class="list-category">카테고리</div>
					<div class="list-name">제목</div>
				</div>
				
				<c:forEach var="faq" items="${list}">
				<ul class="list2">
					<li class="collapsible" onclick="collapse(this);">
							<div class="list-num">${faq.faq_num}</div>
							<div  class="list-category">
							<c:if test="${faq.faq_category==1}">
								회원
							</c:if>
							<c:if test="${faq.faq_category==2}">
								이벤트
							</c:if>
							<c:if test="${faq.faq_category==3}">
								서비스 이용
							</c:if>
							<c:if test="${faq.faq_category==4}">
								시스템 오류
							</c:if>
							</div>
							<div class="list-name">${faq.faq_title}</div>
					</li>
					<li class="content">
							<div>
							<img src="${pageContext.request.contextPath}/images/arw_reply.png" width="10px;">
							<pre>${faq.faq_content}</pre>
							</div>
						<c:if test="${!empty user_num && user_auth==9}">
						<div class="button-box">
							<input type="button" class="small-button" value="수정" onclick="location.href='faqUpdateForm.do?faq_num=${faq.faq_num}'">
						</div> 
						</c:if>
					</li>					
				</ul>
				</c:forEach>
	 			</c:if> 
				<c:if test="${!empty user_num && user_auth==9}">
				<div class="button-box">
					<input type="button" class="small-button" value="글쓰기" onclick="location.href='faqForm.do'">
				</div> 
				</c:if>
				<div class="align-center">${page}</div>  
			</div>
		</div>
		<!-- 내용 끝 -->
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>