package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.faq.dao.FaqDAO;
import kr.faq.vo.FaqVO;

public class FaqUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		int faq_num=Integer.parseInt(request.getParameter("faq_num"));
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		if(user_auth<9) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		FaqDAO dao=FaqDAO.getInstance();
		FaqVO faq=dao.getFaq(faq_num);
		
		request.setAttribute("faq", faq);
		
		return "/WEB-INF/views/faq/faqUpdateForm.jsp";
	}
}

