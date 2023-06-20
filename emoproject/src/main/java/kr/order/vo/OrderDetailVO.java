package kr.order.vo;

public class OrderDetailVO {
	private int order_detail_num;//ORDER_DETAIL_NUM	NUMBER
	private int order_num;//ORDER_NUM	NUMBER
	private int product_num;//PRODUCT_NUM	NUMBER
	private String order_product_name;//ORDER_PRODUCT_NAME	VARCHAR2(60 BYTE)
	private int order_product_price;//ORDER_PRODUCT_PRICE	NUMBER(7,0)
	private int order_product_quantity;//ORDER_PRODUCT_QUANTITY	NUMBER(5,0)
	private int order_product_total; //ORDER_PRODUCT_TOTAL	NUMBER(7,0)
}
