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
	
	
	//카테고리별 글 갯수 구하기 (count)
	
	public int getProductCategoriesCount(int product_category) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<SearchVO> list = null;
		int count = 0;
		try {
			
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM EM_PRODUCT_MANAGE m LEFT OUTER JOIN EM_PRODUCT_DETAIL d USING (PRODUCT_NUM) WHERE product_category=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_category);
			
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//Product Category 조건 목록
	/*
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
	*/
	
	
	
	
	//모든 상품 갯수 구하기
	public int getProductMainEverythingCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM EM_PRODUCT_MANAGE m LEFT OUTER JOIN EM_PRODUCT_DETAIL d USING (PRODUCT_NUM)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	//모든 상품 출력 하기
	public List<SearchVO> getProductMainEverything(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<SearchVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT * FROM EM_PRODUCT_MANAGE m LEFT OUTER JOIN EM_PRODUCT_DETAIL d USING (PRODUCT_NUM))a) WHERE rnum>=? AND rnum<=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<SearchVO>();
			
			while (rs.next()) {
				SearchVO searchvo = new SearchVO();
				searchvo.setProduct_num(rs.getInt("product_num"));
				searchvo.setProduct_category(rs.getInt("product_category"));
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
	
	
	
	
	//ajax 카운트 구하기
	public int getSortingProductWithProductCategoryCount(String searchText ,int sorted_navigator_num, int product_category) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		String sub_sql_search ="";
		int cnt = 0;
		int count = 0;
		///////////////////////////////////////////고쳐야 하는 부분
		try {
			//searchText로 넣을 시에 sorted_navigator는 1로 들어오고 product_category는 0으로 들어옴
			
			//navigator 값들에 대한 order by
			if (sorted_navigator_num == 1) {
				sub_sql = ""; //최신순
			} else if (sorted_navigator_num == 2) {
				sub_sql = ""; //
			} else if (sorted_navigator_num == 3) {
				sub_sql = "";
			} else if (sorted_navigator_num == 4) {
				sub_sql = " ORDER BY product_price DESC";
				//높은 가격 순
			} else if (sorted_navigator_num == 5) {
				//낮은 가격 순
				sub_sql = " ORDER BY product_price";
			}
			
			if (product_category != 0 ) {
				sub_sql_search += " WHERE product_category=? ";
			} else if (product_category == 0 && searchText != null) {
				sub_sql_search += " WHERE (product_name LIKE ? OR product_title LIKE ?) ";
			} else if (product_category !=0 && searchText != null) {
				sub_sql_search += " WHERE product_category = ? AND (product_name LIKE ? OR product_title LIKE ?)" ;
			}
			
			
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM EM_PRODUCT_MANAGE m LEFT OUTER JOIN EM_PRODUCT_DETAIL d USING (PRODUCT_NUM) "+sub_sql_search + sub_sql;
			pstmt = conn.prepareStatement(sql);
			
			if(product_category != 0) {
				pstmt.setInt(++cnt, product_category);
			} else if (product_category == 0 && searchText != null) {
				pstmt.setString(++cnt, "%"+searchText+"%");
				pstmt.setString(++cnt, "%"+searchText+"%");
			} else if (product_category !=0 && searchText != null) {
				pstmt.setInt(++cnt, product_category);
				pstmt.setString(++cnt, "%"+searchText+"%");
				pstmt.setString(++cnt, "%"+searchText+"%");
			}
			
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//ajax 구하기
	public List<SearchVO> getSortingProductWithProductCategory(int start, int end,String searchText ,int sorted_navigator_num, int product_category) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		String sub_sql_search ="";
		int cnt = 0;
		List<SearchVO> list = null;
		///////////////////////////////////////////고쳐야 하는 부분
		try {
			//searchText로 넣을 시에 sorted_navigator는 1로 들어오고 product_category는 0으로 들어옴
			
			//navigator 값들에 대한 order by
			if (sorted_navigator_num == 1) {
				sub_sql = ""; //최신순
			} else if (sorted_navigator_num == 2) {
				sub_sql = ""; //
			} else if (sorted_navigator_num == 3) {
				sub_sql = "";
			} else if (sorted_navigator_num == 4) {
				sub_sql = " ORDER BY product_price DESC";
				//높은 가격 순
			} else if (sorted_navigator_num == 5) {
				//낮은 가격 순
				sub_sql = " ORDER BY product_price";
			}
			
			if (product_category != 0 ) {
				sub_sql_search += " WHERE product_category=? ";
			} else if (product_category == 0 && searchText != null) {
				sub_sql_search += " WHERE (product_name LIKE ? OR product_title LIKE ?) ";
			} else if (product_category !=0 && searchText != null) {
				sub_sql_search += " WHERE product_category = ? AND (product_name LIKE ? OR product_title LIKE ?)" ;
			}
			
			
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT * FROM EM_PRODUCT_MANAGE m LEFT OUTER JOIN EM_PRODUCT_DETAIL d USING (PRODUCT_NUM) "+sub_sql_search + sub_sql+")a) WHERE rnum>=? AND rnum<=? ";
			pstmt = conn.prepareStatement(sql);
			
			if(product_category != 0) {
				pstmt.setInt(++cnt, product_category);
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
			} else if (product_category == 0 && searchText != null) {
				pstmt.setString(++cnt, "%"+searchText+"%");
				pstmt.setString(++cnt, "%"+searchText+"%");
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
			} else if (product_category !=0 && searchText != null) {
				pstmt.setInt(++cnt, product_category);
				pstmt.setString(++cnt, "%"+searchText+"%");
				pstmt.setString(++cnt, "%"+searchText+"%");
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
			}
			
			
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
