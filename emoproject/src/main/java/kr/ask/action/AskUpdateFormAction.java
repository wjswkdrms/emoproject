package kr.ask.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ask.dao.askDAO;
import kr.ask.vo.AskVO;
import kr.controller.Action;

public class AskUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}

		int ask_num=Integer.parseInt(request.getParameter("ask_num"));
		
		askDAO dao=askDAO.getInstance();
		AskVO ask=dao.getAsk(ask_num);
		
		if(user_num!=ask.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		request.setAttribute("ask", ask);
		
		return "/WEB-INF/views/ask/askUpdateForm.jsp";
	}

}
