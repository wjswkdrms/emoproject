package kr.ask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.announce.vo.AnnounceVO;
import kr.ask.vo.AskVO;
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
		
		try {
			conn=DBUtil.getConnection();
			sql="UPDATE em_board_ask SET ask_title=?,ask_content=?,ask_photo1=? WHERE ask_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ask.getAsk_title());
			pstmt.setString(2, ask.getAsk_content());
			pstmt.setString(3, ask.getAsk_photo1());
			pstmt.setInt(4, ask.getAsk_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//1:1 문의 목록(사용자)
	public List<AskVO> getAskBoard(int mem_num) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AskVO> list=null;
		String sql=null;
		String sub_sql="";
		int cnt=0;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM em_board_ask a JOIN em_member_manage m "
					+ "USING (mem_num) WHERE mem_num=? ORDER BY a.ask_num DESC";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			
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
	public List<AskVO> getAskTotalBoard() throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AskVO> list=null;
		String sql=null;
		String sub_sql="";
		int cnt=0;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM em_board_ask a JOIN em_member_manage m "
					+ "USING (mem_num) ORDER BY a.ask_num DESC";
			pstmt=conn.prepareStatement(sql);
			
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
}
