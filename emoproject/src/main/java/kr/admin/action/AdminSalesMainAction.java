package kr.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.admin.dao.AdminDAO;
import kr.admin.vo.AdminVO;
import kr.controller.Action;
import kr.search.vo.SearchVO;

public class AdminSalesMainAction implements Action {

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
		
		//추가
		AdminDAO admindao = AdminDAO.getInstance();
		AdminVO adminvo = new AdminVO();
		List<AdminVO> list = null;
		list = admindao.getProductsOrdersByAdmin();
		String today = null;
		today = admindao.getToday();
		request.setAttribute("today",today);
		request.setAttribute("list", list);
		
		AdminDAO dao = AdminDAO.getInstance();
		AdminVO vo = dao.getTotalSales();
		
		
		int product_total_price = vo.getProduct_total_price();
		int product_total_profit = vo.getProduct_total_profit();
		int product_sales_quantity = vo.getProduct_sales_quantity();
		
		request.setAttribute("product_total_price",product_total_price);
		request.setAttribute("product_total_profit",product_total_profit);
		request.setAttribute("product_sales_quantity",product_sales_quantity);
		return "/WEB-INF/views/admin/adminSalesMain.jsp";
	}

}
