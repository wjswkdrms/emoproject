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

public class AnswerUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi=FileUtil.createFile(request);
		
		int ask_num=Integer.parseInt(multi.getParameter("ask_num"));
		String answer_photo=multi.getFilesystemName("answer_photo");
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		
		if(user_auth<9) {
			FileUtil.removeFile(request, answer_photo);
			return "/WEB-INF/views/common/notice.jsp";			
		}
		
		AnswerDAO dao=AnswerDAO.getInstance();
		AnswerVO answer=new AnswerVO();
		AnswerVO db_answer=dao.getAnswer(ask_num);
		
		answer.setAnswer_num(db_answer.getAnswer_num());
		answer.setAnswer_content(multi.getParameter("answer_content"));
		answer.setAnswer_photo(answer_photo);
		dao.updateAnswer(answer);
		
		if(answer_photo!=null) {
			FileUtil.removeFile(request, db_answer.getAnswer_photo());
		}
		return "redirect:/ask/askDetail.do?ask_num="+ask_num;
	}

}
