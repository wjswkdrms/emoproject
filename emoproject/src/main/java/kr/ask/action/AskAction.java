package kr.ask.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ask.dao.askDAO;
import kr.ask.vo.AskVO;
import kr.controller.Action;

public class AskAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		askDAO dao=askDAO.getInstance();
		
		List<AskVO> list=null;
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		if(user_auth==9) {
			list=dao.getAskTotalBoard();
		}else {
			list=dao.getAskBoard(user_num);
		}
		//if(count>0) {
		//}

		
		request.setAttribute("list", list);	
			
		return "/WEB-INF/views/ask/ask.jsp";
	}

}
