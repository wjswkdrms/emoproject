package kr.order.vo;

import java.sql.Date;

public class OrderDetailVO {
	private int order_detail_num;//ORDER_DETAIL_NUM	NUMBER
	private int order_num;//ORDER_NUM	NUMBER
	private int product_num;//PRODUCT_NUM	NUMBER
	private String order_product_name;//ORDER_PRODUCT_NAME	VARCHAR2(60 BYTE)
	private int order_product_price;//ORDER_PRODUCT_PRICE	NUMBER(7,0)
	private int order_product_quantity;//ORDER_PRODUCT_QUANTITY	NUMBER(5,0)
	private int order_product_total; //ORDER_PRODUCT_TOTAL	NUMBER(7,0)
	
	private String mem_id;
	private MemberHomeVO memberhome;
	private Date order_date;
	
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public MemberHomeVO getMemberhome() {
		return memberhome;
	}
	public void setMemberhome(MemberHomeVO memberhome) {
		this.memberhome = memberhome;
	}
	public int getOrder_detail_num() {
		return order_detail_num;
	}
	public void setOrder_detail_num(int order_detail_num) {
		this.order_detail_num = order_detail_num;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getOrder_product_name() {
		return order_product_name;
	}
	public void setOrder_product_name(String order_product_name) {
		this.order_product_name = order_product_name;
	}
	public int getOrder_product_price() {
		return order_product_price;
	}
	public void setOrder_product_price(int order_product_price) {
		this.order_product_price = order_product_price;
	}
	public int getOrder_product_quantity() {
		return order_product_quantity;
	}
	public void setOrder_product_quantity(int order_product_quantity) {
		this.order_product_quantity = order_product_quantity;
	}
	public int getOrder_product_total() {
		return order_product_total;
	}
	public void setOrder_product_total(int order_product_total) {
		this.order_product_total = order_product_total;
	}
}
