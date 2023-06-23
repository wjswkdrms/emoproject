<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찜한 상품</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditPageOrderAll_style.css">
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
  <div class="page_inner">
    <div class="page_inner_main">
      <c:if test="${count > 0}">
        <table class="page_inner_post"> <!-- 실제 데이터 시작 -->
          <tr class="content_title">
            <th id="th1">상품명</th>
            <th id="th2">총 가격 (개수)</th>
            <th id="th3">주문 날짜</th>
            <th id="th4">배송 상태</th>
          </tr>
          <c:forEach var="zzim" items="${list}">

            <c:if test="${count == 0}">
              <div class="page_none">
                주문한 상품이 없습니다.
              </div>
            </c:if>

            <tr class="content_main">
              <td id="photo1"><a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${zzim.product_num}"><img src="${pageContext.request.contextPath}/upload/${zzim.product_photo1}"></a></td>
              <td id="title"><a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${zzim.product_num}">${zzim.product_title}</a></td>
              <td id="price">${zzim.product_price}원 (${zzim.product_quantity}개)</td>
              <td id="date">${zzim.order_date}</td>
              <td id="status">${zzim.product_status}<input type="button" value="후기 작성" onclick=""></td>
            </tr>

          </c:forEach>
        </table>
        <div class="content_bottom">
          <div class="content_bottom2">${page}</div>
        </div>
      </c:if>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



