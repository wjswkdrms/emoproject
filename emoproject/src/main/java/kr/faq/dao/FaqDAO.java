package kr.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.faq.vo.FaqVO;
import kr.util.DBUtil;

public class FaqDAO {
	//싱글턴 패턴
	private static FaqDAO instance=new FaqDAO();
	
	public static FaqDAO getInstance() {
		return instance;
	}
	
	private FaqDAO() {}
	
	//글 등록
	public void insertFaq(FaqVO faq) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		try {
			conn=DBUtil.getConnection();
			sql="INSERT INTO em_board_faq (faq_num,mem_num,faq_title,faq_content,faq_category) "
					+ "VALUES (em_board_faq_seq.nextval,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, faq.getMem_num());
			pstmt.setString(2, faq.getFaq_title());
			pstmt.setString(3, faq.getFaq_content());
			pstmt.setInt(4, faq.getFaq_category());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//총 레코드 수(검색 레코드 수)
		public int getFaqCount(String keyfield) throws Exception{
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql=null;
			String sub_sql="";
			int count=0;
			
			try {
				conn=DBUtil.getConnection();
				
				if(keyfield!=null) {
					sub_sql += "WHERE a.faq_category LIKE ?";
				}
				
				sql="SELECT COUNT(*) FROM em_board_faq a JOIN "
						+ "em_member_manage m USING(mem_num)" + sub_sql;
				pstmt=conn.prepareStatement(sql);
				
				if(keyfield!=null) {
					pstmt.setInt(1, Integer.parseInt(keyfield));
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
		
		//글 목록
		public List<FaqVO> getFaqBoard(int start, int end,
				 String keyfield) throws Exception{
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<FaqVO> list=null;
			String sql=null;
			String sub_sql="";
			int cnt=0;
			
			try {
				conn=DBUtil.getConnection();
				
				if(keyfield!=null) {
					sub_sql += "WHERE a.faq_category LIKE ?";
				}
				
				sql="SELECT * FROM (SELECT b.*,"
						+ "rownum rnum FROM (SELECT * "
						+ "FROM em_board_faq a JOIN em_member_manage m "
						+ "USING(mem_num) " + sub_sql + " ORDER BY "
						+ "a.faq_num DESC)b) "
						+ "WHERE rnum>=? AND rnum<=?";

				pstmt=conn.prepareStatement(sql);
				if(keyfield!=null) {
					pstmt.setString(++cnt, keyfield);;
				}				
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				
				rs=pstmt.executeQuery();
				list=new ArrayList<FaqVO>();
				while(rs.next()) {
					FaqVO faq=new FaqVO();
					faq.setFaq_num(rs.getInt("faq_num"));
					faq.setMem_num(rs.getInt("mem_num"));
					faq.setFaq_category(rs.getInt("faq_category"));
					faq.setFaq_content(rs.getString("faq_content"));
					faq.setFaq_date(rs.getDate("faq_date"));
					faq.setFaq_title(rs.getString("faq_title"));
					
					list.add(faq);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
			return list;
		}
		
		//글 상세
		public FaqVO getFaq(int faq_num) throws Exception{
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			FaqVO faq=null;
			String sql=null;
			
			try {
				conn=DBUtil.getConnection();
				sql="SELECT * FROM em_board_faq a JOIN em_member_manage m "
						+ "USING(mem_num) WHERE faq_num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, faq_num);
				
				rs=pstmt.executeQuery();
				if(rs.next()) {
					faq=new FaqVO();
					faq.setFaq_num(rs.getInt("faq_num"));
					faq.setFaq_category(rs.getInt("faq_category"));
					faq.setFaq_title(rs.getString("faq_title"));
					faq.setFaq_content(rs.getString("faq_content"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return faq;
		}
		
		//글 수정
		public void updateFaq(FaqVO faq)throws Exception{
			Connection conn=null;
			PreparedStatement pstmt=null;
			String sql=null;
			
			try {
				conn=DBUtil.getConnection();
				sql="UPDATE em_board_faq SET faq_title=?,faq_content=?,faq_category=? WHERE faq_num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, faq.getFaq_title());
				pstmt.setString(2, faq.getFaq_content());
				pstmt.setInt(3, faq.getFaq_category());
				pstmt.setInt(4, faq.getFaq_num());
				
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
}
