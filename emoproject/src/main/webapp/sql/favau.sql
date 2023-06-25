--테이블 제약조건 수정
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

--주문내역
SELECT b.* FROM(SELECT a.*, rownum rnum FROM(SELECT de2.product_photo1, data.order_date, data.order_status, data.product_num, data.order_product_name, data.order_product_total, data.order_product_quantity
FROM (SELECT ma.order_date, ma.order_num, ma.order_status, de.product_num, de.order_product_name, de.order_product_total, de.order_product_quantity FROM em_order_detail de
INNER JOIN em_order_manage ma ON de.order_num = ma.order_num WHERE ma.order_num IN (SELECT order_num FROM em_order_manage WHERE mem_num = 24) ORDER BY ma.order_num DESC) data
INNER JOIN em_product_detail de2 ON data.product_num = de2.product_num ORDER BY order_num DESC) a) b WHERE rnum>=1 AND rnum<=10

--주문 상세
SELECT b.* FROM(SELECT a.*, rownum rnum FROM(SELECT order_date, product_num, order_product_name, order_total_price, order_num FROM 
(SELECT ma.order_total_price, ma.order_date, ma.order_num, de.product_num, de.order_product_name FROM em_order_detail de
INNER JOIN em_order_manage ma ON de.order_num = ma.order_num WHERE ma.order_num IN (SELECT order_num FROM em_order_manage WHERE mem_num = 24) 
ORDER BY ma.order_num DESC) ORDER BY order_num DESC)a) b WHERE rnum>=1 AND rnum<=10

--주문 내역 fixed
SELECT b.* FROM(SELECT a.*, rownum rnum FROM(
SELECT * FROM (SELECT order_num, substr(XMLAGG(XMLELEMENT(nm, ', ',ORDER_PRODUCT_NAME)).EXTRACT('//text()').GETSTRINGVAL(),2) AS product_names 
FROM (SELECT * FROM EM_ORDER_MANAGE LEFT INNER JOIN em_order_detail USING (order_num) WHERE mem_num=27) GROUP BY ORDER_NUM ORDER BY order_num DESC) a INNER JOIN em_order_manage b ON a.order_num=b.order_num
)a) b WHERE rnum>=1 AND rnum<=10

--문의 내역
SELECT b.* FROM (SELECT a.*, rownum rnum FROM (SELECT ask_num, ask_title, ask_content, ask_date, ask_status 
FROM em_board_ask WHERE mem_num = 24 ORDER BY ask_num DESC) a ) b WHERE rnum>=1 AND rnum<=10

--리뷰 조회
--if로 배송 상태가 완료가 아니면 0을 주고 1~10이면 점수를 준다.
SELECT ma.order_num, ma.order_status, re.review_score FROM em_order_manage ma INNER JOIN em_review re ON ma.mem_num = re.mem_num WHERE re.mem_num=24

--문의글 삽입
INSERT INTO em_review (review_num,product_num,mem_num,order_num,review_title,review_content,review_photo1,review_score) VALUES ('1', '44', '24', '22', '제목', '내용', '사진', '7')

