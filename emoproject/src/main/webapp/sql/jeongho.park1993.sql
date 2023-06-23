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
