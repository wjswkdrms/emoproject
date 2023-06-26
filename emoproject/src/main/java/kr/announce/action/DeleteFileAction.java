package kr.announce.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.announce.dao.AnnounceDAO;
import kr.announce.vo.AnnounceVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class DeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax =
				new HashMap<String,String>();
		
		HttpSession session = 
				       request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute(
						            "user_num");
		Integer user_auth=(Integer)session.getAttribute("user_auth");
		
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			int ann_num = 
					Integer.parseInt(
						request.getParameter(
								 "ann_num"));
			AnnounceDAO dao = AnnounceDAO.getInstance();
			AnnounceVO db_ann=dao.getAnnounce(ann_num);
			if(user_auth<9) {
				//관리자 아닐경우
				mapAjax.put("result", "wrongAccess");
			}else {
				//파일 정보 삭제
				dao.deleteFile(ann_num);
				//*** 파일 삭제
				FileUtil.removeFile(request, 
						    db_ann.getAnn_photo1());
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
