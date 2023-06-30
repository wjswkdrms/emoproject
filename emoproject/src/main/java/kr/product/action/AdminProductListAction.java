package kr.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.product.vo.ProductManageVO;
import kr.util.PageUtil;

public class AdminProductListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		//회원번호 check - 로그인 여부
		if(user_num == null) {//로그인 X
			return "redirect:/member/loginForm.do";
		}
		
		//회원등급 check - 관리자 계정 여부
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth < 9) {	//관리자 계정이 아니다
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum="1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		ProductDAO dao = ProductDAO.getInstance();
		
		//status가 0이면 미표시(1), 표시(2) 모두 개수 확인
		int count = dao.getProductCount(keyfield, keyword, 0);
		
		//페이지 처리
		PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 2, 10, "productlist.do");
		//목록 데이터 호출
		List<ProductManageVO> list = null;
		if(count > 0) {
			list = dao.getListProduct(page.getStartRow(), page.getEndRow(), keyfield, keyword, 0);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/product/admin_productlist.jsp";
	}
}