package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MemberEditAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		//�쟾�넚�맂 �뜲�씠�꽣 �씤肄붾뵫 泥섎━
		request.setCharacterEncoding("utf-8");
		//�쟾�넚�맂 �뜲�씠�꽣 諛섑솚
		String id = request.getParameter("id");
		String passwd = 
				request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		if(member!=null) {
			//鍮꾨�踰덊샇 �씪移� �뿬遺� 泥댄겕
			check = member.isCheckedPassword(passwd);
			//濡쒓렇�씤 �떎�뙣�떆 auth 泥댄겕�슜
			request.setAttribute("auth", 
					               member.getAuth());			
		}
		if(check) {//�씤利� �꽦怨�
			session.setAttribute("user_num", 
					           member.getMem_num());
			session.setAttribute("user_id", 
					                member.getId());
			session.setAttribute("user_auth", 
					              member.getAuth());
			
			//�씤利� �꽦怨듭떆 �샇異�
			return "redirect:/main/main.do";
		}
		//�씤利� �떎�뙣�떆 �샇異�
		return "/WEB-INF/views/member/login.jsp";
	}

}
