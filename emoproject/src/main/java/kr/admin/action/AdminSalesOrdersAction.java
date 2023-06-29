package kr.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.admin.dao.AdminDAO;
import kr.admin.vo.AdminVO;
import kr.controller.Action;
import kr.util.PageUtil;

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
		
		
		String today = null;
		today = dao.getToday();
		request.setAttribute("today",today);
		
		//페이징 처리를 위한 초기 세팅
				AdminDAO daoCount = AdminDAO.getInstance();
				String pageNum = request.getParameter("pageNum");
				if (pageNum == null) pageNum = "1";
				int count = daoCount.getMemberOrdersByAdminCount(product_num);
				
				//페이지 유틸에다가 넣기
				//10개씩 10페이지 임
				PageUtil page = new PageUtil(Integer.parseInt(pageNum),count,40,10,"adminSalesOrders.do");
				List<AdminVO> list = null;
				if(count >0) {
					
				list = daoCount.getMemberOrdersByAdmin(page.getStartRow(), page.getEndRow(),product_num);
				
				}
				request.setAttribute("list", list);
				request.setAttribute("page", page.getPage());
				//페이징 처리 끝
		
		return "/WEB-INF/views/admin/adminSalesOrders.jsp";
	}

}
