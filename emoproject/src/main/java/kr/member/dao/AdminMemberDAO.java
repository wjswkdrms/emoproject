package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.order.vo.MemberHomeVO;
import kr.order.vo.OrderDetailVO;
import kr.util.DBUtil;

public class AdminMemberDAO {
	// 싱글턴 패턴
	private static AdminMemberDAO instance = new AdminMemberDAO();

	public static AdminMemberDAO getInstance() {
		return instance;
	}

	private AdminMemberDAO() {
	}

	// 관리자
	// 전체글 개수, 검색글 개수
	public int getMemberCountByAdmin(String keyfield, String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			if (keyword != null && !"".equals(keyword)) {
				if (keyfield.equals("1"))
					sub_sql += "WHERE mem_num LIKE ?";
				else if (keyfield.equals("2"))
					sub_sql += "WHERE mem_id LIKE ?";
			}
			// 삭제한 아이디도 보이게끔 left outer join
			sql = "SELECT COUNT(*) FROM em_member_manage m LEFT OUTER JOIN em_member_detail d USING(mem_num) " + sub_sql;
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터를 바인딩
			if (keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, "%" + keyword + "%");
			}
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			// 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//목록,검색 글 목록
		public List<MemberVO> getListMemberByAdmin(int start,int end,String keyfield,String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			List<MemberVO> list=null;
			String sql = null;
			String sub_sql="";
			int cnt=0;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn=DBUtil.getConnection();
				
				if (keyword != null && !"".equals(keyword)) {
					if (keyfield.equals("1"))
						sub_sql += "HAVING mem_num LIKE ?";
					else if (keyfield.equals("2"))
						sub_sql += "HAVING mem_id LIKE ?";
				}
				
				
				sql="SELECT * FROM (SELECT a.*,rownum rnum FROM(SELECT mem_num, mem_id, NVL(SUM(order_total_price),0) AS mem_using_price , mem_auth "
						+ "FROM em_member_manage LEFT OUTER JOIN (SELECT * FROM em_member_detail LEFT INNER JOIN em_order_manage USING (mem_num)) USING (mem_num) "
						+ "GROUP BY mem_num, mem_id, mem_auth "+sub_sql+" ORDER BY mem_num DESC)a) WHERE rnum>=? AND rnum<=?";
				
				//PreparedStatement 객체 생성
				pstmt=conn.prepareStatement(sql);
				
				//?에 데이터를 바인딩
				if(keyword!=null&&!"".equals(keyword)) {
					pstmt.setString(++cnt, "%"+keyword+"%");//가변적이므로 ++cnt
				}
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				//SQL문 실행
				rs=pstmt.executeQuery();
				list=new ArrayList<MemberVO>();
				while(rs.next()) {
					MemberVO member=new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setId(rs.getString("mem_id"));
					member.setAuth(rs.getInt("mem_auth"));
					member.setOrder_total_price(rs.getInt("mem_using_price"));
					
					list.add(member);
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
	
		//회원상세 정보
		public MemberVO getMemberDetail(int mem_num)
		                      throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM em_member_detail RIGHT OUTER JOIN (SELECT mem_num,mem_auth,mem_id,mem_reg_date, NVL(SUM(order_total_price),0) AS mem_using_price "
						+ "FROM em_member_manage LEFT OUTER JOIN em_order_manage USING (mem_num) "
						+ "GROUP BY mem_num,mem_auth,mem_id,mem_reg_date HAVING mem_num=?) USING (mem_num)";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setInt(1, mem_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					member = new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setId(rs.getString("mem_id"));
					member.setAuth(rs.getInt("mem_auth"));
					member.setGender(rs.getInt("mem_gender"));
					member.setPasswd(rs.getString("mem_passwd"));
					member.setName(rs.getString("mem_name"));
					member.setCell(rs.getString("mem_cell"));
					member.setEmail(rs.getString("mem_email"));
					member.setZipcode(rs.getString("mem_zipcode"));
					member.setAddress1(rs.getString("mem_address1"));
					member.setAddress2(rs.getString("mem_address2"));
					member.setBirth(rs.getString("mem_birth"));
					member.setReg_date(rs.getDate("mem_reg_date"));
					member.setMdate(rs.getDate("mem_mdate"));
					member.setPoint(rs.getInt("mem_point"));
					member.setOrder_total_price(rs.getInt("mem_using_price"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return member;
		}	
		
		// 전체주문 개수, 검색주문 개수
		public int getOrderCountByAdmin(String keyfield, String keyword) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql = "";
			int count = 0;

			try {
				// 커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();

				if (keyword != null && !"".equals(keyword)) {
					if (keyfield.equals("1"))
						sub_sql += "WHERE order_num LIKE ?";
					else if (keyfield.equals("2"))
						sub_sql += "WHERE mem_num LIKE ?";
					else if (keyfield.equals("3"))
						sub_sql += "WHERE mem_id LIKE ?";
				}
				// 삭제한 아이디도 보이게끔 left outer join
				sql = "SELECT COUNT(*) FROM em_member_manage RIGHT OUTER JOIN (SELECT * FROM em_order_manage m LEFT OUTER "
						+ "JOIN em_member_detail d USING(mem_num)) USING (mem_num) " + sub_sql;
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터를 바인딩
				if (keyword != null && !"".equals(keyword)) {
					pstmt.setString(1, "%" + keyword + "%");
				}
				// SQL문 실행
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				// 자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
		
		//주문목록,검색 주문 목록
		public List<MemberVO> getListOrderByAdmin(int start,int end,String keyfield,String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			List<MemberVO> list=null;
			String sql = null;
			String sub_sql="";
			int cnt=0;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn=DBUtil.getConnection();
				
				if (keyword != null && !"".equals(keyword)) {
					if (keyfield.equals("1"))
						sub_sql += "WHERE order_num LIKE ?";
					else if (keyfield.equals("2"))
						sub_sql += "WHERE mem_num LIKE ?";
					else if (keyfield.equals("3"))
						sub_sql += "WHERE mem_id LIKE ?";
				}
				
				
				sql="SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM em_order_manage o LEFT OUTER JOIN "
						+ "em_member_manage m USING (mem_num) "+sub_sql+" ORDER BY order_num DESC)a) WHERE rnum>=? AND rnum<=?";
				
				//PreparedStatement 객체 생성
				pstmt=conn.prepareStatement(sql);
				
				//?에 데이터를 바인딩
				if(keyword!=null&&!"".equals(keyword)) {
					pstmt.setString(++cnt, "%"+keyword+"%");//가변적이므로 ++cnt
				}
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				//SQL문 실행
				rs=pstmt.executeQuery();
				list=new ArrayList<MemberVO>();
				while(rs.next()) {
					MemberVO member=new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setId(rs.getString("mem_id"));
					member.setAuth(rs.getInt("mem_auth"));
					member.setOrder_num(rs.getInt("order_num"));
					member.setOrder_status(rs.getInt("order_status"));
					
					list.add(member);
				}
				
				//conn.commit();
			}catch(Exception e) {
				//conn.rollback();
				throw new Exception(e);
			}finally {
				//자원정리
				//DBUtil.executeClose(rs2, pstmt2, null);
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		
		//주문 상세
		public OrderDetailVO getOrderDetail(int order_num) throws Exception{
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			OrderDetailVO detail=null;
			String sql=null;
			
			try {
				conn=DBUtil.getConnection();
				sql="SELECT * FROM em_member_home d RIGHT OUTER JOIN (SELECT * FROM em_member_manage e INNER JOIN "
						+ "(SELECT * FROM (SELECT order_num, substr(XMLAGG(XMLELEMENT(nm, ', ',ORDER_PRODUCT_NAME)).EXTRACT('//text()').GETSTRINGVAL(),2) AS product_names "
						+ "FROM (SELECT * FROM EM_ORDER_MANAGE RIGHT OUTER JOIN em_order_detail USING (order_num) WHERE order_num=?) "
						+ "GROUP BY ORDER_NUM ORDER BY order_num DESC) a INNER JOIN em_order_manage b USING(order_num)) c USING (mem_num)) e USING (mem_home_num)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, order_num);
				
				rs=pstmt.executeQuery();
				if(rs.next()) {
					detail=new OrderDetailVO();
					detail.setOrder_num(order_num);
					detail.setOrder_product_name(rs.getString("product_names"));
					detail.setMem_id(rs.getString("mem_id"));
					detail.setOrder_product_total(rs.getInt("order_total_price"));
					detail.setOrder_date(rs.getDate("order_date"));
					detail.setOrder_status(rs.getInt("order_status"));
					
					MemberHomeVO home=new MemberHomeVO();
					home.setMem_num(rs.getInt("mem_num"));
					home.setMem_home_num(rs.getInt("mem_home_num"));
					home.setMem_home_name(rs.getString("mem_home_name"));
					home.setMem_home_zipcode(rs.getInt("mem_home_zipcode"));
					home.setMem_home_address1(rs.getString("mem_home_address1"));
					home.setMem_home_address2(rs.getString("mem_home_address2"));
					home.setMem_home_cell(rs.getString("mem_home_cell"));
					detail.setMemberhome(home);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return detail;
		}
		
		//회원 정지
		public void stopMember(int mem_num)
                throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE em_member_manage SET mem_auth=1,mem_stop_date=SYSDATE "
					+ "WHERE mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, mem_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}

		//회원 정지 풀기
		public void normalMember(int mem_num)
                throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE em_member_manage SET mem_auth=2 WHERE mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, mem_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}		
		//회원 탈퇴
		public void expMember(int mem_num)
                throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE em_member_manage SET mem_auth=0,mem_exp_date=SYSDATE "
					+ "WHERE mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, mem_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		//회원 포인트 부여
		public void giveMemberPoint(int mem_point,int mem_num)
                throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE em_member_detail SET mem_point=mem_point+? "
					+ "WHERE mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, mem_point);
				pstmt.setInt(2, mem_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		//배송상태 변경
		public void adminOrderModify(int order_num,int order_status)
                throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				
				if(order_status==1) {
					sql="UPDATE em_order_manage SET order_status=1 WHERE order_num=?";
				}else if(order_status==2) {
					sql="UPDATE em_order_manage SET order_status=2 WHERE order_num=?";
				}
				
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, order_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
}



