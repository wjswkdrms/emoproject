package kr.product.vo;

public class ProductManageVO {
	//
	private int product_num;
	private int product_category;
	private int product_status;
	private ProductDetailVO productdetailVO;
	
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
	public ProductDetailVO getProductdetailVO() {
		return productdetailVO;
	}
	public void setProductdetailVO(ProductDetailVO productdetailVO) {
		this.productdetailVO = productdetailVO;
	}
}
