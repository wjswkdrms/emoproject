package kr.announce.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.announce.dao.AnnounceDAO;
import kr.announce.vo.AnnounceVO;
import kr.controller.Action;

public class AnnounceUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		if(user_auth<9) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		int ann_num=Integer.parseInt(request.getParameter("ann_num"));
		
		AnnounceDAO dao=AnnounceDAO.getInstance();
		AnnounceVO announce=dao.getAnnounce(ann_num);
		if(user_num!=announce.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		request.setAttribute("announce", announce);
		
		return "/WEB-INF/views/announce/announceUpdateForm.jsp";
	}

}
