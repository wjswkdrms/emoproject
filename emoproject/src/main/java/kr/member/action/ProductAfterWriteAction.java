package kr.member.action;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.review.vo.ReviewVO;
import kr.util.FileUtil;

public class ProductAfterWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");	
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		String product_num = request.getParameter("product_num"); //쿼리스트링으로 입력
		String order_num = request.getParameter("order_num"); //쿼리스트링으로 입력
		
		MultipartRequest multi=FileUtil.createFile(request);

		ReviewVO review = new ReviewVO();
		review.setReview_title(multi.getParameter("review_title"));
		review.setReview_content(multi.getParameter("review_content"));
		review.setReview_photo1(multi.getFilesystemName("review_photo1"));
		review.setReview_score(Integer.parseInt(multi.getParameter("review_score")));
		review.setProduct_num(Integer.parseInt(product_num));
		review.setOrder_num(Integer.parseInt(order_num));
		review.setMem_num(user_num);
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.getProductAfterWrite(review);
		//JSP 경로 반환
		return "/WEB-INF/views/member/productAfterWrite.jsp";
	}	
}
	
