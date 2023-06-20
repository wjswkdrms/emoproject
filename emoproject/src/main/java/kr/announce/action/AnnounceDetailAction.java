package kr.announce.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.announce.dao.AnnounceDAO;
import kr.announce.vo.AnnounceVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class AnnounceDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int ann_num=Integer.parseInt(request.getParameter("ann_num"));
		
		AnnounceDAO dao=AnnounceDAO.getInstance();
		AnnounceVO announce=dao.getAnnounce(ann_num);
		
		announce.setAnn_title(StringUtil.useNoHtml(announce.getAnn_title()));
		announce.setAnn_content(StringUtil.useBrNoHtml(announce.getAnn_content()));
		
		request.setAttribute("announce", announce);
		
		return "/WEB-INF/views/announce/announceDetail.jsp";
	}

}
