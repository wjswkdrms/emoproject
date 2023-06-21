package kr.order.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.MemberHomeVO;
import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;
import kr.product.vo.ProductManageVO;

public class UserOrderAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		//post방식의 접근만 허용
		if(request.getMethod().toUpperCase().equals("GET")) {
			return "redirect:/item/itemList.do";
		}
				
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		CartDAO dao = CartDAO.getInstance();
		int all_total = dao.getTotalByMem_num(user_num);
		if(all_total<=0) {
			request.setAttribute("notice_msg", "정삭적인 주문이 아니거나, 상품의 수량이 부족합니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/item/itemList.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		//장바구니에 담겨 있는 상품 정보 반환
		List<CartVO> cartList = dao.getListCart(user_num);
		//주문 상품이 대표 상품명  생성
		String item_name;
		if(cartList.size()==1) {//상품이 하나인 경우
			item_name = cartList.get(0).getProduct().getProduct_name();
		}else {
			item_name = cartList.get(0).getProduct().getProduct_name() + "외 " + (cartList.size() - 1) + "건";
			
		}
		//개별 상품 정보 담기
		List<OrderDetailVO> orderDetailList = new ArrayList<OrderDetailVO>();
		CartDAO itemdao = CartDAO.getInstance();
		for(CartVO cart : cartList) {
			ProductManageVO item = itemdao.getItem(cart.getProduct_num());
			if(item.getProduct_status() == 1) {
				//상품 미표시
				request.setAttribute("notice_msg", "["+item.getProductdetailVO().getProduct_name()+"]상품 판매 중지");
				request.setAttribute("notice_url", request.getContextPath() + "/cart/list.do");
				return "/WEB-INF/views/common/alert_singleView.jsp";
			}
			if(item.getProductdetailVO().getProduct_stock() < cart.getCart_quantity()) {
				//재고 수량이 부족한 경우
				request.setAttribute("notice_msg", "["+item.getProductdetailVO().getProduct_name()+"]재고수량 부족으로 주문 불가");
				request.setAttribute("notice_url", request.getContextPath() + "/cart/list.do");
				
			}
			OrderDetailVO orderDetail = new OrderDetailVO();
			orderDetail.setProduct_num(cart.getProduct_num());
			orderDetail.setOrder_product_name(cart.getProduct().getProduct_name());
			orderDetail.setOrder_product_price(cart.getProduct().getProduct_price());
			orderDetail.setOrder_product_quantity(cart.getCart_quantity());
			orderDetail.setOrder_product_total(cart.getProduct().getProduct_price()*cart.getCart_quantity());
			orderDetailList.add(orderDetail);
		}//end of for
		
		//구매 정보 담기
		OrderVO order = new OrderVO();
		order.setOrder_total_price(all_total);
		order.setOrder_notice(request.getParameter("notice"));
		order.setMem_num(user_num);
		
		MemberHomeVO home = new MemberHomeVO();
		home.setMem_home_zipcode(Integer.parseInt(request.getParameter("receive_post")));
		home.setMem_home_address1(request.getParameter("receive_address1"));
		home.setMem_home_address2(request.getParameter("receive_address2"));
		home.setMem_home_cell(request.getParameter("receive_phone"));
		home.setMem_home_name(request.getParameter("name"));
		home.setMem_num(user_num);
		
		OrderDAO orderDao = OrderDAO.getInstance();
		orderDao.insertOrder(order, home, orderDetailList);
		
		//refresh 정보를 응답 헤더에 추가
		response.addHeader("Refresh", "2;url=../main/main.do");
		request.setAttribute("accessMsg", "주문이 완료되었습니다.");
		request.setAttribute("accessUrl", request.getContextPath()+"/main/main.do");
		
		return "/WEB-INF/views/common/notice.jsp";
	}

}
