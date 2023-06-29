package kr.member.action;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.product.vo.ProductDetailVO;

public class ProductAfterWriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");	
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		//쿼리스트링
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		
		MemberDAO dao = MemberDAO.getInstance();
		int afterCount = 1;
		afterCount = dao.getOrderListBoardCount4(user_num,product_num);
		
		request.setAttribute("afterCount", afterCount);
		request.setAttribute("product_num",product_num);
		request.setAttribute("order_num",order_num);
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/productAfterWriteForm.jsp";
	}

}
