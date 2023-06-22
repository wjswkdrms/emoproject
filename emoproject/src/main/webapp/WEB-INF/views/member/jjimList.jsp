<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찜한 상품</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditPageAll_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditButton1_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page_outer">
  <div class="page_left">
    <ul>
      <li><a href="${pageContext.request.contextPath}/member/mberEditForm.do"><input type="submit" class="button_left" id="button_left_1" value="주문내역"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/questListForm.do"><input type="submit" class="button_left" id="button_left_2" value="문의내역"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/cartListForm.do"><input type="submit" class="button_left" id="button_left_3" value="장바구니"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/jjimListForm.do"></a><input type="submit" class="button_left" id="button_left_4" value="찜한상품"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/productAfterForm.do"><input type="submit" class="button_left" id="button_left_5" value="상품후기"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/couponListForm.do"><input type="submit" class="button_left" id="button_left_6" value="쿠폰함"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/myEditForm.do"><input type="submit" class="button_left" id="button_left_7" value="개인정보수정"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/memberOutForm.do"><input type="submit" class="button_left" id="button_left_8" value="회원탈퇴"></a></li>
    </ul>
  </div>
  <div class="page_inner">
    <div class="page_inner_main">
        <div class="page_inner_post"> <!-- 실제 데이터 시작 -->
          <div class="content_title">
            타이틀
          </div>
          <c:forEach var="zzim" items="${list}">
          <tr>
          	<td><img src="${pageContext.request.contextPath}/upload/${zzim.product_photo1}"></td>
          	<td><a href="#">${zzim.product_title}</a></td>
          	<td>${zzim.product_info}</td>
          	<td>${zzim.product_price}</td>
          	<td>${zzim.product_status}</td>
          </tr>
          <div class="content_list">
            
          </div>
          </c:forEach>
        </div>
        <div class="content_bottom">
          <div class="content_bottom2">${page}</div>
        </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



