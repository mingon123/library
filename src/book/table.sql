create table book(
 num number primary key,
 title varchar2(2000) not null, --제목
 author varchar2(2000) not null, --저자
 publisher varchar2(2000), --출판사
 publication_year NUMBER, --출판년도
 category varchar2(50), --카테고리
 rank number, --추천순위
 volm_cnt number, --권수(권)
 reg_date date not null
);
create sequence book_seq;
