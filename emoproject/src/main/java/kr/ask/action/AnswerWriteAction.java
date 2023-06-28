package kr.ask.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.oreilly.servlet.MultipartRequest;

import kr.ask.dao.AnswerDAO;
import kr.ask.dao.askDAO;
import kr.ask.vo.AnswerVO;
import kr.ask.vo.AskVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class AnswerWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		if(user_auth<9) {
			return "/WEB-INF/views/commone/notice.jsp";
		}
		//관리자일 경우
		MultipartRequest multi=FileUtil.createFile(request);
		
		int ask_num=Integer.parseInt(multi.getParameter("ask_num"));
		askDAO askdao=askDAO.getInstance();
		AskVO ask=askdao.getAsk(ask_num);
		AnswerDAO dao=AnswerDAO.getInstance();
		AnswerVO answer=new AnswerVO();
		answer.setAnswer_content(multi.getParameter("answer_content"));
		answer.setAnswer_photo(multi.getFilesystemName("answer_photo"));
		answer.setMem_num(user_num);
		answer.setAsk_num(ask_num);
		answer.setAsk_vo(ask);
		
		
		dao.insertAnswer(answer);		
		request.setAttribute("answer", answer);
		return "/WEB-INF/views/ask/answerWrite.jsp";
		
	}

}
