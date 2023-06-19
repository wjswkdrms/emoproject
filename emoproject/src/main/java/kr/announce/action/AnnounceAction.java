package kr.announce.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.announce.dao.AnnounceDAO;
import kr.announce.vo.AnnounceVO;
import kr.controller.Action;
import kr.util.PageUtil;

public class AnnounceAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		
		String keyfield=request.getParameter("pageNum");
		String keyword=request.getParameter("keyword");
		
		AnnounceDAO dao=AnnounceDAO.getInstance();
		int count=dao.getAnnounceCount(keyfield, keyword);
		
		PageUtil page=new PageUtil(keyfield, keyword, 
				Integer.parseInt(pageNum),count,20,10,"announce.do");
		
		List<AnnounceVO> list=null;
		if(count>0) {
			list=dao.getAnnounceBoard();
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/announce/announce.jsp";
	}

}
