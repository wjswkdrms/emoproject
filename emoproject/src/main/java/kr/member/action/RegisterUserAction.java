package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class RegisterUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		// 전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");

		// 자바빈을 생성하고 전송된 데이터를 자바빈에
		// 담음
		MemberVO member = new MemberVO();
		member.setId(request.getParameter("id"));
		member.setName(request.getParameter("name"));
		member.setPasswd(request.getParameter("passwd"));
		member.setCell(request.getParameter("cell"));
		member.setEmail(request.getParameter("email"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddress1(request.getParameter("address1"));
		member.setAddress2(request.getParameter("address2"));
		member.setBirth(request.getParameter("birth"));
		member.setGender(Integer.parseInt(request.getParameter("gender")));
		// MemberDAO 호출
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(member);
		// JSP 경로 반환
		return "/WEB-INF/views/member/registerUser.jsp";
	}

}
