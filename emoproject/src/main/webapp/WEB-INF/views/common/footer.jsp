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
  <div class="inner">
    <div class="footer_top">
      <h4>이모 고객센터</h4>
      <h3 >EMO CUSTOMER CENTER <span id="footer_top_time">09 : 00 - 17 : 50</span></h3>
      <ul class="mont">
        <li>
          <h6>고객센터</h6>
          <a>02.1234.5678</a>
        </li>
      </ul>
      <br>
      <div class="sns">
        <a href="https://cafe.naver.com/javaz">
        <img id="sns" src="${pageContext.request.contextPath}/images/footer_icon01.png">
        </a>
      </div>
      <hr>
    </div>
    <div class="footer_mid">
      <h1 class="logo">
        <a href="/">
          <img src="${pageContext.request.contextPath}/images/emo-logo.png">
        </a>
      </h1>
      <br />
      <div id="f1">
        <h5>이모 마켓  <span class="mont">EMO</span></h5>
        <p>
          서울특별시 강남구 테헤란로 132(역삼동) <br>
          Tel_02-1234-5678  Fax_02-1234-5678
        </p>
      </div>
      <div id="f2">
        <h5 class="mont">Company Info</h5>
        <p>
          사업자번호_123-45-67890 <br>
          대표_@@@  개인정보처리관리책임자_@@@
        </p>
      </div>
    </div>
    <br>
    <div class="footer_bot">
      <p class="cpr">Copyright(C) 2023 emo-market Institute of System Technology, All Right Reserved.</p><br>
      <br>
    </div>
  </div>
</div>
<!-- footer 끝 -->



