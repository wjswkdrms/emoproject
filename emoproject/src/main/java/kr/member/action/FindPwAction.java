package kr.member.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class FindPwAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = 
				request.getParameter("email");
		
		MemberDAO dao = MemberDAO.getInstance();
		//입력한 아이디,이름,이메일을 통해서 비밀번호를 반환받음
		MemberVO member = dao.checkpw(id,name,email);
		boolean check = false;
		
		//입력한 데이터랑 일치하는 아이디가 있으면 아이디 반환
		if(member!=null) {
			check = true;
			request.setAttribute("mem_auth", 
		               1);		
			request.setAttribute("mem_passwd", 
		               member.getPasswd());		
		}
		if(check) {//�씤利� �꽦怨�
			//濡쒓렇�씤 泥섎━
			session.setAttribute("mem_num", 
					           member.getMem_num());
			
			//�씤利� �꽦怨듭떆 �샇異�
			return "/WEB-INF/views/member/findPw.jsp";
		}
		//�씤利� �떎�뙣�떆 �샇異�
		return "/WEB-INF/views/member/findPw.jsp";
	}

}
