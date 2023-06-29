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
		request.setCharacterEncoding("utf-8");
		//로그인 된 경우
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberVO member2 = dao.myEditGet(mem_num);
		MemberVO member = new MemberVO();
		//비밀번호, 전화번호, 이메일은 *처리 하였으므로
		//출력과 입력이 같으면 수정이 되지 않은 것. 
		//같지 않을 때 수정하여 *로 UPDATE 방지
		if(!(member2.getPasswd()).equals(request.getParameter("passwd"))) {
			member.setPasswd(request.getParameter("passwd"));
			//*이 포함되어도 수정된 정보면 if문을 통과하기때문에 if문을 추가해 오류처리.
			//조건에 맞으면 error가 1이 되어 오류 페이지로 간다.
			if((request.getParameter("passwd")).indexOf("*")!=-1) {
				request.setAttribute("error",1);
				return "/WEB-INF/views/member/myEdit.jsp";
			}
		}
		if(!(member2.getCell()).equals(request.getParameter("cell"))) {
			member.setCell(request.getParameter("cell"));
			if((request.getParameter("cell")).indexOf("*")!=-1) {
				request.setAttribute("error",1);
				return "/WEB-INF/views/member/myEdit.jsp";
			}
		}
		if(!(member2.getEmail()).equals(request.getParameter("email"))) {
			if((request.getParameter("email")).indexOf("*")!=-1) {
				request.setAttribute("error",1);
				return "/WEB-INF/views/member/myEdit.jsp";
			}
			member.setEmail(request.getParameter("email"));
		}
		//전송된 데이터 dao로 이동
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddress1(request.getParameter("address1"));
		member.setAddress2(request.getParameter("address2"));
		
		dao.myEditSet(member,mem_num);
		//JSP 경로 반환
		return "/WEB-INF/views/member/myEdit.jsp";
	}
	
}
