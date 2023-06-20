package kr.cart.vo;

import kr.product.vo.ProductDetailVO;

public class CartVO {
	private int mem_cart_num;//장바구니 번호
	private int mem_num;//회원번호
	private int product_num;//상품번호
	private int cart_quantity;//구매 상품 수량
	private ProductDetailVO product;//상품
	private int product_Status;//상품 상태
	
	public int getProduct_Status() {
		return product_Status;
	}
	public void setProduct_Status(int product_Status) {
		this.product_Status = product_Status;
	}
	public ProductDetailVO getProduct() {
		return product;
	}
	public void setProduct(ProductDetailVO product) {
		this.product = product;
	}
	public int getMem_cart_num() {
		return mem_cart_num;
	}
	public void setMem_cart_num(int cart_num) {
		this.mem_cart_num = cart_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public int getCart_quantity() {
		return cart_quantity;
	}
	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
	}
	
	
}
