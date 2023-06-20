package kr.announce.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.announce.dao.AnnounceDAO;
import kr.announce.vo.AnnounceVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class AnnounceUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Integer user_num=(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi=FileUtil.createFile(request);
		
		int ann_num=Integer.parseInt(request.getParameter("ann_num"));
		String ann_photo1=multi.getFilesystemName("ann_photo1");
		
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		if(user_auth<9) {
			FileUtil.removeFile(request, ann_photo1);
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		AnnounceDAO dao=AnnounceDAO.getInstance();
		AnnounceVO announce=new AnnounceVO();
		AnnounceVO db_announce=dao.getAnnounce(ann_num);
		announce.setAnn_num(ann_num);
		announce.setAnn_title(multi.getParameter("ann_title"));
		announce.setAnn_content(multi.getParameter("content"));
		announce.setAnn_photo1(ann_photo1);
		
		dao.updateAnnounce(announce);
		if(ann_photo1!=null) {
			FileUtil.removeFile(request, db_announce.getAnn_photo1());
		}
		return "redirect:/announce/announceDetail.do?ann_num="+ann_num;
	}

}
