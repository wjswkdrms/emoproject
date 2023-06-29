package kr.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.cart.vo.CartVO;
import kr.order.vo.MemberHomeVO;
import kr.product.vo.ProductDetailVO;
import kr.product.vo.ProductManageVO;
import kr.util.DBUtil;
//커밋
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
				//sum += (rs.getInt("cart_quantity")*rs.getInt("product_price"));
				sum += (rs.getInt("cart_quantity")*((rs.getInt("product_price")*(100 - rs.getInt("product_discount")))/100));
			}
		}catch(Exception e) {
			
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return sum;
	}
	//회원별 장바구니 총 구매 금액 구하기(할인 전)
		public int getBeforeTotalByMem_num(int mem_num) throws Exception{
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
					/*
					 * sum += (rs.getInt("cart_quantity")*((rs.getInt("product_price")*(100 -
					 * rs.getInt("product_discount")))/100));
					 */
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
			sql = "INSERT INTO em_member_cart (mem_cart_num, mem_num, product_num, cart_quantity) VALUES(em_member_cart_seq.nextval,?,?,?)";
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
				product.setProduct_price_sales((rs.getInt("product_price")*(100 - rs.getInt("product_discount")))/100);
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
	//장바구니 상세
		public CartVO getcart(CartVO cart)throws Exception{
			Connection conn= null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CartVO cartSaved = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM em_member_cart WHERE product_num=? AND mem_num=?";
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, cart.getProduct_num());
				pstmt.setInt(2, cart.getMem_num());
				//sql문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					cartSaved = new CartVO();
					cartSaved.setMem_cart_num(rs.getInt("mem_cart_num"));
					cartSaved.setProduct_num(rs.getInt("product_num"));
					cartSaved.setCart_quantity(rs.getInt("cart_quantity"));
				}
			}catch(Exception e) {
				throw new Exception();
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
			
			return cartSaved;
		}
	//장바구니 수정 (개별 상품 수량 수정)
		public void updateCart(CartVO cart)
		                        throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "UPDATE em_member_cart SET cart_quantity=? "
					+ "WHERE mem_cart_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cart.getCart_quantity());
				pstmt.setInt(2, cart.getMem_cart_num());
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//장바구니 상품 번호와 회원 번호별 수정
		public void updateCartByItem_num(CartVO cart) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "UPDATE em_member_cart SET cart_quantity=? WHERE product_num=? AND mem_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cart.getCart_quantity());
				pstmt.setInt(2, cart.getProduct_num());
				pstmt.setInt(3, cart.getMem_num());
				//sql문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
	//제품상세정보
	public ProductManageVO getItem(int item_num) throws Exception{
		ProductManageVO product = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM em_product_manage m JOIN em_product_detail d ON m.product_num = d.product_num WHERE m.product_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				product = new ProductManageVO();
				ProductDetailVO productDetail = new ProductDetailVO();
				productDetail.setProduct_stock(rs.getInt("product_stock"));
				productDetail.setProduct_name(rs.getString("product_name"));
				product.setProduct_num(rs.getInt("product_num"));
				product.setProduct_status(rs.getInt("product_status"));
				product.setProductdetailVO(productDetail);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return product;
	}
	//장바구니에서 상품 삭제
	public void deleteCart(int cart_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql="DELETE FROM em_member_cart WHERE mem_cart_num=?";
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, cart_num);
			//sql문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}
