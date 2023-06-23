--공지사항
CREATE TABLE em_board_announce(
 ann_num number, --공지사항 번호
 mem_num number not null, --회원 번호
 ann_title varchar2(300) not null, --공지사항 제목
 ann_content varchar2(1000) not null, --공지사항 내용
 ann_photo1 varchar2(100), --게시판 사진
 ann_date date default sysdate not null, --등록일
 constraint em_board_announce_pk primary key (ann_num),
 constraint em_board_announce_fk foreign key (mem_num) references em_member_manage (mem_num)
);

CREATE SEQUENCE em_board_announce_seq;

--FAQ
CREATE TABLE em_board_faq(
 faq_num number,
 mem_num number not null,
 faq_title varchar2(300) not null,
 faq_content varchar2(3000) not null,
 faq_category number(1) default 1 not null,
 faq_date date default sysdate not null,
 constraint em_board_faq_pk primary key (faq_num),
 constraint em_board_faq_fk foreign key (mem_num) references em_member_manage(mem_num)
);
CREATE SEQUENCE em_board_faq_seq;

--1:1 문의 게시판
CREATE TABLE em_board_ask(
 ask_num number,--문의글 번호
 mem_num number not null,--문의자 번호
 ask_title varchar2(100) not null,--문의글 제목
 ask_content varchar2(1000) not null,--문의글 내용
 ask_photo1 varchar2(100),--문의글 사진 첨부
 ask_date date default SYSDATE not null,--문의글 작성 날짜
 ask_status number(1) default 0 not null,--문의 답변 상태 (0: 답변 대기 1: 답변 완료)
 constraint em_board_ask_pk primary key (ask_num),
 constraint em_board_ask_fk foreign key (mem_num) references em_member_manage (mem_num)
);

CREATE SEQUENCE em_board_ask_seq;

--1:1 문의 답변
CREATE TABLE em_board_answer(
 mem_num number not null,--관리자 번호
 ask_num number not null,--문의글 번호
 answer_num number,
 answer_content varchar2(1000) not null,--답변 내용
 answer_photo varchar2(100),--답변 사진 첨부
 answer_date date default SYSDATE not null,--답변 작성 날짜
 constraint em_board_answer_pk primary key (answer_num),
 constraint em_board_answer_fk1 foreign key (ask_num) references em_board_ask (ask_num),
 constraint em_board_answer_fk2 foreign key (mem_num) references em_member_manage (mem_num)
);

CREATE SEQUENCE em_board_answer_seq;

--제품 후기
CREATE TABLE em_review(
 review_num number,--리뷰 글 번호
 product_num number not null,--상품 번호
 mem_num number not null,--회원 번호
 order_detail_num unique not null,--주문내역
 review_title varchar2(100) not null,--리뷰 제목
 review_content varchar2(1000) not null,--리뷰 내용
 review_photo1 varchar2(100),--리뷰 사진
 review_score number(2) not null,--평점(0~10)
 constraint em_review_pk primary key (review_num),
 constraint em_review_fk1 foreign key (product_num) references em_product_manage (product_num),
 constraint em_review_fk2 foreign key (mem_num) references em_member_manage (mem_num),
 constraint em_review_fk3 foreign key (order_detail_num) references em_order_detail (order_detail_num)
);

CREATE SEQUENCE em_review_seq;