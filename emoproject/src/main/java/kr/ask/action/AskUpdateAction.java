package kr.ask.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.ask.dao.askDAO;
import kr.ask.vo.AskVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class AskUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi=FileUtil.createFile(request);
		
		int ask_num=Integer.parseInt(multi.getParameter("ask_num"));
		String ask_photo1=multi.getFilesystemName("ask_photo1");
		
		askDAO dao=askDAO.getInstance();
		AskVO db_ask=dao.getAsk(ask_num);
		if(user_num!=db_ask.getMem_num()) {
			FileUtil.removeFile(request, ask_photo1);
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		AskVO ask=new AskVO();
		ask.setAsk_num(ask_num);
		ask.setAsk_title(multi.getParameter("ask_title"));
		ask.setAsk_content(multi.getParameter("ask_content"));
		ask.setAsk_photo1(ask_photo1);
		
		dao.updateAsk(ask);
		
		if(ask_photo1!=null) {
			FileUtil.removeFile(request, db_ask.getAsk_photo1());
		}
		return "redirect:/ask/askDetail.do?ask_num="+ask_num;
	}

}
