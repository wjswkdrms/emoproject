package kr.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.MemberHomeVO;

public class UserUpdateHomeAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		MemberHomeVO home = new MemberHomeVO();
		
		request.setCharacterEncoding("utf-8");
		home.setMem_home_address1(request.getParameter("receive_address1"));
		home.setMem_home_address2(request.getParameter("receive_address2"));
		home.setMem_home_zipcode(Integer.parseInt(request.getParameter("receive_post")));
		home.setMem_home_name(request.getParameter("receive_name"));
		home.setMem_home_cell(request.getParameter("receive_phone"));
		home.setMem_num(user_num);
		
		OrderDAO dao = OrderDAO.getInstance();
		dao.insertHome(home);
		
		request.setAttribute("accessMsg", "수정이 완료되었습니다.");
		request.setAttribute("accessUrl", request.getContextPath()+"/order/userHomeList.do");
		
		return "/WEB-INF/views/common/notice.jsp";
	}

}
