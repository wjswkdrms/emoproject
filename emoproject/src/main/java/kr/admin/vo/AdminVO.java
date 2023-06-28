package kr.admin.vo;

import java.sql.Date;

public class AdminVO {
	private int product_num;		//상품 번호
	private int product_category;	//카테고리 번호 1:농산물, 2: 수산물, 3: 정육, 4:유제품, 5: 음료, 6: 베이커리
	private int product_status;		//상품 상태 0: 판매 정지 1:재고 없음, 2:판매 가능
	private String product_name;	//이름
	private String product_title;	//제목
	private String product_info;	//정보
	private String product_photo1;	//사진1
	private String product_photo2;	//사진2
	private String product_origin;	//원산지
	private int product_real_price;	//원가
	private int product_price;		//정가
	private int product_stock;		//재고
	private int order_count;
	private int cart_count;
	private int product_discount;
	private int product_price_sales;
	private int mem_num; //회원번호
	private String mem_id;//아이디
	private int mem_auth;//회원등급
	private String mem_name;//이름
	private String mem_passwd;//비밀번호
	private String mem_cell;//전화번호
	private String mem_email;//이메일
	private String mem_zipcode; //우편번호
	private String mem_address1;//주소
	private String mem_address2;//나머지 주소
	private String mem_birth;//생일
	private int mem_gender;//성별 (1:남자 2:여자)
	private int mem_point;//보유 포인트
	private Date mem_stop_date;//정지날
	private Date mem_exp_date;//탈퇴날
	private Date mem_reg_date;//가입일
	private Date mem_modify_date;//정보 수정일
	private int order_total_price;//누적 금액
	private int order_num;
	private int order_status;
	private int order_manage_num;
	private Date em_today;
	private String order_product_name;
	private int product_total_price;
	private int product_sales_quantity;
	private int product_total_profit;
	private int order_product_price;
	private int order_product_total;
	private int order_product_quantity;
	private Date order_date;
	
	
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
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
	public int getOrder_product_price() {
		return order_product_price;
	}
	public void setOrder_product_price(int order_product_price) {
		this.order_product_price = order_product_price;
	}
	public int getProduct_total_profit() {
		return product_total_profit;
	}
	public void setProduct_total_profit(int product_total_profit) {
		this.product_total_profit = product_total_profit;
	}
	public int getProduct_sales_quantity() {
		return product_sales_quantity;
	}
	public void setProduct_sales_quantity(int product_sales_quantity) {
		this.product_sales_quantity = product_sales_quantity;
	}
	public int getProduct_total_price() {
		return product_total_price;
	}
	public void setProduct_total_price(int product_total_price) {
		this.product_total_price = product_total_price;
	}
	public String getOrder_product_name() {
		return order_product_name;
	}
	public void setOrder_product_name(String order_product_name) {
		this.order_product_name = order_product_name;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public int getProduct_category() {
		return product_category;
	}
	public void setProduct_category(int product_category) {
		this.product_category = product_category;
	}
	public int getProduct_status() {
		return product_status;
	}
	public void setProduct_status(int product_status) {
		this.product_status = product_status;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_title() {
		return product_title;
	}
	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}
	public String getProduct_info() {
		return product_info;
	}
	public void setProduct_info(String product_info) {
		this.product_info = product_info;
	}
	public String getProduct_photo1() {
		return product_photo1;
	}
	public void setProduct_photo1(String product_photo1) {
		this.product_photo1 = product_photo1;
	}
	public String getProduct_photo2() {
		return product_photo2;
	}
	public void setProduct_photo2(String product_photo2) {
		this.product_photo2 = product_photo2;
	}
	public String getProduct_origin() {
		return product_origin;
	}
	public void setProduct_origin(String product_origin) {
		this.product_origin = product_origin;
	}
	public int getProduct_real_price() {
		return product_real_price;
	}
	public void setProduct_real_price(int product_real_price) {
		this.product_real_price = product_real_price;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	public int getOrder_count() {
		return order_count;
	}
	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}
	public int getCart_count() {
		return cart_count;
	}
	public void setCart_count(int cart_count) {
		this.cart_count = cart_count;
	}
	public int getProduct_discount() {
		return product_discount;
	}
	public void setProduct_discount(int product_discount) {
		this.product_discount = product_discount;
	}
	public int getProduct_price_sales() {
		return product_price_sales;
	}
	public void setProduct_price_sales(int product_price_sales) {
		this.product_price_sales = product_price_sales;
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
	public int getMem_auth() {
		return mem_auth;
	}
	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_passwd() {
		return mem_passwd;
	}
	public void setMem_passwd(String mem_passwd) {
		this.mem_passwd = mem_passwd;
	}
	public String getMem_cell() {
		return mem_cell;
	}
	public void setMem_cell(String mem_cell) {
		this.mem_cell = mem_cell;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_zipcode() {
		return mem_zipcode;
	}
	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}
	public String getMem_address1() {
		return mem_address1;
	}
	public void setMem_address1(String mem_address1) {
		this.mem_address1 = mem_address1;
	}
	public String getMem_address2() {
		return mem_address2;
	}
	public void setMem_address2(String mem_address2) {
		this.mem_address2 = mem_address2;
	}
	public String getMem_birth() {
		return mem_birth;
	}
	public void setMem_birth(String mem_birth) {
		this.mem_birth = mem_birth;
	}
	public int getMem_gender() {
		return mem_gender;
	}
	public void setMem_gender(int mem_gender) {
		this.mem_gender = mem_gender;
	}
	public int getMem_point() {
		return mem_point;
	}
	public void setMem_point(int mem_point) {
		this.mem_point = mem_point;
	}
	public Date getMem_stop_date() {
		return mem_stop_date;
	}
	public void setMem_stop_date(Date mem_stop_date) {
		this.mem_stop_date = mem_stop_date;
	}
	public Date getMem_exp_date() {
		return mem_exp_date;
	}
	public void setMem_exp_date(Date mem_exp_date) {
		this.mem_exp_date = mem_exp_date;
	}
	public Date getMem_reg_date() {
		return mem_reg_date;
	}
	public void setMem_reg_date(Date mem_reg_date) {
		this.mem_reg_date = mem_reg_date;
	}
	public Date getMem_modify_date() {
		return mem_modify_date;
	}
	public void setMem_modify_date(Date mem_modify_date) {
		this.mem_modify_date = mem_modify_date;
	}
	public int getOrder_total_price() {
		return order_total_price;
	}
	public void setOrder_total_price(int order_total_price) {
		this.order_total_price = order_total_price;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public int getOrder_manage_num() {
		return order_manage_num;
	}
	public void setOrder_manage_num(int order_manage_num) {
		this.order_manage_num = order_manage_num;
	}
	public Date getEm_today() {
		return em_today;
	}
	public void setEm_today(Date em_today) {
		this.em_today = em_today;
	}
	
	
}
