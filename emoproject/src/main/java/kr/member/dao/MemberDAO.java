package kr.member.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ask.vo.AskVO;
import kr.member.vo.MemberVO;
import kr.util.DBUtil;
import kr.zzim.vo.ZZimVO;

public class MemberDAO {
	//싱글턴 패턴
		private static MemberDAO instance = 
				            new MemberDAO();
		public static MemberDAO getInstance() {
			return instance;
		}
		private MemberDAO() {}
		
		//회원정보 저장
		public void insertMember(MemberVO member)
		                         throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			ResultSet rs = null;
			String sql = null;
			int num = 0; //�떆���뒪 踰덊샇 ���옣
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//�삤�넗 而ㅻ컠 �빐�젣
				conn.setAutoCommit(false);
				
				//�쉶�썝踰덊샇(mem_num) 援ы븯湲�
				sql = "SELECT em_member_seq.nextval FROM dual";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					num = rs.getInt(1);
				}
				
				//zmember �뀒�씠釉붿뿉 �뜲�씠�꽣 ���옣
				sql = "INSERT INTO em_member_manage (mem_num,mem_id,mem_auth,mem_reg_date)"
					+ "VALUES (?,?,2,SYSDATE)";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, num);//�떆���뒪
				pstmt2.setString(2, member.getId());//id
				pstmt2.executeUpdate();
				
				//zmember_detail �뀒�씠釉붿뿉 �뜲�씠�꽣 ���옣
				sql = "INSERT INTO em_member_detail (mem_num,"
					+ "mem_name,mem_passwd,mem_cell,mem_email,mem_zipcode,"
					+ "mem_address1,mem_address2,mem_birth,mem_gender,mem_point) VALUES (?,?,?,?,?,?,?,?,?,?,990000)";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1, num);
				pstmt3.setString(2, member.getName());
				pstmt3.setString(3, member.getPasswd());
				pstmt3.setString(4, member.getCell());
				pstmt3.setString(5, member.getEmail());
				pstmt3.setString(6, member.getZipcode());
				pstmt3.setString(7, member.getAddress1());
				pstmt3.setString(8, member.getAddress2());
				pstmt3.setString(9, member.getBirth());
				pstmt3.setInt(10, member.getGender());
				pstmt3.executeUpdate();
				
				//SQL臾몄쓣 �떎�뻾�빐�꽌 紐⑤몢 �꽦怨듯븯硫� commit
				conn.commit();
			}catch(Exception e) {
				//SQL臾몄씠 �븯�굹�씪�룄 �떎�뙣�븯硫� rollback
				conn.rollback();
				throw new Exception(e);
			}finally {
				//�옄�썝�젙由�
				DBUtil.executeClose(null, pstmt3, null);
				DBUtil.executeClose(null, pstmt2, null);
				DBUtil.executeClose(rs, pstmt, conn);
			}
			
		}
		//ID 중복체크
		public MemberVO checkMember(String id)
		                      throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			
			try {
				//而ㅻ꽖�뀡��濡쒕��꽣 而ㅻ꽖�뀡�쓣 �븷�떦
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM em_member_manage m LEFT OUTER JOIN "
					+ "em_member_detail d ON m.mem_num = d.mem_num "
					+ "WHERE m.mem_id=?";
				//PreparedStatement 媛앹껜 �깮�꽦
				pstmt = conn.prepareStatement(sql);
				//?�뿉 �뜲�씠�꽣 諛붿씤�뵫
				pstmt.setString(1, id);
				//SQL臾� �떎�뻾
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new MemberVO();
					member.setMem_num(
							rs.getInt("mem_num"));
					member.setId(
							rs.getString("mem_id"));
					member.setAuth(
							   rs.getInt("mem_auth"));
					member.setPasswd(
						  rs.getString("mem_passwd"));
					member.setEmail(
							rs.getString("mem_email"));
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//�옄�썝�젙由�
				DBUtil.executeClose(rs, pstmt, conn);
			}	
			return member;
		}
		
		//email 중복체크 수정필요
		public MemberVO checkMemberEmail(String email)
		                      throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			
			try {
				//而ㅻ꽖�뀡��濡쒕��꽣 而ㅻ꽖�뀡�쓣 �븷�떦
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM em_member_manage m LEFT OUTER JOIN "
					+ "em_member_detail d ON m.mem_num = d.mem_num "
					+ "WHERE m.mem_email=?";
				//PreparedStatement 媛앹껜 �깮�꽦
				pstmt = conn.prepareStatement(sql);
				//?�뿉 �뜲�씠�꽣 諛붿씤�뵫
				pstmt.setString(1, email);
				//SQL臾� �떎�뻾
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new MemberVO();
					member.setMem_num(
							rs.getInt("mem_num"));
					member.setId(
							rs.getString("mem_id"));
					member.setAuth(
							   rs.getInt("mem_auth"));
					member.setPasswd(
						  rs.getString("mem_passwd"));
					member.setEmail(
							rs.getString("mem_email"));
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//�옄�썝�젙由�
				DBUtil.executeClose(rs, pstmt, conn);
			}	
			return member;
		}
		
		
		//ID 찾기
		public MemberVO checkid(String name,String email)
		                      throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT manage.mem_id FROM EM_MEMBER_MANAGE manage "
						+ "INNER JOIN EM_MEMBER_DETAIL detail ON manage.mem_num = detail.mem_num "
						+ "WHERE detail.mem_name = ? AND detail.mem_email = ?";
				pstmt = conn.prepareStatement(sql);
				//입력받은 데이터(이름,이메일) 바인딩
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new MemberVO();
					member.setId(
							rs.getString("mem_id"));
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//�옄�썝�젙由�
				DBUtil.executeClose(rs, pstmt, conn);
			}	
			return member;
		}
		//비밀번호 찾기
		public MemberVO checkpw(String id,String name,String email)
		                      throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT detail.mem_passwd FROM EM_MEMBER_DETAIL detail "
						+ "INNER JOIN EM_MEMBER_MANAGE manage ON detail.mem_num = manage.mem_num "
						+ "WHERE manage.mem_id = ? AND detail.mem_name = ? AND detail.mem_email = ?";
				pstmt = conn.prepareStatement(sql);
				//입력받은 데이터(이름,이메일) 바인딩
				pstmt.setString(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, email);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new MemberVO();
					member.setPasswd(
							rs.getString("mem_passwd"));
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//�옄�썝�젙由�
				DBUtil.executeClose(rs, pstmt, conn);
			}	
			return member;
		}
		
		
		//아이디 비밀번호가 일치하는지 
		public void MemberOut(int mem_num)
                throws Exception{
			Connection conn = null;
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			PreparedStatement pstmt4 = null;
			PreparedStatement pstmt5 = null;
			PreparedStatement pstmt6 = null;
			PreparedStatement pstmt7 = null;
			PreparedStatement pstmt8 = null;
			
			ResultSet rs = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//auto commit 해제
				conn.setAutoCommit(false);
			
				//em_member_manage 이블의 auth(회원상태)를 0(탈퇴), exp_date(탈퇴일)를 입력
				sql = "UPDATE em_member_manage SET mem_auth=0 ,mem_exp_date=SYSDATE WHERE mem_num=?";
				pstmt1 = conn.prepareStatement(sql);
				pstmt1.setInt(1, mem_num);
				pstmt1.executeUpdate();
				System.out.println("1");
				//em_memer_detail 테이블의 모든 데이터 삭제
				sql = "DELETE FROM em_member_detail WHERE mem_num=?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, mem_num);
				pstmt2.executeUpdate();System.out.println("2");
				//em_member_home 테이블의 모든 데이터 삭제
				sql = "DELETE FROM em_member_home WHERE mem_num=?";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1, mem_num);
				pstmt3.executeUpdate();System.out.println("3");
				//em_member_zzim 테이블의 모든 데이터 삭제
				sql = "DELETE FROM em_member_zzim WHERE mem_num=?";
				pstmt4 = conn.prepareStatement(sql);
				pstmt4.setInt(1, mem_num);
				pstmt4.executeUpdate();System.out.println("4");
				//em_cart 테이블의 모든 데이터 삭제
				sql = "DELETE FROM em_member_cart WHERE mem_num=?";
				pstmt5 = conn.prepareStatement(sql);
				pstmt5.setInt(1, mem_num);
				pstmt5.executeUpdate();System.out.println("5");
				//em_review 테이블의 모든 데이터 삭제
				sql = "DELETE FROM em_review WHERE mem_num=?";
				pstmt6 = conn.prepareStatement(sql);
				pstmt6.setInt(1, mem_num);
				pstmt6.executeUpdate();System.out.println("6");
				//em_board_answer 테이블의 모든 데이터 삭제
				sql = "DELETE FROM em_board_answer WHERE ask_num IN (SELECT ask_num FROM em_board_ask WHERE mem_num=?)";
				pstmt7 = conn.prepareStatement(sql);
				pstmt7.setInt(1, mem_num);
				pstmt7.executeUpdate();System.out.println("7");
				//em_board_ask 테이블의 모든 데이터 삭제, 하기전에 위 sql문 선행 필수
				sql = "DELETE FROM em_board_ask WHERE mem_num=?";
				pstmt8 = conn.prepareStatement(sql);
				pstmt8.setInt(1, mem_num);
				pstmt8.executeUpdate();System.out.println("8");
				
				//모든 SQL문의 실행이 성공하면 커밋
				conn.commit();
			}catch(Exception e) {
				//SQL문이 하나라도 실패하면 롤백
				conn.rollback();
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt8, null);
				DBUtil.executeClose(null, pstmt7, null);
				DBUtil.executeClose(null, pstmt6, null);
				DBUtil.executeClose(null, pstmt5, null);
				DBUtil.executeClose(null, pstmt4, null);
				DBUtil.executeClose(null, pstmt3, null);
				DBUtil.executeClose(null, pstmt2, null);
				DBUtil.executeClose(null, pstmt1, conn);
			}
		}
		//찜목록 카운트
		public int getListBoardCount(int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int cnt = 0;
			int count = 0;
			try {
				conn = DBUtil.getConnection();
	
	
				sql = "SELECT COUNT(*) FROM (SELECT detail.product_num, detail.product_title, " 
				+ "detail.product_photo1, detail.product_price, manage.product_status " 
				+ "FROM em_product_detail detail INNER JOIN em_product_manage manage ON detail.product_num "
				+ "= manage.product_num) data INNER JOIN em_member_zzim zzim ON data.product_num = zzim.product_num "
				+ "WHERE zzim.mem_num = ? ORDER BY zzim.zzim_num DESC";
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(++cnt,mem_num);
				
				
				//SQL문 실행
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
		
		//찜 목록
		public List<ZZimVO> getListBoard(int start, int end, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ZZimVO> list = null;
			String sql = null;
			int cnt = 0;
			
			try {
				conn = DBUtil.getConnection();
				
				sql = "SELECT rnum, product_num, product_title, product_photo1, product_price, product_status "
					+ "FROM (SELECT a.*, rownum rnum FROM (SELECT zzim.product_num, zzim.zzim_num, data.product_title, "
					+ "data.product_photo1, data.product_price, data.product_status  FROM (SELECT detail.product_num, "
					+ "detail.product_title, detail.product_photo1, detail.product_price, manage.product_status "
					+ "FROM em_product_detail detail INNER JOIN em_product_manage manage ON detail.product_num = manage.product_num) data "
					+ "INNER JOIN em_member_zzim zzim ON data.product_num = zzim.product_num  WHERE zzim.mem_num = ? "
					+ "ORDER BY zzim.zzim_num DESC) a) WHERE rnum>=? AND rnum<=?";
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(++cnt,mem_num);
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				
				//SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<ZZimVO>();
				String price = "";
				while(rs.next()) {
					ZZimVO zzim = new ZZimVO();
					zzim.setProduct_num(rs.getInt("product_num"));
					zzim.setProduct_photo1(rs.getString("product_photo1"));
					zzim.setProduct_status(rs.getInt("product_status"));
					
					//금액 천단위 , 처리
					price = rs.getInt("product_price") + ""; 
					price = price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
					zzim.setProduct_price(price);
					//문자열의 길이가 90 이상이면 잘라내고 ...처리
					if((rs.getString("product_title")).length() > 90) {
						String[] arr2 =  new String[(rs.getString("product_title")).length()];
						String str2 = "";
						arr2 = rs.getString("product_title").split("");
						for(int i=0; i<90; i++) {
							str2 += arr2[i];
						}
						str2 += "...";
						zzim.setProduct_title(str2);
					}else {
						zzim.setProduct_title(rs.getString("product_title"));
					}
					list.add(zzim);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		
		//주문상세 카운트
		public int getOrderListBoardCount(int mem_num, String order_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int cnt = 0;
			int count = 0;
			try {
				conn = DBUtil.getConnection();
	
	
				sql = "SELECT count(*) FROM(SELECT de2.product_photo1, data.order_status, data.product_num, data.order_product_name, data.order_product_total, data.order_product_quantity "
						+ "FROM (SELECT ma.order_num, ma.order_status, de.product_num, de.order_product_name, de.order_product_total, de.order_product_quantity FROM em_order_detail de "
						+ "INNER JOIN em_order_manage ma ON de.order_num = ma.order_num WHERE ma.order_num IN (SELECT order_num FROM em_order_manage WHERE mem_num = ? AND order_num=?) ORDER BY ma.order_num DESC) data "
						+ "INNER JOIN em_product_detail de2 ON data.product_num = de2.product_num ORDER BY order_num DESC)";
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(++cnt,mem_num);
				pstmt.setString(++cnt,order_num);
				
				
				//SQL문 실행
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
		
		
		
		//주문상세 목록
		public List<ZZimVO> getOrderListBoard(int start, int end, int mem_num, String order_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ZZimVO> list = null;
			String sql = null;
			int cnt = 0;
			
			try {
				conn = DBUtil.getConnection();
				
				sql = "SELECT b.* FROM(SELECT a.*, rownum rnum FROM(SELECT de2.product_photo1, data.order_num, data.order_date, data.order_status, data.product_num, data.order_product_name, data.order_product_total, data.order_product_quantity "
						+ "FROM (SELECT ma.order_date, ma.order_num, ma.order_status, de.product_num, de.order_product_name, de.order_product_total, de.order_product_quantity FROM em_order_detail de "
						+ "INNER JOIN em_order_manage ma ON de.order_num = ma.order_num WHERE ma.order_num IN (SELECT order_num FROM em_order_manage WHERE mem_num = ?) ORDER BY ma.order_num DESC) data "
						+ "INNER JOIN em_product_detail de2 ON data.product_num = de2.product_num ORDER BY order_num DESC) a) b WHERE rnum>=? AND rnum<=? AND order_num=?";
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(++cnt,mem_num);
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				pstmt.setString(++cnt,order_num);
				
				//SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<ZZimVO>();
				String price = "";
				while(rs.next()) {
					ZZimVO zzim = new ZZimVO();
					zzim.setOrder_date(rs.getString("order_date"));
					zzim.setProduct_num(rs.getInt("product_num"));
					zzim.setProduct_photo1(rs.getString("product_photo1"));
					zzim.setProduct_quantity(rs.getInt("order_product_quantity"));
					zzim.setOrder_num(rs.getInt("order_num"));
					zzim.setProduct_status(rs.getInt("order_status"));
					//금액 천단위 , 처리
					price = rs.getInt("order_product_total") + ""; 
					price = price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
					zzim.setProduct_price(price);
					//문자열의 길이가 90 이상이면 잘라내고 ...처리
					if((rs.getString("order_product_name")).length() > 90) {
						String[] arr =  new String[(rs.getString("order_product_name")).length()];
						String str = "";
						arr = rs.getString("order_product_name").split("");
						for(int i=0; i<90; i++) {
							str += arr[i];
						}
						str += "...";
						zzim.setProduct_title(str);
					}else {
						zzim.setProduct_title(rs.getString("order_product_name"));
					}
					list.add(zzim);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		
		//주문상세 카운트 -- 카트용
		public int getOrderListBoardCount2(int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int cnt = 0;
			int count = 0;
			try {
				conn = DBUtil.getConnection();
	
	
				sql = "SELECT count(*) FROM(SELECT de2.product_photo1, data.order_status, data.product_num, data.order_product_name, data.order_product_total, data.order_product_quantity "
						+ "FROM (SELECT ma.order_num, ma.order_status, de.product_num, de.order_product_name, de.order_product_total, de.order_product_quantity FROM em_order_detail de "
						+ "INNER JOIN em_order_manage ma ON de.order_num = ma.order_num WHERE ma.order_num IN (SELECT order_num FROM em_order_manage WHERE mem_num = ?) ORDER BY ma.order_num DESC) data "
						+ "INNER JOIN em_product_detail de2 ON data.product_num = de2.product_num ORDER BY order_num DESC)";
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(++cnt,mem_num);
				
				
				//SQL문 실행
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
		
		
		
		//주문상세 목록 -- 카트용
		public List<ZZimVO> getOrderListBoard2(int start, int end, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ZZimVO> list = null;
			String sql = null;
			int cnt = 0;
			
			try {
				conn = DBUtil.getConnection();
				
				sql = "SELECT b.* FROM(SELECT a.*, rownum rnum FROM(SELECT de2.product_photo1, data.order_num, data.order_date, data.order_status, data.product_num, data.order_product_name, data.order_product_total, data.order_product_quantity "
						+ "FROM (SELECT ma.order_date, ma.order_num, ma.order_status, de.product_num, de.order_product_name, de.order_product_total, de.order_product_quantity FROM em_order_detail de "
						+ "INNER JOIN em_order_manage ma ON de.order_num = ma.order_num WHERE ma.order_num IN (SELECT order_num FROM em_order_manage WHERE mem_num = ?) ORDER BY ma.order_num DESC) data "
						+ "INNER JOIN em_product_detail de2 ON data.product_num = de2.product_num ORDER BY order_num DESC) a) b WHERE rnum>=? AND rnum<=?";
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(++cnt,mem_num);
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				
				//SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<ZZimVO>();
				String price = "";
				while(rs.next()) {
					ZZimVO zzim = new ZZimVO();
					zzim.setOrder_date(rs.getString("order_date"));
					zzim.setProduct_num(rs.getInt("product_num"));
					zzim.setProduct_photo1(rs.getString("product_photo1"));
					zzim.setProduct_quantity(rs.getInt("order_product_quantity"));
					zzim.setOrder_num(rs.getInt("order_num"));
					zzim.setProduct_status(rs.getInt("order_status"));
					//금액 천단위 , 처리
					price = rs.getInt("order_product_total") + ""; 
					price = price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
					zzim.setProduct_price(price);
					//문자열의 길이가 90 이상이면 잘라내고 ...처리
					if((rs.getString("order_product_name")).length() > 90) {
						String[] arr =  new String[(rs.getString("order_product_name")).length()];
						String str = "";
						arr = rs.getString("order_product_name").split("");
						for(int i=0; i<90; i++) {
							str += arr[i];
						}
						str += "...";
						zzim.setProduct_title(str);
					}else {
						zzim.setProduct_title(rs.getString("order_product_name"));
					}
					list.add(zzim);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		
		//주문 목록 카운트
		public int getOrderListBoardAllCount(int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int cnt = 0;
			int count = 0;
			try {
				conn = DBUtil.getConnection();
	
	
				sql = "SELECT COUNT(*) FROM (SELECT order_num, substr(XMLAGG(XMLELEMENT(nm, ', ',ORDER_PRODUCT_NAME)).EXTRACT('//text()').GETSTRINGVAL(),2) AS product_names "
						+ "FROM (SELECT * FROM EM_ORDER_MANAGE LEFT INNER JOIN em_order_detail USING (order_num) WHERE mem_num=?) GROUP BY ORDER_NUM ORDER BY order_num DESC) a INNER JOIN em_order_manage b ON a.order_num=b.order_num";
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(++cnt,mem_num);
				
				
				//SQL문 실행
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
		
		//주문 목록
		public List<ZZimVO> getOrderListBoardAll(int start, int end, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ZZimVO> list = null;
			String sql = null;
			int cnt = 0;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT b.* FROM(SELECT a.*, rownum rnum FROM(SELECT * FROM (SELECT order_num, substr(XMLAGG(XMLELEMENT(nm, ', ',ORDER_PRODUCT_NAME)).EXTRACT('//text()').GETSTRINGVAL(),2) AS product_names "
						+ "FROM (SELECT * FROM EM_ORDER_MANAGE LEFT INNER JOIN em_order_detail USING (order_num) WHERE mem_num=?) GROUP BY ORDER_NUM ORDER BY order_num DESC) a INNER JOIN em_order_manage b ON a.order_num=b.order_num)a) b WHERE rnum>=? AND rnum<=?";
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(++cnt,mem_num);
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				
				//SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<ZZimVO>();
				String price ="";
				while(rs.next()) {
					ZZimVO zzim = new ZZimVO();
					zzim.setOrder_num(rs.getInt("order_num"));
					zzim.setOrder_date(rs.getString("order_date"));
					zzim.setOrder_status(rs.getInt("order_status"));
					//금액 천단위 , 처리
					price = rs.getInt("order_total_price") + ""; 
					price = price.replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
					zzim.setProduct_price(price);
					//문자열의 길이가 90 이상이면 잘라내고 ...처리
					if((rs.getString("product_names")).length() > 90) {
						String[] arr =  new String[(rs.getString("product_names")).length()];
						String str = "";
						arr = rs.getString("product_names").split("");
						for(int i=0; i<90; i++) {
							str += arr[i];
						}
						str += "...";
						zzim.setProduct_name(str);
					}else {
						zzim.setProduct_name(rs.getString("product_names"));
					}
					list.add(zzim);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		//문의 목록 카운트
		public int getQuestListCount(int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int cnt = 0;
			int count = 0;
			try {
				conn = DBUtil.getConnection();
	
	
				sql = "SELECT COUNT(*) FROM (SELECT ask_num, ask_title, ask_content, ask_date, ask_status "
						+ "FROM em_board_ask WHERE mem_num = ? ORDER BY ask_num DESC)";
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(++cnt,mem_num);
				
				
				//SQL문 실행
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
		
		//문의 목록
		public List<AskVO> getQuestList(int start, int end, int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<AskVO> list = null;
			String sql = null;
			int cnt = 0;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT b.* FROM (SELECT a.*, rownum rnum FROM (SELECT ask_num, ask_title, ask_content, ask_date, ask_status "
						+ "FROM em_board_ask WHERE mem_num = ? ORDER BY ask_num DESC) a ) b WHERE rnum>=? AND rnum<=?";
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(++cnt,mem_num);
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				
				//SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<AskVO>();
				while(rs.next()) {
					AskVO ask = new AskVO();
					ask.setAsk_num(rs.getInt("ask_num"));
					ask.setAsk_title(rs.getString("ask_title"));
					ask.setAsk_date_2(rs.getString("ask_date"));
					ask.setAsk_status(rs.getInt("ask_status"));
					
					

					//문자열의 길이가 90 이상이면 잘라내고 ...처리
					if((rs.getString("ask_content")).length() > 90) {
						String[] arr =  new String[(rs.getString("ask_content")).length()];
						String str = "";
						arr = rs.getString("ask_content").split("");
						for(int i=0; i<90; i++) {
							str += arr[i];
						}
						str += "...";
						ask.setAsk_content(str);
					}else {
						ask.setAsk_content(rs.getString("ask_content"));
					}
					list.add(ask);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
}


