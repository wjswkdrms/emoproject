package kr.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.order.vo.MemberHomeVO;
import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;
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
		ResultSet rs = null;
		ResultSet rs2 = null;
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
				order_num = rs.getInt(1);
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
			DBUtil.executeClose(null, pstmt7, null);
			DBUtil.executeClose(null, pstmt6, null);
			DBUtil.executeClose(null, pstmt5, null);
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(rs2, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
}
		
		