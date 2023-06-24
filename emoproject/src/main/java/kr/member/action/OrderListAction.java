package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.util.PageUtil;
import kr.zzim.vo.ZZimVO;

public class OrderListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		//로그인 여부 체크
		Integer user_num = (Integer)session.getAttribute("user_num");
		//로그인 안되어있을 시 로그인 화면으로 이동
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		MemberDAO dao = MemberDAO.getInstance();
		int count = dao.getOrderListBoardAllCount(user_num);
		//keyfield,keyword,currentPage,count,
		//rowCount,pageCount,요청URL
		PageUtil page = new PageUtil(Integer.parseInt(pageNum),count,10,10,"orderList.do");
		
		List<ZZimVO> list = null;
		if(count > 0) {
			//list로 dao에서 뽑은 데이터 저장
			//${zzim.변수명}으로 jsp에서 출력해야 됨
			list = dao.getOrderListBoardAll(page.getStartRow(),page.getEndRow(),user_num);
		}
		
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());		
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/orderList.jsp";
	}

}
