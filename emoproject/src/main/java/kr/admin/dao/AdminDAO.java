package kr.admin.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.admin.vo.AdminVO;
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
	
	public List<AdminVO> getProductsOrdersByAdmin() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String result = null;
		List<AdminVO> list = null;
		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			
			sql = "select product_num, order_product_name, SUM(order_product_total) AS product_total_price, SUM(ORDER_PRODUCT_QUANTITY) AS product_sales_quantity FROM em_order_detail GROUP BY product_num, order_product_name ORDER BY product_total_price DESC";
			pstmt = conn.prepareStatement(sql);
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
}
