package kr.announce.vo;

import java.sql.Date;

public class AnnounceVO {
	private int ann_num;
	private int mem_num;
	private String ann_title;
	private String ann_content;
	private String ann_photo1;
	private Date ann_date;
	private String mem_id;//회원 아이디
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	public int getAnn_num() {
		return ann_num;
	}
	public void setAnn_num(int ann_num) {
		this.ann_num = ann_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getAnn_title() {
		return ann_title;
	}
	public void setAnn_title(String ann_title) {
		this.ann_title = ann_title;
	}
	public String getAnn_content() {
		return ann_content;
	}
	public void setAnn_content(String ann_content) {
		this.ann_content = ann_content;
	}
	public String getAnn_photo1() {
		return ann_photo1;
	}
	public void setAnn_photo1(String ann_photo1) {
		this.ann_photo1 = ann_photo1;
	}
	public Date getAnn_date() {
		return ann_date;
	}
	public void setAnn_date(Date ann_date) {
		this.ann_date = ann_date;
	}
	
}
