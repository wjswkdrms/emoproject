package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class CheckDuplicatedEmailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�쟾�넚�맂 �뜲�씠�꽣 �씤肄붾뵫 泥섎━
		request.setCharacterEncoding("utf-8");
		//�쟾�넚�맂 �뜲�씠�꽣 諛섑솚
		String email = request.getParameter("email");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMemberEmail(email);
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		if(member==null) {//�븘�씠�뵒 誘몄쨷蹂�
			mapAjax.put("result", "emailNotFound");
		}else {//�븘�씠�뵒 以묐났
			mapAjax.put("result", "emailDuplicated");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		//key�� value�쓽 �뙇�쑝濡� �릺�뼱 �엳�뒗 Map �뜲�씠�꽣瑜�
		//JSON�삎�떇�쓽 臾몄옄�뿴 �뜲�씠�꽣濡� 蹂��솚 �썑 諛섑솚
		String ajaxData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);		
		//JSP 寃쎈줈 諛섑솚
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
