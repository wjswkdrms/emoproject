package kr.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	// 관리자 - 상품 등록
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
	//--------------------
	
	// 관리자/사용자 - 전체 상품 개수/검색 상품 개수
	public int getProductCount(String keyfield, String keyword, int status) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "AND d.product_name LIKE ?";
				if(keyfield.equals("2")) sub_sql += "AND d.product_info LIKE ?";
			}
			sql = "SELECT COUNT(*) FROM em_product_manage m INNER JOIN em_product_detail d "
					+ "ON m.product_num=d.product_num WHERE  m.product_status > ? "+sub_sql;
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, status);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(2, "%"+keyword+"%");
			}
			
			//글의 갯수 => if
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
	// 관리자/사용자 - 전체 상품 목록/검색 상품 목록
	public List<ProductManageVO> getListProduct(int start, int end, String keyfield,String keyword,
            int status) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductManageVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();

			if (keyword != null && !"".equals(keyword)) {
				if (keyfield.equals("1")) sub_sql += "AND d.product_name LIKE ? ";
				if (keyfield.equals("2")) sub_sql += "AND d.product_info LIKE ? ";
			}
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM em_product_manage m INNER JOIN em_product_detail d "
					+ "ON m.product_num=d.product_num WHERE m.product_status > ? "+sub_sql
					+"ORDER BY m.product_num DESC) a) WHERE rnum>=? AND rnum<=?";
			pstmt = conn.prepareStatement(sql);
			
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, status);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<ProductManageVO>();
			while(rs.next()) {
				ProductManageVO product = new ProductManageVO();
				product.setProduct_num(rs.getInt("product_num"));
				product.setProduct_category(rs.getInt("product_category"));
				product.setProduct_status(rs.getInt("product_status"));
				
				//상품 상세정보 담기 위해 객체 생성
				ProductDetailVO product_detail = new ProductDetailVO();
				product_detail.setProduct_name(rs.getString("product_name"));
				product_detail.setProduct_title(rs.getString("product_title"));
				product_detail.setProduct_info(rs.getString("product_info"));
				product_detail.setProduct_photo1(rs.getString("product_photo1"));
				product_detail.setProduct_photo2(rs.getString("product_photo2"));
				product_detail.setProduct_origin(rs.getString("product_origin"));
				product_detail.setProduct_origin(rs.getString("product_origin"));
				product_detail.setProduct_real_price(rs.getInt("product_real_price"));
				product_detail.setProduct_price(rs.getInt("product_price"));
				product_detail.setProduct_stock(rs.getInt("product_stock"));
				
				//ProductDetailVO를 ProductManageVO에 저장
				product.setProductdetailVO(product_detail);
				
				list.add(product);
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	// 관리자/사용자 - 상품 상세
	// 관리자 - 상품 수정
	// 관리자 - 상품 삭제

}
