package kr.announce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.announce.vo.AnnounceVO;
import kr.util.DBUtil;

public class AnnounceDAO {
	//싱글턴 패턴
	private static AnnounceDAO instance=new AnnounceDAO();
	
	public static AnnounceDAO getInstance() {
		return instance;
	}
	
	private AnnounceDAO() {}
	
	//글 등록
	public void insertAnnounce(AnnounceVO announce) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		try {
			conn=DBUtil.getConnection();
			sql="INSERT INTO em_board_announce (ann_num,mem_num,ann_title,ann_content,ann_photo1) "
					+ "VALUES (em_board_announce_seq.nextval,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, announce.getMem_num());
			pstmt.setString(2, announce.getAnn_title());
			pstmt.setString(3, announce.getAnn_content());
			pstmt.setString(4, announce.getAnn_photo1());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//총 레코드 수(검색 레코드 수)
	public int getAnnounceCount(String keyfield,String keyword) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		String sub_sql="";
		int count=0;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT COUNT(*) FROM em_board_announce a JOIN "
					+ "em_member_manage m USING(mem_num)" + sub_sql;
			pstmt=conn.prepareStatement(sql);
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
	
	//글 목록
	public List<AnnounceVO> getAnnounceBoard() throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AnnounceVO> list=null;
		String sql=null;
		String sub_sql="";
		int cnt=0;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM em_board_announce a JOIN em_member_manage m "
					+ "USING (mem_num) ORDER BY a.ann_num DESC";
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			list=new ArrayList<AnnounceVO>();
			while(rs.next()) {
				AnnounceVO announce=new AnnounceVO();
				announce.setAnn_num(rs.getInt("ann_num"));
				announce.setAnn_title(rs.getString("ann_title"));
				announce.setAnn_content(rs.getString("ann_content"));
				announce.setAnn_date(rs.getDate("ann_date"));
				announce.setMem_id(rs.getString("mem_id"));
				list.add(announce);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//글 상세
	public AnnounceVO getAnnounce(int ann_num) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		AnnounceVO announce=null;
		String sql=null;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM em_board_announce a JOIN em_member_manage m "
					+ "USING(mem_num) WHERE ann_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ann_num);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				announce=new AnnounceVO();
				announce.setAnn_num(rs.getInt("ann_num"));
				announce.setAnn_title(rs.getString("ann_title"));
				announce.setAnn_content(rs.getString("ann_content"));
				announce.setAnn_photo1(rs.getString("ann_photo1"));
				announce.setAnn_date(rs.getDate("ann_date"));
				announce.setMem_id(rs.getString("mem_id"));
				announce.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return announce;
	}
	
	//글 수정
	public void updateAnnounce(AnnounceVO announce)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		try {
			conn=DBUtil.getConnection();
			sql="UPDATE em_board_announce SET ann_title=?,ann_content=?,ann_photo1=? WHERE ann_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, announce.getAnn_title());
			pstmt.setString(2, announce.getAnn_content());
			pstmt.setInt(3, announce.getAnn_num());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 삭제
	
}
