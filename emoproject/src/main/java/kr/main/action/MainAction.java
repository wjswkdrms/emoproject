package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.product.vo.ProductDetailVO;
import kr.product.vo.ProductManageVO;
import kr.search.dao.SearchDAO;
import kr.search.vo.SearchVO;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SearchDAO dao = SearchDAO.getInstance();
		List<SearchVO> productList = dao.getProductMainEverything();
		request.setAttribute("productList", productList);
		//JSP 경로 반환
		return "/WEB-INF/views/main/main.jsp";
	}

}




