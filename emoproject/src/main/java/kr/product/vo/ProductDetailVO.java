package kr.product.vo;

public class ProductDetailVO {
	//커밋
	private int product_num;
	private String product_name;
	private String product_title;
	private String product_info;
	private String product_photo1;
	private String product_photo2;
	private String product_origin;
	private int product_real_price;
	private int product_price;
	private int product_stock;
	private int product_discount;
	private int product_price_sales;
	
	public int getProduct_price_sales() {
		return product_price_sales;
	}
	public void setProduct_price_sales(int product_price_sales) {
		this.product_price_sales = product_price_sales;
	}
	public int getProduct_discount() {
		return product_discount;
	}
	public void setProduct_discount(int product_discount) {
		this.product_discount = product_discount;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
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
