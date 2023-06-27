package kr.faq.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.faq.dao.FaqDAO;
import kr.faq.vo.FaqVO;
import kr.util.PageUtil;

public class FaqAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		
		String keyfield=request.getParameter("keyfield");
		
		FaqDAO dao=FaqDAO.getInstance();
		int count=dao.getFaqCount(keyfield);
		
		PageUtil page=new PageUtil(keyfield,null, 
				Integer.parseInt(pageNum),count,10,10,"faq.do");
		
		List<FaqVO> list=null;
		if(count>0) {
			list=dao.getFaqBoard(page.getStartRow(),page.getEndRow(),keyfield);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/faq/faq.jsp";
	}

}
