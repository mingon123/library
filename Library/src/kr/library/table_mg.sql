update member set mem_stop_date=null WHERE mem_id='test';
commit;

select book_volm_cnt from book where book_num=3;
insert into member values ('test','1234','test','010-1234-1268','test@test.com',sysdate,null,null);