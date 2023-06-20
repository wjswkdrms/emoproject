package kr.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.cart.vo.CartVO;
import kr.product.vo.ProductDetailVO;
import kr.util.DBUtil;

public class CartDAO {
	//싱글턴 패턴
	private static CartDAO instance = 
			                new CartDAO();
	public static CartDAO getInstance() {
		return instance;
	}
	private CartDAO() {}
	
	//회원 보유 포인트 구하기
	public int getMemPoint(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int point = 0;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT mem_point FROM em_member_detail WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				point = (rs.getInt("mem_point"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return point;
	}
	//회원별 장바구니 총 구매 금액 구하기
	public int getTotalByMem_num(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int sum = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "select * FROM em_member_cart c join em_product_detail d on c.product_num = d.product_num join em_product_manage m on d.product_num = m.product_num WHERE c.mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				sum += (rs.getInt("cart_quantity")*rs.getInt("product_price"));
			}
		}catch(Exception e) {
			
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return sum;
	}
	//카트에 상품 등록하기
	public void insertCart(CartVO cart) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO em_member_cart (mem_cart_num, mem_num, product_num, cart_quantity) VALUES(em_member_cart_seq,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getMem_num());
			pstmt.setInt(2, cart.getProduct_num());
			pstmt.setInt(3, cart.getCart_quantity());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//카트db에 있는 상품 목록 불러오기
	public List<CartVO> getListCart(int user_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<CartVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "select * FROM em_member_cart c join em_product_detail d on c.product_num = d.product_num join em_product_manage m on d.product_num = m.product_num WHERE c.mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_num);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CartVO>();
			while(rs.next()) {
				CartVO cart = new CartVO();
				cart.setMem_cart_num(rs.getInt("mem_cart_num"));
				cart.setProduct_num(rs.getInt("product_num"));
				cart.setCart_quantity(rs.getInt("cart_quantity"));
				cart.setMem_num(rs.getInt("mem_num"));
				
				ProductDetailVO product = new ProductDetailVO();
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_photo1(rs.getString("product_photo1"));
				product.setProduct_price(rs.getInt("product_price"));
				product.setProduct_stock(rs.getInt("product_stock"));
				product.setProduct_title(rs.getString("product_title"));
				
				cart.setProduct(product);
				list.add(cart);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	public void plusQuantity(
            int cart_num)
          throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = null;
		int quant = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			sql = "SELECT cart_quantity FROM em_member_cart WHERE mem_cart_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, cart_num);
			rs = pstmt2.executeQuery();
			if(rs.next()) {
				quant = rs.getInt("cart_quantity");
				quant++;
			}
			sql = "UPDATE em_member_cart SET cart_quantity=? "
					+ "WHERE mem_cart_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, quant);
			pstmt.setInt(2, cart_num);
			//SQL문 실행
			pstmt.executeUpdate();
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	public void minusQuantity(
            int cart_num)
          throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = null;
		int quant = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			sql = "SELECT cart_quantity FROM em_member_cart WHERE mem_cart_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, cart_num);
			rs = pstmt2.executeQuery();
			if(rs.next()) {
				quant = rs.getInt("cart_quantity");
				quant--;
			}
			sql = "UPDATE em_member_cart SET cart_quantity=? "
					+ "WHERE mem_cart_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, quant);
			pstmt.setInt(2, cart_num);
			//SQL문 실행
			pstmt.executeUpdate();
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
}
