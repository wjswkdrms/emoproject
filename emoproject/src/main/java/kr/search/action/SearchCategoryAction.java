package kr.search.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.search.dao.SearchDAO;
import kr.search.vo.SearchVO;

public class SearchCategoryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> mapAjax = new HashMap<String, Object>();

		/* 회우너정보 가져오기가 필요하면 쓰기
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		*/
		
		request.setCharacterEncoding("utf-8");
		int product_category = Integer.parseInt(request.getParameter("product_category"));
		
		//product name 처리
		String product_category_name = null;
		
		if(product_category == 1) {
			product_category_name = "농산물";
		} else if (product_category == 2) {
			product_category_name = "수산물";
		} else if (product_category == 3) {
			product_category_name = "정육";
		} else if (product_category == 4) {
			product_category_name = "유제품";
		} else if (product_category == 5) {
			product_category_name = "음료";
		} else if (product_category == 6) {
			product_category_name = "베이커리";
		} 
		request.setAttribute("product_category_name", product_category_name);
		
		SearchDAO dao = SearchDAO.getInstance();
		List<SearchVO> list = null;
		
		//ajax 시작
		if(product_category_name != null) { //카테고리가 있을 때
			list = dao.getProductCategories(product_category);
			mapAjax.put("result", "productOk");
			mapAjax.put("list", list);
			
			
		} else {	//카테고리 박스 아닐 때
			mapAjax.put("result","productNo");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		//ajax 등록 끝
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
