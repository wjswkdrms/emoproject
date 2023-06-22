package kr.ask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ask.vo.AnswerVO;
import kr.ask.vo.AskVO;
import kr.cart.vo.CartVO;
import kr.util.DBUtil;

public class AnswerDAO {
	// 싱글턴 패턴
	private static AnswerDAO instance = new AnswerDAO();

	public static AnswerDAO getInstance() {
		return instance;
	}

	private AnswerDAO() {
	}

	// 답변 등록
	public void insertAnswer(AnswerVO answer) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "INSERT INTO em_board_answer (answer_num,answer_content,answer_photo,mem_num,ask_num) "
					+ "VALUES (em_board_answer_seq.nextval,?,?,?,?)";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, answer.getAnswer_content());
			pstmt.setString(2, answer.getAnswer_photo());
			pstmt.setInt(3, answer.getMem_num());
			pstmt.setInt(4, answer.getAsk_num());
			// SQL문 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			// 자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 댓글 개수
	public int getAnswerCount(int ask_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT COUNT(*) FROM em_board_answer a JOIN em_member_manage m "
					+ "ON a.mem_num=m.mem_num WHERE a.ask_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, ask_num);
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			// 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return count;
	}
	
	//답변 목록
	public AnswerVO getAnswer(int ask_num) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		AnswerVO answer=null;
		
		try {
			conn=DBUtil.getConnection();
			sql="SELECT * FROM em_board_answer c JOIN em_board_ask i ON c.ask_num=i.ask_num "
					+ "WHERE i.ask_num=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ask_num);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				answer = new AnswerVO();
				answer.setAnswer_num(rs.getInt("answer_num"));
				answer.setMem_num(rs.getInt("mem_num"));
				answer.setAnswer_content(rs.getString("answer_content"));
				answer.setAnswer_photo(rs.getString("answer_photo"));
				answer.setAnswer_date(rs.getDate("answer_date"));

				AskVO ask = new AskVO();
				ask.setAsk_num(rs.getInt("ask_num"));
				ask.setAsk_content(rs.getString("ask_content"));
				ask.setAsk_photo1(rs.getString("ask_photo1"));
				ask.setAsk_title(rs.getString("ask_title"));
				answer.setAsk_vo(ask);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return answer;
	}

	// 답변 상세(댓글 수정,삭제 시 작성자 회원번호 체크 용도)
	public AnswerVO getDetailAnswer(AnswerVO answer) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AnswerVO answerDetail = null;
		String sql = null;

		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT * FROM em_board_answer WHERE answer_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, answer.getAnswer_num());
			// SQL문 실행
			rs = pstmt.executeQuery();

			if (rs.next()) {
				answerDetail=new AnswerVO();
				
				answerDetail.setAnswer_num(rs.getInt("answer_num"));
				answerDetail.setAnswer_content(rs.getString("answer_content"));
				answerDetail.setAnswer_photo(rs.getString("answer_photo"));
				answerDetail.setAnswer_date(rs.getDate("answer_date"));
				
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			// 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return answer;
	}
}
