package kr.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.MemberHomeVO;

public class UserAddressUpdateAction implements Action{

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
		
		dao.updateMemberAddress(home, user_num);
		request.setAttribute("notice_msg", "주소가 변경되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/main/main.do");
		return "/WEB-INF/views/common/alert_singleView.jsp";
	}
	
}
