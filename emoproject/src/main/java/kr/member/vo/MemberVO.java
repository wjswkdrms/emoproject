package kr.member.vo;

import java.sql.Date;

public class MemberVO {
	private int mem_num; //회원번호
	private String id;//아이디
	private int auth;//회원등급
	private String name;//이름
	private String passwd;//비밀번호
	private String cell;//전화번호
	private String email;//이메일
	private String zipcode; //우편번호
	private String address1;//주소
	private String address2;//나머지 주소
	private String birth;//생일
	private int gender;//성별 (1:남자 2:여자)
	private int point;//보유 포인트
	private Date stop_date;//가입일
	private Date exp_date;//가입일
	private Date reg_date;//가입일
	private String reg_date_2;//가입일
	private Date mdate;//정보 수정일
	
	private int order_total_price;//누적 금액
	private int order_num;
	private int order_status;


	//비밀번호 일치 여부 체크
	public boolean isCheckedPassword(
			            String userPasswd) {
		//회원등급(auth):0탈퇴회원,1정지회원,2일반회원,9관리자
		if(auth > 1 && 
				passwd.equals(userPasswd)) {
			return true;
		}
		return false;
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
	public int getOrder_total_price() {
		return order_total_price;
	}
	
	public void setOrder_total_price(int order_total_price) {
		this.order_total_price = order_total_price;
	}
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getStop_date() {
		return stop_date;
	}
	public void setStop_date(Date stop_date) {
		this.stop_date = stop_date;
	}
	public Date getExp_date() {
		return exp_date;
	}
	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public String getReg_date_2() {
		return reg_date_2;
	}

	public void setReg_date_2(String reg_date_2) {
		this.reg_date_2 = reg_date_2;
	}
	
	
}
