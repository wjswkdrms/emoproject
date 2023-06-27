package kr.ask.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ask.dao.askDAO;
import kr.ask.vo.AskVO;
import kr.controller.Action;
import kr.util.PageUtil;

public class AskAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		askDAO dao=askDAO.getInstance();
		int count=dao.getAskCount();

		PageUtil page=new PageUtil(null, null, 
				Integer.parseInt(pageNum),count,10,10,"ask.do");
		
		List<AskVO> list=null;
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		if(count>0) {
			if(user_auth==9) {
				list=dao.getAskTotalBoard(page.getStartRow(),page.getEndRow());
			}else {
				list=dao.getAskBoard(page.getStartRow(),page.getEndRow(),user_num);
			}
		}

		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
			
		return "/WEB-INF/views/ask/ask.jsp";
	}

}
