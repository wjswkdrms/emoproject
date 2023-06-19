package kr.announce.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.announce.dao.AnnounceDAO;
import kr.announce.vo.AnnounceVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class AnnounceWriteAction implements Action{

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
		
		MultipartRequest multi=FileUtil.createFile(request);
		
		AnnounceVO announce=new AnnounceVO();
		announce.setAnn_title(multi.getParameter("ann_title"));
		announce.setAnn_content(multi.getParameter("ann_content"));
		announce.setAnn_photo1(multi.getFilesystemName("ann_photo1"));
		announce.setMem_num(user_num);
		
		AnnounceDAO dao=AnnounceDAO.getInstance();
		dao.insertAnnounce(announce);
		
		//JSP 경로 반환
		return "/WEB-INF/views/announce/announceWrite.jsp";
	}
	
}
