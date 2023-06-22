-- �쉶�썝愿�由�
create table em_member_manage(
 mem_num number,--�쉶�썝踰덊샇
 mem_id varchar2(12) unique not null,--id
 mem_auth number(1) default 2 not null, --�쉶�썝�벑湲�: 0�깉�눜�쉶�썝,1�젙吏��쉶�썝,2�씪諛섑쉶�썝,9愿�由ъ옄
 mem_reg_date date default sysdate not null,--媛��엯�씪
 mem_stop_date date,--�젙吏��씪
 mem_exp_date date,--�깉�눜�씪
 constraint em_member_manage_pk primary key (mem_num)
);
create table em_member_detail(
 mem_num number,--�쉶�썝踰덊샇
 mem_name varchar2(30) not null,--�쉶�썝�씠由�
 mem_passwd varchar2(20) not null,--鍮꾨�踰덊샇
 mem_cell varchar2(15) unique not null,--�쟾�솕踰덊샇
 mem_email varchar2(50) unique not null,--�씠硫붿씪
 mem_zipcode varchar2(5) not null,--�슦�렪踰덊샇
 mem_address1 varchar2(90) not null,--二쇱냼
 mem_address2 varchar2(90) not null,--�긽�꽭二쇱냼
 mem_birth varchar2(10) not null, --�깮�씪
 mem_gender number(1) not null, -- �꽦蹂�(1:�궓�옄, 2:�뿬�옄)
 mem_point number(10) not null, -- 蹂댁쑀 �룷�씤�듃 
 mem_mdate date,--留덉�留� �닔�젙�씪
 constraint em_member_detail_pk 
                   primary key (mem_num),
 constraint em_member_detail_fk
                 foreign key (mem_num) 
                 references em_member_manage (mem_num)
);
create sequence em_member_seq;

create table em_member_home(
 mem_num number not null,--�쉶�썝踰덊샇
 mem_home_num number not null,--諛곕떖二쇱냼踰덊샇
 mem_home_cell varchar2(15) not null,--�쟾�솕踰덊샇
 mem_home_zipcode number(5) not null,--�슦�렪踰덊샇
 mem_home_address1 varchar2(90) not null,--諛곕떖諛쏅뒗二쇱냼
 mem_home_address2 varchar2(90) not null,--�긽�꽭二쇱냼
 mem_home_name varchar2(50),--二쇱냼�쓽 紐낆묶
 constraint em_member_home_pk
 					primary key(mem_home_num),
 constraint em_member_home_fk
                 foreign key (mem_num) 
                 references em_member_manage (mem_num)
);
create sequence em_member_home_seq;

create table em_member_zzim(
 zzim_num number not null,--李쒕쾲�샇
 mem_num number not null,--�쉶�썝踰덊샇
 product_num number not null,--�긽�뭹踰덊샇
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
 mem_cart_num number not null,--�옣諛붽뎄�땲 踰덊샇
 mem_num number not null,--�쉶�썝踰덊샇
 product_num number not null,--�긽�뭹踰덊샇
 cart_quantity number(4) not null,--二쇰Ц�븳 �긽�뭹 �닔�웾
 constraint em_member_cart_pk
 					primary key(mem_cart_num),
 constraint em_member_cart_fk
                 foreign key (mem_num) 
                 references em_member_manage (mem_num),
 constraint em_member_cart_fk2
                 foreign key (product_num) 
                 references em_product_manage (product_num)
);
create sequence em_member_cart_seq;--seq
