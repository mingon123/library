create table book_order(
 order_num number primary key, --대여번호
 mem_id varchar2(12) not null, --회원ID(영문,숫자 최소 6~12자)
 book_num number not null, --책번호
 order_date	date default sysdate not null, --대여일
 return_date date not null, --반납일
 is_add	number(1) default 0 not null, --연장 유무(1회만 가능, +7일)(0 or 1)
 is_return number(1) default 0 not null, --반납유무(0 or 1)
 
 foreign key(mem_id) references member(mem_id) on delete cascade, --회원 삭제 시 함께 삭제
 foreign key(book_num) references book(book_num) on delete set null --책 삭제 시 null로 변경
);
create sequence book_order_seq;