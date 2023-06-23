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
import kr.util.PageUtil;

public class SearchSortedNavigatorAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String product_category_str = request.getParameter("product_category");
		int product_category_num = 0;
		if(request.getParameter("product_category") != null && !product_category_str.isEmpty()) {
			
			product_category_num = Integer.parseInt(request.getParameter("product_category"));
			
		} 
		
		
		int sorted_navigator_num = 0;
		if(request.getParameter("sorted_navigator_num") != null) {
			sorted_navigator_num = Integer.parseInt(request.getParameter("sorted_navigator_num"));
			
		} else if (request.getParameter("sorted_navigator_num") == null) {
			sorted_navigator_num = 1;
		}
			
		String searchText = request.getParameter("searchText");
		Map<String,Object> mapAjax = new HashMap<>();
		List<SearchVO> list = null;
		SearchDAO dao = SearchDAO.getInstance();
		//페이징 처리
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageBlock = Integer.parseInt(request.getParameter("pageBlock"));
		int count = dao.getSortingProductWithProductCategoryCount(searchText, sorted_navigator_num, product_category_num);
		PageUtil page = new PageUtil(Integer.parseInt(pageNum), count, pageSize, pageBlock, "${pageContext.request.contextPath}/search/searchSortedNavigator.do");
		
		list = dao.getSortingProductWithProductCategory(page.getStartRow(),page.getEndRow(),searchText,sorted_navigator_num,product_category_num);
		//페이지 처리 끝...
		mapAjax.put("result", "success");
		mapAjax.put("list", list);
		mapAjax.put("count", count);
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
