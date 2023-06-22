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

public class WriteZzimAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {	//로그인 X
			mapAjax.put("result", "logout");
		}else {	//로그인 O
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");
			//전송된 데이터 반환
			int product_num = Integer.parseInt(request.getParameter("product_num"));
			ZZimDAO dao = ZZimDAO.getInstance();
			
			ZZimVO zzimVO = new ZZimVO();
			zzimVO.setProduct_num(product_num);
			zzimVO.setMem_num(user_num);
			
			//찜 여부 체크
			ZZimVO db_zzim = dao.selectZzim(zzimVO);
			
			if(db_zzim != null) {	//찜 등록 O
				//찜 삭제처리
				dao.deleteZzim(db_zzim.getZzim_num());
				mapAjax.put("result", "success");
				mapAjax.put("status", "noZzim");
				mapAjax.put("zzim-count", dao.selectZzimCount(product_num));
			}else {	//찜 등록 X
				//찜 등록처리
				dao.insertzzim(zzimVO);
				mapAjax.put("result", "success");
				mapAjax.put("status", "yesZzim");
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
