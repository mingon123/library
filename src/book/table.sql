create table book(
 book_num number primary key,
 book_title varchar2(2000) not null, --제목
 book_author varchar2(2000) not null, --저자
 book_publisher varchar2(2000) not null, --출판사
 book_p_year number(4) not null, --출판년도
 book_category varchar2(50) not null, --카테고리
 book_rank number(4), --추천순위
 book_volm_cnt number not null, --권수(권)
 book_reg_date date default sysdate not null --등록일
);
create sequence book_seq;
