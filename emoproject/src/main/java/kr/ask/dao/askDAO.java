package kr.ask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.announce.vo.AnnounceVO;
import kr.ask.vo.AskVO;
import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class askDAO {
	//싱글턴 패턴
	private static askDAO instance=new askDAO();
	
	public static askDAO getInstance() {
		return instance;
	}
	
	private askDAO() {}
	
	//글 등록
	public void insertAsk(AskVO ask) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		try {
			conn=DBUtil.getConnection();
			sql="INSERT INTO em_board_ask (ask_num,ask_title,ask_content,ask_photo1,"
					+ "ask_status,mem_num) VALUES (em_board_ask_seq.nextval,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ask.getAsk_title());
			pstmt.setString(2, ask.getAsk_content());
			pstmt.setString(3, ask.getAsk_photo1());
			pstmt.setInt(4, ask.getAsk_status());
			pstmt.setInt(5, ask.getMem_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 수정
	public void updateAsk(AskVO ask)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn=DBUtil.getConnection();
			
			if(ask.getAsk_photo1()!=null) {
				//파일을 업로드한 경우
				sub_sql += ",ask_photo1=?";
			}
			
			sql="UPDATE em_board_ask SET ask_title=?,ask_content=?"+sub_sql+" WHERE ask_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(++cnt, ask.getAsk_title());
			pstmt.setString(++cnt, ask.getAsk_content());
			if(ask.getAsk_photo1()!=null) {
				pstmt.setString(++cnt, ask.getAsk_photo1());
			}
			pstmt.setInt(++cnt, ask.getAsk_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//1:1 문의 목록(사용자)
	public List<AskVO> getAskBoard(int start,int end,int mem_num) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AskVO> list=null;
		String sql=null;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM (SELECT b.*,rownum rnum FROM (SELECT * FROM em_board_ask a "
					+ "JOIN em_member_manage m USING(mem_num) WHERE mem_num=? ORDER BY "
					+ "a.ask_num DESC)b) WHERE rnum>=? AND rnum<=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs=pstmt.executeQuery();
			list=new ArrayList<AskVO>();
			while(rs.next()) {
				AskVO ask=new AskVO();
				
				ask.setAsk_num(rs.getInt("ask_num"));
				ask.setAsk_title(rs.getString("ask_title"));
				ask.setAsk_content(rs.getString("ask_content"));
				ask.setAsk_photo1(rs.getString("ask_photo1"));
				ask.setAsk_date(rs.getDate("ask_date"));
				ask.setAsk_status(rs.getInt("ask_status"));
				ask.setMem_id(rs.getString("mem_id"));
				ask.setMem_num(rs.getInt("mem_num"));
				
				list.add(ask);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//1:1 문의 목록(관리자)
	public List<AskVO> getAskTotalBoard(int start,int end) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AskVO> list=null;
		String sql=null;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM (SELECT b.*,rownum rnum FROM (SELECT * FROM em_board_ask a "
					+ "JOIN em_member_manage m USING(mem_num) ORDER BY "
					+ "a.ask_num DESC)b) WHERE rnum>=? AND rnum<=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs=pstmt.executeQuery();
			list=new ArrayList<AskVO>();
			while(rs.next()) {
				AskVO ask=new AskVO();
				
				ask.setAsk_num(rs.getInt("ask_num"));
				ask.setAsk_title(rs.getString("ask_title"));
				ask.setAsk_content(rs.getString("ask_content"));
				ask.setAsk_photo1(rs.getString("ask_photo1"));
				ask.setAsk_date(rs.getDate("ask_date"));
				ask.setAsk_status(rs.getInt("ask_status"));
				ask.setMem_id(rs.getString("mem_id"));
				ask.setMem_num(rs.getInt("mem_num"));
				
				list.add(ask);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//글 상세
	public AskVO getAsk(int ask_num) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		AskVO ask=null;
		String sql=null;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM em_board_ask a JOIN em_member_manage m "
					+ "USING(mem_num) WHERE ask_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ask_num);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ask=new AskVO();
				
				ask.setAsk_title(rs.getString("ask_title"));
				ask.setAsk_content(rs.getString("ask_content"));
				ask.setMem_id(rs.getString("mem_id"));
				ask.setAsk_photo1(rs.getString("ask_photo1"));
				ask.setAsk_date(rs.getDate("ask_date"));
				ask.setAsk_status(rs.getInt("ask_status"));
				ask.setAsk_num(rs.getInt("ask_num"));
				ask.setMem_num(rs.getInt("mem_num"));
				
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return ask;
	}
	
	//글 삭제
	public void deleteAsk(int ask_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			 conn = DBUtil.getConnection();
			 //글 삭제
			 sql = "DELETE FROM em_board_answer WHERE ask_num=?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, ask_num);
			 pstmt.executeUpdate();
			 
			 sql="DELETE FROM em_board_ask WHERE ask_num=?";
			 pstmt2=conn.prepareStatement(sql);
			 pstmt2.setInt(1, ask_num);
			 pstmt2.executeUpdate();
			 
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//파일 삭제
	public void deleteFile(int ask_num)
            throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE em_board_ask SET ask_photo1='' "
			+ "WHERE ask_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, ask_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
		//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//총 레코드 수(검색 레코드 수)
	public int getAskCount(int mem_auth,int mem_num) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		String sub_sql="";
		int count=0;
		
		try {
			conn=DBUtil.getConnection();
			if(mem_auth==2) {
				sub_sql+="WHERE mem_num=?";
			}
			sql="SELECT COUNT(*) FROM em_board_ask a JOIN "
					+ "em_member_manage m USING(mem_num)"+sub_sql;
			pstmt=conn.prepareStatement(sql);
			if(mem_auth==2) {
				pstmt.setInt(1, mem_num);
			}			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}	
}
