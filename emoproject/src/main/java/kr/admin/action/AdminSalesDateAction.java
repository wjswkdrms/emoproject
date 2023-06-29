package kr.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.admin.dao.AdminDAO;
import kr.admin.vo.AdminVO;
import kr.controller.Action;
import kr.util.PageUtil;

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
		//페이징 처리를 위한 초기 세팅
		AdminDAO daoCount = AdminDAO.getInstance();
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		int count = daoCount.getMemberOrdersByAdminCount(order_date);
				
		//페이지 유틸에다가 넣기
		//10개씩 10페이지 임
		PageUtil page = new PageUtil(Integer.parseInt(pageNum),count,40,10,"adminSalesDate.do");
		List<AdminVO> list = null;
		if(count >0) {
		list = daoCount.getMemberOrdersByAdmin(page.getStartRow(), page.getEndRow(),order_date);
					}
		request.setAttribute("page", page.getPage());
				//페이징 처리 끝
				
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
