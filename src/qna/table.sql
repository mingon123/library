create table qna(
 qna_num number primary key, --qna번호
 qna_title varchar2(1000) not null, --qna질문제목
 qna_content varchar2(4000) not null, --qna질문내용
 qna_view number not null, --qna조회수
 qna_re	varchar2(1000),	--qna답변내용
 q_date	date not null, --질문 날짜
 a_date	date not null --답변 날짜
);
create sequence qna_seq;