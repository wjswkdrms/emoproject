package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

import kr.search.dao.SearchDAO;
import kr.search.vo.SearchVO;
import kr.util.PageUtil;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SearchDAO dao = SearchDAO.getInstance();
		//페이징 처리를 위한 초기 세팅
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		int count = dao.getProductMainEverythingCount();
		
		//페이지 유틸에다가 넣기
		//32개씩 10페이지 임
		PageUtil page = new PageUtil(Integer.parseInt(pageNum),count,32,10,"main.do");
		List<SearchVO> productList = null;
		if(count >0) {
			
		productList = dao.getProductMainEverything(page.getStartRow(), page.getEndRow());
		
		}
		
		request.setAttribute("count", count);
		request.setAttribute("productList", productList);
		request.setAttribute("page", page.getPage());
		/*
		PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 20, 10, "list.do");

		List<BoardVO> list = null;
		if (count > 0) {
			list = dao.getListBoard(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}

		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		*/
		//JSP 경로 반환
		return "/WEB-INF/views/main/main.jsp";
	}

}




