package kr.answer.vo;

import java.sql.Date;

public class AnswerVO {
	private int answer_num;
	private int mem_num;
	private String answer_content;
	private String answer_photo;
	private Date answer_date;
	private int ask_num;
	
	private String mem_id;
	private AskVO AskVO;
	
	public int getAnswer_num() {
		return answer_num;
	}
	public void setAnswer_num(int answer_num) {
		this.answer_num = answer_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getAnswer_content() {
		return answer_content;
	}
	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
	}
	public String getAnswer_photo() {
		return answer_photo;
	}
	public void setAnswer_photo(String answer_photo) {
		this.answer_photo = answer_photo;
	}
	public Date getAnswer_date() {
		return answer_date;
	}
	public void setAnswer_date(Date answer_date) {
		this.answer_date = answer_date;
	}
	public int getAsk_num() {
		return ask_num;
	}
	public void setAsk_num(int ask_num) {
		this.ask_num = ask_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public AskVO getAskVO() {
		return AskVO;
	}
	public void setAskVO(AskVO askVO) {
		AskVO = askVO;
	}
	
	
}
