package kr.ask.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.announce.dao.AnnounceDAO;
import kr.announce.vo.AnnounceVO;
import kr.ask.dao.AnswerDAO;
import kr.ask.dao.askDAO;
import kr.ask.vo.AnswerVO;
import kr.ask.vo.AskVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class AskDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax =
				new HashMap<String,String>();		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute(
						             "user_num");

		int ask_num=Integer.parseInt(request.getParameter("ask_num"));
		askDAO dao=askDAO.getInstance();
		AnswerDAO dao2=AnswerDAO.getInstance();
		AskVO db_ask=dao.getAsk(ask_num);
		AnswerVO db_answer=dao2.getAnswer(ask_num);
		
		
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			
			if(user_num !=  db_ask.getMem_num()) {
				mapAjax.put("result", "wrongAccess");
			}else {
				//글 삭제
				dao.deleteAsk(ask_num);
				if(db_answer!=null&&db_answer.getAnswer_photo()!=null) {
					FileUtil.removeFile(request, db_answer.getAnswer_photo());
				}
				FileUtil.removeFile(request, db_ask.getAsk_photo1());
				mapAjax.put("result", "success");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";				
	}

}
