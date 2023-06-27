package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.product.vo.ProductDetailVO;
import kr.product.vo.ProductManageVO;
import kr.util.FileUtil;

public class ProductModifyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");

		// 회원번호 check - 로그인 여부
		if (user_num == null) {// 로그인 X
			return "redirect:/member/loginForm.do";
		}
		// 회원등급 check - 관리자 계정 여부
		Integer user_auth = (Integer) session.getAttribute("user_auth");
		if (user_auth < 9) { // 관리자 계정이 아니다
			return "/WEB-INF/views/common/notice.jsp";
		}

		// 관리자로 로그인한 경우
		
		
		MultipartRequest multi = FileUtil.createFile(request);

		int product_num = Integer.parseInt(multi.getParameter("product_num"));
		String photo1 = multi.getFilesystemName("photo1");
		String photo2 = multi.getFilesystemName("photo2");

		ProductDAO dao = ProductDAO.getInstance();
		
		 //수정전 데이터 반환 
		ProductManageVO db_product = dao.getProduct(product_num); 
		/*
		ProductDetailVO db_product_detail = dao.getProduct(product_num); 
		*/
		//상품번호반환(?)
		 

		ProductManageVO product = new ProductManageVO();
		ProductDetailVO product_detail = new ProductDetailVO();

		product.setProduct_num(product_num);
		product.setProduct_category(Integer.parseInt(multi.getParameter("category")));
		product.setProduct_status(Integer.parseInt(multi.getParameter("status")));

		product_detail.setProduct_name(multi.getParameter("name"));
		product_detail.setProduct_title(multi.getParameter("title"));
		product_detail.setProduct_info(multi.getParameter("info"));
		product_detail.setProduct_photo1(photo1);
		product_detail.setProduct_photo2(photo2);
		product_detail.setProduct_origin(multi.getParameter("origin"));
		product_detail.setProduct_real_price(Integer.parseInt(multi.getParameter("real_price")));
		product_detail.setProduct_price(Integer.parseInt(multi.getParameter("price")));
		product_detail.setProduct_stock(Integer.parseInt(multi.getParameter("stock")));
		product_detail.setProduct_discount(Integer.parseInt(multi.getParameter("discount")));

		dao.updateProduct(product, product_detail);

		// 새 파일로 교체할 때 원래 파일 제거
		if(photo1 != null) {
			FileUtil.removeFile(request, db_product.getProductdetailVO().getProduct_photo1());
		}
		if(photo2 != null) {
			FileUtil.removeFile(request, db_product.getProductdetailVO().getProduct_photo2());
		}
		
		request.setAttribute("notice_msg", "정상적으로 수정되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/product/modifyForm.do?product_num="+ product.getProduct_num());

		return "/WEB-INF/views/common/alert_singleView.jsp";
	}

}
