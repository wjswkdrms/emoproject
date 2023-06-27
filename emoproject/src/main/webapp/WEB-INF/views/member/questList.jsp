<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 문의</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditPageQuestList_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberEditButton2_style.css">
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
				문의내역이 없습니다.
			</div>	
		</c:if>
            <c:if test="${count > 0}">
                <table class="page_inner_post"> <!-- 실제 데이터 시작 -->
                    <tr class="content_title">
                        <th id="th1">문의 번호</th>
                        <th id="th2">문의 제목</th>
                        <th id="th3">문의 내용</th>
                        <th id="th4">작성일</th>
                        <th id="th5">답변 상태</th>
                    </tr>
                    <c:forEach var="zzim" items="${list}">
                        <c:if test="${count == 0}">
                            <div class="page_none">
                                등록한 문의가 없습니다
                            </div>
                        </c:if>
                        <tr class="content_main">
                            <td id="num"><a href="${pageContext.request.contextPath}/ask/askDetail.do?ask_num=${zzim.ask_num}">${zzim.ask_num}</a></td>
                            <td id="title"><a href="${pageContext.request.contextPath}/ask/askDetail.do?ask_num=${zzim.ask_num}">${zzim.ask_title}</a></td>
                            <td id="content"><a href="${pageContext.request.contextPath}/ask/askDetail.do?ask_num=${zzim.ask_num}">${zzim.ask_content}</a></td>
                            <td id="date">${zzim.ask_date_2}</td>
                            <td id="status">
                                <c:if test="${zzim.ask_status==0}">
                                    답변 대기
                                </c:if>
                                <c:if test="${zzim.ask_status==1}">
                                    답변 완료
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



