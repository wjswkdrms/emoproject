<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
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
      <form id="register_form" action="registerUser.do" method="post">
        <div class="page_login">
          <div class="input_idpw_outer" id="input_id">
            <div class="input_idpw_inner">
              <div class="input_title">
                <label for="id">아이디</label>
              </div>
              <div class="input_box">
                <input type="text" name="id" id="id" class="input_style" maxlength="12" autocomplete="off">
                <input type="button" class="input_style" value="id중복체크" id="id_check">
                <span id="message_id"></span>
              </div>
            </div>
          </div>
          <div class="text_announce">
            <div class="input-notice"><strong>* 영문 또는 숫자(4자~12자)</strong></div>
          </div>
          <div class="input_idpw_outer" id="input_name">
            <div class="input_idpw_inner">
              <div class="input_title">
                <label for="name">이름</label>
              </div>
              <div class="input_box">
                <input type="text" class="input_style" name="name" id="name" maxlength="10">
              </div>
            </div>
          </div>
          <div class="input_idpw_outer" id="input_passwd">
            <div class="input_idpw_inner">
              <div class="input_title">
                <label for="passwd">비밀번호</label>
              </div>
              <div class="input_box">
                <input type="password" class="input_style" name="passwd" id="passwd" maxlength="12">
              </div>
            </div>
          </div>
          <div class="text_announce">
            <div class="input-notice"><strong>* 영문 또는 숫자(4자~12자)</strong></div>
          </div>
          <div class="input_idpw_outer" id="input_cell">
            <div class="input_idpw_inner">
              <div class="input_title">
                <label for="cell">전화번호</label>
              </div>
              <div class="input_box">
                <input type="text" name="cell" class="input_style" id="cell" maxlength="15">
              </div>
            </div>
          </div>
          <div class="input_idpw_outer" id="input_email">
            <div class="input_idpw_inner">
              <div class="input_title">
                <label for="email">이메일</label>
              </div>
              <div class="input_box">
                <input type="email" name="email" class="input_style" id="email" maxlength="50">
                <input type="button" class="input_style" value="eamil중복체크" id="email_check">
              </div>
            </div>
          </div>
          <div class="input_idpw_outer" id="input_zipcode">
            <div class="input_idpw_inner">
              <div class="input_title">
                <label for="zipcode">우편번호</label>
              </div>
              <div class="input_box">
                <input type="text" name="zipcode" class="input_style" id="zipcode" maxlength="5">
                <input type="button" value="우편번호 찾기" class="input_style" onclick="execDaumPostcode()">
              </div>
            </div>
          </div>
          <div class="input_idpw_outer" id="input_address1">
            <div class="input_idpw_inner">
              <div class="input_title">
                <label for="address1">주소</label>
              </div>
              <div class="input_box">
                <input type="text" name="address1" class="input_style" id="address1" maxlength="30">
              </div>
            </div>
          </div>
          <div class="input_idpw_outer" id="input_address2">
            <div class="input_idpw_inner">
              <div class="input_title">
                <label for="address2">상세 주소</label>
              </div>
              <div class="input_box">
                <input type="text" name="address2" class="input_style" id="address2" maxlength="30">
              </div>
            </div>
          </div>
          <div class="input_idpw_outer" id="input_birth">
            <div class="input_idpw_inner">
              <div class="input_title">
                <label for="birth">생년월일</label>
              </div>
              <div class="input_box">
                <input type="text" name="birth" class="input_style" id="birth" maxlength="6">
              </div>
            </div>
          </div>
          <div class="text_announce">
            <div class="input-notice"><strong>ex) 961221</strong></div>
          </div>
          <div class="input_idpw_outer" id="input_gender">
            <div class="input_idpw_inner">
              <div class="input_title_radio">
                <label for="gender">성별</label>
                <span class="gender_span">남자</span>
                <input type="radio" name="gender"
                       id="gender" class="gender" value="1">
                <span class="gender_span">여자</span>
                <input type="radio" name="gender"
                       id="gender" class="gender" value="2">
              </div>
            </div>
          </div>

        </div>
        <div class="page_login">
          <ul class="button_all" id="page_button_4">
            <li><input type="submit" class="button" value="확인"></li>
            <li><input type="button" class="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'"></li>
          </ul>
        </div>
      </form>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>



