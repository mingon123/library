create table member(
 mem_id varchar2(12) primary key, --회원ID(영문,숫자 최소 6~12자)
 mem_pw varchar2(12) not null, --회원 비밀번호(영문,숫자,특수문자 최소8 이상)
 mem_name varchar2(30) not null, --회원의 이름
 mem_cell varchar2(15) not null unique, --전화번호(010-1234-5678 형식)
 mem_email varchar2(50) not null, --이메일(test@test.com 형식)
 mem_date date default sysdate not null, --회원가입일(default sysdate)
 mem_mdate date, --회원 정보 수정일
 mem_stop_date date --책 연체일
);



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



create table notice(
 notice_num	number primary key, --공지사항 번호
 notice_title varchar2(1000) not null, --공지사항 제목
 notice_content varchar2(4000) not null, --공지사항 내용
 notice_view number default 0 not null, --공지사항 조회수
 notice_reg_date date default sysdate not null --공지사항 등록일
);
create sequence notice_seq;



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



create table qna(
 qna_num number primary key, --qna번호
 qna_title varchar2(1000) not null, --qna질문제목
 qna_content varchar2(4000) not null, --qna질문내용
 qna_view number default 0 not null, --qna조회수
 qna_re	varchar2(1000),	--qna답변내용
 q_date	date default sysdate not null, --질문 날짜
 a_date date --답변 날짜
);
create sequence qna_seq;



create table reservation(
 re_num number primary key, --책 예약번호
 mem_id varchar2(12) not null, --회원ID(영문,숫자 최소 6~12자)
 book_num number not null, --책번호
 
 foreign key(mem_id) references member(mem_id) on delete cascade, 
 foreign key(book_num) references book(book_num) on delete cascade --책 삭제 시 예약 함께 삭제
);
create sequence reservation_seq;



create table review(
 review_num number primary key, --리뷰번호
 book_num number not null, --책번호
 review_content varchar2(4000) not null, --리뷰내용
 review_rate number(1) not null, --책 평점(1~5점)
 review_reg_date date default sysdate not null, --리뷰등록일
 
 foreign key(book_num) references book(book_num) on delete cascade --책 삭제 시 리뷰도 함께 삭제
);
create sequence review_seq;



create table wish_book(
 wish_num number primary key, --희망도서 번호
 wish_title varchar2(2000) not null, --희망도서 제목
 wish_author varchar2(2000)	not null, --희망도서 저자
 wish_publisher	varchar2(2000), --희망도서 출판사	
 wish_date date default sysdate not null --희망도서 신청일
);
create sequence wish_book_seq;

