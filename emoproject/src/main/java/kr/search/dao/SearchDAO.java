package kr.search.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.search.vo.SearchVO;
import kr.util.DBUtil;

public class SearchDAO {
	//싱글턴 패턴
	private static SearchDAO instance = new SearchDAO();
	public static SearchDAO getInstance() {return instance;};
	private SearchDAO() {}
	
	//Product Category 조건 목록
	public List<SearchVO> getProductCategories(int product_category) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<SearchVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM EM_PRODUCT_MANAGE m LEFT OUTER JOIN EM_PRODUCT_DETAIL d USING (PRODUCT_NUM) WHERE product_category=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_category);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<SearchVO>();
			
			while (rs.next()) {
				SearchVO searchvo = new SearchVO();
				searchvo.setProduct_num(rs.getInt("product_num"));
				searchvo.setProduct_category(product_category);
				searchvo.setProduct_status(rs.getInt("product_status"));
				searchvo.setProduct_name(rs.getString("product_name"));
				searchvo.setProduct_title(rs.getString("product_title"));
				searchvo.setProduct_info(rs.getString("product_info"));
				searchvo.setProduct_photo1(rs.getString("product_photo1"));
				searchvo.setProduct_photo2(rs.getString("product_photo2"));
				searchvo.setProduct_origin(rs.getString("product_origin"));
				searchvo.setProduct_real_price(rs.getInt("product_real_price"));
				searchvo.setProduct_price(rs.getInt("product_price"));
				searchvo.setProduct_stock(rs.getInt("product_stock"));
				list.add(searchvo);
			}
			
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
}
