package kr.faq.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.faq.dao.FaqDAO;
import kr.faq.vo.FaqVO;

public class FaqWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		
		if(user_auth<9) {
			return "/WEB-INF/views/common/notice.jsp";			
		}
		
		FaqVO faq=new FaqVO();
		faq.setMem_num(user_num);
		faq.setFaq_title(request.getParameter("faq_title"));
		faq.setFaq_content(request.getParameter("faq_content"));
		faq.setFaq_category(Integer.parseInt(request.getParameter("faq_category")));
		
		FaqDAO dao=FaqDAO.getInstance();
		dao.insertFaq(faq);
		
		//JSP 경로 반환
		return "/WEB-INF/views/faq/faqWrite.jsp";
	}	
}
	
