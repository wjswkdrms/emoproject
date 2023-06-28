package kr.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.admin.dao.AdminDAO;
import kr.admin.vo.AdminVO;
import kr.controller.Action;

public class AdminSalesDateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");
		String order_date = request.getParameter("order_date");

		if (user_num == null) {
			return "redirect:/member/loginForm.do";
		}

		Integer user_auth = (Integer) session.getAttribute("user_auth");
		if (user_auth < 9) {
			return "/WEB-INF/views/commone/notice.jsp";
		}
		
		AdminDAO dao = AdminDAO.getInstance();
		AdminVO vo = new AdminVO();
		List<AdminVO> list = null;
		list = dao.getMemberOrdersByAdmin(order_date);
		request.setAttribute("order_date",order_date);
		request.setAttribute("list", list);
		
		AdminDAO admindao = AdminDAO.getInstance();
		AdminVO adminvo = admindao.getTotalSalesDate(order_date);
		int product_total_price = adminvo.getProduct_total_price();
		int product_sales_quantity = adminvo.getProduct_sales_quantity();
		
		request.setAttribute("product_total_price",product_total_price);
		
		request.setAttribute("product_sales_quantity",product_sales_quantity);
		
		return "/WEB-INF/views/admin/adminSalesDate.jsp";
		
	}

}
