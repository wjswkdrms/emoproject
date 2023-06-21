package kr.zzim.vo;

public class ZZimVO {
	private int zzim_num;
	private int mem_num;
	private int product_num;
	
	public ZZimVO() {}
	
	//생성자 정의 
	public ZZimVO(int mem_num, int product_num) {
		this.mem_num = mem_num;
		this.product_num = product_num;
	}

	public int getZzim_num() {
		return zzim_num;
	}

	public void setZzim_num(int zzim_num) {
		this.zzim_num = zzim_num;
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
}
