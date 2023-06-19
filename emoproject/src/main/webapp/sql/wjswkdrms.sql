-- 회원관리
create table em_member_manage(
 mem_num number,
 mem_id varchar2(12) unique not null,
 mem_auth number(1) default 2 not null, --회원등급: 0탈퇴회원,1정지회원,2일반회원,9관리자
 mem_reg_date date default sysdate not null,
 mem_stop_date date,
 mem_exp_date date,
 constraint em_member_manage_pk primary key (mem_num)
);
create table em_member_detail(
 mem_num number,
 mem_name varchar2(30) not null,
 mem_passwd varchar2(20) not null,
 mem_cell varchar2(15) not null,
 mem_email varchar2(50) not null,
 mem_zipcode varchar2(5) not null,
 mem_address1 varchar2(90) not null,
 mem_address2 varchar2(90) not null,
 mem_birth varchar2(10) not null,
 mem_gender number(1) not null, -- 성별(1:남자, 2:여자)
 mem_point number(10) not null, -- 보유 포인트 
 mem_mdate date,
 constraint em_member_detail_pk 
                   primary key (mem_num),
 constraint em_member_detail_fk
                 foreign key (mem_num) 
                 references em_member_manage (mem_num)
);
create sequence em_member_seq;

create table em_member_home(
 mem_num number not null,--회원번호
 mem_home_num number not null,--배달주소번호
 mem_home_cell varchar2(15) not null,--전화번호
 mem_home_zipcode number(5) not null,--우편번호
 mem_home_address1 varchar2(50) not null,--배달받는주소
 mem_home_address2 varchar2(50) not null,--상세주소
 mem_home_name varchar2(50),--주소의 명칭
 constraint em_member_home_pk
 					primary key(mem_home_num),
 constraint em_member_home_fk
                 foreign key (mem_num) 
                 references em_member_manage (mem_num)
);
create sequence em_member_home_seq;

create table em_member_zzim(
 zzim_num number not null,--찜번호
 mem_num number not null,--회원번호
 product_num number not null,--상품번호
 constraint em_member_zzim_pk
 					primary key(zzim_num),
 constraint em_member_zzim_fk
                 foreign key (mem_num) 
                 references em_member_manage (mem_num),
 constraint em_member_zzim_fk2
                 foreign key (product_num) 
                 references em_product_manage (product_num)
);
create sequence em_member_zzim_seq;

create table em_member_cart(
 mem_cart_num number not null,--장바구니 번호
 mem_num number not null,--회원번호
 product_num number not null,--상품번호
 cart_quantity number(4) not null,--주문한 상품 수량
 constraint em_member_cart_pk
 					primary key(mem_cart_num),
 constraint em_member_cart_fk
                 foreign key (mem_num) 
                 references em_member_manage (mem_num),
 constraint em_member_zzim_fk2
                 foreign key (product_num) 
                 references em_product_manage (product_num)
);
create sequence em_member_cart_seq;
