package kr.search.action;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.controller.Action;
import kr.search.dao.SearchDAO;
import kr.search.vo.SearchVO;


public class SearchCategoryMainAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		/* 회원 정보 가져오기가 필요하면 쓰기
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		*/
		
		//product category number 받기
		request.setCharacterEncoding("utf-8");
		
		//null값 생길 시에 어떻게 할지..
		int product_category_num = 0;
		if(request.getParameter("product_category") == null) { //search box로 들어 왔을 때 ////////////////////
			
			String searchText = request.getParameter("searchText");
			String search_Result_Text = "";
			
			search_Result_Text += searchText;
			
			//dao
			request.setAttribute("product_category_name",searchText);
			
			
			return "/WEB-INF/views/search/searchCategoryMain.jsp";
		} 
		product_category_num = Integer.parseInt(request.getParameter("product_category"));
		
		
		//product name 처리
		String product_category_name = null;
		if(product_category_num == 1) {
			product_category_name = "농산물";
		} else if (product_category_num == 2) {
			product_category_name = "수산물";
		} else if (product_category_num == 3) {
			product_category_name = "정육";
		} else if (product_category_num == 4) {
			product_category_name = "유제품";
		} else if (product_category_num == 5) {
			product_category_name = "음료";
		} else if (product_category_num == 6) {
			product_category_name = "베이커리";
		} else {
			product_category_name = "";
		}
		request.setAttribute("product_category_name", product_category_name);
		request.setAttribute("product_category", product_category_num);
		/*
		SearchDAO dao = SearchDAO.getInstance();
		List<SearchVO> categoryList = null;
		categoryList = dao.getProductCategories(product_category_num);
		
		request.setAttribute("categoryList",categoryList);
		*/
		/*
		 * Map<String, Object> mapAjax = new HashMap<String, Object>();
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
		*/
		//ajax 등록 끝
		return "/WEB-INF/views/search/searchCategoryMain.jsp";
	}

}
