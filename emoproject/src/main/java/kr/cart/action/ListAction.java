package kr.cart.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				    (Integer)session.getAttribute(
				                       "user_num");
		if(user_num==null) {//로그인 하지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		CartDAO dao = CartDAO.getInstance();
		//정상가
		int before_total = dao.getBeforeTotalByMem_num(user_num);
		//회원별 총구매 금액
		int all_total = dao.getTotalByMem_num(user_num);
		//회원 보유 보인트
		int point = dao.getMemPoint(user_num);
		int discount_total = before_total-all_total;
		List<CartVO> list = null;
		if(all_total>0) {
			list = dao.getListCart(user_num);
			} 
		
		request.setAttribute("discount_total", discount_total);
		request.setAttribute("before_total",before_total);
		request.setAttribute("all_total", all_total);
		request.setAttribute("point", point);
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/cart/list.jsp";
	}
}
