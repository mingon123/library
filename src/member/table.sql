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