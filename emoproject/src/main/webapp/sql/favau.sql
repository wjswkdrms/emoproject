-- ���̺� �������� ����
alter table ���̺�� modify �÷��� ��������;


CREATE TABLE em_coupon(
coupon_num number not null,
coupon_name varchar2(50) unique not null,
coupon_info varchar2(100) not null,
coupon_price number(7) not null,
coupon_status number(1) default 1 not null, --1:�̻�� 2:��� 3:�Ⱓ����
mem_num number not null,
product_num number(5),
constraint em_coupon_pk primary key (coupon_num),
constraint em_coupon_fk foreign key (mem_num) references em_member_manage (mem_num),
constraint em_coupon_fk2 foreign key (product_num) references em_product_manage (product_num)
)
create sequence em_coupon_seq;