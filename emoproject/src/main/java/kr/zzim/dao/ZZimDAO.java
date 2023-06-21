package kr.zzim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.product.vo.ProductManageVO;
import kr.util.DBUtil;
import kr.zzim.vo.ZZimVO;

public class ZZimDAO {
	private static ZZimDAO instance = new ZZimDAO();
	public static ZZimDAO getInstance() {
		return instance;
	}
	private ZZimDAO() {};
	
	
	//좋아요 등록
	public void insertzzim(ZZimVO zzimVO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO em_member_zzim (zzim_num,mem_num,product_num) VALUES (em_member_zzim_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, zzimVO.getMem_num());
			pstmt.setInt(2, zzimVO.getProduct_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//좋아요 삭제
	public void deleteZzim(int zzim_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM em_member_zzim WHERE zzim_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, zzim_num);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//회원번호와 게시물 번호를 이용한 좋아요 정보
	public ZZimVO selectZzim(ZZimVO zzimVO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ZZimVO zzim = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM em_member_zzim WHERE mem_num=? AND product_num=?";
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, zzimVO.getMem_num());
			pstmt.setInt(2, zzimVO.getProduct_num());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				zzim = new ZZimVO();
				zzim.setZzim_num(rs.getInt("zzim_num"));
				zzim.setMem_num(rs.getInt("mem_num"));
				zzim.setProduct_num(rs.getInt("product_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return zzim;
	}
	//내가 선택한 좋아요 목록
	public List<ProductManageVO> getListProductZzim(int start, int end, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductManageVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM(SELECT * FROM em_member_manage m JOIN em_member_zzim z USING(mem_num) "
					+ "JOIN em_prodct_manage p USING(product_num) "
					+ "WHERE z.mem_num=? ORDER BY zzim_num DESC) a) WHERE rnum >= ? AND rnum <=?";
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<ProductManageVO>(); 
			
			while(rs.next()) {
				ProductManageVO zzim_product = new ProductManageVO();
				zzim_product.setProduct_num(rs.getInt("product_num"));
				zzim_product.getProductdetailVO().setProduct_title(rs.getString("product_title"));;
				zzim_product.getProductdetailVO().setProduct_photo1(rs.getString("product_photo1"));
				
				list.add(zzim_product);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//(좋아요개수)
	public int selectZzimCount(int product_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM em_member_zzim WHERE product_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
}
