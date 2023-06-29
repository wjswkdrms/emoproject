package kr.admin.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.admin.vo.AdminVO;
import kr.search.vo.SearchVO;
import kr.util.DBUtil;

public class AdminDAO {
	private static AdminDAO instance = new AdminDAO();
	public static AdminDAO getInstance() {return instance;}
	private AdminDAO() {}
	
	//현재 날짜 뽑기
	public String getToday() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String result = null;
		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT SYSDATE FROM DUAL";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = String.valueOf(rs.getDate(1));
			}
			return result;
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	//주문 상품 카운트
	public int getProductsOrdersByAdminCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM (select product_num, order_product_name, SUM(order_product_total) AS product_total_price, SUM(ORDER_PRODUCT_QUANTITY) AS product_sales_quantity FROM em_order_detail GROUP BY product_num, order_product_name ORDER BY product_total_price DESC)";
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
	
	//주문 상품 rnum으로 받기
	public List<AdminVO> getProductsOrdersByAdmin(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<AdminVO> list = null;
		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			
			sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM (select product_num, order_product_name, SUM(order_product_total) AS product_total_price, SUM(ORDER_PRODUCT_QUANTITY) AS product_sales_quantity FROM em_order_detail GROUP BY product_num, order_product_name ORDER BY product_total_price DESC)a) WHERE rnum>=? AND rnum<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<AdminVO>();
			while(rs.next()) {
				AdminVO adminvo = new AdminVO();
				adminvo.setProduct_num(rs.getInt("product_num"));
				adminvo.setOrder_product_name(rs.getString("order_product_name"));
				adminvo.setProduct_total_price(rs.getInt("product_total_price"));
				adminvo.setProduct_sales_quantity(rs.getInt("product_sales_quantity"));
				
				list.add(adminvo);
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	public AdminVO getTotalSales() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String result = null;
		AdminVO adminvo = null;
		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "select (sales_total) AS product_total_price, (sales_total - sales_real_total) AS product_total_profit, (sales_quantity) AS product_sales_quantity FROM (select SUM(order_product_total) AS sales_total, SUM(product_real_price) AS sales_real_total, sum(order_product_quantity) AS sales_quantity FROM (select * FROM em_product_detail LEFT INNER JOIN (select * FROM em_order_detail) USING (product_num)))";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				adminvo = new AdminVO();
				adminvo.setProduct_total_price(rs.getInt("product_total_price"));
				adminvo.setProduct_total_profit(rs.getInt("product_total_profit"));
				adminvo.setProduct_sales_quantity(rs.getInt("product_sales_quantity"));
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return adminvo;
	}
	
	//count 하기
		public int getMemberOrdersByAdminCount(int product_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;
			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				
				// SQL문 작성
				
				sql = "select COUNT(*) FROM (select product_num, order_product_name, mem_num ,mem_name, order_product_price ,order_product_quantity ,order_product_total, order_date  FROM em_member_detail LEFT INNER JOIN (select * FROM em_order_manage LEFT INNER JOIN em_order_detail USING (order_num)) USING (mem_num) WHERE product_num = ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, product_num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
					
				}
				
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
	
	//rnum 추가 하기
	public List<AdminVO> getMemberOrdersByAdmin(int start,int end,int product_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<AdminVO> list = null;
		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			
			sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM (select product_num, order_product_name, mem_num ,mem_name, order_product_price ,order_product_quantity ,order_product_total, order_date  FROM em_member_detail LEFT INNER JOIN (select * FROM em_order_manage LEFT INNER JOIN em_order_detail USING (order_num)) USING (mem_num) WHERE product_num = ?)a) WHERE rnum>=? AND rnum<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<AdminVO>();
			while(rs.next()) {
				AdminVO adminvo = new AdminVO();
				adminvo.setProduct_num(rs.getInt("product_num"));
				adminvo.setOrder_product_name(rs.getString("order_product_name"));
				adminvo.setMem_num(rs.getInt("mem_num"));
				adminvo.setMem_name(rs.getString("mem_name"));
				adminvo.setOrder_product_price(rs.getInt("order_product_price"));
				adminvo.setOrder_product_quantity(rs.getInt("order_product_quantity"));
				adminvo.setOrder_product_total(rs.getInt("order_product_total"));
				adminvo.setOrder_date(rs.getDate("order_date"));
				list.add(adminvo);
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//count 하기
	public int getMemberOrdersByAdminCount(String order_date) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			
			sql = "select count(*) from (select product_num, order_product_name, mem_num ,mem_name, order_product_price ,order_product_quantity ,order_product_total, order_date  FROM em_member_detail LEFT INNER JOIN (select * FROM em_order_manage LEFT INNER JOIN em_order_detail USING (order_num)) USING (mem_num) WHERE to_char(order_date,'YYYY-MM-DD') = ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_date);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	public List<AdminVO> getMemberOrdersByAdmin(int start, int end, String order_date) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<AdminVO> list = null;
		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			
			sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM (select product_num, order_product_name, mem_num ,mem_name, order_product_price ,order_product_quantity ,order_product_total, order_date  FROM em_member_detail LEFT INNER JOIN (select * FROM em_order_manage LEFT INNER JOIN em_order_detail USING (order_num)) USING (mem_num) WHERE to_char(order_date,'YYYY-MM-DD') = ?)a) WHERE rnum>=? AND rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_date);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<AdminVO>();
			while(rs.next()) {
				AdminVO adminvo = new AdminVO();
				adminvo.setProduct_num(rs.getInt("product_num"));
				adminvo.setOrder_product_name(rs.getString("order_product_name"));
				adminvo.setMem_num(rs.getInt("mem_num"));
				adminvo.setMem_name(rs.getString("mem_name"));
				adminvo.setOrder_product_price(rs.getInt("order_product_price"));
				adminvo.setOrder_product_quantity(rs.getInt("order_product_quantity"));
				adminvo.setOrder_product_total(rs.getInt("order_product_total"));
				adminvo.setOrder_date_str(rs.getString("order_date"));
				list.add(adminvo);
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	public AdminVO getTotalSalesDate(String order_date) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		AdminVO adminvo = null;
		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			// SQL문 작성///////////////////////////////////////////////////////
			sql = "select SUM(order_product_total) AS product_total_price, SUM(order_product_quantity) AS product_sales_quantity FROM (select product_num, order_product_name, mem_num, mem_name, order_product_price ,order_product_quantity ,order_product_total, order_date FROM em_member_detail LEFT INNER JOIN (select * FROM em_order_manage LEFT INNER JOIN em_order_detail USING (order_num)) USING (mem_num) WHERE to_char(order_date,'YYYY-MM-DD') = ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_date);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				adminvo = new AdminVO();
				adminvo.setProduct_total_price(rs.getInt("product_total_price"));
				adminvo.setProduct_sales_quantity(rs.getInt("product_sales_quantity"));
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return adminvo;
	}
}
