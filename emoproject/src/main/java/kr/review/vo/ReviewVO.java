package kr.review.vo;

import kr.product.vo.ProductManageVO;

public class ReviewVO {
	private int review_num;
	private int product_num;
	private int mem_num;
	private String review_title;
	private String review_content;
	private String review_photo1;
	private int review_score;
	
	private String mem_id;
	private ProductManageVO ProductManageVO;
	
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_photo1() {
		return review_photo1;
	}
	public void setReview_photo1(String review_photo1) {
		this.review_photo1 = review_photo1;
	}
	public int getReview_score() {
		return review_score;
	}
	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public ProductManageVO getProductManageVO() {
		return ProductManageVO;
	}
	public void setProductManageVO(ProductManageVO productManageVO) {
		ProductManageVO = productManageVO;
	}
	
}
