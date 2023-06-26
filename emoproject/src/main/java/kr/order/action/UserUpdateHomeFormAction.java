package kr.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.MemberHomeVO;

public class UserUpdateHomeFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		int home_num = Integer.parseInt(request.getParameter("home_num"));
		
		OrderDAO dao = OrderDAO.getInstance();
		MemberHomeVO home = dao.getHome(home_num);
		
		request.setAttribute("home", home);
		return "/WEB-INF/views/order/updateHomeForm.jsp";
	}

}
