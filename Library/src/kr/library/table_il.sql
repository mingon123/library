--관리자 메뉴> 3.대여 관리
--대여목록 조회시 책제목 추가 출력
SELECT bo.*, (SELECT book_title FROM book WHERE book_num=bo.book_num) 책제목 
FROM book_order bo ORDER BY order_num;

--관리자 메뉴> 6.리뷰 관리
--리뷰목록 조회시 책제목 추가 출력
SELECT r.*, (SELECT book_title FROM book WHERE book_num=r.book_num) 책제목 
FROM review r ORDER BY review_num;

--관리자 메뉴> 8.통계
--도서별 누적 대여횟수
SELECT book_num, (SELECT book_title FROM book WHERE book_num=bo.book_num) 책제목, 
COUNT(*) AS 대여횟수 FROM book_order bo GROUP BY book_num ORDER BY 대여횟수 DESC;
--회원별 누적 대여횟수
SELECT mem_id, COUNT(*) 대여횟수 FROM book_order GROUP BY mem_id 
ORDER BY 대여횟수 DESC, mem_id;
--회원별 누적 리뷰횟수
SELECT mem_id, COUNT(*) 리뷰횟수 FROM review GROUP BY mem_id 
ORDER BY 리뷰횟수 DESC, mem_id;

