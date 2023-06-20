package kr.member.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	//�떛湲��꽩 �뙣�꽩
		private static MemberDAO instance = 
				            new MemberDAO();
		public static MemberDAO getInstance() {
			return instance;
		}
		private MemberDAO() {}
		
		//�쉶�썝媛��엯
		public void insertMember(MemberVO member)
		                         throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			ResultSet rs = null;
			String sql = null;
			int num = 0; //�떆���뒪 踰덊샇 ���옣
			try {
				//而ㅻ꽖�뀡��濡쒕��꽣 而ㅻ꽖�뀡�쓣 �븷�떦
				conn = DBUtil.getConnection();
				//�삤�넗 而ㅻ컠 �빐�젣
				conn.setAutoCommit(false);
				
				//�쉶�썝踰덊샇(mem_num) 援ы븯湲�
				sql = "SELECT em_member_seq.nextval FROM dual";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					num = rs.getInt(1);
				}
				
				//zmember �뀒�씠釉붿뿉 �뜲�씠�꽣 ���옣
				sql = "INSERT INTO em_member_manage (mem_num,mem_id,mem_auth,mem_reg_date)"
					+ "VALUES (?,?,2,SYSDATE)";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, num);//�떆���뒪
				pstmt2.setString(2, member.getId());//id
				pstmt2.executeUpdate();
				
				//zmember_detail �뀒�씠釉붿뿉 �뜲�씠�꽣 ���옣
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
				
				//SQL臾몄쓣 �떎�뻾�빐�꽌 紐⑤몢 �꽦怨듯븯硫� commit
				conn.commit();
			}catch(Exception e) {
				//SQL臾몄씠 �븯�굹�씪�룄 �떎�뙣�븯硫� rollback
				conn.rollback();
				throw new Exception(e);
			}finally {
				//�옄�썝�젙由�
				DBUtil.executeClose(null, pstmt3, null);
				DBUtil.executeClose(null, pstmt2, null);
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
		}
		//ID 以묐났 泥댄겕 諛� 濡쒓렇�씤 泥섎━
		public MemberVO checkMember(String id)
		                      throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			
			try {
				//而ㅻ꽖�뀡��濡쒕��꽣 而ㅻ꽖�뀡�쓣 �븷�떦
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM em_member_manage m LEFT OUTER JOIN "
					+ "em_member_detail d ON m.mem_num = d.mem_num "
					+ "WHERE m.mem_id=?";
				//PreparedStatement 媛앹껜 �깮�꽦
				pstmt = conn.prepareStatement(sql);
				//?�뿉 �뜲�씠�꽣 諛붿씤�뵫
				pstmt.setString(1, id);
				//SQL臾� �떎�뻾
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
				//�옄�썝�젙由�
				DBUtil.executeClose(rs, pstmt, conn);
			}	
			return member;
		}
		
		//email 중복체크
		public MemberVO checkMemberEmail(String email)
		                      throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			
			try {
				//而ㅻ꽖�뀡��濡쒕��꽣 而ㅻ꽖�뀡�쓣 �븷�떦
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM em_member_manage m LEFT OUTER JOIN "
					+ "em_member_detail d ON m.mem_num = d.mem_num "
					+ "WHERE m.mem_email=?";
				//PreparedStatement 媛앹껜 �깮�꽦
				pstmt = conn.prepareStatement(sql);
				//?�뿉 �뜲�씠�꽣 諛붿씤�뵫
				pstmt.setString(1, email);
				//SQL臾� �떎�뻾
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
				//�옄�썝�젙由�
				DBUtil.executeClose(rs, pstmt, conn);
			}	
			return member;
		}
		
		
		//ID 찾기
		public MemberVO checkid(String name,String email)
		                      throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT manage.mem_id FROM EM_MEMBER_MANAGE manage \r\n"
						+ "INNER JOIN EM_MEMBER_DETAIL detail ON manage.mem_num = detail.mem_num \r\n"
						+ "WHERE detail.mem_name = ? AND detail.mem_email = ?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
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
				//�옄�썝�젙由�
				DBUtil.executeClose(rs, pstmt, conn);
			}	
			return member;
		}
}
