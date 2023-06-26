<%--
  Created by IntelliJ IDEA.
  User: favau
  Date: 2023-06-16
  Time: 오후 2:44
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- footer 시작 -->
<div id="footer">
  <div id="inner">
    <div id="footer_top">
      <h4 id="footer_top_service">이모 고객센터</h4>
      <h1 id="footer_top_service2">EMO CUSTOMER CENTER<span id="footer_top_time"> 09 : 00 - 17 : 50</span></h1>
      <div class="footer_top_sns">
        <a href="https://cafe.naver.com/javaz">
          <img class="sns" id="sns_right" src="${pageContext.request.contextPath}/images/footer_icon01.png">
        </a>
        <a href="https://pf.kakao.com/_xnnWSu">
          <img class="sns" src="${pageContext.request.contextPath}/images/footer_icon02.png">
        </a>
        <a href="https://www.instagram.com/sist3482/">
          <img class="sns" src="${pageContext.request.contextPath}/images/footer_icon03.png">
        </a>
      </div>
      <div id="footer_top_mont">
        <ul class="footer_top_mont" id="footer_anker">
          <li>
            <h6 class="mont_title">고객센터</h6>
            <a class="call">02.1234.5678</a>
          </li>
        </ul>
        <ul class="footer_top_mont">
          <li>
            <h6 class="mont_title">입점문의</h6>
            <a class="call">02.1234.5679</a>
          </li>
        </ul>
      </div>
      <hr>
    </div>
    <h1 class="footer_mid1_logo">
      <a href="${pageContext.request.contextPath}/main/main.do">
        <img id="sns" src="${pageContext.request.contextPath}/images/emo_main_logo.png">
      </a>
    </h1>
    <div id="footer_mid1">

      <br />
      <div id="footer_mid1_f1">
        <h5>이모 마켓  EMO</h5>
        <p>
          서울특별시 강남구 테헤란로 132(역삼동) <br>
          Tel_02-1234-5678  Fax_02-1234-5678
        </p>
      </div>
      <div id="footer_mid1_f2">
        <h5 class="mont">Company Info</h5>
        <p>
          사업자번호_123-45-67890 <br>
          대표_@@@  개인정보처리관리책임자_@@@
        </p>
      </div>
    </div>
    <hr>
    <div id="footer_mid21">
      <div id="footer_mid2">
        <ol>
          <li>회사소개</li>
          <li>광고문의</li>
          <li>입점문의</li>
          <li>이용약관</li>
          <li>개인정보처리방침</li>
          <li><a href="https://map.naver.com/v5/directions/-/14141273.687056389,4508936.736905865,%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C%20%EA%B0%95%EB%82%A8%EA%B5%AC%20%ED%85%8C%ED%97%A4%EB%9E%80%EB%A1%9C%20132,,/-/car?c=15,0,0,0,dh">찾아오시는 길</a></li>
          <li><a href="${pageContext.request.contextPath}/announce/announce.do">공지사항</a></li>
        </ol>
      </div>
    </div>
    <hr>
    <div id="footer_bot">
      <p id="footer_bot_cpr">Copyright(C) 2023 emo-market Institute of System Technology, All Right Reserved.</p><br>
      <br>
    </div>
  </div>
</div>
<!-- footer 끝 -->



