<%--
  Created by IntelliJ IDEA.
  User: favau
  Date: 2023-06-16
  Time: 오후 2:44
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- footer 시작 -->
<div id="footer">
  <div id="inner">
    <div id="footer_top">
      <h4>이모 고객센터</h4>
      <h3 >EMO CUSTOMER CENTER <span id="footer_top_time">09 : 00 - 17 : 50</span></h3>
      <ul class="footer_top_mont">
        <li>
          <h6>고객센터</h6>
          <a>02.1234.5678</a>
        </li>
      </ul>
      <ul class="footer_top_mont">
        <li>
          <h6>입점문의</h6>
          <a>02.1234.5679</a>
        </li>
      </ul>
      <br>
      <div class="footer_top_sns">
        <a href="https://cafe.naver.com/javaz">
        <img id="sns" src="${pageContext.request.contextPath}/images/footer_icon01.png">
        </a>
      </div>
      <hr>
    </div>
    <div id="footer_mid1">
      <h1 class="footer_mid1_logo">
        <a href="/">
          <img src="${pageContext.request.contextPath}/images/emo-logo.png">
        </a>
      </h1>
      <br />
      <div id="footer_mid1_f1">
        <h5>이모 마켓  <span class="mont">EMO</span></h5>
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
    <div id="footer_mid2">
    	<ol>
    		<li>회사소개</li>
    		<li>광고문의</li>
    		<li>입점문의</li>
    		<li>이용약관</li>
    		<li>개인정보처리방침</li>
    		<li>찾아오시는 길</li>
    		<li>공지사항</li>
    	</ol>
    </div>
    <div id="footer_bot">
      <p id="footer_bot_cpr">Copyright(C) 2023 emo-market Institute of System Technology, All Right Reserved.</p><br>
      <br>
    </div>
  </div>
</div>
<!-- footer 끝 -->



