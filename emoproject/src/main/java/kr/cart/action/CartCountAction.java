package kr.cart.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;


import kr.controller.Action;

import kr.search.dao.SearchDAO;

public class CartCountAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Map<String, Object> mapAjax = new HashMap<>();

		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");
		if (user_num == null) {// 로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
			
		} else {// 로그인이 된 경우
				// 전송된 데이터 인코딩 처리
			
			
			mapAjax.put("result", "success");
			// Object로 바꿔서 null처리를 해 줘야함
			SearchDAO searchDao = SearchDAO.getInstance();
			int cartCount = 0;
			cartCount = searchDao.getCartCount(user_num);
			mapAjax.put("cartCount", cartCount);
			
			// json 데이터 생성
			ObjectMapper mapper = new ObjectMapper();
			String ajaxData = mapper.writeValueAsString(mapAjax);
			request.setAttribute("ajaxData", ajaxData);
		}
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
