package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MemberOutAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer mem_num = 
				    (Integer)session.getAttribute(
				                         "mem_num");
		if(mem_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String passwd = 
				request.getParameter("passwd");
				
		//로그인한 아이디
		String login_id = 
				(String)session.getAttribute(
						             "id");
		String login_passwd = 
				(String)session.getAttribute(
						             "passwd");
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO db_member = dao.checkMember(id);
		boolean check = false;
		//사용자가 입력한 아이디가 존재하고 로그인한 아이디와
		//입력한 아이디, 로그인한 비밀번호와 입력한 비밀번호가
		//일치하는지 여부 체크
		if(db_member!=null && id.equals(login_id)
				&& passwd.equals(login_passwd)) {
			//비밀번호 일치 여부 체크
			check = 
				db_member.isCheckedPassword(passwd);
		}
		
		if(check) {//인증 성공
			//회원탈퇴 
			dao.MemberOut(mem_num);
			//로그아웃
			session.invalidate();
		}
		
		request.setAttribute("check", check);
		//JSP 경로 반환
		return "/WEB-INF/views/member/memberOut.jsp";
	}

}
