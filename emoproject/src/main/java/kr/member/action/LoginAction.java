package kr.member.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.search.dao.SearchDAO;
import kr.search.vo.SearchVO;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String passwd = 
				request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		//cart count 추가를 위한 영역
		SearchDAO searchDao = SearchDAO.getInstance();
		SearchVO searchVo = new SearchVO();
		int cartCount = 0;
		//by 박정호...
		
		if(member!=null) {
			//비밀번호 일치 여부 체크
			check = member.isCheckedPassword(passwd);
			//로그인 실패시 auth 체크용
			request.setAttribute("auth", 
					               member.getAuth());			
		}
		if(check) {//인증 성공
			//로그인 처리
			session.setAttribute("user_num", 
					           member.getMem_num());
			session.setAttribute("user_id", 
					                member.getId());
			session.setAttribute("user_auth", 
					              member.getAuth());
			//cartCount 영역...
			
			cartCount = searchDao.getCartCount(member.getMem_num());
			session.setAttribute("cartCount", cartCount);
			//carCount 끝
			//인증 성공시 호출
			return "redirect:/main/main.do";
		}
		//cartCount
		session.setAttribute("cartCount", cartCount);
		//cartCount 끝
		//인증 실패시 호출
		return "/WEB-INF/views/member/login.jsp";
	}

}
