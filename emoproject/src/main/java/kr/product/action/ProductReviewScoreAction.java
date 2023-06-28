package kr.product.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.review.vo.ReviewVO;

public class ProductReviewScoreAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		// 전송된 데이터 반환
		int product_num = Integer.parseInt(request.getParameter("product_num"));

		ProductDAO dao = ProductDAO.getInstance();
		int count = dao.getReviewCount(product_num); // 게시물 수

		

		List<ReviewVO> list = null;
		if (count > 0) {
			list = dao.getProductReviewScoreList(product_num);
		} else {
			list = Collections.emptyList();
		}

		Map<String, Object> mapAjax = new HashMap<String, Object>();
		mapAjax.put("count", count);
		mapAjax.put("list", list);

		// JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);
		// JSP 경로 반환
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
