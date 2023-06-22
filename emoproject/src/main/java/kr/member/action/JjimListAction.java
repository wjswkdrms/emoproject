package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.PageUtil;
import kr.zzim.vo.ZZimVO;

public class JjimListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer mem_num = 
			    (Integer)session.getAttribute(
			                         "user_num");
		String pageNum = 
				request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		MemberDAO dao = MemberDAO.getInstance();
		int count = dao.getListBoardCount(mem_num);
		//keyfield,keyword,currentPage,count,
		//rowCount,pageCount,요청URL
		PageUtil page = new PageUtil(Integer.parseInt(pageNum),count,10,10,"${pageContext.request.contextPath}/member/jjimList.do");
		
		List<ZZimVO> list = null;
		
		
		
		if(count > 0) {
			list = dao.getListBoard(page.getStartRow(),page.getEndRow(),mem_num);
		}
		
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());		
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/jjimList.jsp";
	}

}
