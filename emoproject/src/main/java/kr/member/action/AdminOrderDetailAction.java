package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.AdminMemberDAO;
import kr.order.vo.MemberHomeVO;
import kr.order.vo.OrderDetailVO;

public class AdminOrderDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		
		if(user_auth<9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		int order_num=Integer.parseInt(request.getParameter("order_num"));
		
		AdminMemberDAO dao=AdminMemberDAO.getInstance();
		OrderDetailVO detail=dao.getOrderDetail(order_num);
		MemberHomeVO home=detail.getMemberhome();
		
		request.setAttribute("detail", detail);
		request.setAttribute("home", home);
		
		return "/WEB-INF/views/member/adminOrderDetail.jsp";
	}

}
