---------------------------정연-----------------------------
SELECT * FROM EM_ORDER_MANAGE LEFT INNER JOIN em_order_detail USING (order_num) WHERE mem_num=27 ; 

-- 텍스트 합쳐가지고 넣는 방법... DAO에서 객체 하나 만들어가지고 따로 넣어야 할듯... (DAO 여러개 만들고 VO에 담은 뒤에 합치면 될듯)
SELECT order_num, substr(XMLAGG(XMLELEMENT(nm, ',',ORDER_PRODUCT_NAME)).EXTRACT('//text()').GETSTRINGVAL(),2) AS product_names 
FROM (SELECT * FROM EM_ORDER_MANAGE LEFT INNER JOIN em_order_detail USING (order_num) WHERE mem_num=27) GROUP BY ORDER_NUM;

--중복 제거된 order_num, mem_num, order_total_price 받는법 count는 무시하고 VO에 넣으면 될듯...
SELECT COUNT(*), ORDER_NUM, MEM_NUM, ORDER_TOTAL_PRICE FROM EM_ORDER_MANAGE LEFT INNER JOIN em_order_detail USING (order_num) WHERE mem_num=27 GROUP BY ORDER_NUM, MEM_NUM, ORDER_TOTAL_PRICE ; 

--OrderVO ordervo = new OrderVO() 하고 여기에서 dao 두개 들락날락 거리면서 odervo.set 뭐시기로 담으면 두개 정보가 한꺼번에 담긴 vo가 생김
--OrderVO를 두번 선언하면 안되고 한번에 담으면 좋을 것 같네... 이걸로 ㄱㄱ

-------------------------------------------------------------


--------------------------체은----------------------------------
SELECT product_num, product_name, product_title, product_price, product_discount, FLOOR(((PRODUCT_PRICE)*(100-PRODUCT_DISCOUNT))/100) AS PRODUCT_PRICE_SALES FROM em_product_manage m LEFT JOIN em_product_detail d USING(product_num) ORDER BY PRODUCT_DISCOUNT DESC;
--상품 바꿀 때 할인 form 넣는것도 추가하면 되겠네요
---------------------------------------------------------------


--------------------------정은--------------------------------

SELECT mem_num, mem_id, SUM(order_product_total) AS mem_using_price , mem_auth FROM em_member_manage LEFT INNER JOIN (SELECT * FROM em_member_detail LEFT INNER JOIN (SELECT * FROM em_order_manage LEFT INNER JOIN em_order_detail USING(order_num) ) USING (mem_num)) USING (mem_num) GROUP BY mem_num, mem_id, mem_auth;

-----------------------------------------------------------


--SQL문 수정 (Search DAO)
SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT product_num, product_category, product_status, product_name, product_title, product_photo1, product_price, order_cnt, COUNT(mem_num) AS ZZIM FROM em_member_zzim RIGHT OUTER JOIN (SELECT COUNT(PRODUCT_NUM) AS order_cnt , product_num, product_name, product_photo1, product_title, product_category, product_price, product_status FROM EM_ORDER_DETAIL o RIGHT JOIN (SELECT * FROM EM_PRODUCT_MANAGE m LEFT OUTER JOIN EM_PRODUCT_DETAIL d USING (PRODUCT_NUM)) USING (PRODUCT_NUM) GROUP BY product_num, product_name, product_photo1, product_title, product_category, product_price, product_status ) USING (product_num) GROUP BY product_num, product_category, product_status, product_name, product_title, product_photo1, product_price, order_cnt ) a) WHERE  rnum >=1 AND rnum <=10;

--SQL문 4차 수정 
SELECT * FROM (SELECT a.*,rownum rnum FROM (
SELECT c.*, FLOOR(((PRODUCT_PRICE)*(100-PRODUCT_DISCOUNT))/100) AS PRODUCT_PRICE_SALES FROM em_product_manage f LEFT JOIN
(SELECT product_num, product_category, product_status, product_name, product_title, product_photo1, product_price, order_cnt, product_discount, COUNT(mem_num) AS ZZIM  
FROM em_member_zzim z RIGHT OUTER JOIN (SELECT COUNT(PRODUCT_NUM) AS order_cnt , product_discount, product_num, product_name, product_photo1, product_title, product_category, product_price, product_status 
FROM EM_ORDER_DETAIL o RIGHT JOIN (SELECT * FROM EM_PRODUCT_MANAGE m LEFT OUTER JOIN EM_PRODUCT_DETAIL d USING (PRODUCT_NUM) ) 
USING (product_num) GROUP BY product_num, product_name, product_photo1, product_title, product_category, product_price, product_status, product_discount  )
USING (product_num) GROUP BY product_num, product_category, product_status, product_name, product_title, product_photo1, product_price, order_cnt, product_discount) c ON c.product_num = f.product_num
)a) WHERE  rnum >=1 AND rnum <=10;