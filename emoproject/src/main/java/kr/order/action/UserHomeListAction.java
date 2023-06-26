package kr.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.MemberHomeVO;

public class UserHomeListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		List<MemberHomeVO> list = null;
		OrderDAO dao = OrderDAO.getInstance();
		list = dao.getHomeList(user_num);
		
		request.setAttribute("list", list);
		return "/WEB-INF/views/order/userHomeList.jsp";
	}

}
