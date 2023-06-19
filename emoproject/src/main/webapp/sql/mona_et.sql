--product
create table em_product_manage(
	product_num number(5),					-- 상품 식별 번호
	product_category number(2) not null,	-- 상품 카테고리
	product_status number not null,			-- 상품 (판매)상태
	constraint em_product_manage_pk primary key (product_num)
);
create sequence em_product_manage_seq;

create table em_product_detail(
	product_num number(5),					-- 상품 식별 번호
	product_name varchar2(60) not null,		-- 상품 이름
	product_title varchar2(100) not null,	-- 상품 타이틀
	product_info varchar2(1000) not null,	-- 상품 정보
	product_photo1 varchar2(100) not null,	-- 상품 사진1
	product_photo2 varchar2(100) not null,	-- 상품 사진 2
	product_origin varchar2(60) not null,	-- 상품 원산지
	product_real_price number(7) not null,	-- 상품 원가
	product_price number(7) not null,		-- 상품 정가
	product_stock number(7) not null,		-- 상품 재고
	constraint em_product_detail_pk primary key (product_num),
	constraint em_product_detail_fk foreign key (product_num) references em_product_manage (product_num)
);


--order
create table em_order_manage(
	order_num number(5),					-- 주문 식별 번호
	mem_num number,							-- 주문한 회원 번호
	mem_home_num number,					-- 배달 주소 식별
	order_total_price number(10) not null,	-- 총 구매 금액
	order_status number not null,			-- 배달 상태
	order_date date default sysdate not null,	-- 주문 날짜
	order_notice varchar2(100),				-- 배송 요청 사항
	constraint em_order_manage_pk primary key (order_num),
	constraint em_order_manage_fk foreign key (mem_num) references em_member_manage (mem_num),
	constraint em_order_manage_fk2 foreign key (mem_home_num) references em_member_home (mem_home_num)
);
create sequence em_order_manage_seq;

create table em_order_detail(
	order_detail_num number,					-- 구매 상세 식별 번호
	order_num number,							-- 주문 번호
	product_num number,							-- 상품 번호
	order_product_name varchar2(60) not null,	-- 상품 이름
	order_product_price number(7) not null,		-- 상품 가격
	order_product_quantity number(5) not null,	-- 상품 수량
	order_product_total number(7) not null,		-- 총 구매 금액
	constraint em_order_detail_pk primary key (order_detail_num),
	constraint em_order_detail_fk foreign key (order_num) references em_order_manage (order_num),
	constraint em_order_detail_fk2 foreign key (product_num) references em_product_manage (product_num)
);
create sequence em_order_detail_seq;