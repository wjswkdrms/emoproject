<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myEdit.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myEditForm.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer_style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){			
		//0:중복 체크 미실시, email 중복
		//1:email 미중복
		let emailChecked = 0;
		
		//이메일 중복 체크
		$('#email_check').click(function(){
			if(!/^[a-zA-Z0-9._-]{1,46}@[a-zA-Z0-9._-]+\.[a-zA-Z]{2,3}$/.test(
					            $('#email').val())){
				alert('영문 또는 숫자와 일부 특수문자만 사용, 최대50자를 사용하세요');
				$('#email').val('');
				$('#email').focus();
				return false;
			}
			//서버와의 통신
			$.ajax({
				url:'checkDuplicatedEmail.do',
				type:'post',
				data:{email:$('#email').val()},
				dataType:'json',
				success:function(param){
					if(param.result == 'emailNotFound'){
						//email 미중복
						emailChecked = 1;
						$('#message_email').css('color','#000000')
						                .text('등록 가능 email');
					}else if(param.result == 'emailDuplicated'){
						//email 중복
						emailChecked = 0;
						$('#message_email').css('color','red')
						                .text('중복된 email');
						$('#email').val('').focus();
					}else{
						emailChecked = 0;
						alert('이메일 중복 체크 오류 발생');
					}
				}
				error:function(){
					emailChecked = 0;
					alert('네트워크 오류 발생');
				}
			});
			
		});//end of click
		
		//이메일 중복 안내 메시지 초기화 및 이메일
		//중복 값 초기화
		$('#myEdit_form #email').keydown(function(){
			emailChecked = 0;
			$('#message_email').text('');
		});//end of keydown
		
		//전화번호 중복 체크
		$('#cell_check').click(function(){
			if(![0-9\-]{10,13}$/.test(
					            $('#cell').val())){
				alert('올바른 전화번호를 입력하세요');
				$('#cell').val('');
				$('#cell').focus();
				return false;
			}
			//서버와의 통신
			$.ajax({
				url:'checkDuplicatedCell.do',
				type:'post',
				data:{cell:$('#cell').val()},
				dataType:'json',
				success:function(param){
					if(param.result == 'cellNotFound'){
						//email 미중복
						cellChecked = 1;
						$('#message_cell').css('color','#000000')
						                .text('등록 가능 전화번호');
					}else if(param.result == 'cellDuplicated'){
						//email 중복
						cellChecked = 0;
						$('#message_cell').css('color','red')
						                .text('중복된 전화번호');
						$('#cell').val('').focus();
					}else{
						cellChecked = 0;
						alert('전화번호 중복 체크 오류 발생');
					}
				}
				error:function(){
					cellChecked = 0;
					alert('네트워크 오류 발생');
				}
			});
			
		});//end of click
		
		//전화번호 중복 안내 메시지 초기화 및 전화번호
		//중복 값 초기화
		$('#myEdit_form #cell').keydown(function(){
			cellChecked = 0;
			$('#message_cell').text('');
		});//end of keydown

		//공백 입력 방지
		$('#myEdit_form').submit(function(){
			if($('#passwd').val().trim()==''){
				alert('비밀번호를 입력하세요');
				$('#passwd').val('').focus();
				return false;
			}
			if($('#cell').val().trim()==''){
				alert('전화번호를 입력하세요');
				$('#passwd').val('').focus();
				return false;
			}
			if($('#zipcode').val().trim()==''){
				alert('우편번호를 입력하세요');
				$('#passwd').val('').focus();
				return false;
			}
			if($('#address1').val().trim()==''){
				alert('주소를 입력하세요');
				$('#passwd').val('').focus();
				return false;
			}
			if($('#address2').val().trim()==''){
				alert('상세주소를 입력하세요');
				$('#passwd').val('').focus();
				return false;
			}
			//잘못된 입력 방지
			if(!/^[0-9\-]+$/.test($('#cell').val())){
				alert('전화번호는 숫자와 -만 입력 가능합니다.');
				$('#cell').val('').focus();
				return false;
			}
			if(!/^[a-zA-Z0-9.@*]+$/.test($('#email').val())){
				alert('이메일은 영문자와 숫자만 입력 가능합니다.');
				$('#email').val('').focus();
				return false;
			}
			if(!/^[0-9]+$/.test($('#zipcode').val())){
				alert('우편번호는 숫자만 입력 가능합니다.');
				$('#zipcode').val('').focus();
				return false;
			}
		});
		
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="whole">
  <div class="container">
    <div class="left-div">

      <div class="page-name">내 정보</div>
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
      <div class="title">
        <h2>개인정보 수정</h2>
      </div>
      <form id="myEdit_form" action="myEdit.do" method="post">
        <div class="page_input">
        
              <div class="page_input_box">
              <div class="page_input_title">아이디</div>
                <input type="text" class="output_style_check" value="${mem_id}" readonly> 	
          </div>
              <div class="page_input_box">
              <div class="page_input_title">이름</div>
                <input type="text" class="output_style_check" value="${mem_name}" readonly>
          </div>
              <div class="page_input_box">
              <div class="page_input_title">비밀번호</div>
                 <input type="password" class="input_style" name="passwd" id="passwd" pattern=".{4,12}" required title="4~12자의 비밀번호를 입력하세요" value="${mem_passwd}">
          </div>
          <div class="text_announce">
            <div class="input-notice"><strong>* 영문 대소문자 또는 숫자(4자~12자)</strong></div>
          </div>
              <div class="page_input_box">
              <div class="page_input_title">전화번호</div>
                <input type="text" name="cell" class="input_style" id="cell" pattern=".{10,13}" required title="올바른 전화번호를 입력하세요" value="${mem_cell}">
                <input type="button" class="input_style_check" value="전화번호 중복체크" id="cell_check" >
                <span id="message_cell"></span>
              </div>
              <div class="page_input_box">
              <div class="page_input_title">이메일</div>
                <input type="email" name="email" class="input_style" id="email" pattern=".{1,50}" required title="이메일 입력 필수" value="${mem_email}">
                <input type="button" class="input_style_check" value="이메일 중복체크" id="email_check" >
                <span id="message_email"></span>
          </div>
              <div class="page_input_box">
              <div class="page_input_title">우편번호</div>
                <input type="text" name="zipcode" class="input_style" id="zipcode" pattern=".{5,5}" required title="5자리 입력 필수" value="${mem_zipcode}">
                <input type="button" value="우편번호 찾기" class="input_style_check" onclick="execDaumPostcode()">
          </div>
              <div class="page_input_box">
              <div class="page_input_title">주소</div>
                <input type="text" name="address1" class="input_style" id="address1" pattern=".{1,150}" required title="주소를 입력하세요" value="${mem_address1}">
          </div>
              <div class="page_input_box">
              <div class="page_input_title">상세주소</div>
                <input type="text" name="address2" class="input_style" id="address2" pattern=".{1,150}" required title="상세주소를 입력하세요" value="${mem_address2}">
          </div>
              <div class="page_input_box">
              <div class="page_input_title">생년월일</div>
                <input type="text" class="output_style_check" value="${mem_birth}" readonly>
          </div>
        <div class="page_button2">
          <div class="page_button_box">
            <input type="submit" class="button3" value="수정 완료">
            <input type="button" class="button4" value="취소" onclick="location.href='${pageContext.request.contextPath}/member/orderList.do'">
          </div>
        </div>
        </div>
      </form>
    </div>
  </div>
</div>
  	<!-- 내용 끝 -->
	<!-- 우편번호 검색 시작 -->
	<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }

    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J).
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    //(주의)address1에 참고항목이 보여지도록 수정
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    //(수정) document.getElementById("address2").value = extraAddr;
                
                } 
                //(수정) else {
                //(수정)    document.getElementById("address2").value = '';
                //(수정) }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode;
                //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
                document.getElementById("address1").value = addr + extraAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("address2").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width : '100%',
            height : '100%',
            maxSuggestItems : 5
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition(){
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 400; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }
</script>
	<!-- 우편번호 검색 끝 -->
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>