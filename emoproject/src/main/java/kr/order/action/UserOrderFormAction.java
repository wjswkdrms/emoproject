package kr.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.product.vo.ProductManageVO;

public class UserOrderFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		//post방식의 접근만 허용
		if(request.getMethod().toUpperCase().equals("GET")) {
			return "redirect:/cart/list.do";
		}
		CartDAO dao = CartDAO.getInstance();
		int all_total = dao.getTotalByMem_num(user_num);
		if(all_total<=0) {
			request.setAttribute("notice_msg", "정상적인 주문이 아니거나 상품의 수량이 부족합니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/cart/list.do");
			
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		int mem_point = dao.getMemPoint(user_num);
		if(mem_point-all_total < 0) {
			request.setAttribute("notice_msg", "보유중인 포인트가 부족합니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/cart/list.do");
			
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		//장바구니에 담겨 있는 상품 정보 호출
		List<CartVO> cartList = dao.getListCart(user_num);
		CartDAO itemDao = CartDAO.getInstance();
		for(CartVO cart : cartList) {
			ProductManageVO item = itemDao.getItem(cart.getProduct_num());
			if(item.getProduct_status()==1) {//상품 미표시
				request.setAttribute("notice_msg", "["+item.getProductdetailVO().getProduct_name()+"]상품 판매 중지");
				request.setAttribute("notice_url", request.getContextPath()+"/cart/list.do");
				return "/WEB-INF/views/common/alert_singleView.jsp";
			}
			if(item.getProductdetailVO().getProduct_stock() < cart.getCart_quantity()) {
				request.setAttribute("notice_msg", "["+item.getProductdetailVO().getProduct_name()+"]재고수량 부족으로 주문 불가");
				request.setAttribute("notice_url", request.getContextPath()+"/cart/list.do");
				return "/WEB-INF/views/common/alert_singleView.jsp";
			}
		}
		//주문 상품이 대표 상품명  생성
		String item_name;
		if(cartList.size()==1) {//상품이 하나인 경우
			item_name = cartList.get(0).getProduct().getProduct_title();
		}else {
			item_name = cartList.get(0).getProduct().getProduct_title() + "외 " + (cartList.size() - 1) + "건";
		}
		//회원 보유 보인트
		int point = dao.getMemPoint(user_num);
		
		request.setAttribute("item_name", item_name);
		request.setAttribute("point", point);
		request.setAttribute("list", cartList);
		request.setAttribute("all_total", all_total);
		
		return "/WEB-INF/views/order/user_orderForm.jsp";
	}

}
