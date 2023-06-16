package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	//싱글턴 패턴
		private static MemberDAO instance = 
				            new MemberDAO();
		public static MemberDAO getInstance() {
			return instance;
		}
		private MemberDAO() {}
		
		//회원가입
		public void insertMember(MemberVO member)
		                         throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			ResultSet rs = null;
			String sql = null;
			int num = 0; //시퀀스 번호 저장
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//오토 커밋 해제
				conn.setAutoCommit(false);
				
				//회원번호(mem_num) 구하기
				sql = "SELECT em_member_seq.nextval FROM dual";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					num = rs.getInt(1);
				}
				
				//zmember 테이블에 데이터 저장
				sql = "INSERT INTO em_member_manage (mem_num,mem_id,mem_auth,mem_reg_date)"
					+ "VALUES (?,?,2,SYSDATE)";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, num);//시퀀스
				pstmt2.setString(2, member.getId());//id
				pstmt2.executeUpdate();
				
				//zmember_detail 테이블에 데이터 저장
				sql = "INSERT INTO em_member_detail (mem_num,"
					+ "mem_name,mem_passwd,mem_cell,mem_email,mem_zipcode,"
					+ "mem_address1,mem_address2,mem_birth,mem_gender,mem_point) VALUES (?,?,?,?,?,?,?,?,?,?,990000)";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1, num);
				pstmt3.setString(2, member.getName());
				pstmt3.setString(3, member.getPasswd());
				pstmt3.setString(4, member.getCell());
				pstmt3.setString(5, member.getEmail());
				pstmt3.setString(6, member.getZipcode());
				pstmt3.setString(7, member.getAddress1());
				pstmt3.setString(8, member.getAddress2());
				pstmt3.setString(9, member.getBirth());
				pstmt3.setInt(10, member.getGender());
				pstmt3.executeUpdate();
				
				//SQL문을 실행해서 모두 성공하면 commit
				conn.commit();
			}catch(Exception e) {
				//SQL문이 하나라도 실패하면 rollback
				conn.rollback();
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt3, null);
				DBUtil.executeClose(null, pstmt2, null);
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
		}
		//ID 중복 체크 및 로그인 처리
		public MemberVO checkMember(String id)
		                      throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM em_member_manage m LEFT OUTER JOIN "
					+ "em_member_detail d ON m.mem_num = d.mem_num "
					+ "WHERE m.mem_id=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setString(1, id);
				//SQL문 실행
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new MemberVO();
					member.setMem_num(
							rs.getInt("mem_num"));
					member.setId(
							rs.getString("mem_id"));
					member.setAuth(
							   rs.getInt("mem_auth"));
					member.setPasswd(
						  rs.getString("mem_passwd"));
					member.setEmail(
							rs.getString("mem_email"));
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
