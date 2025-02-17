create table reservation(
 re_num number primary key, --책 예약번호
 mem_id varchar2(12) not null, --회원ID(영문,숫자 최소 6~12자)
 book_num number not null, --책번호
 
 foreign key(mem_id) references member(mem_id) on delete cascade, 
 foreign key(book_num) references book(book_num) on delete cascade --책 삭제 시 예약 함께 삭제
);
create sequence reservation_seq;
