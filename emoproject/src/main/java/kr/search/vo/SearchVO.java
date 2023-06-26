package kr.search.vo;

public class SearchVO {
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
	
	public int getCart_count() {
		return cart_count;
	}
	public void setCart_count(int cart_count) {
		this.cart_count = cart_count;
	}
	public int getOrder_count() {
		return order_count;
	}
	public void setOrder_count(int order_count) {
		this.order_count = order_count;
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
	
	
}
