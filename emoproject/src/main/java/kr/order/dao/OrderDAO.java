package kr.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.order.vo.MemberHomeVO;
import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;
import kr.product.dao.ProductDAO;
import kr.product.vo.ProductDetailVO;
import kr.product.vo.ProductManageVO;
import kr.util.DBUtil;

public class OrderDAO {
	//싱글턴 패턴
	private static OrderDAO instance = 
			                new OrderDAO();
	public static OrderDAO getInstance() {
		return instance;
	}
	private OrderDAO() {}
	
	//주문 등록
	public void insertOrder(OrderVO order, MemberHomeVO home, List<OrderDetailVO> orderDetailList) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		PreparedStatement pstmt8 = null;
		PreparedStatement pstmt9 = null;
		
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String sql = null;
		int order_num = 0;
		int home_num = 0;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			
			//home_num값 구하기
			sql = "SELECT em_member_home_seq.nextval FROM dual";
			pstmt6 = conn.prepareStatement(sql);
			rs = pstmt6.executeQuery();
			if(rs.next()) {
				home_num = rs.getInt(1);
			}
			//home정보 저장
			sql= "INSERT INTO em_member_home (mem_home_num,mem_num,mem_home_cell,mem_home_zipcode,"
					+ "mem_home_address1,mem_home_address2,mem_home_name)"
					+ "VALUES (?,?,?,?,?,?,?)";
			pstmt7 = conn.prepareStatement(sql);
			pstmt7.setInt(1, home_num);
			pstmt7.setInt(2, home.getMem_num());
			pstmt7.setString(3, home.getMem_home_cell());
			pstmt7.setInt(4, home.getMem_home_zipcode());
			pstmt7.setString(5, home.getMem_home_address1());
			pstmt7.setString(6, home.getMem_home_address2());
			pstmt7.setString(7, "default");
			
			pstmt7.executeUpdate();
			//order_num값 구하기
			sql = "SELECT em_order_manage_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs2 = pstmt.executeQuery();
			if(rs2.next()) {
				order_num = rs2.getInt(1);
			}
			//주문정보 저장
			sql= "INSERT INTO em_order_manage (order_num,order_total_price,mem_num,mem_home_num,order_status,order_date,"
					+ "order_notice) "
					+ "VALUES (?,?,?,?,0,SYSDATE,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, order_num);
			pstmt2.setInt(2, order.getOrder_total_price());
			pstmt2.setInt(3, order.getMem_num());
			pstmt2.setInt(4, home_num);
			pstmt2.setString(5, order.getOrder_notice());
			
			pstmt2.executeUpdate();
			
			
			//주문상세정보 저장
			sql = "INSERT INTO em_order_detail (order_detail_num,order_num,product_num,order_product_name,order_product_price,order_product_quantity,order_product_total) "
					+ "VALUES(em_order_detail_seq.nextval,?,?,?,?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			for(int i = 0; i<orderDetailList.size();i++) {
				OrderDetailVO orderDetail = orderDetailList.get(i);
				//?에 데이터 바인딩
				pstmt3.setInt(1, order_num);
				pstmt3.setInt(2, orderDetail.getProduct_num());
				pstmt3.setString(3, orderDetail.getOrder_product_name());
				pstmt3.setInt(4, orderDetail.getOrder_product_price());
				pstmt3.setInt(5, orderDetail.getOrder_product_quantity());
				pstmt3.setInt(6, orderDetail.getOrder_product_total());
				
				pstmt3.addBatch();//쿼리를 메모리에 올림
				//계속 추가하면 outOfMemory발생,
				//1000개 단위로 executebatch()
				if(i%1000 == 0) {
					pstmt3.executeBatch();
				}
				
			}
			pstmt3.executeBatch();
			//회원 포인트 차감
			sql = "UPDATE em_member_detail SET mem_point=mem_point-? WHERE mem_num=?";
			pstmt8 = conn.prepareStatement(sql);
			pstmt8.setInt(1, order.getOrder_total_price());
			pstmt8.setInt(2, order.getMem_num());
			pstmt8.executeUpdate();
			
			//상품의 재고 수 차감
			sql = "UPDATE em_product_detail SET product_stock=product_stock-? WHERE product_num=?";
			pstmt4 = conn.prepareStatement(sql);
			for(int i = 0; i < orderDetailList.size();i++) {
				OrderDetailVO orderDetail = orderDetailList.get(i);
				
				pstmt4.setInt(1, orderDetail.getOrder_product_quantity());
				pstmt4.setInt(2, orderDetail.getProduct_num());
				
				pstmt4.addBatch();//쿼리를 메모리에 올림
				//계속 추가하면 outOfMemory발생,
				//1000개 단위로 excutebatch()
				if(i%1000 == 0) {
					pstmt4.executeBatch();
				}
				
			}//end of for
			pstmt4.executeBatch();			

			//장바구니에서 주문 상품 삭제
			sql="DELETE FROM em_member_cart WHERE mem_num=?";
			pstmt5 = conn.prepareStatement(sql);
			pstmt5.setInt(1, order.getMem_num());
			pstmt5.executeUpdate();
			
			//모든 sql문이 정상 수행 되었을 경우
			conn.commit();
		}catch(Exception e) {
			//하나라도 sql문이 실패할 경우
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt9, null);
			DBUtil.executeClose(null, pstmt8, null);
			DBUtil.executeClose(null, pstmt7, null);
			DBUtil.executeClose(null, pstmt6, null);
			DBUtil.executeClose(null, pstmt5, null);
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(rs3, pstmt3, null);
			DBUtil.executeClose(rs2, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	public void updateStatus(List<OrderDetailVO> list) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE em_product_manage SET product_status=1 WHERE product_num=? AND (SELECT product_stock FROM em_product_detail where product_num=?)=0";
			pstmt = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				int product_num = list.get(i).getProduct_num();
				pstmt.setInt(1, product_num);
				pstmt.setInt(2, product_num);
				pstmt.addBatch();
				if(i%1000 == 0) {
					pstmt.executeBatch();
				}
			}
			pstmt.executeBatch();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	public List<MemberHomeVO> getHomeList(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<MemberHomeVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "select * FROM(select mem_num,mem_home_address1,mem_home_address2,mem_home_name,mem_home_num,mem_home_zipcode,mem_home_cell, RANK() OVER(PARTITION by mem_home_zipcode order by mem_home_num DESC) r FROM em_member_home WHERE mem_num=?) WHERE r = 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			list = new ArrayList<MemberHomeVO>();
			while(rs.next()) {
				MemberHomeVO home = new MemberHomeVO();
				home.setMem_home_address1(rs.getString("mem_home_address1"));
				home.setMem_home_address2(rs.getString("mem_home_address2"));
				home.setMem_home_cell(rs.getString("mem_home_cell"));
				home.setMem_home_name(rs.getString("mem_home_name"));
				home.setMem_home_num(rs.getInt("mem_home_num"));
				home.setMem_home_zipcode(rs.getInt("mem_home_zipcode"));
				home.setMem_num(mem_num);
				list.add(home);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	public MemberHomeVO getHome(int home_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MemberHomeVO home = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM em_member_home WHERE mem_home_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, home_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				home = new MemberHomeVO();
				home.setMem_home_address1(rs.getString("mem_home_address1"));
				home.setMem_home_address2(rs.getString("mem_home_address2"));
				home.setMem_home_cell(rs.getString("mem_home_cell"));
				home.setMem_home_name(rs.getString("mem_home_name"));
				home.setMem_home_num(rs.getInt("mem_home_num"));
				home.setMem_home_zipcode(rs.getInt("mem_home_zipcode"));
				home.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return home;
	}
	public void updateMemberAddress(MemberHomeVO home, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE em_member_detail SET mem_cell=?, mem_zipcode=?, mem_address1=?, mem_address2=? WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, home.getMem_home_cell());
			pstmt.setInt(2, home.getMem_home_zipcode());
			pstmt.setString(3, home.getMem_home_address1());
			pstmt.setString(4, home.getMem_home_address2());
			pstmt.setInt(5, mem_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	public void insertHome(MemberHomeVO home)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO em_member_home (mem_home_num,mem_num,mem_home_cell,mem_home_zipcode,"
					+ "mem_home_address1,mem_home_address2,mem_home_name)"
					+ "VALUES (em_member_home_seq.nextval,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, home.getMem_num());
			pstmt.setString(2, home.getMem_home_cell());
			pstmt.setInt(3, home.getMem_home_zipcode());
			pstmt.setString(4, home.getMem_home_address1());
			pstmt.setString(5, home.getMem_home_address2());
			pstmt.setString(6, home.getMem_home_name());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
		
		