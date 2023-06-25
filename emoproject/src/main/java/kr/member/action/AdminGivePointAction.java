package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.AdminMemberDAO;

public class AdminGivePointAction implements Action{

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
		
		int mem_num=Integer.parseInt(request.getParameter("mem_num"));
		int give_point=Integer.parseInt(request.getParameter("give-point"));
		AdminMemberDAO dao=AdminMemberDAO.getInstance();
		dao.giveMemberPoint(give_point, mem_num);
		
		return "redirect:/member/adminMemberDetail.do?mem_num="+mem_num;
	}

}
