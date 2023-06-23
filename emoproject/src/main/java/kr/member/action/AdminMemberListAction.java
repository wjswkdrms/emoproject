package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.AdminMemberDAO;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.PageUtil;

public class AdminMemberListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		
		if(user_auth<9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//관리자로 로그인한 경우
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		
		String keyfield=request.getParameter("keyfield");
		String keyword=request.getParameter("keyword");
		
		AdminMemberDAO dao=AdminMemberDAO.getInstance();
		int count = dao.getMemberCountByAdmin(keyfield, keyword);
		int total = dao.getTotalPrice();
		
		//페이지 처리
		PageUtil page=new PageUtil(keyfield, keyword, Integer.parseInt(pageNum),count,20,10,"adminMemberList.do");
		List<MemberVO> list=null;
		if(count>0) {
			list=dao.getListMemberByAdmin(page.getStartRow(), page.getEndRow(), keyfield, keyword, total);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("member", list);
		request.setAttribute("page", page.getPage());
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/adminMemberList.jsp";
	}

}
