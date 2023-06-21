package kr.search.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.search.dao.SearchDAO;
import kr.search.vo.SearchVO;

public class SearchSortedNavigatorAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		Integer product_category_num = Integer.parseInt(request.getParameter("product_category"));
		int sorted_navigator_num = Integer.parseInt(request.getParameter("sorted_navigator_num"));
		Map<String,Object> mapAjax = new HashMap<>();
		List<SearchVO> list = null;
		SearchDAO dao = SearchDAO.getInstance();
		
		if(request.getParameter("sorted_navigator_num") == null) {
			sorted_navigator_num = 1;
		}
		
		list = dao.getSortingProductWithProductCategory(sorted_navigator_num,product_category_num);
		
		mapAjax.put("result", "success");
		mapAjax.put("list", list);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
