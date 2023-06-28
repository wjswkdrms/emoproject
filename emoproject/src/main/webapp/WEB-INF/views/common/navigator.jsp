<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<title>Navigator</title>
<style>
	html{
		scroll-behavior: smooth;
	}
	
	
	#nav_top_bottom {
	
		position:fixed;
		width:200px;
		height:200px;
		top : 50%;
		right : 0;
		display:flex;
		flex-wrap: wrap;
		flex-direction: column;
		justify-content: center;
		align-items:center;
		z-index:5;
		
	}
	
	#nav_top_bottom a {
		border-radius : 10px;
		width:40px;
		height:40px;
		margin-top:10px;
		
		display:flex;
		justify-content: center;
		align-items:center;
	}
	
	#nav_top_bottom a img {
		display:block;
		width:40px;
		height:40px;
	}
	
	#up_arrow {
		transform: rotate(270deg);
		
	}
	
	#down_arrow {
		transform: rotate(90deg);
	}
</style>
<script>
function scrollTo(element, to, 300) {
	  if (duration <= 0) return;
	  var difference = to - element.scrollTop;
	  var perTick = difference / duration * 10;

	  setTimeout(function() {
	    element.scrollTop = element.scrollTop + perTick;
	    if (element.scrollTop === to) return;
	    scrollTo(element, to, duration - 10);
	  }, 10);
	}

	// 앵커 링크 클릭 시 스크롤 애니메이션 트리거
	var smoothScrollLinks = document.getElementsByClassName('smooth-scroll');
	for (var i = 0; i < smoothScrollLinks.length; i++) {
	  smoothScrollLinks[i].addEventListener('click', function(e) {
	    e.preventDefault();
	    var target = document.querySelector(this.getAttribute('href'));
	    scrollTo(document.documentElement, target.offsetTop, 800);
	  });
	}
</script>
<div id="nav_top_bottom">
	<a href="#" class="smooth-scroll"><img src="${pageContext.request.contextPath}/images/emo_icon_right01.svg" id="up_arrow"></a>
	<a href="#footer_anker" class="smooth-scroll"><img src="${pageContext.request.contextPath}/images/emo_icon_right01.svg" id="down_arrow"></a>
</div>