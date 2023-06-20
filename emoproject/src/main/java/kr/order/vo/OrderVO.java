package kr.order.vo;

import java.sql.Date;

public class OrderVO {
	private int order_num;//ORDER_NUM	
	private int mem_num;//MEM_NUM	
	private int mem_home_num;//MEM_HOME_NUM	
	private int order_total_price;//ORDER_TOTAL_PRICE	
	private int order_status;//ORDER_STATUS	
	private Date order_date;//ORDER_DATE	
	private String order_notice;//ORDER_NOTICE	
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getMem_home_num() {
		return mem_home_num;
	}
	public void setMem_home_num(int mem_home_num) {
		this.mem_home_num = mem_home_num;
	}
	public int getOrder_total_price() {
		return order_total_price;
	}
	public void setOrder_total_price(int order_total_price) {
		this.order_total_price = order_total_price;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getOrder_notice() {
		return order_notice;
	}
	public void setOrder_notice(String order_notice) {
		this.order_notice = order_notice;
	}
}
