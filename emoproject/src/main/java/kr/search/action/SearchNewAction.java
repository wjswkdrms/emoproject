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

public class SearchNewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션 받을게 없음
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		SearchDAO dao = SearchDAO.getInstance();
		List<SearchVO> list = null;
		//list = dao.getProductCategories();
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		//ajax 등록 끝
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
