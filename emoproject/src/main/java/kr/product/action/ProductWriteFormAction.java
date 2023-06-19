package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class ProductWriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		/*
		 * // 회원번호 check - 로그인 여부 if (mem_num == null) {// 로그인 X return
		 * "redirect:/member/loginForm.do"; }
		 * 
		 * // 회원등급 check - 관리자 계정 여부 Integer mem_auth = (Integer)
		 * session.getAttribute("mem_auth"); if (mem_auth < 9) { // 관리자 계정이 아니다 return
		 * "/WEB-INF/views/common/notice.jsp"; }
		 */
		//관리자로 로그인한 경우
		//JSP 경로설정
		return "/WEB-INF/views/product/product_writeForm.jsp";
	}
	
}
