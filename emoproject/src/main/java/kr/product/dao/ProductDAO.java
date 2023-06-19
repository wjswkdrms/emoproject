package kr.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.product.vo.ProductDetailVO;
import kr.product.vo.ProductManageVO;
import kr.util.DBUtil;

public class ProductDAO {
	//싱글턴 패턴
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	private ProductDAO() {}
	
	//관리자 - 상품 등록
	public void insertItem(ProductManageVO product, ProductDetailVO product_detail) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		
		int product_num = 0;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			//product_num 구하기
			//커밋
			sql = "SELECT em_product_manage_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				product_num = rs.getInt(1);
			}
			
			sql = "INSERT INTO em_product_manage (product_num, product_category, product_status) VALUES (?,?,?)";
			pstmt2 = conn.prepareStatement(sql);
			
			pstmt2.setInt(1, product_num);
			pstmt2.setInt(2, product.getProduct_category());
			pstmt2.setInt(3, product.getProduct_status());
			
			pstmt2.executeUpdate();
			
			sql = "INSERT INTO em_product_detail (product_num, product_name, product_title, product_info, product_photo1, product_photo2, product_origin, product_real_price, product_price, product_stock) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			
			pstmt3.setInt(1, product_num);
			pstmt3.setString(2, product_detail.getProduct_name());
			pstmt3.setString(3, product_detail.getProduct_title());
			pstmt3.setString(4, product_detail.getProduct_info());
			pstmt3.setString(5, product_detail.getProduct_photo1());
			pstmt3.setString(6, product_detail.getProduct_photo2());
			pstmt3.setString(7, product_detail.getProduct_origin());
			pstmt3.setInt(8, product_detail.getProduct_real_price());
			pstmt3.setInt(9, product_detail.getProduct_price());
			pstmt3.setInt(10, product_detail.getProduct_stock());
			
			pstmt3.executeUpdate();
			
			//모든 SQL문이 정상 수행
			conn.commit();
		}catch (Exception e) {
			//하나라도 SQL문이 실패하면
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, conn);
			DBUtil.executeClose(null, pstmt2, conn);
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
	}

}
