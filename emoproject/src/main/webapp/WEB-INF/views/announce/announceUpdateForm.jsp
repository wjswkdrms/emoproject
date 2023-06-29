<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eesamsaoh.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#update_form').submit(function(){
		if($('#ann_title').val().trim() == ''){
			alert('제목을 입력하세요');
			$('#ann_title').val('').focus();
			return false;
		}
		if($('#ann_content').val().trim() == ''){
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
						<li><a class="detail-menu">자주하는 질문<img></a></li>
						<li><a class="detail-menu" href="${pageContext.request.contextPath}/ask/ask.do">1:1 문의<img></a></li>
					</ul>
				</div>
				<div class="right-div">
				<h2>공지사항</h2>
				<form id="update_form" action="announceUpdate.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="ann_num" value="${announce.ann_num}">
					<table class="form-box">
						<tr>
							<th class="row-title">제목</th>
							<td class="row-content"><input class="text-title" type="text" name="ann_title" value="${announce.ann_title}" id="ann_title" maxlength="33"></td>
						</tr>
						<tr>
							<th class="row-title">내용</th>
							<td class="row-content"><textarea rows="5" cols="30" name="ann_content" id="ann_content" maxlength="333">${announce.ann_content}</textarea></td>
						</tr>
						<tr>
							<th class="row-title"><label for="ann_photo1">사진첨부</label></th>
							<td class="row-content">
								<label  for="ann_photo1">
									<div class="file-button">
									</div>
								</label>
								<div id="image_container"></div> 								
							<input type="file" name="ann_photo1" id="ann_photo1" accept="image/gif,image/png,image/jpeg" onchange="setThumbnail(event);">
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
											var span=document.createElement("span");
											span.classList.add("del");
											if(div.children.length>0){
												div.replaceChildren();	
											}
											div.appendChild(img);
											div.appendChild(span);
										};

										reader.readAsDataURL(event.target.files[0]);
									}
									$('#image_container').on('click', '.del', function () {
									    $("#image_container").empty()
									    $("#ann_photo1").val("");
									});
								</script>							
							<c:if test="${!empty announce.ann_photo1}">
								<div class="row-content" id="file_detail">
									<img src="${pageContext.request.contextPath}/upload/${announce.ann_photo1}" width="50" height="50" 
										class="photo" data-img="${announce.ann_photo1}">
									(${announce.ann_photo1})이 등록되어 있습니다.
									<input class="small-button" type="button" value="삭제" id="photo_del"><br>
								</div>
 								<script type="text/javascript">
									$(function(){
										$('#photo_del').click(function(){
											let choice=confirm('삭제하시겠습니까?');
											if(choice){
												$.ajax({
													url:'deleteFile.do',
													type:'post',
													data:{ann_num:${announce.ann_num}},
													dataType:'json',
													success:function(param){
														if(param.result == 'logout'){
															alert('로그인 후 사용하세요');
														}else if(param.result == 'success'){
															$('#file_detail').hide();
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
							</c:if>
							</td>
						</tr>
					</table>
					<div class="button-box">
						<input class="button" type="submit" value="수정"> <input class="button" type="button" value="목록" onclick="location.href='announce.do'">
					</div>
				</form>
				</div>
			<!-- 내용 끝 -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>