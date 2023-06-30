package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.order.vo.MemberHomeVO;

public class RegisterUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");

		// 자바빈을 생성하고 전송된 데이터를 자바빈에
		// 담음
		if(request.getParameter("id")==null) {
			return "/WEB-INF/views/member/registerUserForm.jsp";
		}else if(request.getParameter("name")==null) {
			return "/WEB-INF/views/member/registerUserForm.jsp";
		}else if(request.getParameter("passwd")==null) {
			return "/WEB-INF/views/member/registerUserForm.jsp";
		}else if(request.getParameter("cell")==null) {
			return "/WEB-INF/views/member/registerUserForm.jsp";
		}else if(request.getParameter("email")==null) {
			return "/WEB-INF/views/member/registerUserForm.jsp";
		}else if(request.getParameter("zipcode")==null) {
			return "/WEB-INF/views/member/registerUserForm.jsp";
		}else if(request.getParameter("address1")==null) {
			return "/WEB-INF/views/member/registerUserForm.jsp";
		}else if(request.getParameter("address2")==null) {
			return "/WEB-INF/views/member/registerUserForm.jsp";
		}else if(request.getParameter("birth")==null) {
			return "/WEB-INF/views/member/registerUserForm.jsp";
		}else if(request.getParameter("gender")==null) {
			return "/WEB-INF/views/member/registerUserForm.jsp";
		}
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