package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.product.vo.ProductManageVO;
import kr.util.StringUtil;

public class ProductDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//상품 번호
		int item_num = Integer.parseInt(request.getParameter("product_num"));
		
		ProductDAO dao = ProductDAO.getInstance();
		ProductManageVO product = dao.getProduct(item_num);
		
		//내용 줄바꿈 처리
		product.getProductdetailVO().setProduct_info(StringUtil.useBrHtml(product.getProductdetailVO().getProduct_info()));
		
		request.setAttribute("product", product);
		return "/WEB-INF/views/product/product_detail.jsp";
	}

}
