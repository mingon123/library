create table review(
 review_num number primary key, --리뷰번호
 book_num number, --책번호
 review_content varchar2(4000) not null, --리뷰내용
 review_rate number(1) not null, --책 평점(1~5점)
 review_reg_date date default sysdate not null, --리뷰등록일
 
 foreign key(book_num) references book(book_num) on delete cascade --책 삭제 시 리뷰도 함께 삭제
);
create sequence review_seq;