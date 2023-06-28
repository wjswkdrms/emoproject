package kr.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.admin.dao.AdminDAO;
import kr.admin.vo.AdminVO;
import kr.controller.Action;

public class AdminSalesOrdersAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");

		if (user_num == null) {
			return "redirect:/member/loginForm.do";
		}

		Integer user_auth = (Integer) session.getAttribute("user_auth");
		if (user_auth < 9) {
			return "/WEB-INF/views/commone/notice.jsp";
		}
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		AdminDAO dao = AdminDAO.getInstance();
		AdminVO vo = new AdminVO();
		List<AdminVO> list = null;
		list = dao.getMemberOrdersByAdmin(product_num);
		String today = null;
		today = dao.getToday();
		request.setAttribute("today",today);
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/admin/adminSalesOrders.jsp";
	}

}
