create table wish_book(
 wish_num number primary key, --희망도서 번호
 wish_title varchar2(2000) not null, --희망도서 제목
 wish_author varchar2(2000)	not null, --희망도서 저자
 wish_publisher	varchar2(2000), --희망도서 출판사	
 wish_date date default sysdate not null --희망도서 신청일
);
create sequence wish_book_seq;
