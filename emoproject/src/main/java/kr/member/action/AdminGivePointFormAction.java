package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.AdminMemberDAO;
import kr.member.vo.MemberVO;

public class AdminGivePointFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		AdminMemberDAO dao=AdminMemberDAO.getInstance();
		MemberVO member=dao.getMemberDetail(mem_num);
		
		request.setAttribute("member", member);
		
		return "/WEB-INF/views/member/adminGivePointForm.jsp?mem_num="+mem_num;
	}

}
