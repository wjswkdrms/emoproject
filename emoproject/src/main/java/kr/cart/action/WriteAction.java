package kr.cart.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.product.vo.ProductManageVO;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인이 된 경우
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");
			
			CartVO cart = new CartVO();
			
			//Object로 바꿔서 null처리를 해 줘야함
			
				
				cart.setProduct_num(Integer.parseInt(request.getParameter("product_num")));
				cart.setCart_quantity(Integer.parseInt(request.getParameter("order_quantity")));
				cart.setMem_num(user_num);
				
			
			CartDAO dao = CartDAO.getInstance();
			CartVO db_cart = dao.getcart(cart);
			
			
			
			if(db_cart==null) {//동일 상품이 없을 경우
				dao.insertCart(cart);
				mapAjax.put("result", "success");
			}else {//동일 상품이 있을 경우
				//재고수를 구하기 위해서 itemDAO 호출
				
				ProductManageVO item = dao.getItem(db_cart.getProduct_num());
				//구매 수량 합산(기존 장바구니에 저장된 구매수량 + 새로 입력한 구매 수량)
				int order_quantity = db_cart.getCart_quantity()+cart.getCart_quantity();
				if(item.getProductdetailVO().getProduct_stock() < order_quantity) {//상품 재고 수량보다 장바구니에 담은 구매 수량이 더 많을때
					mapAjax.put("result", "over_quantity");
				}else {
					cart.setCart_quantity(order_quantity);
					dao.updateCartByItem_num(cart);
					mapAjax.put("result", "success");
					
					//추가 한 곳
					/*
					SearchDAO searchDao = SearchDAO.getInstance();
					int cartCount = 0;
					cartCount = searchDao.getCartCount(user_num);
					mapAjax.put("cartCount_ajax", String.valueOf(cartCount));
					*/
				}
			}
		}
		
		//json 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
