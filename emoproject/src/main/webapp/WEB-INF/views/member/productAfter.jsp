<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 후기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productAfter.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditButton5_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"> 
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="whole">
  <div class="container">
    <div class="left-div">

      <div class="page-name">후기 작성</div>
      <ul class="menu-box">
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/orderList.do">주문내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/questList.do">문의내역</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/cart/list.do">장바구니</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/jjimList.do">찜한상품</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/productAfter.do">상품후기</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/myEditForm.do">개인정보 수정</a></li>
        <li><a class="detail-menu" href="${pageContext.request.contextPath}/member/memberOutForm.do">회원 탈퇴</a></li>
      </ul>
    </div>

    <div class="right-div">
      <div class="list-name">
        <h2> 후기 작성</h2>
      </div>
      <c:if test="${empty list}">
        <div class="result-display">
          작성 가능한 후기가 없습니다.
        </div>
      </c:if>
      <c:if test="${count > 0}">
      <div class="list">
        <div class="list-null"></div>
        <div class="list-list">상품명</div>
        <div class="list-price">총 가격 (개수)</div>
        <div class="list-date">주문 날짜</div>
        <div class="list-status">배송 상태</div>
        <div class="list-write">후기 작성</div>
      </div>

      <c:forEach var="zzim" items="${list}">
        <ul class="list">
          <li class="list-photo1"><a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${zzim.product_num}"><img src="${pageContext.request.contextPath}/upload/${zzim.product_photo1}"></a></li>
          <li class="list-list"><a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${zzim.product_num}">${zzim.product_title}</a></li>
          <li class="list-price">${zzim.product_price}원 (${zzim.product_quantity}개)</li>
          <li class="list-date">${zzim.order_date}</li>
          <li class="list-status">
            <c:if test="${zzim.order_status==0}"><b>주문 완료</b></c:if>
            <c:if test="${zzim.order_status==1}"><b>배송중</b></c:if>
            <c:if test="${zzim.order_status==2}"><b>배송 완료</b></c:if>
          </li>
          <li class="list-write">
            <c:if test="${zzim.order_status==0}"><a class="write_c" >작성 불가</a></c:if>
            <c:if test="${zzim.order_status==1}"><a class="write_c" >작성 불가</a></c:if>
            <c:if test="${zzim.order_status==2}"><a class="write" onclick="location.href='productAfterWriteForm.do?product_num=${zzim.product_num}&order_num=${zzim.order_num}'">후기 작성</a></c:if>
          </li>
        </ul>
      </c:forEach>
      <div class="align-center">${page}</div>
      </c:if>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



