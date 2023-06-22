-- 테이블 제약조건 수정
alter table 테이블명 modify 컬럼명 제약조건;


CREATE TABLE em_coupon(
coupon_num number not null,
coupon_name varchar2(50) unique not null,
coupon_info varchar2(100) not null,
coupon_price number(7) not null,
coupon_status number(1) default 1 not null, --1:미사용 2:사용 3:기간만료
mem_num number not null,
product_num number(5),
constraint em_coupon_pk primary key (coupon_num),
constraint em_coupon_fk foreign key (mem_num) references em_member_manage (mem_num),
constraint em_coupon_fk2 foreign key (product_num) references em_product_manage (product_num)
)
create sequence em_coupon_seq;



--찜
SELECT rnum, product_title, product_info, product_photo1, product_price, product_status FROM 
(SELECT a.*, rownum rnum 
FROM (SELECT zzim.zzim_num, data.product_title, data.product_info, data.product_photo1, data.product_price, data.product_status  
FROM (SELECT detail.product_num, detail.product_title, detail.product_info, detail.product_photo1, detail.product_price, manage.product_status 
    FROM em_product_detail detail INNER JOIN em_product_manage manage ON detail.product_num = manage.product_num) data 
    INNER JOIN em_member_zzim zzim ON data.product_num = zzim.product_num  WHERE zzim.mem_num = 24 ORDER BY zzim.zzim_num DESC) a) 
WHERE rnum>=1 AND rnum<=10;