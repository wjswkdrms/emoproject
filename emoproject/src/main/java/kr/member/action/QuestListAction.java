package kr.member.action;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ask.vo.AskVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.util.PageUtil;

public class QuestListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		//로그인 여부 체크
		Integer user_num = (Integer)session.getAttribute("user_num");
		//로그인 안되어있을 시 로그인 화면으로 이동
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		
		MemberDAO dao = MemberDAO.getInstance();
		int count = dao.getQuestListCount(user_num);
		//keyfield,keyword,currentPage,count,
		//rowCount,pageCount,요청URL
		PageUtil page = new PageUtil(Integer.parseInt(pageNum),count,10,10,"questList.do");
		
		List<AskVO> list = null;
		if(count > 0) {
			//list로 dao에서 뽑은 데이터 저장
			list = dao.getQuestList(page.getStartRow(),page.getEndRow(),user_num);
		}
		
		System.out.println(list);
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());		
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/questList.jsp";
	}
}
