package kr.ask.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.ask.dao.askDAO;
import kr.ask.vo.AskVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class AskWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi=FileUtil.createFile(request);
		
		AskVO ask=new AskVO();
		ask.setAsk_title(multi.getParameter("ask_title"));
		ask.setAsk_content(multi.getParameter("ask_content"));
		ask.setAsk_photo1(multi.getFilesystemName("ask_photo1"));
		ask.setMem_num(user_num);
		
		askDAO dao=askDAO.getInstance();
		dao.insertAsk(ask);
		
		return "/WEB-INF/views/ask/askWrite.jsp";
	}

}
