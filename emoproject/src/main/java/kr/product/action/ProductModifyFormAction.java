package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.product.vo.ProductManageVO;

public class ProductModifyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");

		// 회원번호 check - 로그인 여부
		if (user_num == null) {// 로그인 X
			return "redirect:/member/loginForm.do";
		}

		// 회원등급 check - 관리자 계정 여부
		Integer user_auth = (Integer) session.getAttribute("user_auth");
		if (user_auth < 9) { // 관리자 계정이 아니다
			return "/WEB-INF/views/common/notice.jsp";
		}

		int product_num = Integer.parseInt(request.getParameter("product_num"));
		ProductDAO dao = ProductDAO.getInstance();
		ProductManageVO product = dao.getProduct(product_num);

		// 관리자로 로그인한 경우
		request.setAttribute("product", product);
		// JSP 경로설정
		return "/WEB-INF/views/product/product_modifyForm.jsp";
	}

}
