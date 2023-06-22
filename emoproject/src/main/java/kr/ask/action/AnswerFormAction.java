package kr.ask.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ask.dao.askDAO;
import kr.ask.vo.AskVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class AnswerFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		if(user_auth<9) {
			return "/WEB-INF/views/commone/notice.jsp";
		}		
		
		int ask_num=Integer.parseInt(request.getParameter("ask_num"));
		askDAO dao=askDAO.getInstance();
		AskVO ask=dao.getAsk(ask_num);
		
		ask.setAsk_title(StringUtil.useNoHtml(ask.getAsk_title()));
		ask.setAsk_content(StringUtil.useBrNoHtml(ask.getAsk_content()));
		
		request.setAttribute("ask", ask);
		
		return "/WEB-INF/views/ask/answerForm.jsp";
	}

}
