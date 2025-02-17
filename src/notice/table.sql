create table notice(
 notice_num	number primary key, --공지사항 번호
 notice_title varchar2(1000) not null, --공지사항 제목
 notice_content varchar2(4000) not null, --공지사항 내용
 notice_view number default 0 not null, --공지사항 조회수
 notice_reg_date date default sysdate not null --공지사항 등록일
);
create sequence notice_seq;