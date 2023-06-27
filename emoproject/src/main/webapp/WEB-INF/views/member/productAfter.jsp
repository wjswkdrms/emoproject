<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 후기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditPageProductAfter_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditButton5_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"> 
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page_outer">
  <div class="page_left">
    <ul>
      <li><a href="${pageContext.request.contextPath}/member/orderList.do"><input type="submit" class="button_left" id="button_left_1" value="주문내역"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/questList.do"><input type="submit" class="button_left" id="button_left_2" value="문의내역"></a></li>
      <li><a href="${pageContext.request.contextPath}/cart/list.do"><input type="submit" class="button_left" id="button_left_3" value="장바구니"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/jjimList.do"><input type="submit" class="button_left" id="button_left_4" value="찜한상품"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/productAfter.do"><input type="submit" class="button_left" id="button_left_5" value="상품후기"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/myEditForm.do"><input type="submit" class="button_left" id="button_left_7" value="개인정보수정"></a></li>
      <li><a href="${pageContext.request.contextPath}/member/memberOutForm.do"><input type="submit" class="button_left" id="button_left_8" value="회원탈퇴"></a></li>
    </ul>
  </div>
   <div class="page_inner">
    <div class="page_inner_main">
    		<c:if test="${empty list}">
			<div class="result-display">
				작성 가능한 후기가 없습니다.
			</div>	
		</c:if>
      <c:if test="${count > 0}">
        <table class="page_inner_post"> <!-- 실제 데이터 시작 -->
          <tr class="content_title">
            <th id="th1">상품명</th>
            <th id="th2">총 가격 (개수)</th>
            <th id="th3">주문 날짜</th>
            <th id="th4">배송 상태</th>
            <th id="th5">후기 작성</th>
          </tr>
          <c:forEach var="zzim" items="${list}">
            <c:if test="${list == 0}">
              <div class="page_none">
                작성 가능한 상품 후기가 없습니다.
              </div>
            </c:if>

            <tr class="content_main">
              <td id="photo1"><a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${zzim.product_num}"><img src="${pageContext.request.contextPath}/upload/${zzim.product_photo1}"></a></td>
              <td id="title"><a href="${pageContext.request.contextPath}/product/productDetail.do?product_num=${zzim.product_num}">${zzim.product_title}</a></td>
              <td id="price">${zzim.product_price}원 (${zzim.product_quantity}개)</td>
              
              <td id="date">${zzim.order_date}</td>
              <td id="status">
				<c:if test="${zzim.order_status==0}">
				  <b>주문 완료</b>
				</c:if>
				<c:if test="${zzim.order_status==1}">
			      <b>배송중</b>
				</c:if>
				<c:if test="${zzim.order_status==2}">
				  <b>배송 완료</b>
				</c:if>
		      </td>
		      
               <td id="after">
				<c:if test="${zzim.order_status==0}">
				  <input class="button_after" type="button" value="작성 불가">
				</c:if>
				<c:if test="${zzim.order_status==1}">
			      <input class="button_after" type="button" value="작성 불가">
				</c:if>
				<c:if test="${zzim.order_status==2}">
				<input class="button_after" type="button" value="후기 작성" onclick="location.href='productAfterWriteForm.do?product_num=${zzim.product_num}&order_num=${zzim.order_num}'">
				</c:if>
		      </td>
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



