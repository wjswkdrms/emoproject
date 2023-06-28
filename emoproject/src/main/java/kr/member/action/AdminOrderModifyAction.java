package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.AdminMemberDAO;

public class AdminOrderModifyAction implements Action{

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
		
		int order_num=Integer.parseInt(request.getParameter("order_num"));
		int order_status=Integer.parseInt(request.getParameter("order_status"));
		AdminMemberDAO dao=AdminMemberDAO.getInstance();
		dao.adminOrderModify(order_num,order_status);
		return "redirect:/member/adminOrderDetail.do?order_num="+order_num;
	}
}

