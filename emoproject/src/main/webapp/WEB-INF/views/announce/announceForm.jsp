<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#announce_form').submit(function() {
			if ($('#ann_title').val().trim() == '') {
				alert('제목을 입력하세요');
				$('#ann_title').val('').focus();
				return false;
			}
			if ($('#ann_content').val().trim() == '') {
				alert('내용을 입력하세요');
				$('#ann_content').val('').focus();
				return false;
			}
		});
		 $("input[type='file']").change(function(e){

		      //div 내용 비워주기
		      $('#image_container').empty();
		 });  		
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="whole">
		<div class="container">
			<!-- 내용 시작 -->
			<div class="left-div">
				<div class="page-name">고객센터</div>
				<ul class="menu-box">
					<li><a class="detail-menu" href="announce.do">공지사항<img></a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/faq/faq.do">자주하는 질문<img></a></li>
					<li><a class="detail-menu"
						href="${pageContext.request.contextPath}/ask/ask.do">1:1 문의<img></a></li>
				</ul>
			</div>
			<div class="right-div">
				<h2>공지사항 작성</h2>
				<form id="announce_form" action="announceWrite.do" method="post"
					enctype="multipart/form-data">
					<table class="form-box">
						<tr>
							<td class="row-title">제목</td>
							<td class="row-content"><input class="text-title"
								type="text" name="ann_title" id="ann_title" maxlength="33"></td>
						</tr>
						<tr>
							<td class="row-title">내용</td>
							<td class="row-content"><textarea rows="5" cols="30"
									name="ann_content" id="ann_content" maxlength="333"></textarea></td>
						</tr>
						<tr>
							<td class="row-title">사진첨부</td>
							<td class="row-content">
								<label for="ann_photo1">
									<div class="file-button"></div>
								</label>
								<div id="image_container"></div> 
								<input type="file" name="ann_photo1" id="ann_photo1" 
								accept="image/gif,image/png,image/jpeg" onchange="setThumbnail(event);"> 
								<script type="text/javascript">
									function setThumbnail(event) {
										var reader = new FileReader();

										reader.onload = function(event) {
											var img = document
													.createElement("img");
											img.setAttribute("src",
													event.target.result);
											var div=document.querySelector(
													"div#image_container");
													
											if(div.children.length>0){
												div.firstElementChild.remove();	
											}
											div.appendChild(img);
										};

										reader.readAsDataURL(event.target.files[0]);
									}
								</script>
								</td>
						</tr>
					</table>
					<div class="button-box">
						<input class="button" type="submit" value="등록"> <input
							class="button" type="button" value="목록"
							onclick="location.href='announce.do'">
					</div>
				</form>
			</div>
			<!-- 내용 끝 -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>