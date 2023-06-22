package kr.ask.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ask.dao.AnswerDAO;
import kr.ask.dao.askDAO;
import kr.ask.vo.AnswerVO;
import kr.ask.vo.AskVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class AskDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/login.do";
		}
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		
		askDAO dao=askDAO.getInstance();
		int ask_num=Integer.parseInt(request.getParameter("ask_num"));
		AskVO ask=dao.getAsk(ask_num);
		AnswerDAO dao2=AnswerDAO.getInstance();
		AnswerVO answer=dao2.getAnswer(ask_num);
		if(user_num==ask.getMem_num() || user_auth==9) {
			ask.setAsk_title(StringUtil.useNoHtml(ask.getAsk_title()));
			ask.setAsk_content(StringUtil.useBrNoHtml(ask.getAsk_content()));
			request.setAttribute("ask", ask);
			if(answer!=null) {
				answer.setAsk_vo(ask);
				answer.setAsk_num(ask_num);
				request.setAttribute("answer", answer);
			}
		}else {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		return "/WEB-INF/views/ask/askDetail.jsp";
	}

}
