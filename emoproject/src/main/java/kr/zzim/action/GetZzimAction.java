package kr.zzim.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.zzim.dao.ZZimDAO;
import kr.zzim.vo.ZZimVO;

public class GetZzimAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 변환
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		ZZimDAO dao = ZZimDAO.getInstance();
		
		if(user_num == null) {	//로그인X
			mapAjax.put("status", "noZzim");
			mapAjax.put("zzim-count", dao.selectZzimCount(product_num));
		}else {	//로그인O
			ZZimVO zzim = dao.selectZzim(new ZZimVO());
			if(zzim != null) {
				//로그인한 회원이 해당 물품에 찜O
				mapAjax.put("status", "yesZzim");
				mapAjax.put("zzim-count", dao.selectZzimCount(product_num));
			}else {
				//로그인한 회원이 해당 물품에 찜X
				mapAjax.put("status", "noZzim");
				mapAjax.put("zzim-count", dao.selectZzimCount(product_num));
			}
		}
		// JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);

		// JSP 경로 반환
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
	

}
