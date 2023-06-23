package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
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
				else if (keyfield.equals("3"))
					sub_sql += "WHERE mem_name LIKE ?";
				else if (keyfield.equals("4"))
					sub_sql += "WHERE mem_email LIKE ?";
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
		public List<MemberVO> getListMemberByAdmin(int start,int end,String keyfield,String keyword,int total) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs=null;
			ResultSet rs2=null;
			List<MemberVO> list=null;
			String sql = null;
			String sub_sql="";
			int cnt=0;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn=DBUtil.getConnection();
				
				conn.setAutoCommit(false);
				if (keyword != null && !"".equals(keyword)) {
					if (keyfield.equals("1"))
						sub_sql += "WHERE mem_num LIKE ?";
					else if (keyfield.equals("2"))
						sub_sql += "WHERE mem_id LIKE ?";
					else if (keyfield.equals("3"))
						sub_sql += "WHERE mem_name LIKE ?";
					else if (keyfield.equals("4"))
						sub_sql += "WHERE mem_email LIKE ?";
				}
				
				sql="SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM em_member_manage m LEFT OUTER JOIN "
						+ "em_member_detail d USING (mem_num) "+sub_sql+" ORDER BY mem_num DESC)a) WHERE rnum>=? AND rnum<=?";
				
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
				}
				
				sql="SELECT SUM(order_total_price) AS order_total_price, mem_num, mem_name FROM (SELECT * FROM EM_ORDER_MANAGE o RIGHT OUTER JOIN EM_MEMBER_DETAIL d USING(mem_num)) GROUP BY mem_num" ;
				pstmt2=conn.prepareStatement(sql);
				rs2=pstmt2.executeQuery();
				AdminMemberDAO dao=AdminMemberDAO.getInstance();
				while(rs2.next()) {
					MemberVO member2=dao.getMember(rs.getInt("mem_num"));
					
				}
				conn.commit();
			}catch(Exception e) {
				conn.rollback();
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs2, pstmt2, null);
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
	
		//누적금액
		public int getTotalPrice() throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			String sql = null;
			int total=0;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn=DBUtil.getConnection();

				sql="SELECT SUM(order_total_price) AS order_total_price, mem_num, mem_name FROM "
						+ "(SELECT * FROM EM_ORDER_MANAGE o RIGHT OUTER JOIN EM_MEMBER_DETAIL d USING(mem_num)) GROUP BY mem_num";
				
				pstmt=conn.prepareStatement(sql);
				//SQL문 실행
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					total=rs.getInt(1);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return total;
		}
	
		//회원상세 정보
		public MemberVO getMember(int mem_num)
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
				sql = "SELECT * FROM em_member_manage m JOIN "
					+ "em_member_detail d ON "
					+ "m.mem_num=d.mem_num WHERE m.mem_num=?";
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
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return member;
		}		
}
