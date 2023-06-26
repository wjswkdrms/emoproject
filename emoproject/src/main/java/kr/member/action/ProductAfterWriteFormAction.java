package kr.member.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.product.vo.ProductDetailVO;

public class ProductAfterWriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//쿼리스트링
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		request.setAttribute("product_num",product_num);
		request.setAttribute("order_num",order_num);
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/productAfterWriteForm.jsp";
	}

}
