package kr.announce.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.announce.dao.AnnounceDAO;
import kr.announce.vo.AnnounceVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class AnnounceDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute(
						             "user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		int ann_num=Integer.parseInt(request.getParameter("ann_num"));
		AnnounceDAO dao=AnnounceDAO.getInstance();
		AnnounceVO db_announce=dao.getAnnounce(ann_num);
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		
		if(user_auth<9) {
			return "/WEB-INF/views/common/notice.jsp";			
		}
		//글 삭제
		dao.deleteAnnounce(ann_num);
		
		//파일 삭제
		FileUtil.removeFile(request, db_announce.getAnn_photo1());
		return "redirect:/announce/announce.do";
	}

}
