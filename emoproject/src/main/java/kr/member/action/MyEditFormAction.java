package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MyEditFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부 체크
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("user_num");
		if(mem_num==null) {
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.myEditGet(mem_num);
		
		//기존 개인정보 출력
		request.setAttribute("mem_zipcode",member.getZipcode());
		request.setAttribute("mem_address1",member.getAddress1());
		request.setAttribute("mem_address2",member.getAddress2());
		request.setAttribute("mem_birth",member.getBirth());
		request.setAttribute("mem_gender",member.getGender());
		request.setAttribute("mem_point",member.getPoint_str());
		request.setAttribute("mem_id",member.getId());
		request.setAttribute("mem_auth",member.getAuth());
		request.setAttribute("mem_reg_date",member.getReg_date_2());
		request.setAttribute("mem_passwd",member.getPasswd());
		request.setAttribute("mem_email",member.getEmail());
		request.setAttribute("mem_name",member.getName());
		request.setAttribute("mem_cell",member.getCell());
		//JSP 경로 반환
		return "/WEB-INF/views/member/myEditForm.jsp";
	}

}
