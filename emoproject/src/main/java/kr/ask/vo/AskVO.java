package kr.ask.vo;

import java.sql.Date;

public class AskVO {
	private int ask_num;
	private String ask_title;
	private String ask_content;
	private String ask_photo1;
	private Date ask_date;
	private int ask_status;
	private int mem_num;
	
	private String mem_id;

	public int getAsk_num() {
		return ask_num;
	}

	public void setAsk_num(int ask_num) {
		this.ask_num = ask_num;
	}

	public String getAsk_title() {
		return ask_title;
	}

	public void setAsk_title(String ask_title) {
		this.ask_title = ask_title;
	}

	public String getAsk_content() {
		return ask_content;
	}

	public void setAsk_content(String ask_content) {
		this.ask_content = ask_content;
	}

	public String getAsk_photo1() {
		return ask_photo1;
	}

	public void setAsk_photo1(String ask_photo1) {
		this.ask_photo1 = ask_photo1;
	}

	public Date getAsk_date() {
		return ask_date;
	}

	public void setAsk_date(Date ask_date) {
		this.ask_date = ask_date;
	}

	public int getAsk_status() {
		return ask_status;
	}

	public void setAsk_status(int ask_status) {
		this.ask_status = ask_status;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
}
