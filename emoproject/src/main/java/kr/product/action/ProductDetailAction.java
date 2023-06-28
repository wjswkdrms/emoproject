package kr.product.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.product.vo.ProductManageVO;
import kr.util.StringUtil;

public class ProductDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 상품 번호
		int product_num = Integer.parseInt(request.getParameter("product_num"));

		ProductDAO dao = ProductDAO.getInstance();
		ProductManageVO product = dao.getProduct(product_num);

		/*
		 * //리뷰 페이징 String review_pageNum = request.getParameter("pageNum");
		 * if(review_pageNum == null) review_pageNum="1";
		 */
		int count = dao.getReviewCount(product_num);	//게시물 수
		String title = dao.getReviewProductTitle(product_num);
		// 내용 줄바꿈 처리
		product.getProductdetailVO()
				.setProduct_info(StringUtil.useBrHtml(product.getProductdetailVO().getProduct_info()));

		request.setAttribute("count", count);
		request.setAttribute("product", product);
		
		return "/WEB-INF/views/product/product_detail.jsp";
	}

}
