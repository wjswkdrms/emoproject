<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세</title>
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
					공지사항
				</div>
				<div class="content-box">
					<div class="content-detail">
						<div class="detail-title">제목</div>
						<div class="specific">${announce.ann_title}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">작성자</div>
						<div class="specific">${announce.mem_id}</div>
					</div>	
					<div class="content-detail">
						<div class="detail-title">작성일</div>
						<div class="specific">${announce.ann_date}</div>
					</div>	
					<div class="main-content">
						<div><img src="${pageContext.request.contextPath}/upload/${announce.ann_photo1}"></div>
						<div>${announce.ann_content}</div>
					</div>	
					<div class="button-box">
						<c:if test="${!empty user_num&&user_auth==9}">
						<input type="button" value="수정" onclick="location.href='announceUpdateForm.do?ann_num=${announce.ann_num}'">
						<input type="button" value="삭제" id="delete_btn">
						<script type="text/javascript">
							let delete_btn = 
								  document.getElementById('delete_btn');
							//이벤트 연결
							delete_btn.onclick=function(){
								let choice = confirm('삭제하겠습니까?');
								if(choice){
									location.replace('delete.do?ann_num=${announce.ann_num}');
								}
							};
						</script>
						</c:if>
						<input type="button" value="목록" onclick="location.href='announce.do'">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>