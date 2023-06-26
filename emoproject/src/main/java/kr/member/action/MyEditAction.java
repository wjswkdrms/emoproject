package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MyEditAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("user_num");
		if(mem_num==null) {
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		MemberDAO dao = MemberDAO.getInstance();
		
		List<MemberVO> list = null;

		request.setAttribute("list", list);	
		//JSP 경로 반환
		return "/WEB-INF/views/member/myEdit.jsp";
	}
	
}
