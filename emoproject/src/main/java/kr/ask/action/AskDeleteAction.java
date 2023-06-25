package kr.ask.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ask.dao.AnswerDAO;
import kr.ask.dao.askDAO;
import kr.ask.vo.AnswerVO;
import kr.ask.vo.AskVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class AskDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute(
						             "user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		int ask_num=Integer.parseInt(request.getParameter("ask_num"));
		askDAO dao=askDAO.getInstance();
		AnswerDAO dao2=AnswerDAO.getInstance();
		AskVO db_ask=dao.getAsk(ask_num);
		AnswerVO db_answer=dao2.getAnswer(ask_num);
		if(user_num !=  db_ask.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//글 삭제
		dao.deleteAsk(ask_num);
		
		//파일 삭제
		if(db_answer!=null&&db_answer.getAnswer_photo()!=null) {
			FileUtil.removeFile(request, db_answer.getAnswer_photo());
		}
		FileUtil.removeFile(request, db_ask.getAsk_photo1());
		return "/WEB-INF/views/ask/askDelete.jsp";
	}

}
