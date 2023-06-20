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
import kr.product.dao.ProductDAO;
import kr.product.vo.ProductManageVO;


public class ModifyCartAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute(
				                       "user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");
			//전송된 데이터 반환
			int item_num = 
					Integer.parseInt(
			          request.getParameter(
			        		    "item_num"));
			int item_quantity =
					Integer.parseInt(
					  request.getParameter(
							  "order_quantity"));
			
			CartDAO itemDao = CartDAO.getInstance();
			ProductManageVO item = itemDao.getItem(item_num);
			if(item.getProduct_status()==1) {//미표시
				mapAjax.put("result", "noSale");
			}else if(item.getProductdetailVO().getProduct_stock()<item_quantity) {
				//상품 재고 수량보다 장바구니에 담은 구매 수량이
				//더 많음
				mapAjax.put("result", "noQuantity");
			}else {//표시 상품이면서 재고가 부족하지 않음
				CartVO cart = new CartVO();
				cart.setMem_cart_num(
						Integer.parseInt(
								request.getParameter(
										"cart_num")));
				cart.setCart_quantity(item_quantity);
				
				CartDAO cartDao = CartDAO.getInstance();
				cartDao.updateCart(cart);
				
				mapAjax.put("result", "success");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}



