--------------------------------------------------------
--  파일이 생성됨 - 금요일-2월-28-2025   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence BOOK_ORDER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "BOOK_ORDER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence BOOK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "BOOK_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1041 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence NOTICE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "NOTICE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence QNA_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "QNA_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence RESERVATION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "RESERVATION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence REVIEW_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "REVIEW_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 81 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence WISH_BOOK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "WISH_BOOK_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table BOOK
--------------------------------------------------------

  CREATE TABLE "BOOK" 
   (	"BOOK_NUM" NUMBER, 
	"BOOK_TITLE" VARCHAR2(2000 BYTE), 
	"BOOK_AUTHOR" VARCHAR2(2000 BYTE), 
	"BOOK_PUBLISHER" VARCHAR2(2000 BYTE), 
	"BOOK_P_YEAR" NUMBER(4,0), 
	"BOOK_CATEGORY" VARCHAR2(50 BYTE), 
	"BOOK_RANK" NUMBER(4,0), 
	"BOOK_VOLM_CNT" NUMBER, 
	"BOOK_REG_DATE" DATE DEFAULT sysdate
   ) ;
--------------------------------------------------------
--  DDL for Table BOOK_ORDER
--------------------------------------------------------

  CREATE TABLE "BOOK_ORDER" 
   (	"ORDER_NUM" NUMBER, 
	"MEM_ID" VARCHAR2(12 BYTE), 
	"BOOK_NUM" NUMBER, 
	"ORDER_DATE" DATE DEFAULT sysdate, 
	"RETURN_DATE" DATE DEFAULT sysdate+14, 
	"IS_ADD" NUMBER(1,0) DEFAULT 0, 
	"IS_RETURN" NUMBER(1,0) DEFAULT 0
   );
--------------------------------------------------------
--  DDL for Table MEMBER
--------------------------------------------------------

  CREATE TABLE "MEMBER" 
   (	"MEM_ID" VARCHAR2(12 BYTE), 
	"MEM_PW" VARCHAR2(12 BYTE), 
	"MEM_NAME" VARCHAR2(30 BYTE), 
	"MEM_CELL" VARCHAR2(15 BYTE), 
	"MEM_EMAIL" VARCHAR2(50 BYTE), 
	"MEM_DATE" DATE DEFAULT sysdate, 
	"MEM_MDATE" DATE, 
	"MEM_STOP_DATE" DATE
   );
--------------------------------------------------------
--  DDL for Table NOTICE
--------------------------------------------------------

  CREATE TABLE "NOTICE" 
   (	"NOTICE_NUM" NUMBER, 
	"NOTICE_TITLE" VARCHAR2(1000 BYTE), 
	"NOTICE_CONTENT" VARCHAR2(4000 BYTE), 
	"NOTICE_VIEW" NUMBER DEFAULT 0, 
	"NOTICE_REG_DATE" DATE DEFAULT sysdate
   );
--------------------------------------------------------
--  DDL for Table QNA
--------------------------------------------------------

  CREATE TABLE "QNA" 
   (	"QNA_NUM" NUMBER, 
	"QNA_TITLE" VARCHAR2(1000 BYTE), 
	"QNA_CONTENT" VARCHAR2(4000 BYTE), 
	"QNA_VIEW" NUMBER DEFAULT 0, 
	"QNA_RE" VARCHAR2(1000 BYTE), 
	"Q_DATE" DATE DEFAULT sysdate, 
	"A_DATE" DATE, 
	"MEM_ID" VARCHAR2(12 BYTE)
   );
--------------------------------------------------------
--  DDL for Table RESERVATION
--------------------------------------------------------

  CREATE TABLE "RESERVATION" 
   (	"RE_NUM" NUMBER, 
	"MEM_ID" VARCHAR2(12 BYTE), 
	"BOOK_NUM" NUMBER
   );
--------------------------------------------------------
--  DDL for Table REVIEW
--------------------------------------------------------

  CREATE TABLE "REVIEW" 
   (	"REVIEW_NUM" NUMBER, 
	"BOOK_NUM" NUMBER, 
	"REVIEW_CONTENT" VARCHAR2(4000 BYTE), 
	"REVIEW_RATE" NUMBER(1,0), 
	"REVIEW_REG_DATE" DATE DEFAULT sysdate, 
	"MEM_ID" VARCHAR2(12 BYTE)
   );
--------------------------------------------------------
--  DDL for Table WISH_BOOK
--------------------------------------------------------

  CREATE TABLE "WISH_BOOK" 
   (	"WISH_NUM" NUMBER, 
	"WISH_TITLE" VARCHAR2(2000 BYTE), 
	"WISH_AUTHOR" VARCHAR2(2000 BYTE), 
	"WISH_PUBLISHER" VARCHAR2(2000 BYTE), 
	"WISH_DATE" DATE DEFAULT sysdate, 
	"MEM_ID" VARCHAR2(12 BYTE)
   );
REM INSERTING into BOOK
SET DEFINE OFF;
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (1,'불편한 편의점 :김호연 장편소설','지은이: 김호연','나무옆의자',2021,'소설',1,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (2,'아버지의 해방일지 :정지아 장편소설','지은이: 정지아','창비',2022,'소설',2,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (3,'불편한 편의점2 :김호연 장편소설','지은이: 김호연','나무옆의자',2022,'소설',3,0,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (4,'달러구트 꿈 백화점.이미예 장편소설','지은이: 이미예','팩토리나인',2020,'소설',4,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (5,'어서오세요, 휴남동 서점입니다 :황보름 장편소설','지은이: 황보름','클레이하우스',2022,'소설',5,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (6,'역행자 :돈·시간·운명으로부터 완전한 자유를 얻는 7단계 인생 공략집','자청 지음','웅진씽크빅',2022,'기타',6,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (7,'하얼빈 :김훈 장편소설','지은이: 김훈','문학동네',2022,'소설',7,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (8,'(이미 늦었다고 생각하는 당신을 위한) 김미경의 마흔 수업','김미경 지음','엠케이유니버스',2023,'기타',8,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (9,'흔한남매1','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',9,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (10,'흔한남매2','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',10,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (11,'작별인사 :김영하 장편소설','지은이: 김영하','복복서가',2022,'소설',11,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (12,'흔한남매3','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',11,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (13,'흔한남매4','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',13,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (14,'흔한남매5','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',14,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (15,'흔한남매6','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',15,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (16,'흔한남매7','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',16,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (17,'흔한남매8','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',17,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (18,'물고기는 존재하지 않는다 :상실, 사랑 그리고 숨어 있는 삶의 질서에 관한 이야기','룰루 밀러 지음 ,정지인 옮김','곰출판',2021,'소설',18,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (19,'흔한남매9','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',19,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (20,'흔한남매10','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',20,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (21,'흔한남매11','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',21,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (22,'밝은 밤 :최은영 장편소설','지은이: 최은영','문학동네',2021,'소설',22,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (23,'아몬드손원평 장편소설','손원평','창비',2017,'소설',22,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (24,'파친코 :이민진 장편소설','이민진 지음 ,이미정 옮김','문학사상',2018,'소설',24,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (25,'고양이 해결사 깜냥','홍민정 동화 ,김재희 그림','창비',2020,'기타',25,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (26,'고양이 해결사 깜냥','홍민정 동화 ,김재희 그림','창비',2020,'기타',26,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (27,'만일 내가 인생을 다시 산다면 :벌써 마흔이 된 당신에게 해 주고 싶은 말들 42','김혜남 지음','메이븐',2022,'기타',27,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (28,'체리새우 :황영미 장편소설','지은이: 황영미','문학동네',2019,'소설',28,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (29,'돈의 속성 :최상위 부자가 말하는 돈에 대한 모든 것','지은이: 김승호','Snowfox(스노우폭스북스)',2020,'기타',29,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (30,'흔한남매의 흔한 호기심 :일상에서 만나는 과학 상식','원작: 흔한남매 ,그림: 유난희','미래엔',2020,'과학',30,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (31,'달러구트 꿈 백화점.이미예 장편소설','지은이: 이미예','팩토리나인',2020,'소설',31,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (32,'세이노의 가르침','세이노 (지은이)','데이원',2023,'기타',32,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (33,'순례 주택 :유은실 소설','지은이: 유은실','비룡소',2021,'소설',33,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (34,'고양이 해결사 깜냥','홍민정 동화 ,김재희 그림','창비',2020,'기타',33,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (35,'흔한남매12','원작: 흔한남매 ,그림: 유난희','미래엔',2019,'기타',35,12,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (36,'세상의 마지막 기차역','무라세 다케시 지음 ,김지연 옮김','바이포엠 스튜디오',2022,'기타',36,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (37,'흔한남매의 흔한 호기심 :일상에서 만나는 과학 상식1','원작: 흔한남매 ,그림: 유난희','미래엔',2020,'과학',37,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (38,'고양이 해결사 깜냥1','홍민정 동화 ,김재희 그림','창비',2020,'기타',38,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (39,'흔한남매의 흔한 호기심 :일상에서 만나는 과학 상식2','원작: 흔한남매 ,그림: 유난희','미래엔',2020,'과학',39,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (40,'생에 감사해','지은이: 김혜자','수오서재',2022,'기타',40,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (41,'흔한남매의 흔한 호기심 :일상에서 만나는 과학 상식3','원작: 흔한남매 ,그림: 유난희','미래엔',2020,'과학',41,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (42,'흔한남매 과학 탐험대1','원작: 흔한남매 ,그림: 김덕영','김영사',2021,'과학',42,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (43,'흔한남매의 흔한 호기심 :일상에서 만나는 과학 상식4','원작: 흔한남매 ,그림: 유난희','미래엔',2020,'과학',43,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (44,'고양이 해결사 깜냥2','홍민정 동화 ,김재희 그림','창비',2020,'기타',44,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (45,'흔한남매 과학 탐험대2','원작: 흔한남매 ,그림: 김덕영','김영사',2021,'과학',45,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (46,'파친코 :이민진 장편소설','이민진 지음 ,이미정 옮김','문학사상',2018,'소설',46,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (47,'메리골드 마음 세탁소','윤정은 (지은이)','북로망스',2023,'기타',47,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (48,'흔한남매의 흔한 호기심 :일상에서 만나는 과학 상식5','원작: 흔한남매 ,그림: 유난희','미래엔',2020,'과학',48,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (49,'수박 수영장 :안녕달 그림책','지은이: 안녕달','창비',2015,'기타',49,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (50,'지구 끝의 온실 :김초엽 장편소설','지은이: 김초엽','Giant Books(자이언트북스)',2021,'소설',50,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (51,'흔한남매의 흔한 호기심 :일상에서 만나는 과학 상식6','원작: 흔한남매 ,그림: 유난희','미래엔',2020,'과학',51,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (52,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',52,15,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (53,'이상한 과자 가게 전천당 16','히로시마 레이코 (지은이), 쟈쟈 (그림), 김정화 (옮긴이)','길벗스쿨',2022,'기타',53,16,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (54,'똥볶이 할멈 :K-히어로 판타지1','글: 강효미 ,그림: 김무연','슈크림북',2021,'기타',54,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (55,'(서울 자가에 대기업 다니는) 김 부장 이야기','송희구 지음','서삼독',2021,'소설',55,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (56,'희망의 끈','히가시노 게이고 (지은이), 김난주 (옮긴이)','재인',2022,'기타',56,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (57,'흔한남매 과학 탐험대 4 : 물질','흔한남매 (원작), 이현진, 닥터 스코 (글), 김덕영 (그림), 최진수 (감수), 정현철, 김희목, 권경아, 최진수 (기획)','주니어김영사',2022,'과학',56,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (58,'흔한남매 이상한 나라의 고전 읽기','최재훈 글 ,정주연 그림','미래엔',2022,'기타',58,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (59,'흔한남매 불꽃 튀는 우리말 1 - 초등 국어 고수 되기 프로젝트','흔한남매 (원작)|한은호','다산어린이',2021,'기타',59,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (60,'흔한남매 불꽃 튀는 우리말 :초등 국어 고수 되기 프로젝트','원작: 흔한남매 ,그림: 유희석','다산북스',2021,'기타',60,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (61,'낭만 강아지 봉봉','홍민정 글 ,김무연 그림','다산북스',2022,'기타',61,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (62,'이토록 평범한 미래 :김연수 소설','지은이: 김연수','문학동네',2022,'소설',61,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (63,'똥볶이 할멈 :K-히어로 판타지2','글: 강효미 ,그림: 김무연','슈크림북',2021,'기타',63,0,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (64,'흔한남매 이상한 나라의 고전 읽기','최재훈 글 ,정주연 그림','미래엔',2022,'기타',64,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (65,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',65,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (66,'흔한남매 과학 탐험대 3 : 인체','흔한남매 (원작), 이재국, 이현진, 서지은 (글), 김덕영 (그림), 정현철, 김희목, 권경아, 최진수, 박준건 (기획)','주니어김영사',2021,'과학',66,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (67,'스즈메의 문단속','신카이 마코토 지음 ,민경욱 옮김','대원씨아이',2023,'기타',67,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (68,'호랭떡집','글·그림: 서현','사계절(사계절출판사)',2023,'기타',68,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (69,'흔한남매의 흔한 호기심 :일상에서 만나는 과학 상식','원작: 흔한남매 ,그림: 유난희','미래엔',2020,'과학',68,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (70,'똥볶이 할멈 :K-히어로 판타지3','글: 강효미 ,그림: 김무연','슈크림북',2021,'기타',70,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (71,'흔한남매 과학 탐험대 5 : 물리 1','흔한남매 (원작), 이현진, 닥터 스코 (글), 김덕영 (그림), 김희목 (감수), 김희목, 정현철, 권경아, 최진수 (기획)','주니어김영사',2022,'과학',71,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (72,'오싹오싹 크레용!','글: 에런 레이놀즈 ,옮김: 홍연미','토토북',2022,'기타',72,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (73,'똥볶이 할멈 :K-히어로 판타지4','글: 강효미 ,그림: 김무연','슈크림북',2021,'기타',73,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (74,'긴긴밤','루리 글·그림','문학동네',2021,'소설',74,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (75,'(추리 천재) 엉덩이 탐정','트롤 글·그림 ,김정화 옮김','미래엔',2016,'기타',74,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (76,'미드나잇 라이브러리','매트 헤이그 지음 ,노진선 옮김','인플루엔셜',2021,'기타',76,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (77,'(흔한남매) 안 흔한 일기','흔한남매 원작 ,조병주 그림','미래엔',2020,'기타',77,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (78,'흔한남매 불꽃 튀는 우리말 :초등 국어 고수 되기 프로젝트','원작: 흔한남매 ,그림: 유희석','다산북스',2021,'기타',78,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (79,'원씽 :복잡한 세상을 이기는 단순함의 힘','게리 켈러,구세희 옮김','비즈니스북스',2013,'기타',79,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (80,'오늘 밤, 세계에서 이 사랑이 사라진다 해도','이치조 미사키 지음 ,권영주 옮김','바이포엠',2021,'기타',80,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (81,'에그박사 :Egg&bugs','원작: 에그박사 ,그림: 홍종현','미래엔',2020,'기타',81,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (82,'(서울 자가에 대기업 다니는) 김 부장 이야기','송희구 지음','서삼독',2021,'소설',82,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (83,'겨울 이불 :안녕달 그림책','지은이: 안녕달','창비',2023,'기타',83,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (84,'완전한 행복 :정유정 장편소설','지은이: 정유정','은행나무',2021,'소설',84,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (85,'이상한 과자 가게 전천당 17','히로시마 레이코 (지은이), 쟈쟈 (그림), 김정화 (옮긴이)','길벗스쿨',2023,'기타',85,17,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (86,'에그박사 :Egg&bugs 1','원작: 에그박사 ,그림: 홍종현','미래엔',2020,'기타',86,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (87,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',87,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (88,'(흔한남매) 안 흔한 일기','흔한남매 원작 ,조병주 그림','미래엔',2020,'기타',88,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (89,'낭만 강아지 봉봉','홍민정 글 ,김무연 그림','다산북스',2022,'기타',89,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (90,'에그박사 :Egg&bugs 2','원작: 에그박사 ,그림: 홍종현','미래엔',2020,'기타',90,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (91,'흔한남매 과학 탐험대 6 : 물리 2','흔한남매 (원작), 이현진, 닥터 스코 (글), 김덕영 (그림), 김희목 (감수), 김희목, 정현철, 권경아, 최진수 (기획)','주니어김영사',2022,'과학',91,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (92,'흔한남매 불꽃 튀는 우리말 :초등 국어 고수 되기 프로젝트','원작: 흔한남매 ,그림: 유희석','다산북스',2021,'기타',91,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (93,'에그박사 :Egg&bugs 3','원작: 에그박사 ,그림: 홍종현','미래엔',2020,'기타',93,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (94,'(서울 자가에 대기업 다니는) 김 부장 이야기','송희구 지음','서삼독',2021,'소설',93,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (95,'에그박사 :Egg&bugs 4','원작: 에그박사 ,그림: 홍종현','미래엔',2020,'기타',95,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (96,'(추리 천재) 엉덩이 탐정','트롤 글·그림 ,김정화 옮김','미래엔',2016,'기타',96,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (97,'에그박사 :Egg&bugs 5','원작: 에그박사 ,그림: 홍종현','미래엔',2020,'기타',97,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (98,'타이탄의 도구들 :1만 시간의 법칙을 깬 거인들의 61가지 전략','팀 페리스 지음 ,정지현 옮김','Tornado(토네이도)',2017,'기타',98,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (99,'낭만 강아지 봉봉','홍민정 글 ,김무연 그림','다산북스',2022,'기타',99,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (100,'나미야 잡화점의 기적 :히가시노 게이고 장편소설','지은이: 히가시노 게이고 ,옮긴이: 양윤옥','현대문학',2012,'소설',100,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (101,'에그박사 :Egg&bugs 6','원작: 에그박사 ,그림: 홍종현','미래엔',2020,'기타',101,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (102,'에그박사 :Egg&bugs 7','원작: 에그박사 ,그림: 홍종현','미래엔',2020,'기타',102,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (103,'시선으로부터, :정세랑 장편소설','지은이: 정세랑','문학동네',2020,'소설',103,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (104,'(세상에서 가장 쉬운) 본질육아 :삶의 근본을 보여주는 부모, 삶을 스스로 개척하는 아이','지나영 지음','21세기북스(북이십일 21세기북스)',2022,'기타',104,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (105,'우리가 빛의 속도로 갈 수 없다면 :김초엽 소설','지은이: 김초엽','허블',2019,'소설',105,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (106,'당신도 느리게 나이 들 수 있습니다 :나이가 들어도 몸의 시간을 젊게','정희원 지음','길벗',2023,'기타',106,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (107,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','Dankkum i(단꿈아이)',2022,'역사',107,23,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (108,'안젤리크 :기욤 뮈소 장편소설','글: 기욤 뮈소 ,옮긴이: 양영란','밝은세상',2022,'소설',108,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (109,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',108,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (110,'(흔한남매) 별난 방탈출 :탈출 미션 스토리북','흔한남매 원작 ,차차 그림','미래엔',2020,'기타',110,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (111,'흔한남매 오해요 :귀염 뽀짝 부캐들과 펼치는 초단편 콩트 만화','원작: 흔한남매 ,그림: 유난희','미래엔',2022,'기타',111,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (112,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',112,14,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (113,'이어령의 마지막 수업','김지수 지음','열림원',2021,'기타',113,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (114,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','Dankkum i(단꿈아이)',2022,'역사',114,22,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (115,'책들의 부엌 :김지혜 장편소설','지은이: 김지혜','팩토리나인',2022,'소설',115,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (116,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',116,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (117,'환상서점 :소서림 장편소설','지은이: 소서림','해피북스투유',2023,'소설',117,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (118,'(흔한남매) 안 흔한 일기','흔한남매 원작 ,조병주 그림','미래엔',2020,'기타',118,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (119,'어머니, 사교육을 줄이셔야 합니다','정승익 (지은이)','메이트북스',2023,'기타',119,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (120,'(추리 천재) 엉덩이 탐정','트롤 글·그림 ,김정화 옮김','미래엔',2016,'기타',119,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (121,'가녀장의 시대 :이슬아 장편소설','지은이: 이슬아','문학동네',2022,'소설',121,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (122,'(흔한남매) 별난 방탈출 3 :탈출 미션 스토리북','흔한남매 원작 ,차차 그림','미래엔',2020,'기타',122,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (123,'가재가 노래하는 곳 :델리아 오언스 장편소설','지은이: 델리아 오언스 ,옮긴이: 김선형','살림출판사',2019,'소설',123,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (124,'아주 작은 습관의 힘 :최고의 변화는 어떻게 만들어지는가','제임스 클리어 지음 ,이한이 옮김','비즈니스북스',2019,'자기계발',124,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (125,'소년이 온다 :한강 장편소설','지은이: 한강','창비',2014,'소설',125,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (126,'페인트 :이희영 장편소설','지은이: 이희영','창비',2019,'소설',126,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (127,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','Dankkum i(단꿈아이)',2022,'역사',127,20,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (128,'(흔한남매) 별난 방탈출 1 :탈출 미션 스토리북','흔한남매 원작 ,차차 그림','미래엔',2020,'기타',128,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (129,'스카이 버스 =명문 대학으로 직행하는 초등 공부 전략서 /Sky bus','분당강쌤 지음','다산북스',2023,'기타',129,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (130,'(흔한남매) 별난 방탈출 2 :탈출 미션 스토리북','흔한남매 원작 ,차차 그림','미래엔',2020,'기타',130,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (131,'사피엔스 :유인원에서 사이보그까지, 인간 역사의 대담하고 위대한 질문','지은이: 유발 하라리 ,옮긴이: 조현욱','김영사',2015,'역사',131,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (132,'튜브 :손원평 장편소설','지은이: 손원평','창비',2022,'소설',131,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (133,'고래:천명관 장편소설','천명관 지음','문학동네',2004,'소설',133,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (134,'홍학의 자리 :정해연 장편소설','지은이: 정해연','문학동네',2021,'소설',134,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (135,'(추리 천재) 엉덩이 탐정','트롤 글·그림 ,김정화 옮김','미래엔',2016,'기타',135,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (136,'마흔에 읽는 니체 =지금 이순간을 살기 위한 철학 수업 /Nietzsche','장재형 지음','유노콘텐츠그룹',2022,'기타',136,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (137,'흔한남매 불꽃 튀는 우리말 5 - 초등 국어 고수 되기 프로젝트','한은호 (지은이), 흔한남매 (원작), 유희석 (그림), 흔한컴퍼니 (감수)','다산어린이',2022,'기타',137,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (138,'(정재승의) 인간 탐구 보고서','글: 정재은,그림: 김현민','북이십일',2019,'기타',138,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (139,'(추리 천재) 엉덩이 탐정','트롤 글·그림 ,김정화 옮김','미래엔',2016,'기타',138,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (140,'(추리 천재) 엉덩이 탐정','트롤 글·그림 ,김정화 옮김','미래엔',2016,'기타',140,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (141,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',141,56,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (142,'오싹오싹 팬티!','글: 에런 레이놀즈 ,옮김: 홍연미','토토북',2018,'기타',142,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (143,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',143,12,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (144,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',144,17,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (145,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',145,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (146,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',146,13,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (147,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',147,55,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (148,'(신기한 맛) 도깨비 식당','김용세,센개 그림','꿈터',2022,'기타',148,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (149,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',149,53,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (150,'흔한남매 이상한 나라의 고전 읽기 3 - 양반전·박씨부인전·운영전','최재훈 (글), 정주연 (그림), 흔한남매, 흔한컴퍼니 (감수)','미래엔아이세움',2023,'기타',150,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (151,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',151,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (152,'(흔한남매) 수수께끼 어드벤처 :수수께끼로 배우는 교과 지식','원작: 흔한남매 ,그림: 도니패밀리','미래엔',2022,'기타',152,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (153,'(흔한남매) 별난 방탈출 :탈출 미션 스토리북','흔한남매 원작 ,차차 그림','미래엔',2020,'기타',153,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (154,'추리 천재 엉덩이 탐정 5 - 유적에서 날아온 SOS 사건','트롤','아이세움',2017,'기타',154,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (155,'(추리 천재) 엉덩이 탐정','트롤 글·그림 ,김정화 옮김','미래엔',2016,'기타',155,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (156,'천 개의 파랑 :천선란 장편소설','지은이: 천선란','허블',2020,'소설',155,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (157,'세계를 건너 너에게 갈게 :이꽃님 장편소설','글쓴이: 이꽃님','문학동네',2018,'소설',157,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (158,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',158,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (159,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','Dankkum i(단꿈아이)',2022,'역사',159,24,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (160,'어린이라는 세계 :김소영 에세이','지은이: 김소영','사계절(사계절출판사)',2020,'기타',160,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (161,'죽이고 싶은 아이 :이꽃님 장편소설','지은이: 이꽃님','우리학교',2021,'소설',161,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (162,'(추리 천재) 엉덩이 탐정','트롤 글·그림 ,김정화 옮김','미래엔',2016,'기타',162,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (163,'우리, 편하게 말해요 :마음을 다해 듣고 할 말은 놓치지 않는 이금희의 말하기 수업','이금희 지음','웅진씽크빅',2022,'기타',163,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (164,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',164,16,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (165,'당신은 결국 무엇이든 해내는 사람 :김상현 에세이','지은이: 김상현','필름(필름출판사)',2022,'기타',165,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (166,'(추리 천재) 엉덩이 탐정','트롤 글·그림 ,김정화 옮김','미래엔',2016,'기타',166,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (167,'거인의 노트 :인생에서 무엇을 보고 어떻게 기록할 것인가','김익한 지음','다산북스',2023,'기타',167,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (168,'책 먹는 여우의 여름 이야기','프란치스카 비어만 글·그림 ,송순섭 옮김','김영사',2022,'소설',168,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (169,'당근 유치원','지은이: 안녕달','창비',2020,'기타',169,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (170,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',170,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (171,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',170,19,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (172,'파친코 :이민진 장편소설','지은이: 이민진 ,옮긴이: 신승미','인플루엔셜',2022,'소설',172,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (173,'(신기한 맛) 도깨비 식당','김용세,센개 그림','꿈터',2022,'기타',173,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (174,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',173,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (175,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',175,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (176,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',175,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (177,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',177,20,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (178,'부자 아빠 가난한 아빠 :20주년 특별 기념판','지은이: 로버트 기요사키 ,옮긴이: 안진환','민음인',2018,'기타',178,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (179,'구의 증명 :최진영 소설','지은이: 최진영','은행나무',2015,'소설',179,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (180,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',180,13,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (181,'장하준의 경제학 레시피 :마늘에서 초콜릿까지 18가지 재료로 요리한 경제 이야기','장하준 지음 ,김희정 옮김','부키',2023,'소설',181,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (182,'(건강과 다이어트를 동시에 잡는) 채소·과일식 :단순하면서 자연스러운 가장 효과적인 식단','글: 조승우','바이북스',2022,'기타',181,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (183,'여행의 이유 :김영하 산문','지은이: 김영하','문학동네',2019,'소설',183,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (184,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',184,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (185,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',185,26,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (186,'K 배터리 레볼루션 =향후 3년, 새로운 부의 시장에서 승자가 되는 법 /K battery revolution','박순혁 지음','지와인',2023,'기타',186,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (187,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',187,54,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (188,'아홉 살 마음 사전','박성우 글 ,김효은 그림','창비',2017,'기타',188,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (189,'내 멋대로 초능력 뽑기','최은옥 글 ,김무연 그림','김영사',2022,'기타',189,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (190,'망원동 브라더스 :김호연 장편소설','지은이: 김호연','나무옆의자',2013,'소설',189,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (191,'흔한남매 과학 탐험대 7 : 생물 1','김언정 (글), 흔한남매 (원작), 이현진 (글), 김덕영 (그림), 권경아 (감수), 김희목, 정현철, 권경아, 최진수 (기획)','주니어김영사',2023,'과학',191,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (192,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',192,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (193,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',193,14,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (194,'설민석의 한국사 대모험 18 - 후삼국 편 : 온달의 기묘한 시간 여행','설민석, 스토리박스 (지은이), 정현희 (그림), 강석화 (감수)','아이휴먼',2021,'역사',194,18,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (195,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',195,12,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (196,'(사이토 히토리의) 1퍼센트 부자의 법칙','사이토 히토리 [지음] ,김진아 옮김','나비스쿨',2023,'기타',196,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (197,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',197,15,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (198,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',197,15,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (199,'내면소통 =삶의 변화를 가져오는 마음근력 훈련/Inner communication','김주환 지음','인플루엔셜',2023,'기타',199,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (200,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',200,14,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (201,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',201,24,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (202,'(정재승의) 인간 탐구 보고서','글: 정재은,그림: 김현민','북이십일',2019,'기타',201,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (203,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',203,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (204,'오백 년째 열다섯 :김혜정 장편소설','글: 김혜정','위즈덤하우스',2022,'소설',203,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (205,'내가 틀릴 수도 있습니다 =숲속의 현자가 전하는 마지막 인생수업 /I may be wrong','비욘 나티코 린데블라드 지음 ,박미경 옮김','다산북스',2022,'기타',205,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (206,'엄마 자판기','글·그림: 조경희','노란돼지',2019,'기타',205,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (207,'챗GPT :2023 전 세계를 뒤흔든 빅이슈의 탄생','반병현 지음','생능출판사',2023,'기타',207,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (208,'(신기한 맛) 도깨비 식당','김용세,센개 그림','꿈터',2022,'기타',208,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (209,'다정한 것이 살아남는다','지은이: 브라이언 헤어,옮긴이: 이민아','디플롯',2021,'기타',209,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (210,'종의 기원 =정유정 장편소설 /The origin of species','지은이: 정유정','은행나무',2016,'소설',210,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (211,'오리 부리 이야기','황선애 글 ,간장 그림','비룡소',2022,'소설',211,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (212,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',212,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (213,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',212,12,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (214,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',214,25,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (215,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',215,30,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (216,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',216,13,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (217,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',217,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (218,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',218,23,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (219,'(전설의 모험왕) 엉덩이 댄디 :the young','원작·감수: 트롤 ,옮긴이: 김정화','미래엔',2021,'기타',219,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (220,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',219,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (221,'돈의 심리학 :당신은 왜 부자가 되지 못했는가','모건 하우절 지음 ,이지연 옮김','인플루엔셜',2021,'기타',221,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (222,'(정재승의) 인간 탐구 보고서','글: 정재은,그림: 김현민','북이십일',2019,'기타',222,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (223,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',222,20,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (224,'이 책은 돈 버는 법에 관한 이야기 :서민갑부 고명환의 생각법, 독서법, 장사법','고명환 지음','라곰',2022,'소설',222,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (225,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',225,17,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (226,'이파라파냐무냐무 :이지은 그림책','글·그림: 이지은','사계절(사계절출판사)',2020,'기타',226,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (227,'상처 주는 말 하는 친구에게 똑똑하게 말하는 법 - 화내지 않고, 참지 않고, 울지 않는 마법의 표현 59','김윤나 (지은이), 유영근 (그림)','북라이프',2023,'기타',227,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (228,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',227,52,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (229,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',229,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (230,'훌훌 :문경민 장편소설','지은이: 문경민','문학동네',2022,'소설',229,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (231,'흔한남매 겨울밤 대소동 :흔한남매 스페셜 에디션 겨울','원작: 흔한남매 ,그림: 유난희','미래엔',2021,'기타',229,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (232,'(엉뚱한 기자) 김방구 :목에 사는 두꺼비','주봄 글 ,한승무 그림','비룡소',2022,'기타',232,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (233,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',233,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (234,'(정재승의) 인간 탐구 보고서','글: 정재은,그림: 김현민','북이십일',2019,'기타',234,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (235,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',235,16,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (236,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',236,51,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (237,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',236,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (238,'흔한남매 흔한 MBTI - 믿거나 말거나 나에게 어울리는 직업','김승자, 정미화 (지은이), 흔한남매 (원작), 투윙스, 유영근 (그림), 흔한컴퍼니 (감수)','대원키즈',2021,'기타',238,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (239,'랑랑 형제 떡집','김리리 글 ,김이랑 그림','비룡소',2022,'기타',239,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (240,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',239,19,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (241,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',239,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (242,'채사장의 지대넓얕 :지적 대화를 위한 넓고 얕은 지식','글: 채사장,그림: 정용환','Dolphin Books(돌핀북)',2021,'기타',239,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (243,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2022,'기타',243,13,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (244,'채사장의 지대넓얕 :지적 대화를 위한 넓고 얕은 지식','글: 채사장,그림: 정용환','Dolphin Books(돌핀북)',2021,'기타',244,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (245,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',245,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (246,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',245,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (247,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',245,50,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (248,'공정하다는 착각 :능력주의는 모두에게 같은 기회를 제공하는가','마이클 샌델 지음 ,함규진 옮김','미래엔',2020,'기타',248,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (249,'흔한남매 수수께끼 어드벤처 2','흔한남매 (원작), 노지영 (글), 도니패밀리 (그림), 흔한컴퍼니, 정재형 (감수)','미래엔아이세움',2023,'기타',249,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (250,'살 때, 팔 때, 벌 때 =여의도 닥터둠 강영현이 공개하는 진격의 주식 투자 타이밍 /Timing to buy, sell, make a fortune','강영현 지음','21세기북스(북이십일 21세기북스)',2023,'기타',250,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (251,'H마트에서 울다','미셸 자우너 지음 ,정혜윤 옮김','문학동네',2022,'소설',251,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (252,'저주토끼 (리커버)','정보라 (지은이)','아작',2022,'기타',251,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (253,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',253,27,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (254,'도파민네이션 :쾌락 과잉 시대에서 균형 찾기','애나 렘키 지음 ,김두완 옮김','흐름출판',2022,'기타',254,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (255,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',255,21,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (256,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',255,16,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (257,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',257,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (258,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',258,57,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (259,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',259,14,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (260,'역사의 쓸모 :자유롭고 떳떳한 삶을 위한 22가지 통찰','최태성 지음','다산북스',2019,'역사',260,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (261,'내 멋대로 선생님 뽑기','최은옥 글 ,김무연 그림','김영사',2022,'기타',261,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (262,'어느 날, 내 죽음에 네가 들어왔다','세이카 료겐 지음 ,김윤경 옮김','바이포엠 스튜디오',2022,'기타',262,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (263,'브라질에 비가 내리면 스타벅스 주식을 사라 :경제의 큰 흐름에서 기회를 잡는 매크로 투자 가이드','피터 나바로 지음 ,이창식 옮김','에프엔미디어',2022,'기타',262,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (264,'영어책 한 권 외워봤니? :딱 한 권만 넘으면 영어 울렁증이 사라진다','김민식 지음','위즈덤하우스',2017,'기타',264,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (265,'소크라테스 익스프레스 :철학이 우리 인생에 스며드는 순간','에릭 와이너 지음 ,김하현 옮김','어크로스(어크로스출판그룹)',2021,'기타',265,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (266,'(이상한 과자 가게) 전천당','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2019,'기타',266,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (267,'파친코 :이민진 장편소설','지은이: 이민진 ,옮긴이: 신승미','인플루엔셜',2022,'소설',267,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (268,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2022,'기타',268,14,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (269,'천하무적 개냥이 수사대','이승민 글 ,하민석 그림','위즈덤하우스',2020,'기타',269,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (270,'외사랑','저자: 히가시노 게이고 ,옮긴이: 민경욱','소미미디어',2022,'기타',270,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (271,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2019,'기타',270,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (272,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',272,31,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (273,'위풍당당 여우 꼬리','손원평 글 ,만물상 그림','창비',2021,'기타',273,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (274,'화이트 러시 =White rush','저자: 히가시노 게이고 ,옮긴이: 민경욱','소미미디어',2023,'기타',273,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (275,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',275,13,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (276,'설민석의 세계사 대모험 15 - 몽골 편 : 칭기즈 칸의 꿈','설민석, 김정욱 (지은이), 박성일 (그림), 김장구 (감수)','단꿈아이',2022,'역사',276,15,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (277,'의사 어벤저스 :어린이 의학 동화','글: 고희정 ,그림: 조승연','가나문화콘텐츠',2021,'기타',277,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (278,'의사 어벤저스 :어린이 의학 동화','글: 고희정 ,그림: 조승연','가나문화콘텐츠',2021,'기타',278,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (279,'Go go 카카오프렌즈 :자연탐사','글: 조주희 ,이혜림','북이십일',2022,'기타',279,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (280,'기분이 태도가 되지 말자 - 감정조절이 필요한 당신을 위한 책','김수현 (지은이)','하이스트',2022,'기타',279,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (281,'어떻게 말해줘야 할까 :오은영의 현실밀착 육아회화','글: 오은영 ,그림: 차상미','김영사',2020,'기타',281,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (282,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',282,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (283,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',283,22,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (284,'바다가 들리는 편의점','마치다 소노코 지음 ,황국영 옮김','바이포엠 스튜디오',2023,'기타',283,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (285,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',285,49,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (286,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',286,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (287,'(오은영의) 화해 :상처받은 내면의 나와 마주하는 용기','오은영 지음','대성',2019,'기타',286,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (288,'(전설의 모험왕) 엉덩이 댄디 :the young','원작·감수: 트롤 ,옮긴이: 김정화','미래엔',2021,'기타',288,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (289,'설민석의 삼국지 대모험','원작: 나관중 ,만화: 스튜디오 담','Dankkum i(단꿈아이)',2021,'기타',289,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (290,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',290,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (291,'설민석의 한국사 대모험','글: 설민석,그림: 정현희','휴먼큐브',2017,'역사',291,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (292,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',292,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (293,'신기한 맛 도깨비 식당 4','김용세, 김병섭 (지은이), 센개 (그림)','꿈터',2023,'기타',292,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (294,'둥실이네 떡집','김리리 글 ,김이랑 그림','비룡소',2022,'기타',294,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (295,'정재승의 인간 탐구 보고서 11 - 인간을 울고 웃게 만드는 스트레스','정재은 (글), 김현민 (그림), 정재승 (기획), 이고은 (자문)','아울북',2023,'기타',295,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (296,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',295,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (297,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',297,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (298,'(정재승의) 인간 탐구 보고서','글: 정재은,그림: 김현민','북이십일',2019,'기타',298,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (299,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',299,47,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (300,'의사 어벤저스 :어린이 의학 동화','글: 고희정 ,그림: 조승연','가나문화콘텐츠',2021,'기타',300,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (301,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',300,15,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (302,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',300,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (303,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',300,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (304,'(정재승의) 인간 탐구 보고서','글: 정재은,그림: 김현민','북이십일',2019,'기타',304,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (305,'하쿠다 사진관 :허태연 장편소설','지은이: 허태연','다산북스',2022,'소설',305,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (306,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',306,17,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (307,'장수탕 선녀님','지은이: 백희나','책읽는곰',2012,'기타',307,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (308,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',308,46,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (309,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',308,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (310,'(내 멋대로) 친구 뽑기','최은옥 글 ,김무연 그림','김영사',2016,'기타',310,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (311,'천하무적 개냥이 수사대','이승민 글 ,하민석 그림','위즈덤하우스',2020,'기타',311,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (312,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2019,'기타',311,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (313,'선량한 차별주의자','김지혜 지음','창비',2019,'기타',313,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (314,'(고정욱 선생님이 들려주는) 다정한 말 단단한 말','고정욱 글 ,릴리아 그림','우리학교',2022,'기타',314,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (315,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',315,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (316,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',316,29,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (317,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',316,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (318,'최재천의 공부 :어떻게 배우며 살 것인가','지은이: 최재천,안희경','김영사',2022,'기타',318,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (319,'어느 날, 노비가 되었다','글: 지은지,그림: 유영근','지학사',2022,'기타',318,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (320,'내 멋대로 반려동물 뽑기','최은옥 글 ,김무연 그림','김영사',2020,'기타',320,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (321,'할머니의 여름휴가','안녕달','창비',2016,'기타',320,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (322,'웰씽킹 =부를 창조하는 생각의 뿌리 /Wealthinking','켈리 최 지음','다산북스',2021,'기타',322,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (323,'팥빙수의 전설 :이지은 그림책','글·그림: 이지은','웅진씽크빅',2019,'기타',322,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (324,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',324,12,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (325,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2022,'기타',325,12,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (326,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',326,18,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (327,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',326,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (328,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',328,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (329,'오늘 밤, 세계에서 이 눈물이 사라진다 해도','이치조 미사키 지음 ,김윤경 옮김','바이포엠 스튜디오',2022,'기타',328,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (330,'천하제일 치킨 쇼','이희정 글 ,김무연 그림','비룡소',2023,'기타',328,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (331,'건전지 아빠 :전승배·강인숙 그림책','지은이: 전승배,강인숙','창비',2021,'기타',331,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (332,'네 기분은 어떤 색깔이니? :최숙희 그림책','최숙희','책읽는곰',2023,'기타',331,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (333,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',333,32,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (334,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',333,13,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (335,'알사탕','백희나','책읽는곰',2017,'기타',335,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (336,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',336,47,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (337,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',337,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (338,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',338,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (339,'Go go 카카오 프렌즈 :세계 역사 문화 체험 학습만화','글: 김미영 ,그림: 김정한','북이십일',2018,'역사',339,14,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (340,'모순 :양귀자 장편소설','지은이: 양귀자','쓰다',2013,'소설',339,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (341,'채사장의 지대넓얕 :지적 대화를 위한 넓고 얕은 지식','글: 채사장,그림: 정용환','Dolphin Books(돌핀북)',2021,'기타',339,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (342,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',342,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (343,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',342,28,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (344,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',344,23,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (345,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',344,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (346,'방구석 미술관 :가볍고 편하게 시작하는 유쾌한 교양 미술','조원재 지음','백도씨',2018,'기타',346,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (347,'채사장의 지대넓얕 :지적 대화를 위한 넓고 얕은 지식','글: 채사장,그림: 정용환','Dolphin Books(돌핀북)',2021,'기타',347,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (348,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',348,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (349,'마법천자문 :손오공의 한자 대탐험','저자: 스튜디오시리얼,정수영','아울북',2005,'기타',349,45,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (350,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',349,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (351,'인스타 브레인 =몰입을 빼앗긴 시대, 똑똑한 뇌 사용법 /Insta brain','안데르스 한센 지음 ,김아영 옮김','동양북스',2020,'기타',351,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (352,'바삭바삭 갈매기','전민걸 글·그림','한림출판사',2014,'기타',351,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (353,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2019,'기타',353,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (354,'위풍당당 여우 꼬리','손원평 글 ,만물상 그림','창비',2021,'기타',353,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (355,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',355,25,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (356,'설민석의 삼국지 대모험','원작: 나관중 ,만화: 스튜디오 담','Dankkum i(단꿈아이)',2021,'기타',355,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (357,'두근두근 편의점 :김영진 그림책','지은이: 김영진','책읽는곰',2022,'기타',357,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (358,'에그박사 9 - 자연 생물 관찰 만화','에그박사 (지은이), 박송이 (글), 홍종현 (그림), 이승현 (감수)','미래엔아이세움',2023,'기타',358,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (359,'쇼코의 미소 :최은영 소설','지은이: 최은영','문학동네',2016,'소설',359,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (360,'만복이네 떡집','김리리 글 ,이승현 그림','비룡소',2010,'기타',359,354,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (361,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2019,'기타',359,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (362,'(전설의 모험왕) 엉덩이 댄디 :the young','원작·감수: 트롤 ,옮긴이: 김정화','미래엔',2021,'기타',362,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (363,'꽁꽁꽁 아이스크림 :윤정주 그림책','윤정주','책읽는곰',2022,'기타',362,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (364,'세금 내는 아이들','옥효진 글 ,김미연 그림','한경BP',2021,'기타',362,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (365,'당연하게도 나는 너를','이꽃님 (지은이)','우리학교',2023,'기타',365,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (366,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2022,'기타',366,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (367,'숲속 100층짜리 집','글·그림: 이와이 도시오 ,옮김: 김숙','북뱅크',2021,'기타',366,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (368,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',368,12,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (369,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',369,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (370,'의사 어벤저스 :어린이 의학 동화','글: 고희정 ,그림: 조승연','가나문화콘텐츠',2021,'기타',370,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (371,'무조건 합격하는 암기의 기술 :26살 9개월 만에 사법시험을 패스한 이윤규 변호사의 책 한 권 통째로 씹어먹는 공부법','이윤규 지음','길벗',2023,'과학',371,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (372,'(정재승의) 인간 탐구 보고서','글: 정재은,그림: 김현민','북이십일',2019,'기타',372,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (373,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',372,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (374,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',372,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (375,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',375,13,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (376,'Go go 카카오프렌즈 :자연탐사','글: 조주희 ,이혜림','북이십일',2022,'기타',375,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (377,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',377,21,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (378,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',377,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (379,'수상한 중고상점 :미치오 슈스케 장편소설','지은이: 미치오 슈스케 ,옮긴이: 김은모','다산북스',2022,'소설',379,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (380,'천하무적 개냥이 수사대','이승민 글 ,하민석 그림','위즈덤하우스',2020,'기타',379,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (381,'정재승의 인류 탐험 보고서','글: 차유진,그림: 김현민','북이십일',2021,'기타',381,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (382,'아빠 자판기 :아빠, 사랑, 딸, 놀이','글·그림: 조경희','노란돼지',2021,'기타',381,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (383,'기분이 태도가 되지 않게 :기분 따라 행동하다 손해 보는 당신을 위한 심리 수업','레몬심리 지음 ,박영란 옮김','웅진씽크빅',2020,'기타',381,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (384,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',384,22,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (385,'튤립 호텔 :김지안 그림책','지은이: 김지안','창비',2022,'기타',385,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (386,'내 멋대로 나 뽑기','최은옥 글 ,김무연 그림','김영사',2018,'기타',386,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (387,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',387,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (388,'채식주의자:한강 연작소설','한강','창비',2007,'소설',388,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (389,'기분을 관리하면 인생이 관리된다 :김다슬 에세이','지은이: 김다슬','클라우디아',2022,'기타',388,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (390,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',388,24,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (391,'최재천의 동물대탐험 1 : 비글호의 푸른 유령 - 동물들의 숨바꼭질 ''의태''','황혜영 (글), 박현미 (그림), 최재천 (기획), 안선영 (해설)','다산어린이',2022,'기타',391,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (392,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2022,'기타',391,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (393,'정재승의 인류 탐험 보고서','글: 차유진,그림: 김현민','북이십일',2021,'기타',391,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (394,'에그박사의 채집 일기 :스티커로 채집하는 자연 생물 관찰 만화','원작: 에그박사 ,그림: 홍종현','Mirae N 아이세움',2021,'기타',394,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (395,'천하무적 개냥이 수사대','이승민 글 ,하민석 그림','위즈덤하우스',2020,'기타',394,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (396,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',394,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (397,'부의 추월차선 :부자들이 말해 주지 않는 진정한 부를 얻는 방법','엠제이 드마코 지음 ,신소영 옮김','북새통',2013,'기타',397,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (398,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',397,26,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (399,'위풍당당 여우 꼬리','손원평 글 ,만물상 그림','창비',2021,'기타',397,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (400,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',400,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (401,'슈퍼 거북','유설화 글·그림','책읽는곰',2014,'기타',401,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (402,'천하무적 개냥이 수사대','이승민 글 ,하민석 그림','위즈덤하우스',2020,'기타',402,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (403,'읽으면서 바로 써먹는 어린이 맞춤법 행성','한날 (지은이)','파란정원',2023,'기타',402,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (404,'틀려도 괜찮아','마키타 신지 글,유문조 옮김','토토북',2006,'기타',402,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (405,'지구에서 한아뿐 :정세랑 장편소설','지은이: 정세랑','난다',2019,'소설',402,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (406,'양순이네 떡집','김리리 (지은이), 김이랑 (그림)','비룡소',2021,'기타',406,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (407,'어느 날, 노비가 되었다','글: 지은지,그림: 유영근','지학사',2022,'기타',407,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (408,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',407,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (409,'(정재승의) 인간 탐구 보고서','글: 정재은,그림: 김현민','북이십일',2019,'기타',407,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (410,'이상한 엄마 1','백희나','책읽는곰',2016,'기타',410,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (411,'(읽으면서 바로 써먹는) 어린이 맞춤법','글·그림: 한날','파란정원',2019,'기타',411,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (412,'모든 것은 기본에서 시작한다 :실력도 기술도 사람 됨됨이도, 기본을 지키는 손웅정의 삶의 철학','손웅정 지음','수오서재',2021,'과학',411,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (413,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2019,'기타',413,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (414,'(정재승의) 인간 탐구 보고서','글: 정재은,그림: 김현민','북이십일',2019,'기타',414,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (415,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2019,'기타',414,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (416,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2019,'기타',416,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (417,'미움받을 용기 :자유롭고 행복한 삶을 위한 아들러의 가르침','기시미 이치로,전경아 옮김','인플루엔셜',2014,'기타',416,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (418,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',418,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (419,'(오은영 박사가 전하는) 금쪽이들의 진짜 마음속','오은영 지음','Oeun(오은라이프사이언스)',2022,'기타',418,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (420,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2019,'기타',420,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (421,'열세 살 우리는','문경민 (지은이), 이소영 (그림)','우리학교',2023,'기타',421,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (422,'리보와 앤 :아무도 오지 않는 도서관의 두 로봇','어윤정 글 ,해마 그림','문학동네',2023,'소설',422,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (423,'달콩이네 떡집','김리리 글 ,김이랑 그림','비룡소',2021,'기타',423,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (424,'채사장의 지대넓얕 :지적 대화를 위한 넓고 얕은 지식','글: 채사장,그림: 정용환','Dolphin Books(돌핀북)',2021,'기타',424,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (425,'내 멋대로 아빠 뽑기','최은옥 글 ,김무연 그림','김영사',2017,'기타',425,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (426,'의사 어벤저스 :어린이 의학 동화','글: 고희정 ,그림: 조승연','가나문화콘텐츠',2021,'기타',426,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (427,'인생의 역사 :신형철 시화','지은이: 신형철','난다',2022,'역사',426,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (428,'친구의 전설 :이지은 그림책','글·그림: 이지은','웅진씽크빅',2021,'기타',428,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (429,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',428,27,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (430,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',430,12,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (431,'(백종원의) 도전 요리왕 :음식으로 맛보는 세계 역사 문화 체험','글: 백종원,그림: 이정태','위즈덤하우스 미디어그룹',2019,'역사',430,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (432,'정재승의 인류 탐험 보고서','글: 차유진,그림: 김현민','북이십일',2021,'기타',432,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (433,'나도 상처 받지 않고 친구도 상처 받지 않는 말하기 연습','강승임 (지은이), 김규정 (그림)','위즈덤하우스',2023,'기타',432,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (434,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',434,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (435,'종이달 :가쿠타 미쓰요 장편소설','지은이: 가쿠다 미쓰요 ,옮긴이: 권남희','위즈덤하우스',2014,'소설',434,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (436,'설민석의 삼국지 대모험 10 - 동탁의 최후','단꿈아이 (지은이), 스튜디오 담 (그림)','단꿈아이',2022,'기타',434,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (437,'방주 :유키 하루오 장편소설','지은이: 유키 하루오 ,옮긴이: 김은모','블루홀6(블루홀식스)',2023,'소설',437,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (438,'사라진 소녀들의 숲 :허주은 장편소설','지은이: 허주은 ,옮긴이: 유혜인','M창비(미디어창비)',2022,'소설',437,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (439,'긴긴밤','루리 글·그림','문학동네',2021,'소설',439,83,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (440,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',439,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (441,'돈은 좋지만 재테크는 겁나는 너에게','뿅글이 지음','첨단',2023,'기타',441,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (442,'(양육, 학습, 입시를 꿰뚫는) 자녀교육 절대공식','방종임 지음','위즈덤하우스',2023,'기타',442,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (443,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',443,15,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (444,'지리의 힘 :지리는 어떻게 개인의 운명을, 세계사를, 세계 경제를 좌우하는가','지은이: 팀 마샬 ,옮긴이: 김미선','사이',2016,'역사',443,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (445,'피프티 피플 :정세랑 장편소설','지은이: 정세랑','창비',2016,'소설',445,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (446,'비밀요원 레너드','글: 박설연 ,그림: 김덕영','북이십일',2019,'기타',446,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (447,'엉뚱한 문방구','글·그림: 간장','제제의숲',2022,'기타',446,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (448,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',448,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (449,'어린 왕자','생텍쥐페리 지음,김숙 그림','가나출판사',2003,'기타',448,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (450,'설민석의 삼국지 대모험','원작: 나관중 ,만화: 스튜디오 담','Dankkum i(단꿈아이)',2021,'기타',448,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (451,'설민석의 삼국지 대모험','원작: 나관중 ,만화: 스튜디오 담','Dankkum i(단꿈아이)',2021,'기타',448,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (452,'안녕 팝콘','이준혁 원작 ,강한 그림','M창비(미디어창비)',2022,'기타',452,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (453,'놓지 마 과학! 7 - 정신이 모험에 정신 놓다','신태훈|나승훈','위즈덤하우스',2018,'과학',453,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (454,'에그박사의 채집 일기 :스티커로 채집하는 자연 생물 관찰 만화','원작: 에그박사 ,그림: 홍종현','Mirae N 아이세움',2021,'기타',453,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (455,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',455,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (456,'(백종원의) 도전 요리왕 :음식으로 맛보는 세계 역사 문화 체험','글: 백종원,그림: 이정태','위즈덤하우스 미디어그룹',2019,'역사',456,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (457,'(설민석의) 그리스 로마 신화 대모험','글: 설민석,만화: 이미나','Dankkum i(단꿈아이)',2022,'기타',456,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (458,'시간이 있었으면 좋겠다 :김신지 에세이','지은이: 김신지','잠비',2023,'기타',458,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (459,'한문철의 어린이 교통안전 :어린이가 스스로 안전을 지키는 그날까지','글: 유경원 ,그림: 파키나미','다산북스',2023,'기타',458,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (460,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',458,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (461,'아주 오랜만에 행복하다는 느낌 :백수린 에세이','지은이: 백수린','창비',2022,'기타',458,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (462,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈드림',2019,'역사',462,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (463,'감기 걸린 물고기 :박정섭 그림책','글·그림: 박정섭','사계절',2016,'기타',462,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (464,'사장학개론','지은이: 김승호','스노우폭스북스',2023,'기타',464,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (465,'(어린이를 위한) 역사의 쓸모','글: 최태성 ,그림: 신진호','다산북스',2022,'역사',464,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (466,'더 좋은 삶을 위한 철학','지은이: 마이클 슈어 ,옮긴이: 염지선','김영사',2023,'기타',464,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (467,'슈퍼 토끼의 결심','프란치스카 비어만 (지은이), 송순섭 (옮긴이)','주니어김영사',2022,'기타',464,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (468,'잘될 수밖에 없는 너에게 :최서영 에세이','지은이: 최서영','북로망스',2022,'기타',464,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (469,'책 먹는 여우의 겨울 이야기','프란치스카 비어만 글·그림 ,송순섭 옮김','김영사',2020,'소설',469,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (470,'놓지 마 과학!','글·그림: 신태훈,나승훈','위즈덤하우스 미디어그룹',2016,'과학',470,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (471,'내 멋대로 동생 뽑기','최은옥 (지은이), 김무연 (그림)','주니어김영사',2019,'기타',471,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (472,'(정재승의) 인간 탐구 보고서','글: 정재은,그림: 김현민','북이십일',2019,'기타',471,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (473,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',471,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (474,'수학 잘하는 아이는 이렇게 공부합니다 :수학이 어려운 엄마를 위한 전략적 학습 로드맵','류승재 지음','블루무스',2021,'기타',474,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (475,'사랑의 이해 :이혁진 장편소설','지은이: 이혁진','민음사',2019,'소설',475,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (476,'원청 :위화 장편소설','지은이: 위화 ,옮긴이: 문현선','푸른숲',2022,'소설',475,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (477,'의사 어벤저스 :어린이 의학 동화','글: 고희정 ,그림: 조승연','가나문화콘텐츠',2021,'기타',477,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (478,'바다 100층짜리 집','글·그림: 이와이 도시오 ,옮김: 김숙','북뱅크',2014,'기타',477,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (479,'의사 어벤저스 :어린이 의학 동화','글: 고희정 ,그림: 조승연','가나문화콘텐츠',2021,'기타',477,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (480,'(백종원의) 도전 요리왕 :음식으로 맛보는 세계 역사 문화 체험','글: 백종원,그림: 이정태','위즈덤하우스 미디어그룹',2019,'역사',480,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (481,'마당이 있는 집','김진영 지음','문학동네',2018,'소설',481,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (482,'언스크립티드 :부의 추월차선 완결판','엠제이 드마코 지음 ,안시열 옮김','토트',2018,'기타',481,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (483,'(설민석의) 그리스 로마 신화 대모험','글: 설민석,만화: 이미나','Dankkum i(단꿈아이)',2022,'기타',481,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (484,'(백종원의) 도전 요리왕 :음식으로 맛보는 세계 역사 문화 체험','글: 백종원,그림: 이정태','위즈덤하우스 미디어그룹',2019,'역사',484,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (485,'두더지의 여름 :김상근 그림책','글·그림: 김상근','사계절(사계절출판사)',2022,'기타',484,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (486,'나의 돈 많은 고등학교 친구 - 슈퍼리치와의 대화에서 찾아낸 부자의 길','송희구 (지은이)','서삼독',2023,'기타',486,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (487,'(읽으면서 바로 써먹는) 어린이 고사성어','글·그림: 한날','파란정원',2018,'기타',487,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (488,'오백 년째 열다섯 2 - 구슬의 무게','김혜정 (지은이)','위즈덤하우스',2023,'기타',488,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (489,'십 년 가게','히로시마 레이코 글 ,이소담 옮김','위즈덤하우스 미디어그룹',2020,'기타',489,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (490,'우리가 케이크를 먹는 방법 :김효은 그림책','지은이: 김효은','문학동네',2022,'소설',490,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (491,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',491,20,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (492,'자발적 방관육아 :스스로 공부하는 아이로 키우는 엄마의 이유 있는 게으름','최은아 지음','쌤앤파커스',2023,'기타',492,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (493,'고요한 우연 - 제13회 문학동네청소년문학상 대상 수상작','김수빈 (지은이)','문학동네',2023,'소설',492,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (494,'(읽으면서 바로 써먹는) 어린이 속담','글·그림: 한날','파란정원',2017,'기타',494,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (495,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',495,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (496,'재수사 :장강명 장편소설','지은이: 장강명','은행나무',2022,'소설',495,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (497,'바깥은 여름 :김애란 소설','지은이: 김애란','문학동네',2017,'소설',497,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (498,'벌거벗은 세계사','글: 이현희,최호정','북이십일',2022,'역사',497,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (499,'트렌드 코리아 2023 :더 높은 도약을 준비하는 검은 토끼의 해','지은이: 김난도,추예린','미래의창',2022,'기타',499,2023,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (500,'(백종원의) 도전 요리왕 :음식으로 맛보는 세계 역사 문화 체험','글: 백종원,그림: 이정태','위즈덤하우스 미디어그룹',2019,'역사',499,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (501,'설민석의 삼국지 대모험','원작: 나관중 ,만화: 스튜디오 담','Dankkum i(단꿈아이)',2021,'기타',501,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (502,'눈아이 :안녕달 그림책','지은이: 안녕달','창비',2021,'기타',502,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (503,'(류승재 특급 비법) 진짜 수학 공부법 :수학 핵심 공략법 & 수학 공부 로드맵','류승재 지음','경향BP(경향비피)',2023,'기타',503,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (504,'마음버스','글: 김유 ,그림: 소복이','천개의바람',2022,'기타',504,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (505,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',504,16,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (506,'정재승의 인류 탐험 보고서','글: 차유진,그림: 김현민','북이십일',2021,'기타',506,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (507,'작은 땅의 야수들 :김주혜 장편소설','지은이: 김주혜 ,옮긴이: 박소현','다산북스',2022,'소설',507,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (508,'디스코 팡팡 소시지 :박세랑 그림책','글·그림: 박세랑','Fika Junior(피카주니어)',2022,'기타',508,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (509,'그릿 :IQ, 재능, 환경을 뛰어넘는 열정적 끈기의 힘','앤절라 더크워스 지음 ,김미정 옮김','비즈니스북스',2016,'기타',508,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (510,'이게 정말 마음일까?','요시타케 신스케 글·그림 ,양지연 옮김','김영사',2020,'기타',510,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (511,'시간을 파는 상점 :김선영 장편소설','지은이: 김선영','자음과모음',2012,'소설',510,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (512,'(전설의 모험왕) 엉덩이 댄디 :the young','원작·감수: 트롤 ,옮긴이: 김정화','미래엔',2021,'기타',510,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (513,'견딜 수 없는 사랑','이언 매큐언 (지은이), 한정아 (옮긴이)','복복서가',2023,'기타',510,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (514,'슈퍼 토끼 :유설화 그림책','유설화 글·그림','책읽는곰',2020,'기타',514,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (515,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',514,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (516,'에그박사의 닮은꼴 사파리 :전격비교관찰 생물도감','에그박사 지음 ,유남영 그림','다락원',2020,'기타',516,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (517,'의사 어벤저스 :어린이 의학 동화','글: 고희정 ,그림: 조승연','가나문화콘텐츠',2021,'기타',516,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (518,'아비투스 :인간의 품격을 결정하는 7가지 자본','도리스 메르틴 지음 ,배명자 옮김','다산북스',2020,'기타',516,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (519,'이상한 손님','백희나','책읽는곰',2018,'기타',519,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (520,'의사 어벤저스 :어린이 의학 동화','글: 고희정 ,그림: 조승연','가나문화콘텐츠',2021,'기타',519,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (521,'156층 나무 집','앤디 그리피스 (지은이), 테리 덴톤 (그림), 신수진 (옮긴이)','시공주니어',2023,'기타',521,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (522,'설민석의 세계사 대모험','글: 설민석,그림: 박성일','단꿈아이',2019,'역사',522,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (523,'(읽으면서 바로 써먹는) 어린이 신체 관용구','글·그림: 한날','파란정원',2021,'기타',522,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (524,'총, 균, 쇠 :무기, 병균, 금속 은 인류 의 운명 을 어떻게 바꿨는가','재레드 다이아몬드 지음 ,김진준 옮김','문학사상',2012,'소설',524,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (525,'월급쟁이 부자로 은퇴하라 :3년 만에 30년 치 연봉을 번 김 과장의 시스템 마련법','너나위 지음','RHK(알에이치코리아)',2019,'기타',525,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (526,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',526,18,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (527,'의사 어벤저스 :어린이 의학 동화','글: 고희정 ,그림: 조승연','가나문화콘텐츠',2021,'기타',526,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (528,'80년대생 학부모, 당신은 누구십니까 :우리의 미래를 좌우할 새로운 세대 발견, 더 하이퍼리얼 보고서','이은경 지음','메타미디어월드',2023,'기타',526,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (529,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',529,19,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (530,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',530,14,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (531,'설민석의 삼국지 대모험','원작: 나관중 ,만화: 스튜디오 담','Dankkum i(단꿈아이)',2021,'기타',530,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (532,'좋아 싫어 대신 뭐라고 말하지? - 어린이 감정 공부 그림책','송현지 (지은이), 순두부 (그림)','이야기공간',2023,'소설',532,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (533,'백 번 산 고양이 백꼬선생','정연철 글 ,오승민 그림','우리학교',2022,'기타',532,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (534,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',532,16,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (535,'이기적 유전자','리처드 도킨스 지음 ,이상임 옮김','을유문화사',2018,'기타',535,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (536,'코믹 메이플 스토리 수학도둑 90 - 국내 최초 수학논술만화','송도수 (지은이), 서정 엔터테인먼트 (그림), 여운방 (감수)','서울문화사',2022,'기타',535,90,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (537,'7년의 밤 :정유정 장편소설','지은이: 정유정','은행나무',2011,'소설',537,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (538,'순간을 믿어요 :이석원 이야기 산문집','지은이: 이석원','을유문화사',2023,'소설',538,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (539,'칵테일, 러브, 좀비','지은이: 조예은','안전가옥',2020,'기타',539,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (540,'나는 나의 주인','채인선 글 ,안은진 그림','토토북',2010,'기타',540,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (541,'설민석의 삼국지 대모험','원작: 나관중 ,만화: 스튜디오 담','Dankkum i(단꿈아이)',2021,'기타',541,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (542,'내 멋대로 행운 뽑기','최은옥 글 ,김무연 그림','김영사',2021,'기타',542,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (543,'열린 어둠 :렌조 미키히코 단편집','지은이: 렌조 미키히코 ,옮긴이: 양윤옥','바이포엠 스튜디오',2022,'기타',543,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (544,'(읽으면서 바로 써먹는) 어린이 수수께끼','글·그림: 한날','파란정원',2020,'기타',544,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (545,'돌이킬 수 없는 약속 :야쿠마루 가쿠 장편소설','저자: 야쿠마루 가쿠 ,옮긴이: 김성미','Book plaza(북플라자)',2017,'소설',544,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (546,'아주 무서운 날','탕무니우 글·그림 ,홍연숙 옮김','찰리북',2014,'기타',544,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (547,'설민석의 삼국지 대모험','원작: 나관중 ,만화: 스튜디오 담','Dankkum i(단꿈아이)',2021,'기타',544,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (548,'인생의 허무를 어떻게 할 것인가','지은이: 김영민','사회평론아카데미',2022,'기타',548,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (549,'그리스 로마 신화','글: 박시연 ,그림: 최우빈','북이십일',2017,'기타',549,17,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (550,'꽁꽁꽁 좀비','윤정주 글·그림','책읽는곰',2021,'기타',549,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (551,'슈퍼 히어로의 똥 닦는 법','안영은 글 ,최미란 그림','책읽는곰',2018,'기타',551,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (552,'소원 떡집','김리리 글 ,이승현 그림','비룡소',2020,'기타',552,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (553,'장군이네 떡집','김리리 글 ,이승현 그림','비룡소',2020,'기타',553,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (554,'챗GPT에게 묻는 인류의 미래 :김대식 교수와 생성인공지능의 대화','지은이: 김대식,추서연','동아시아',2023,'기타',554,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (555,'나는 나로 살기로 했다 :냉담한 현실에서 어른살이를 위한 to do list','글·그림: 김수현','마음의숲',2016,'기타',555,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (556,'작별하지 않는다 :한강 장편소설','지은이: 한강','문학동네',2021,'소설',556,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (557,'트러스트 :에르난 디아스 장편소설','지은이: 에르난 디아스 ,옮긴이: 강동혁','문학동네',2023,'소설',556,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (558,'(읽으면서 바로 써먹는) 어린이 관용구','글·그림: 한날','파란정원',2018,'기타',556,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (559,'(처음 읽는) 그리스 로마 신화 =First Greek mythology','글: 최설희 ,그림: 한현동','미래엔',2020,'기타',556,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (560,'녹나무의 파수꾼 =The camphorwood custodian','저자: 히가시노 게이고 ,옮긴이: 양윤옥','소미미디어',2020,'기타',556,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (561,'(처음 읽는) 그리스 로마 신화 =First Greek mythology','글: 최설희 ,그림: 한현동','미래엔',2020,'기타',556,8,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (562,'브레드이발소 2 : 귀염뽀짝 베이커리타운','(주)몬스터주식회사 (지은이)','한솔수북',2020,'기타',562,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (563,'책 먹는 여우','프란치스카 비어만 지음 ,김경연 옮김','김영사',2001,'기타',563,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (564,'호랑이 빵집 1 : 신단 쑥 위조 사건','서지원 (지은이), 홍그림 (그림)','아르볼',2023,'기타',563,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (565,'코스모스 :특별판','칼 세이건 지음 ,홍승수 옮김','사이언스북스',2006,'기타',563,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (566,'애쓰지 않아도 :최은영 짧은 소설','지은이: 최은영 ,그린이: 김세희','마음산책',2022,'소설',563,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (567,'정재승의 인류 탐험 보고서','글: 차유진,그림: 김현민','북이십일',2021,'기타',563,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (568,'이게 정말 뭘까?','요시타케 신스케 글·그림 ,김정화 옮김','김영사',2020,'기타',568,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (569,'(그대로 따라 하면 식비가 줄어드는) 기적의 집밥책 :유기농 재료로 만드는 알뜰하고 맛있는 집밥 공식','김해진 지음','청림출판',2023,'기타',569,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (570,'수상한 지하실','박현숙 글 ,장서영 그림','북멘토',2022,'기타',570,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (571,'최재천의 동물대탐험 2 : 나무늘보의 노래 - 달라서 좋아, 동물들의 생존 전략','황혜영 (글), 박현미 (그림), 최재천 (기획), 안선영 (해설)','다산어린이',2023,'기타',570,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (572,'(처음 읽는) 그리스 로마 신화 =First Greek mythology','글: 최설희 ,그림: 한현동','미래엔',2020,'기타',570,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (573,'채사장의 지대넓얕 6 : 성장 VS 분배 - 지적 대화를 위한 넓고 얕은 지식','채사장, 마케마케 (지은이), 정용환 (그림)','돌핀북',2023,'기타',570,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (574,'연이와 버들 도령','백희나','책읽는곰',2022,'기타',574,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (575,'나는 개다','백희나','책읽는곰',2019,'기타',575,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (576,'엄마의 말 연습 :화내지 않고 사랑하는 마음을 오롯이 전하는 39가지 존중어 수업','윤지영 지음','카시오페아(카시오페아 출판사)',2022,'기타',575,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (577,'모든 삶은 흐른다 :삶의 지표가 필요한 당신에게 바다가 건네는 말','로랑스 드빌레르 지음 ,이주영 옮김','Fika(피카)',2023,'기타',575,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (578,'(백종원의) 도전 요리왕 :음식으로 맛보는 세계 역사 문화 체험','글: 백종원,그림: 이정태','위즈덤하우스 미디어그룹',2019,'역사',575,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (579,'(처음 읽는) 그리스 로마 신화 =First Greek mythology','글: 최설희 ,그림: 한현동','미래엔',2020,'기타',579,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (580,'수학 탐정스','조인하 글 ,조승연 그림','미래엔',2018,'기타',580,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (581,'(한밤중) 달빛 식당','이분희 글 ,윤태규 그림','비룡소',2018,'기타',581,265,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (582,'(이상한 과자 가게) 전천당 :공식 가이드북','글: 히로시마 레이코 ,옮긴이: 김정화','길벗스쿨',2022,'기타',581,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (583,'(EBS) 당신의 문해력 :공부의 기초체력을 키워주는 힘','글: 김윤정','한국교육방송공사',2021,'기타',581,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (584,'(아이에게 주는) 감정 유산 :가족심리학자 엄마가 열어준 마음 성장의 힘','이남옥 지음','라이프앤페이지',2023,'기타',584,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (585,'신경 끄기 연습 :걱정, 초조, 두려움을 뛰어넘는 61가지 심리 기술','나이토 요시히토 지음 ,김한나 옮김','유노콘텐츠그룹',2023,'과학',585,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (586,'(읽으면서 바로 써먹는) 어린이 퀴즈','글·그림: 한날','파란정원',2019,'기타',586,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (587,'이상한 무인 아이스크림 가게','서아람 (지은이), 안병현 (그림)','라곰스쿨',2023,'기타',586,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (588,'깊은 밤 필통 안에서','길상효 글 ,심보영 그림','비룡소',2021,'기타',588,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (589,'고구마구마','지은이: 사이다','반달',2017,'기타',588,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (590,'멘탈을 바꿔야 인생이 바뀐다 :나의 잠재력을 최대로 끌어올려 100% 성공하는 삶을 사는 방법','박세니 지음','바이포엠 스튜디오',2022,'자기계발',588,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (591,'재수사 :장강명 장편소설','지은이: 장강명','은행나무',2022,'소설',588,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (592,'물 요정의 숲','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2022,'기타',592,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (593,'의사 어벤저스 12 : 정신 질환, 마음이 아프다! - 어린이 의학 동화','고희정 (지은이), 조승연 (그림), 류정민 (감수)','가나출판사',2023,'기타',592,12,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (594,'(처음 읽는) 그리스 로마 신화 =First Greek mythology','글: 최설희 ,그림: 한현동','미래엔',2020,'기타',592,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (595,'(백종원의) 도전 요리왕 :음식으로 맛보는 세계 역사 문화 체험','글: 백종원,그림: 이정태','위즈덤하우스 미디어그룹',2019,'역사',595,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (596,'코믹 메이플 스토리 수학도둑 92 - 국내 최초 수학논술만화','송도수 (지은이), 서정 엔터테인먼트 (그림), 여운방 (감수)','서울문화사',2023,'기타',595,92,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (597,'푸른 사자 와니니 :이현 장편동화','지은이: 이현 ,그린이: 오윤화','창비',2015,'기타',597,280,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (598,'설민석의 삼국지 대모험','원작: 나관중 ,만화: 스튜디오 담','Dankkum i(단꿈아이)',2021,'기타',597,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (599,'천문학자는 별을 보지 않는다','심채경 지음','문학동네',2021,'소설',597,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (600,'우다다 꽁냥파크 - 제2회 리틀 스토리킹 수상작','권혁진 (지은이), 심보영 (그림)','비룡소',2023,'기타',600,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (601,'코믹 메이플 스토리 수학도둑 91 - 국내 최초 수학논술만화','송도수 (지은이), 서정 엔터테인먼트 (그림), 여운방 (감수)','서울문화사',2022,'기타',601,91,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (602,'(어린이를 위한) 역사의 쓸모','글: 최태성 ,그림: 신진호','다산북스',2022,'역사',601,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (603,'마법의 설탕 두 조각','미카엘 엔데 글,유혜자 옮김','한길사',2001,'기타',603,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (604,'출동, 고양이 요원 캣스코','박주혜 글 ,홍그림 그림','김영사',2022,'기타',603,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (605,'돈 공부는 처음이라 :0원부터 시작하는 난생처음 부자 수업','김종봉,제갈현열 지음','다산북스',2019,'기타',603,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (606,'하늘 100층짜리 집','글·그림: 이와이 도시오 ,옮김: 김숙','북뱅크',2017,'기타',606,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (607,'공부머리 독서법 :실현 가능하고 지속 가능한 독서교육의 모든 것','최승필 지음','책구루',2018,'기타',606,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (608,'일의 기쁨과 슬픔 :장류진 소설집','지은이: 장류진','창비',2019,'소설',608,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (609,'오베라는 남자 :프레드릭 배크만 장편소설','지은이: 프레드릭 배크만 ,옮긴이: 최민우','다산북스',2015,'소설',608,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (610,'머리는 이렇게 부스스해도','요시타케 신스케 글·그림 ,권남희 옮김','김영사',2022,'기타',610,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (611,'당신이 옳다 :정혜신의 적정심리학','지은이: 정혜신','해냄',2018,'기타',610,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (612,'(처음 읽는) 그리스 로마 신화 =First Greek mythology','글: 최설희 ,그림: 한현동','미래엔',2020,'기타',612,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (613,'죽고 싶지만 떡볶이는 먹고 싶어 :백세희 에세이','지은이: 백세희','흔',2018,'기타',613,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (614,'알로하, 나의 엄마들 :이금이 장편소설','지은이: 이금이','창비',2020,'소설',614,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (615,'나의 아름다운 날들 :정지아 소설','지은이: 정지아','은행나무',2023,'소설',614,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (616,'지하 100층짜리 집','글·그림: 이와이 도시오 ,옮긴이: 김숙','북뱅크',2011,'기타',614,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (617,'벌거벗은 세계사','글: 이현희,최호정','북이십일',2022,'역사',617,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (618,'내게 무해한 사람 :최은영 소설','지은이: 최은영','문학동네',2018,'소설',617,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (619,'마음이 흐르는 대로 =삶이 흔들릴 때 우리가 바라봐야 할 단 한 가지 /Follow your heart','지나영 지음','다산북스',2020,'기타',619,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (620,'참 괜찮은 태도 :15년 동안 길 위에서 만난 수많은 사람들에게 배운 삶의 의미','지은이: 박지현 ,일러스트: 오하이오','메이븐',2022,'기타',619,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (621,'내 멋대로 산타 뽑기','최은옥 글 ,김무연 그림','김영사',2021,'기타',621,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (622,'나의 투자는 새벽 4시에 시작된다 :3년 만에 300억으로 돌아온 유목민의 투자 인사이트','유목민 지음','웅진씽크빅',2022,'기타',622,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (623,'트로피컬 나이트 =조예은 소설집 /Tropical night','지은이: 조예은','한겨레엔',2022,'소설',623,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (624,'(코믹 메이플스토리) 수학도둑 :언제 어디에서나 원리를 응용하여 문제를 격파하는 수학의 해결사!','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',2021,'기타',623,84,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (625,'설민석의 삼국지 대모험','원작: 나관중 ,만화: 스튜디오 담','Dankkum i(단꿈아이)',2021,'기타',623,11,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (626,'에그박사의 이건 누구 똥?!','원작: 에그박사 ,그림: 김덕영','Mirae N 아이세움',2022,'기타',623,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (627,'열 번 잘해도 한 번 실수로 무너지는 게 관계다 :김다슬 에세이','지은이: 김다슬','필름',2023,'기타',623,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (628,'(읽으면서 바로 써먹는) 어린이 한국사 퀴즈','글·그림: 한날','파란정원',2021,'역사',628,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (629,'레버리지 :자본주의 속에 숨겨진 부의 비밀','롭 무어 지음 ,김유미 옮김','다산북스',2017,'기타',628,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (630,'오늘부터 배프! 베프!','지안 글 ,김성라 그림','문학동네',2021,'소설',628,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (631,'클루지 :생각의 역사를 뒤집는 기막힌 발견','지은이: 개리 마커스 ,옮긴이: 최호영','웅진씽크빅',2008,'역사',628,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (632,'주식시세의 비밀','정재호 지음','프런트페이지',2023,'기타',628,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (633,'게임하고 싶어!','김영진 글·그림','길벗어린이',2022,'기타',633,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (634,'정리하는 뇌 :디지털 시대, 정보와 선택 과부하로 뒤엉킨 머릿속과 일상을 정리하는 기술','대니얼 J. 레비틴 지음 ,김성훈 옮김','미래엔',2014,'과학',634,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (635,'세상 끝의 카페','존 스트레레키 지음 ,고상숙 옮김','클레이하우스',2023,'기타',635,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (636,'갑자기 악어 아빠','소연 글 ,이주희 그림','비룡소',2021,'기타',636,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (637,'냥 작가의 맞춤법 상담소','글: 즐비 ,그림: 류수형','파란정원',2023,'기타',636,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (638,'오늘도 용맹이','이현 글 ,국민지 그림','비룡소',2022,'기타',638,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (639,'우리들의 MBTI 2 : 친구 관계 - 나의 성격을 이해하고 더 멋진 내가 되는','조수연 (지은이), 소윤 (그림)','다산어린이',2022,'기타',638,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (640,'에그박사의 채집 일기 :스티커로 채집하는 자연 생물 관찰 만화','원작: 에그박사 ,그림: 홍종현','Mirae N 아이세움',2021,'기타',640,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (641,'비밀의 보석 가게 마석관','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2020,'기타',641,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (642,'(처음 읽는) 그리스 로마 신화 =First Greek mythology','글: 최설희 ,그림: 한현동','미래엔',2020,'기타',641,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (643,'공부 독립','주단,권태형 지음','북북북',2022,'기타',641,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (644,'빵이 되고 싶은 토끼','마루야마 나오 지음 ,고향옥 옮김','스푼북',2020,'기타',644,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (645,'(무엇이든 해결단) 허팝 연구소','라곰씨 글 ,차차 그림','부즈펌',0,'기타',644,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (646,'살인자의 기억법 :김영하 장편소설','지은이: 김영하','문학동네',2013,'소설',646,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (647,'크리스마스 타일 :김금희 연작소설','지은이: 김금희','창비',2022,'소설',646,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (648,'나는 소망한다 내게 금지된 것을 :양귀자 장편소설','지은이: 양귀자','쓰다',2019,'소설',646,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (649,'나의 아름다운 할머니 :심윤경 에세이','지은이: 심윤경','사계절(사계절출판사)',2022,'기타',646,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (650,'5번 레인','은소홀 글 ,노인경 그림','문학동네',2020,'소설',650,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (651,'부의 인문학 :슈퍼리치의 서재에서 찾아낸 부자의 길','브라운스톤 지음','Openmind(오픈마인드)',2019,'소설',651,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (652,'아파트 투자는 사이클이다','이현철 지음','여의도책방',2022,'기타',651,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (653,'(읽으면서 바로 써먹는) 어린이 사자성어','글·그림: 한날','파란정원',2021,'기타',653,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (654,'대박 쉽게 숙제하는 법','천효정 글 ,김무연 그림','비룡소',2022,'기타',654,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (655,'멈출 수 없는 우리 1 - 인간은 어떻게 지구를 지배했을까','유발 하라리 (지은이), 리카르드 사플라나 루이스 (그림), 김명주 (옮긴이)','주니어김영사',2023,'기타',654,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (656,'여기는 커스터드, 특별한 도시락을 팝니다','가토 겐 지음 ,양지윤 옮김','필름(필름 출판사)',2022,'기타',654,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (657,'예의 없는 친구들을 대하는 슬기로운 말하기 사전','김원아 글 ,김소희 그림','사계절(사계절출판사)',2022,'기타',657,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (658,'서울대생의 비밀과외 - 무조건 통하는 전교 1등의 합격 루틴','소린TV(안소린) (지은이)','다산에듀',2023,'기타',658,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (659,'내일은 발명왕 :본격 대결 과학발명 만화','글: 곰돌이 co. ,그림: 홍종현','Mirae N 아이세움',2011,'과학',658,37,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (660,'음식 중독 :먹고 싶어서 먹는다는 착각','지은이: 마이클 모스 ,옮긴이: 연아람','민음사',2023,'기타',660,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (661,'(읽으면서 바로 써먹는) 어린이 한국사 퀴즈','글·그림: 한날','파란정원',2021,'역사',660,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (662,'신사고 SSEN 쎈 고등 수학 1 + 수학 2 세트 ( 2014 고1적용 / 새교육과정 ) - 오답노트+단어장 사은품증정','저자 정보 없음','좋은책신사고',2013,'기타',662,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (663,'불만이 있어요','요시타케 신스케 글·그림 ,김정화 옮김','봄나무',2016,'기타',662,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (664,'꽁꽁꽁 피자','윤정주 글·그림','책읽는곰',2020,'기타',664,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (665,'비밀의 보석 가게 마석관','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2020,'기타',664,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (666,'태양의 마녀 나코와 코기 봉봉','히로시마 레이코 글 ,김정화 옮김','웅진씽크빅',2022,'기타',664,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (667,'기분 가게','도키 나쓰키 글·그림 ,김숙 옮김','김영사',2022,'기타',667,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (668,'추리 천재 엉덩이 탐정과 카레 사건','트롤 글·그림 ,김정화 옮김','미래엔',2019,'기타',667,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (669,'오늘도 용맹이','이현 글 ,국민지 그림','비룡소',2022,'기타',669,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (670,'에이징 솔로 =혼자를 선택한 사람들은 어떻게 나이 드는가 /Aging solo','김희경 지음','동아시아',2023,'기타',669,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (671,'그렇게 그렇게','요시타케 신스케 글·그림 ,양지연 옮김','김영사',2021,'기타',671,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (672,'(읽으면서 바로 써먹는) 어린이 수수께끼 :공포 특급','글·그림: 한날','파란정원',2022,'기타',671,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (673,'(코믹 메이플스토리) 수학도둑 :언제 어디에서나 원리를 응용하여 문제를 격파하는 수학의 해결사!','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',2021,'기타',671,83,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (674,'(처음 읽는) 그리스 로마 신화 =First Greek mythology','글: 최설희 ,그림: 한현동','미래엔',2020,'기타',674,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (675,'꽝 없는 뽑기 기계','곽유진 글 ,차상미 그림','비룡소',2020,'기타',675,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (676,'(처음 읽는) 그리스 로마 신화 =First Greek mythology','글: 최설희 ,그림: 한현동','미래엔',2020,'기타',676,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (677,'토깽이네 지구 구출 대작전','원작: 토깽이네 ,그림: 양선모','위즈덤하우스',2021,'기타',676,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (678,'(코믹 메이플스토리) 수학도둑 :언제 어디에서나 원리를 응용하여 문제를 격파하는 수학의 해결사!','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',2021,'기타',678,89,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (679,'100층짜리 집','글·그림: 이와이 도시오 ,옮김: 김숙','북뱅크',2009,'기타',679,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (680,'그리고 펌킨맨이 나타났다','유소정 글 ,김상욱 그림','비룡소',2022,'기타',680,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (681,'인생을 숙제처럼 살지 않기로 했다 :힘 빼고 유연하게, 모든 순간을 파도 타듯 즐기는 심리 수업','웃따 지음','다산북스',2023,'기타',680,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (682,'너무 잘하려고 애쓰지 마라 :나태주 시집','지은이: 나태주','열림원',2022,'기타',680,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (683,'몰입 =인생을 바꾸는 자기 혁명 /Think hard','황농문 지음','랜덤하우스코리아',2008,'기타',683,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (684,'(코믹 메이플스토리) 수학도둑 :언제 어디에서나 원리를 응용하여 문제를 격파하는 수학의 해결사!','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',2021,'기타',683,88,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (685,'조선 미술관 :풍속화와 궁중기록화로 만나는 문화 절정기 조선의 특별한 순간들','탁현규 지음','백도씨',2023,'기타',685,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (686,'만복이네 떡집','김리리 글 ,이승현 그림','비룡소',2010,'기타',685,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (687,'열두 발자국','지은이: 정재승','어크로스',2018,'기타',687,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (688,'무례한 사람에게 웃으며 대처하는 법 :인생 자체는 긍정적으로 개소리에는 단호하게','정문정 지음','가나문화콘텐츠',2018,'기타',688,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (689,'마음의 법칙 :사람의 마음을 사로잡는 51가지 심리학','폴커 키츠,김희상 옮김','포레스트',2022,'기타',688,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (690,'코믹 메이플 스토리 수학도둑 82 - 국내 최초 수학논술만화','송도수 (지은이), 서정 엔터테인먼트 (그림), 여운방 (감수)','서울문화사',2021,'기타',690,82,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (691,'위저드 베이커리 =구병모 장편소설 /Wizard bakery','지은이: 구병모','창비',2009,'소설',690,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (692,'세금 내는 아이들의 생생 경제 교실','글: 최재훈 ,그림: 안병현','샌드박스네트워크',2022,'기타',690,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (693,'파쇄 :일단 마음먹고 칼을 집었으면, 뜸 들이지 마','지은이: 구병모','위즈덤하우스',2023,'기타',693,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (694,'독고솜에게 반하면 - 제10회 문학동네청소년문학상 대상 수상작','허진희 (지은이)','문학동네',2020,'소설',694,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (695,'돈의 시나리오 :계획이 있는 돈은 흔들리지 않는다','김종봉,제갈현열 지음','다산북스',2021,'기타',694,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (696,'(보도 섀퍼의) 이기는 습관 :불가능을 뛰어넘어 최후의 승자가 된 사람들','보도 섀퍼 지음 ,박성원 옮김','토네이도미디어그룹',2022,'자기계발',694,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (697,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',694,73,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (698,'(코믹 메이플스토리) 수학도둑 :언제 어디에서나 원리를 응용하여 문제를 격파하는 수학의 해결사!','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',2021,'기타',698,86,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (699,'아주 세속적인 지혜 - 400년 동안 사랑받은 인생의 고전','발타자르 그라시안 (지은이), 강정선 (옮긴이)','페이지2(page2)',2023,'기타',698,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (700,'오싹오싹 당근','애런 레이놀즈 글 ,홍연미 옮김','알에이치코리아',2013,'기타',700,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (701,'마지막 몰입 :나를 넘어서는 힘','짐 퀵 지음 ,김미정 옮김','비즈니스북스',2021,'기타',700,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (702,'내일은 발명왕 38 - 음식과 발명','곰돌이 co. (지은이), 홍종현 (그림), 박완규, 황성재 (감수)','미래엔아이세움',2023,'기타',702,38,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (703,'브레드이발소 3 : 베이커리타운의 악동들','(주)몬스터주식회사 (지은이)','한솔수북',2021,'기타',703,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (704,'미스터 프레지던트 =국가 기념식과 대통령 행사 이야기 /Mr. president','탁현민 지음','메디치(메디치미디어)',2023,'소설',703,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (705,'난장이가 쏘아올린 작은 공:조세희 소설집','조세희','이성과 힘',2000,'소설',705,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (706,'(코믹 메이플스토리) 수학도둑 :언제 어디에서나 원리를 응용하여 문제를 격파하는 수학의 해결사!','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',2021,'기타',705,87,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (707,'세계 방방곡곡 여행 일기 :마스다 미리 에세이','지은이: 마스다 미리 ,옮긴이: 이소담','북포레스트',2023,'기타',705,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (708,'(무엇이든 해결단) 허팝 연구소','라곰씨 글 ,차차 그림','부즈펌',0,'기타',705,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (709,'전설의 모험왕 엉덩이 댄디 5 - 가자, 구름 왕국으로!','트롤 (지은이), 하루하라 로빈슨 (글), 기쿠치 아키히로 (그림), 김정화 (옮긴이)','미래엔아이세움',2023,'기타',705,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (710,'유령 고양이 후쿠코','히로시마 레이코 글 ,고향옥 옮김','김영사',2021,'기타',710,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (711,'언어의 온도 :말과 글에는 나름의 따뜻함과 차가움이 있다','지은이: 이기주','말글터',2016,'기타',711,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (712,'부모의 말 :아이의 마음을 제대로 읽은 부모의 말은 다릅니다','지은이: 김종원','상상아카데미',2022,'기타',711,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (713,'내일은 실험왕 :본격 대결 과학실험 만화','글: 곰돌이 co. ,그림: 홍종현','미래엔',2011,'과학',713,50,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (714,'(설민석의) 만만 한국사 :재미 만점★효과 만점★한국사 만화','글: 설민석,그림: 김덕영','미래엔',2020,'역사',713,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (715,'백년허리 :백년 동안 간직할 허리 사용설명서','저자: 정선근','언탱글링',2021,'기타',713,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (716,'회색 인간','지은이: 김동식','요다',2017,'기타',713,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (717,'(박병창의 돈을 부르는) 매매의 기술','박병창 지음','콘텐츠그룹 포레스트',2021,'과학',713,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (718,'팩트풀니스 :우리가 세상을 오해하는 10가지 이유와 세상이 생각보다 괜찮은 이유','지은이: 한스 로슬링,옮긴이: 이창신','김영사',2019,'기타',718,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (719,'설전도 수련관','글: 김경미 ,그림: 센개','슈크림북',2022,'기타',718,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (720,'내일은 실험왕 :본격 대결 과학실험 만화','글: 곰돌이 co. ,그림: 홍종현','미래엔',2011,'과학',718,48,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (721,'건방이의 건방진 수련기','천효정 글 ,강경수 그림','비룡소',0,'기타',721,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (722,'시간 고양이 :박미연 장편동화','지은이: 박미연 ,그린이: 박냠','이지북',2021,'기타',721,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (723,'생기부 필독서 100 - 현직 고등학교 선생님들이 직접 고른','주경아, 정재화, 방희조, 이재환, 이현규 (지은이)','센시오',2023,'기타',721,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (724,'(읽으면서 바로 써먹는) 어린이 사자소학','글·그림: 이수인','파란정원',2019,'기타',721,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (725,'급식 알바 구드래곤','박현숙 글 ,이경석 그림','다산북스',2022,'기타',721,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (726,'(무엇이든 해결단) 허팝 연구소','라곰씨 글 ,차차 그림','부즈펌',0,'기타',726,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (727,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',727,72,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (728,'사자마트','김유 (지은이), 소복이 (그림)','천개의바람',2023,'기타',727,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (729,'코믹 메이플 스토리 수학도둑 81 - 국내 최초 수학논술만화','송도수 (지은이), 서정 엔터테인먼트 (그림), 여운방 (감수)','서울문화사',2021,'기타',727,81,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (730,'도망치고, 찾고','요시타케 신스케 글·그림 ,권남희 옮김','주니어김영사',2021,'기타',730,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (731,'나는 3학년 2반 7번 애벌레 :김원아 동화','지은이: 김원아 ,그린이: 이주희','창비',2016,'기타',730,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (732,'놓지 마 어휘 :한자어','신태훈,나승훈 글·그림','김영사',2021,'기타',730,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (733,'드라랄라 치과','윤담요 쓰고 그림','보림출판사',2021,'기타',733,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (734,'머니 트렌드 2023 :45가지 키워드로 전망하는 대한민국 돈의 흐름','지은이: 정태익,홍춘욱','북로망스',2022,'기타',733,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (735,'7~9세 독립보다 중요한 시기는 없습니다','이서윤 지음','아울북(북이십일 아울북)',2023,'기타',733,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (736,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',736,70,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (737,'내 멋대로 산타 뽑기','최은옥 글 ,김무연 그림','김영사',2022,'기타',737,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (738,'십 년 가게','히로시마 레이코 글 ,이소담 옮김','위즈덤하우스 미디어그룹',2020,'기타',737,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (739,'우리들의 MBTI 3 : 가족 관계 (한정판 윈터 에디션) - 나의 성격을 이해하고 더 멋진 내가 되는','조수연 (지은이), 소윤 (그림)','다산어린이',2022,'기타',737,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (740,'마음의 온도는 몇 도일까요? - 그림 시집','정여민 (지은이), 허구 (그림)','주니어김영사',2016,'기타',740,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (741,'정세현의 통찰 :국제질서에서 시대의 해답을 찾다','정세현 지음','푸른숲',2023,'기타',740,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (742,'(읽으면서 바로 써먹는) 어린이 명심보감','글·그림: 한날','파란정원',2020,'기타',740,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (743,'GPT 제너레이션 - 챗GPT가 바꿀 우리 인류의 미래','이시한 (지은이)','북모먼트',2023,'기타',740,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (744,'내일은 실험왕 :본격 대결 과학실험 만화','글: 곰돌이 co. ,그림: 홍종현','미래엔',2011,'과학',740,47,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (745,'코믹 메이플 스토리 수학도둑 80 - 국내 최초 수학논술만화','송도수 (지은이), 서정 엔터테인먼트 (그림), 여운방 (감수)','서울문화사',2021,'기타',740,80,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (746,'수학 탐정스','조인하 글 ,조승연 그림','미래엔',2018,'기타',740,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (747,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',747,75,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (748,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',747,74,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (749,'프로젝트 헤일메리','앤디 위어 지음 ,강동혁 옮김','RHK(알에이치코리아)',2021,'기타',749,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (750,'꿀꺽 쓰레기통','공수경 글 ,김이조 그림','보리',2023,'기타',749,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (751,'염증 해방 :병 없이 오래 사는 사람들의 비밀','정세연 지음','다산북스',2022,'기타',749,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (752,'갑자기 기린 선생님','소연 글 ,이주희 그림','비룡소',2022,'기타',749,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (753,'아쿠아리움이 문을 닫으면 :셸비 반 펠트 장편소설','지은이: 셸비 반 펠트 ,옮긴이: 신솔잎','미디어창비',2023,'소설',753,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (754,'코믹 메이플 스토리 수학도둑 71 - 국내 최초 수학논술만화','송도수 (지은이), 서정 엔터테인먼트 (그림), 여운방 (감수)','서울문화사',2019,'기타',753,71,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (755,'럭키 드로우 =나만의 길을 찾을 때까지 인생의 레버를 당기는 법 /Lucky draw','드로우앤드류 지음','다산북스',2022,'기타',753,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (756,'블랙 쇼맨과 환상의 여자','히가시노 게이고 (지은이), 최고은 (옮긴이)','알에이치코리아(RHK)',2023,'기타',756,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (757,'과학 탐정스 :기발한 과학 추리 동화','조인하 글 ,조승연 그림','미래엔',2020,'과학',757,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (758,'착한 달걀','조리 존 글 ,김경희 옮김','길벗어린이',2022,'기타',757,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (759,'트렌드 코리아 2023 :더 높은 도약을 준비하는 검은 토끼의 해','지은이: 김난도,추예린','미래의창',2022,'기타',759,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (760,'당근 밭의 수상한 발자국','선시야 글 ,심보영 그림','김영사',2022,'기타',759,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (761,'일생에 단 한번은 독기를 품어라 :한번 사는 인생 간절히, 후회 없이 살아라','권민창 지음','바이포엠 스튜디오',2022,'기타',761,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (762,'이토록 공부가 재미있어지는 순간 :공부에 지친 청소년들을 위한 힐링 에세이','박성혁 지음','다산북스',2015,'기타',761,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (763,'보도 섀퍼 부의 레버리지 :경제적 자유로 가는 가장 빠르고 확실한 길','보도 섀퍼 지음 ,한윤진 옮김','비즈니스북스',2023,'기타',763,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (764,'벌거벗은 세계사','글: 이현희,최호정','북이십일',2022,'역사',764,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (765,'황당하지만 수학입니다','남호영 글 ,미늉킴 그림','와이즈만 Books',2022,'기타',764,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (766,'(현실은 엉망이지만 그럼에도 불구하고) 제 마음대로 살아보겠습니다','글·사진: 이원지','상상출판',2019,'기타',764,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (767,'왔구마 :고구마구마','글: 조주희 ,이다빈','킨더랜드',2023,'기타',767,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (768,'용기를 내, 비닐장갑! :유설화 그림책','유설화','책읽는곰',2021,'기타',767,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (769,'하루 한 장, 인생 그림 :아트메신저 이소영이 전하는 명화의 세계','이소영 지음','RHK(알에이치코리아)',2023,'기타',769,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (770,'(어린이를 위한) 역사의 쓸모','글: 최태성 ,그림: 신진호','다산북스',2022,'역사',770,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (771,'토깽이네 지구 구출 대작전','원작: 토깽이네 ,그림: 양선모','위즈덤하우스',2021,'기타',770,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (772,'(코믹 메이플스토리) 수학도둑 :언제 어디에서나 원리를 응용하여 문제를 격파하는 수학의 해결사!','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',2021,'기타',770,85,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (773,'부자의 그릇 :남을 위해 돈 쓰는 능력을 키우는 법','이즈미 마사토 지음 ,김윤수 옮김','다산북스',2015,'기타',773,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (774,'십 년 가게','히로시마 레이코 글 ,이소담 옮김','위즈덤하우스 미디어그룹',2020,'기타',773,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (775,'143층 나무 집','앤디 그리피스 글 ,신수진 옮김','시공사',2022,'기타',773,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (776,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',773,77,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (777,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',777,76,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (778,'태양의 마녀 나코와 코기 봉봉','히로시마 레이코 글 ,김정화 옮김','웅진씽크빅',2022,'기타',777,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (779,'(우당탕탕 야옹이와) 금빛 마법사','구도 노리코 동화 ,윤수정 옮김','책읽는곰',2021,'기타',779,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (780,'강아지똥','글: 권정생 ,그림: 정승각','길벗어린이',2012,'기타',779,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (781,'(무엇이든 해결단) 허팝 연구소','라곰씨 글 ,차차 그림','부즈펌',0,'기타',781,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (782,'내 마음을 알아주세요 내 마음을 안아주세요 :힘든 열 살을 위한 마음책','박진영 글 ,소복이 그림','우리학교',2023,'기타',782,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (783,'거인의 어깨 =홍진채 해설로 통찰하는 주식 대가들의 투자법.Shoulders of giants','홍진채 지음','콘텐츠그룹 포레스트',2022,'기타',782,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (784,'엉뚱하지만 과학입니다','원종우,이철민 그림','와이즈만 Books',2022,'과학',782,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (785,'백조와 박쥐 :히가시노 게이고 장편소설','지은이: 히가시노 게이고 ,옮긴이: 양윤옥','현대문학',2021,'소설',785,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (786,'안녕의 의식 :미야베 미유키 소설집','지은이: 미야베 미유키 ,옮긴이: 홍은주','김영사',2023,'소설',786,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (787,'오십에 읽는 논어 :굽이치는 인생을 다잡아 주는 공자의 말','최종엽 지음','유노북스',2021,'기타',786,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (788,'흔한남매 13','흔한남매 (지은이), 백난도 (글), 유난희 (그림), 흔한컴퍼니 (감수)','미래엔아이세움',2023,'기타',786,13,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (789,'토깽이네 지구 구출 대작전','원작: 토깽이네 ,그림: 양선모','위즈덤하우스',2021,'기타',789,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (790,'꽁꽁꽁 아이스크림 :윤정주 그림책','윤정주','책읽는곰',2022,'기타',790,91,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (791,'내일은 실험왕 :본격 대결 과학실험 만화','글: 곰돌이 co. ,그림: 홍종현','미래엔',2011,'과학',790,45,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (792,'당신은 생각보다 강하다 :스스로를 괴롭히는 생각의 고리를 끊고 진짜 변화를 불러오는 마음의 기술','전미경 지음','웅진씽크빅',2023,'과학',790,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (793,'블랙 쇼맨과 이름 없는 마을의 살인','지은이: 히가시노 게이고 ,옮긴이: 최고은','RHK(알에이치코리아)',2020,'기타',790,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (794,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',790,66,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (795,'이게 정말 나일까?','요시타케 신스케 글·그림 ,김소연 옮김','김영사',2015,'기타',790,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (796,'보이거나 안 보이거나','요시타케 신스케 글·그림 ,고향옥 옮김','토토북',2019,'기타',790,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (797,'총, 균, 쇠','재레드 다이아몬드 지음 ,김진준 옮김','문학사상',2013,'소설',790,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (798,'설민석의 고사성어 대격돌','글: 설민석,그림: 김문식','한솔수북',2020,'기타',798,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (799,'내일은 실험왕 :본격 대결 과학실험 만화','글: 곰돌이 co. ,그림: 홍종현','미래엔',2011,'과학',798,49,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (800,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',800,78,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (801,'김경일의 지혜로운 인간생활 :님을 위한 행복한 인간관계 지침서','김경일 지음','저녁달',2022,'기타',800,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (802,'비밀의 보석 가게 마석관','히로시마 레이코 글 ,김정화 옮김','길벗스쿨',2020,'기타',802,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (803,'여름의 빌라 :백수린 소설','지은이: 백수린','문학동네',2020,'소설',802,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (804,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',802,69,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (805,'더 해빙 =부와 행운을 끌어당기는 힘 /The having','이서윤,홍주연 지음','수오서재',2020,'기타',802,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (806,'세금 내는 아이들의 생생 경제 교실 2','최재훈 (지은이), 안병현 (그림), 옥효진 (감수)','샌드박스스토리 키즈',2023,'기타',802,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (807,'내일은 실험왕 :본격 대결 과학실험 만화','글: 곰돌이 co. ,그림: 홍종현','대한교과서',0,'과학',807,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (808,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',807,65,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (809,'달팽이 식당 :오가와 이토 장편소설','지은이: 오가와 이토 ,옮긴이: 권남희','RHK(알에이치코리아)',2022,'소설',807,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (810,'장수탕 선녀님','지은이: 백희나','책읽는곰',2012,'기타',807,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (811,'(무엇이든 해결단) 허팝 연구소','라곰씨 글 ,차차 그림','부즈펌',0,'기타',807,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (812,'건방이의 속담 수련기 :초절정 속담 고수 되기','천효정 글 ,이정태 그림','비룡소',2022,'기타',812,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (813,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',812,79,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (814,'더우면 벗으면 되지','요시타케 신스케 (지은이), 양지연 (옮긴이)','주니어김영사',2021,'기타',814,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (815,'정의란 무엇인가','지은이: 마이클 샌델 ,옮긴이: 김명철','미래엔',2014,'기타',814,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (816,'구해줘 카카오프렌즈 :경제','글: 한유진,스토리: 강민희','메가스터디books',2020,'기타',814,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (817,'사소한 것들이 신경 쓰입니다','저자: 마스다 미리 ,옮긴이: 권남희','소미미디어',2023,'기타',814,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (818,'토깽이네 지구 구출 대작전 3 : 거대 괴물로부터 바다를 구하라! - 서바이벌 환경 학습만화','토깽이네 (지은이), 잼 스토리 (글), 양선모 (그림), 샌드박스 네트워크 (감수)','위즈덤하우스',2022,'기타',814,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (819,'(무엇이든 해결단) 허팝 연구소','라곰씨 글 ,차차 그림','부즈펌',0,'기타',814,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (820,'사라진 물건의 비밀','이분희 글 ,이덕화 그림','비룡소',2021,'기타',820,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (821,'기소영의 친구들 :정은주 창작동화','지은이: 정은주 ,그린이: 해랑','사계절(사계절출판사)',2022,'기타',820,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (822,'방금 떠나온 세계 :김초엽 소설집','지은이: 김초엽','한겨레엔',2021,'소설',820,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (823,'김미경의 리부트 :코로나로 멈춘 나를 다시 일으켜 세우는 법','김미경 지음','웅진씽크빅',2020,'기타',823,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (824,'(코믹 메이플스토리) 수학도둑 :창의사고력 강화, 수리논술력을 키워 주는 스토리텔링 수학의 선구자!','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',823,61,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (825,'악인론 :닥치고 성공해 누구에게도 지배받지 않는 삶','손수현 지음','다산북스',2023,'자기계발',823,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (826,'나는 4시간만 일한다 :디지털 노마드 시대 완전히 새로운 삶의 방식','팀 페리스 지음 ,윤동준 옮김','빛과향기',2017,'기타',823,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (827,'책 먹는 여우와 이야기 도둑 :《책 먹는 여우》두 번째 이야기','프란치스카 비어만 글·그림 ,송순섭 옮김','김영사',2015,'소설',827,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (828,'친밀한 이방인 :정한아 장편소설','지은이: 정한아','문학동네',2017,'소설',827,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (829,'요괴의 아이를 돌봐드립니다','히로시마 레이코 글 ,김지영 옮김','넥서스',2021,'기타',827,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (830,'13층 나무 집','앤디 그리피스 글 ,신수진 옮김','시공사',2015,'기타',830,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (831,'욕심은 그만, 레이스 장갑! :유설화 그림책','유설화','책읽는곰',2022,'기타',831,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (832,'우리 반 목소리 작은 애','김수현 글 ,소복이 그림','풀빛',2022,'기타',832,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (833,'내일은 발명왕 :본격 대결 과학발명 만화','글: 곰돌이 co. ,그림: 홍종현','Mirae N 아이세움',2011,'과학',832,36,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (834,'웃소','원작: 웃소 ,그림: 박강호','미래엔',2021,'기타',834,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (835,'백년운동 :척추·관절 아프지 않게 100세까지 운동하는 방법','저자: 정선근','아티잔',2019,'기타',834,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (836,'(아이들도 잘 모르고 어른들은 더 모르는) 신기한 현상 사전','코코로사 글 ,이소담 옮김','김영사',2022,'기타',836,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (837,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',836,62,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (838,'윔피 키드','제프 키니 글·그림 ,지혜연 옮김','미래엔',2016,'기타',836,17,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (839,'내일은 발명왕 :본격 대결 과학발명 만화','글: 곰돌이 co. ,그림: 홍종현','Mirae N 아이세움',2011,'과학',839,34,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (840,'떨림과 울림 :물리학자 김상욱이 바라본 우주와 세계 그리고 우리','김상욱 지음','동아시아',2018,'과학',839,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (841,'센 강의 이름 모를 여인 :기욤 뮈소 장편소설','지은이: 기욤 뮈소 ,옮긴이: 양영란','밝은세상',2022,'소설',839,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (842,'듄','지은이: 프랭크 허버트 ,옮긴이: 김승욱','황금가지',2021,'기타',839,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (843,'내일은 발명왕 :본격 대결 과학발명 만화','글: 곰돌이 co. ,그림: 홍종현','Mirae N 아이세움',2011,'과학',843,31,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (844,'웃소','원작: 웃소 ,그림: 박강호','미래엔',2021,'기타',843,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (845,'웃소','원작: 웃소 ,그림: 박강호','미래엔',2021,'기타',845,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (846,'신비아파트 한자 귀신 1 - 1001개의 구슬','김강현 (지은이), 김기수 (그림), 김경익, 박상우 (감수)','서울문화사',2018,'기타',845,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (847,'겨울이 좋아! 토끼 베이커리','글·그림: 마츠오 리카코 ,옮김: 김숙','지학사아르볼',2022,'기타',847,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (848,'이유가 있어요','요시타케 신스케 글·그림 ,김정화 옮김','봄나무',2015,'기타',847,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (849,'(10대를 위한) 정의란 무엇인가 :하버드대 마이클 샌델 교수의 정의 수업','마이클 샌델 원저 ,조혜진 그림','미래엔',2014,'기타',847,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (850,'날씨와 얼굴 :이슬아 칼럼집','지은이: 이슬아','위고',2023,'기타',847,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (851,'팩토피아 :꼬리에 꼬리를 무는 400가지 사실들','케이트 헤일 글 ,조은영 옮김','시공사',2022,'기타',847,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (852,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',847,64,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (853,'돼지책','앤서니 브라운 글·그림,허은미 옮김','웅진닷컴',2001,'기타',853,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (854,'모래알만 한 진실이라도 :박완서 에세이','지은이: 박완서','세계사 컨텐츠 그룹',2020,'역사',853,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (855,'브레드이발소','원작·그림: 몬스터스튜디오','한솔수북',2021,'기타',853,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (856,'신비아파트 한자 귀신 2 - 저주의 대가','김강현 (지은이), 김기수 (그림), 김경익, 박상우 (감수)','서울문화사',2019,'기타',856,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (857,'우당탕탕 야옹이와 바다 끝 괴물','구도 노리코 글·그림 ,윤수정 옮김','책읽는곰',2021,'기타',856,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (858,'웃소','원작: 웃소 ,그림: 박강호','미래엔',2021,'기타',858,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (859,'나인 :천선란 장편소설','지은이: 천선란','창비',2021,'소설',858,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (860,'그대 늙어가는 것이 아니라 익어가는 것이다','오평선 지음','콘텐츠그룹 포레스트',2022,'기타',860,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (861,'목련 만두','백유연 (지은이)','웅진주니어',2023,'기타',860,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (862,'아몬드 :손원평 장편소설','지은이: 손원평','창비',2017,'소설',862,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (863,'건방이의 건방진 수련기','천효정 글 ,강경수 그림','비룡소',0,'기타',862,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (864,'러브 몬스터 =이두온 장편소설 /Love monster','지은이: 이두온','창비',2023,'소설',864,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (865,'생각하라 그리고 부자가 되어라 :나폴레온 힐, 부와 성공의 원칙','나폴레온 힐 지음 ,이한이 옮김','반니',2021,'자기계발',864,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (866,'곤충보다 작아진 정브르 :생물 학습 만화','원작: 정브르 ,그림: 강신영','겜툰',2022,'기타',864,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (867,'가짜 모범생 :손현주 장편소설','지은이: 손현주','특별한서재',2021,'소설',867,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (868,'별의 커비 디스커버리 1 : 새로운 세계를 향해 달려라!','다카세 미에 (지은이), 가리노 타우, 가리노 포토 (그림), 현승희 (옮긴이)','해피북스투유',2023,'기타',867,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (869,'(무엇이든 해결단) 허팝 연구소','라곰씨 글 ,차차 그림','부즈펌',0,'기타',869,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (870,'백앤아 1 : 미스터리 100층 감옥 - 교양이 층층 쌓이는 점프 맵','백앤아 (원작), 안성훈 (글), 돌만 (그림)','샌드박스스토리 키즈',2023,'기타',870,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (871,'웃소','원작: 웃소 ,그림: 박강호','미래엔',2021,'기타',870,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (872,'(비울수록 사람을 더 채우는) 말그릇','김윤나 지음','카시오페아',2017,'기타',870,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (873,'뼈뼈 사우루스','글·그림: 암모나이트 ,김정화 옮김','미래엔',2018,'기타',870,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (874,'딸기가 좋아! 토끼 베이커리','글·그림: 마츠오 리카코 ,옮김: 문지연','지학사아르볼',2021,'기타',870,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (875,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',870,68,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (876,'살까? 말까? :똑똑한 소비를 위한 어린이 경제','권재원 지음','창비',2022,'기타',876,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (877,'시간 고양이','박미연 글 ,박냠 그림','이지북',2022,'기타',876,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (878,'알사탕','백희나','책읽는곰',2017,'기타',876,39,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (879,'내일은 실험왕 :본격 대결 과학실험 만화','글: 곰돌이 co. ,그림: 홍종현','미래엔',2011,'과학',876,42,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (880,'이상한 초대장','박현숙 글 ,국민지 그림','김영사',2021,'기타',880,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (881,'고구마유','지은이: 사이다','반달',2021,'기타',880,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (882,'슈퍼 거북','유설화 글·그림','책읽는곰',2014,'기타',880,15,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (883,'봉제인형 살인사건 :다니엘 콜 장편소설','저자: 다니엘 콜 ,옮긴이: 유혜인','Book plaza(북플라자)',2017,'소설',883,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (884,'10배의 법칙 :성공과 실패를 가르는 유일한 차이','그랜트 카돈 지음 ,최은아 옮김','부·키',2022,'자기계발',883,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (885,'내 마음 ㅅㅅㅎ :김지영 그림책','글·그림: 김지영','사계절(사계절출판사)',2021,'기타',883,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (886,'놓지 마 어휘 :한자어','신태훈,나승훈 글·그림','김영사',2021,'기타',886,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (887,'달까지 가자 :장류진 장편소설','지은이: 장류진','창비',2021,'소설',886,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (888,'빵도둑','시바타 케이코 글·그림 ,황진희 옮김','길벗어린이',2021,'기타',886,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (889,'너의 이름은. =Your name.','지은이: 신카이 마코토 ,옮긴이: 박미정','대원씨아이',2017,'기타',886,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (890,'(곤충 탐정) 정브르','원작: 정브르 ,그림: 도니패밀리','서울문화사',2022,'기타',886,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (891,'돈의 규칙 - 돈은 당신의 명령을 기다린다','신민철(처리형) (지은이)','베가북스',2023,'기타',886,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (892,'그것만 있을 리가 없잖아','요시타케 신스케 글·그림 ,고향옥 옮김','김영사',2019,'기타',886,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (893,'간다아아!','글·그림: 코리 R. 테이버 ,옮김: 노은정','키즈스콜레',2022,'기타',893,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (894,'우리는 모두 죽는다는 것을 기억하라 :삶의 다른 방식을 찾고 있는 당신에게','웨인 다이어 지음 ,정지현 옮김','Tornado(토네이도)',2019,'기타',894,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (895,'내일은 실험왕 :본격 대결 과학실험 만화','글: 곰돌이 co. ,그림: 홍종현','미래엔',2011,'과학',894,46,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (896,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',896,67,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (897,'구해줘 카카오프렌즈 과학','글: 박영희,그림: 도니패밀리','메가스터디',2019,'과학',896,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (898,'메리 :안녕달 그림책','글·그림: 안녕달','사계절',2017,'기타',896,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (899,'내일은 발명왕 :본격 대결 과학발명 만화','글: 곰돌이 co. ,그림: 홍종현','Mirae N 아이세움',2011,'과학',896,30,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (900,'(설민석의) 역사 고민 상담소 :역사 이야기로 고민을 해결하는 스토리텔링 한국사','글: 설민석,정주연','미래엔',2020,'소설',896,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (901,'(신비아파트) 한자귀신','글: 김강현 ,그림: 김기수','서울문화사',2019,'기타',896,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (902,'(이론과 실전을 모두 담아 새로 쓴) 한국형 가치투자','최준철,김민국 지음','이콘(이콘출판)',2023,'기타',902,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (903,'소원빵집 위시위시 베이커리','글: 안영은 ,이다빈','한솔수북',2022,'기타',902,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (904,'쌍둥이 탐정 똥똥구리','글: 류미원 ,그림: 이경석','초록개구리',2022,'기타',902,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (905,'내일은 발명왕 :본격 대결 과학발명 만화','글: 곰돌이 co. ,그림: 홍종현','Mirae N 아이세움',2011,'과학',905,32,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (906,'건방이의 건방진 수련기','천효정 글 ,강경수 그림','비룡소',0,'기타',905,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (907,'강남 사장님 :이지음 장편동화','글쓴이: 이지음 ,그린이: 국민지','비룡소',2020,'기타',907,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (908,'시작의 기술 :침대에 누워서 걱정만 하는 게으른 완벽주의자를 위한 7가지 무기','개리 비숍 지음 ,이지연 옮김','웅진씽크빅',2019,'과학',908,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (909,'자존감 수업 :하루에 하나, 나를 사랑하게 되는 자존감 회복 훈련','윤홍균 지음','심플라이프',2016,'기타',908,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (910,'내일은 실험왕 :본격 대결 과학실험 만화','글: 곰돌이 co. ,그림: 홍종현','미래엔',2011,'과학',908,44,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (911,'은유의 글쓰기 상담소 :계속 쓰려는 사람을 위한 48가지 이야기','은유 지음','김영사',2023,'소설',911,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (912,'12가지 인생의 법칙 :혼돈의 해독제','조던 B. 피터슨 지음 ,강주헌 옮김','메이븐',2018,'기타',911,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (913,'구미호 식당 :박현숙 장편소설','지은이: 박현숙','특별한서재',2018,'소설',911,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (914,'쿠키런 어드벤처 39 : 제주도','송도수 (지은이), 서정은 (그림)','서울문화사',2020,'기타',911,39,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (915,'(수학 잘하는 아이를 만드는) 초등수학 심화 공부법 :평범한 아이를 고등수학 1등급 만드는 결정적인 힘','류승재 지음','블루무스',2022,'기타',915,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (916,'엉덩이탐정','트롤 글·그림 ,전경아 옮김','문학수첩리틀북',2020,'소설',915,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (917,'대도시의 사랑법 :박상영 연작소설','지은이: 박상영','창비',2019,'소설',917,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (918,'수상한 기차역','박현숙 글 ,장서영 그림','북멘토',2021,'기타',917,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (919,'복제인간 윤봉구','임은하 글 ,정용환 그림','비룡소',2017,'기타',917,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (920,'소중해 소중해 너의 마음도 - 5-7세를 위한 첫 회복탄력성 그림책','아다치 히로미 (지은이), 가와하라 미즈마루 (그림), 권남희 (옮긴이), 최성애 (해설)','주니어RHK(주니어랜덤)',2023,'기타',920,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (921,'(읽으면서 바로 써먹는) 어린이 영단어','글·그림: 한날','파란정원',2019,'기타',920,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (922,'(읽으면서 바로 해 보는) 어린이 게임','글·그림: 한날','책먹는아이',2018,'기타',920,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (923,'건방이의 건방진 수련기','천효정 글 ,강경수 그림','비룡소',0,'기타',920,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (924,'7년 동안의 잠 :박완서 그림동화','글: 박완서 ,그림: 김세현','작가정신',2015,'기타',924,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (925,'신비아파트 한자 귀신 10 - 팔척귀의 함정','김강현 (지은이), 김기수 (그림), 김경익, 박상우 (감수)','서울문화사',2020,'기타',924,10,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (926,'내일은 발명왕 :본격 대결 과학발명 만화','글: 곰돌이 co. ,그림: 홍종현','Mirae N 아이세움',2011,'과학',924,33,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (927,'두근두근 편의점2 :김영진 그림책','지은이: 김영진','책읽는곰',2022,'기타',924,88,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (928,'엽기 과학자 프래니','글·그림: 짐 벤튼 ,옮김: 양윤선','이퍼블릭',2022,'과학',928,9,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (929,'행운이 너에게 다가오는 중 :이꽃님 장편소설','지은이: 이꽃님','문학동네',2020,'소설',929,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (930,'아들의 뇌 =딸로 태어난 엄마들을 위한 아들 사용 설명서 /Son''s brain','곽윤정 지음','콘텐츠그룹 포레스트',2021,'기타',929,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (931,'받침구조대','곽미영 글 ,지은 그림','만만한책방',2023,'기타',929,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (932,'침묵의 봄','지은이: 레이첼 카슨 ,옮긴이: 김은령','에코리브르',2011,'기타',929,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (933,'(코믹 메이플스토리) 수학도둑 :지식·능력·경험을 융합하여 정리하는 No.1 수학학습만화','글: 송도수 ,그림: 서정 엔터테인먼트','서울문화사',0,'기타',933,63,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (934,'으라차차 라면 가게','구도 노리코 글·그림 ,윤수정 옮김','책읽는곰',2022,'기타',933,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (935,'수박','김영진 글·그림','길벗어린이',2021,'기타',933,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (936,'곰탕 :김영탁 장편소설','지은이: 김영탁','Arte(아르테)',2018,'소설',933,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (937,'기억 전달자','로이스 로리 글,장은수 옮김','비룡소',2007,'기타',937,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (938,'쿠키런 킹덤스쿨 1 - 부자 쿠키 vs 거지 쿠키','김언정 (지은이), 이태영 (그림), JA Korea(국제비영리청소년교육기관) (감수)','서울문화사',2021,'기타',937,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (939,'오직 두 사람 :김영하 소설','지은이: 김영하','문학동네',2017,'소설',937,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (940,'급식왕 go','원작: 급식왕 ,그림: 구은미','북이십일',2020,'기타',940,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (941,'오늘도 너를 사랑해','이누이 사에코 (지은이), 고향옥 (옮긴이)','비룡소',2023,'기타',940,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (942,'밥 프록터 부의 확신 :세계 단 1%만이 알고 있는 부와 성공의 비밀','밥 프록터 지음 ,김문주 옮김','비즈니스북스',2022,'자기계발',942,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (943,'설민석의 고사성어 대격돌','글: 설민석,그림: 김문식','한솔수북',2020,'기타',942,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (944,'기묘한 모모 한약방','히로시마 레이코 글 ,김난주 옮김','미래엔',2021,'기타',942,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (945,'할머니의 용궁 여행','권민조 글·그림','천개의바람',2020,'기타',945,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (946,'엑시트 =Exit','송희창 지음','지혜로',2020,'기타',945,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (947,'들숨에 긍정 날숨에 용기','지나영 (지은이)','자음과모음',2023,'기타',947,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (948,'거꾸로 읽는 세계사','유시민 지음','돌베개',2021,'역사',947,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (949,'배터리 전쟁 :리튬부터 2차 전지까지, 누가 새로운 경제 영토를 차지할 것인가','루카스 베드나르스키 지음 ,안혜림 옮김','위즈덤하우스',2023,'기타',947,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (950,'토깽이네 지구 구출 대작전','원작: 토깽이네 ,그림: 양선모','위즈덤하우스',2021,'기타',947,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (951,'걱정 세탁소 :걱정을 세탁해 드립니다!','홍민정 글 ,김도아 그림','좋은책신사고 좋은책어린이',2020,'기타',951,115,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (952,'내일은 발명왕 :본격 대결 과학발명 만화','글: 곰돌이 co. ,그림: 홍종현','Mirae N 아이세움',2011,'과학',951,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (953,'신비아파트 한자 귀신 6 - 명령의 힘','김강현 (지은이), 김기수 (그림), 김경익, 박상우 (감수)','서울문화사',2019,'기타',951,6,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (954,'씽킹 101 :더 나은 삶을 위한 생각하기 연습','지은이: 안우경 ,옮긴이: 김보람','흐름출판',2023,'기타',951,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (955,'(이웃집 프로파일러) 하이다의 사건 파일 :표창원의 미스터리 추리 동화','글: 선자은 ,그림: 이태영','북이십일',2023,'기타',951,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (956,'쿠키런 어드벤처 :쿠키들의 신나는 세계여행','글: 송도수 ,그림: 서정은','서울문화사',0,'기타',956,27,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (957,'너의 하루가 따숩길 바라 :마음에 약 발라주는 ''힐링곰 꽁달이''의 폭신한 위로','고은지 지음','북라이프',2022,'기타',956,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (958,'민들레는 민들레','김장성 글 ,오현경 그림','이야기꽃',2014,'소설',956,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (959,'(귀신도 반한) 숲속 라면 가게','이서영 글 ,송효정 그림','크레용하우스',2022,'기타',956,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (960,'조국의 법고전 산책 :열다섯 권의 고전, 그 사상가들을 만나다','조국 지음','오마이북',2022,'기타',960,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (961,'별의 커비 스타 얼라이즈 1 : 프렌즈 대모험!','다카세 미에 (지은이), 가리노 타우, 가리노 포토 (그림), 현승희 (옮긴이)','해피북스투유',2022,'기타',960,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (962,'서울 부동산 절대원칙 :일자리, 인구, 교통망, 학군, 인프라, 재개발&재건축 총망라','지은이: 김학렬','길벗',2023,'기타',960,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (963,'신비아파트 한자 귀신 3 - 새로운 봉인 카드','김강현 (지은이), 김기수 (그림), 김경익, 박상우 (감수)','서울문화사',2019,'기타',963,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (964,'법 만드는 아이들 :어린이를 위한 민주 시민 교육 동화','옥효진 글 ,김미연 그림','한경BP',2022,'기타',963,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (965,'거짓말의 색깔','김화요 글 ,다나 그림','키즈스콜레',2022,'기타',963,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (966,'고롱고롱 하우스','글·그림: 조신애','사계절(사계절출판사)',2023,'기타',963,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (967,'가면산장 살인사건','지은이: 히가시노 게이고 ,옮긴이: 김난주','재인',2014,'기타',963,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (968,'(5천만 원으로 시작하는) 미라클 기적의 재개발 재건축 :세금·금리·청약에서 자유로운 재개발 투자의 마법','진와이스 지음','웅진씽크빅',2023,'기타',968,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (969,'수상한 화장실','박현숙 글 ,유영주 그림','북멘토',2020,'기타',968,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (970,'N','지은이: 미치오 슈스케 ,옮긴이: 이규원','북스피어',2023,'기타',968,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (971,'마지막 이야기 전달자','도나 바르바 이게라 글 ,김선희 옮김','위즈덤하우스',2022,'소설',968,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (972,'마트 사장 구드래곤','박현숙 글 ,이경석 그림','다산북스',2022,'기타',968,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (973,'푸른 사자 와니니 :이현 장편동화','지은이: 이현 ,그린이: 오윤화','창비',2015,'기타',973,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (974,'달 샤베트','지은이: 백희나','책읽는곰',2014,'기타',973,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (975,'(신비아파트) 한자귀신','글: 김강현 ,그림: 김기수','서울문화사',2019,'기타',973,7,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (976,'부자의 그릇 :돈을 다루는 능력을 키우는 법','이즈미 마사토 지음 ,김윤수 옮김','다산북스',2020,'기타',976,4,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (977,'급식왕 go','원작: 급식왕 ,그림: 구은미','북이십일',2020,'기타',976,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (978,'틀리면 어떡해?','김영진 글·그림','길벗어린이',2019,'기타',978,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (979,'십 년 가게와 마법사들 1 - 트루, 다시 만드는 마법사','히로시마 레이코 (지은이), 사다케 미호 (그림), 이소담 (옮긴이)','위즈덤하우스',2021,'기타',978,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (980,'수학 탐정스','조인하 글 ,조승연 그림','미래엔',2018,'기타',978,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (981,'오늘은 용돈 버는 날 :용돈을 똑똑하게 불리기 위한 첫걸음','연유진 글 ,간장 그림','풀빛',2022,'기타',978,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (982,'푸른 사자 와니니 :이현 장편동화','지은이: 이현 ,그린이: 오윤화','창비',2015,'기타',982,305,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (983,'비상식적 성공 법칙 :부의 추월차선에 올라타는 가장 강력한 8가지 습관','간다 마사노리 지음 ,서승범 옮김','생각지도',2022,'자기계발',982,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (984,'(쿠키런) 킹덤스쿨','글: 김언정 ,그림: 이태영','서울문화사',2021,'기타',982,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (985,'내 고백을 돌려줘! :숭민이의 일기 쉿! 비밀이야','이승민 글 ,박정섭 그림','풀빛',2022,'기타',982,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (986,'놓지 마 어휘 :한자어','신태훈,나승훈 글·그림','김영사',2021,'기타',986,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (987,'메이드','니타 프로스 지음 ,노진선 옮김','한국경제신문 한경BP',2023,'기타',986,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (988,'배드 가이즈','애런 블레이비 지음 ,신수진 옮김','비룡소',2021,'기타',986,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (989,'과학 탐정스 :기발한 과학 추리 동화','조인하 글 ,조승연 그림','미래엔',2020,'과학',986,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (990,'파과 :구병모 장편소설','지은이: 구병모','위즈덤하우스 미디어그룹',2018,'소설',986,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (991,'내일은 발명왕 :본격 대결 과학발명 만화','글: 곰돌이 co. ,그림: 홍종현','Mirae N 아이세움',2011,'과학',986,35,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (992,'(읽으면서 바로 써먹는) 어린이 속담','글·그림: 한날','파란정원',2020,'기타',986,2,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (993,'여행의 시간 :도시건축가 김진애의 인생 여행법','지은이: 김진애','창비',2023,'기타',993,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (994,'알로하, 나의 엄마들 :이금이 장편소설','지은이: 이금이','창비',2020,'소설',993,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (995,'지적 대화를 위한 넓고 얕은 지식 1 - 현실 편 : 역사 / 경제 / 정치 / 사회 / 윤리','채사장 (지은이)','웨일북',2020,'역사',995,1,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (996,'열두 살 경제학교 :부자가 되고 싶은 어린이를 위한 경제 교육 동화','권오상 지음 ,손수정 그림','카시오페아',2022,'기타',995,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (997,'데미안','지은이: 헤르만 헤세 ,옮긴이: 전영애','민음사',2000,'기타',997,44,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (998,'이상한 엄마 2','백희나','책읽는곰',2016,'기타',997,33,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (999,'수학을 잘하고 싶어졌습니다 :서울대 3번 입학, 14년을 다니며 깨달은 공부의 본질','서준석 지음','다산북스',2022,'기타',997,3,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (1000,'엄마 까투리','권정생 글 ,김세현 그림','낮은산',2008,'기타',997,5,to_date('25/02/26','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (1003,'잊지말자 지문출결','S.Y.lee','쌍용문학사',2025,'기타',null,2,to_date('25/02/28','RR/MM/DD'));
Insert into BOOK (BOOK_NUM,BOOK_TITLE,BOOK_AUTHOR,BOOK_PUBLISHER,BOOK_P_YEAR,BOOK_CATEGORY,BOOK_RANK,BOOK_VOLM_CNT,BOOK_REG_DATE) values (1004,'소나기','류선재','TVN',2021,'소설',1001,1,to_date('25/02/28','RR/MM/DD'));
REM INSERTING into BOOK_ORDER
SET DEFINE OFF;
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (33,'qwe123',80,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,0);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (34,'asdf1234',127,to_date('25/02/01','RR/MM/DD'),to_date('25/02/15','RR/MM/DD'),0,0);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (3,'asd123',1,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (4,'huibao',63,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,0);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (5,'asd123',2,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (6,'asd123',123,to_date('25/02/27','RR/MM/DD'),to_date('25/02/17','RR/MM/DD'),0,0);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (7,'asd123',1,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (8,'qwe123',122,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (9,'zxcv1234',51,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (61,'qwe1234',942,to_date('25/02/28','RR/MM/DD'),to_date('25/03/14','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (11,'qwer1234',2,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (12,'qwer1234',2,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (13,'zxcv1234',341,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,0);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (21,'qwer1234',255,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (22,'qwer1234',299,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,0);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (23,'asdf1234',123,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (24,'asdf1234',12,to_date('25/02/27','RR/MM/DD'),to_date('25/03/20','RR/MM/DD'),1,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (25,'asdf1234',24,to_date('25/02/27','RR/MM/DD'),to_date('25/02/17','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (26,'asd123',1,to_date('25/02/05','RR/MM/DD'),to_date('25/02/26','RR/MM/DD'),0,0);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (27,'uiop1234',2,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (28,'qwer1234',12,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (29,'asd123',696,to_date('25/02/01','RR/MM/DD'),to_date('25/02/15','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (30,'asdf1234',12,to_date('25/02/27','RR/MM/DD'),to_date('25/02/17','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (31,'qwer1234',80,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,0);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (32,'qwe123',3,to_date('25/02/27','RR/MM/DD'),to_date('25/03/13','RR/MM/DD'),0,1);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (35,'huibao',38,to_date('25/02/28','RR/MM/DD'),to_date('25/03/14','RR/MM/DD'),0,0);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (42,'hjkl123',3,to_date('25/02/28','RR/MM/DD'),to_date('25/03/14','RR/MM/DD'),0,0);
Insert into BOOK_ORDER (ORDER_NUM,MEM_ID,BOOK_NUM,ORDER_DATE,RETURN_DATE,IS_ADD,IS_RETURN) values (43,'hjkl123',3,to_date('25/02/28','RR/MM/DD'),to_date('25/03/14','RR/MM/DD'),0,0);
REM INSERTING into MEMBER
SET DEFINE OFF;
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('admin','admin1234^^','관리자','02-123-1234','admin@lb.com',to_date('24/01/01','RR/MM/DD'),null,null);
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('zxcv1234','zxcv1234%','김하언','010-2020-3040','zxcv1234@naver.com',to_date('25/02/26','RR/MM/DD'),to_date('25/02/27','RR/MM/DD'),null);
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('ruibao','rui7890^^','박슬기','010-7891-5763','rui@ever.land',to_date('25/02/03','RR/MM/DD'),null,null);
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('huibao','hui8282@@','박빛나','010-8357-1254','huit@ever.land',to_date('25/02/27','RR/MM/DD'),null,null);
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('qwe123','qwe123!!','qqq','010-1234-1234','qwe123@qwe123.com',to_date('25/02/27','RR/MM/DD'),to_date('25/02/28','RR/MM/DD'),null);
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('asd123','asd123!!','asd123','010-2345-2345','test@test.net',to_date('25/02/27','RR/MM/DD'),null,to_date('25/03/17','RR/MM/DD'));
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('qwer1234','qwer1234!','홍길동','010-1235-1234','hong_123@email.com',to_date('25/02/27','RR/MM/DD'),to_date('25/02/27','RR/MM/DD'),null);
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('asdf1234','asdf1234!','asdf','010-1234-1235','asdf@asdf.com',to_date('25/02/27','RR/MM/DD'),null,to_date('25/01/16','RR/MM/DD'));
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('uiop1234','uiop1234%','김하연','010-2929-3939','uiop@uiop.com',to_date('25/02/27','RR/MM/DD'),to_date('25/02/27','RR/MM/DD'),null);
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('hjkl123','hjkl1234%','김김김','010-2020-2020','hjkl@hjkl.com',to_date('25/02/28','RR/MM/DD'),null,null);
Insert into MEMBER (MEM_ID,MEM_PW,MEM_NAME,MEM_CELL,MEM_EMAIL,MEM_DATE,MEM_MDATE,MEM_STOP_DATE) values ('qwe1234','qwe123!!','qwe','010-4535-3455','test@tset.com',to_date('25/02/28','RR/MM/DD'),null,null);
REM INSERTING into NOTICE
SET DEFINE OFF;
Insert into NOTICE (NOTICE_NUM,NOTICE_TITLE,NOTICE_CONTENT,NOTICE_VIEW,NOTICE_REG_DATE) values (1,'찾아오시는 길','우리 도서관 휴무일은 매주 화요일과 공휴일(대체공휴일 포함)입니다. 도서관 이용에 참고하시기 바랍니다.',8,to_date('25/02/01','RR/MM/DD'));
Insert into NOTICE (NOTICE_NUM,NOTICE_TITLE,NOTICE_CONTENT,NOTICE_VIEW,NOTICE_REG_DATE) values (2,'도서관 휴무일 안내','우리 도서관 휴무일은 매주 화요일과 공휴일(대체공휴일 포함)입니다. 도서관 이용에 참고하시기 바랍니다.',3,to_date('25/02/01','RR/MM/DD'));
Insert into NOTICE (NOTICE_NUM,NOTICE_TITLE,NOTICE_CONTENT,NOTICE_VIEW,NOTICE_REG_DATE) values (3,'도서 대여 시스템 이용 안내','도서 대여시 14일간 대여가능하며, 연장 필요시 1회에 한하여 일주일 연장이 가능합니다. 1인당 대여권수는 3권, 예약권수는 2권으로 제한합니다.',5,to_date('25/02/27','RR/MM/DD'));
REM INSERTING into QNA
SET DEFINE OFF;
Insert into QNA (QNA_NUM,QNA_TITLE,QNA_CONTENT,QNA_VIEW,QNA_RE,Q_DATE,A_DATE,MEM_ID) values (1,'RE:도서관 운영시간 문의','도서관 운영시간 문의드립니다.',0,'우리 도서관 운영시간은 운영일 9AM~6PM입니다.',to_date('25/02/05','RR/MM/DD'),to_date('25/02/27','RR/MM/DD'),'ruibao');
Insert into QNA (QNA_NUM,QNA_TITLE,QNA_CONTENT,QNA_VIEW,QNA_RE,Q_DATE,A_DATE,MEM_ID) values (2,'RE:1인당 대여가능 권수 문의','1인당 대여가능 권수 문의드립니다.',0,'1인당 최대 3권까지 대여 가능합니다.',to_date('25/02/27','RR/MM/DD'),to_date('25/02/28','RR/MM/DD'),'huibao');
Insert into QNA (QNA_NUM,QNA_TITLE,QNA_CONTENT,QNA_VIEW,QNA_RE,Q_DATE,A_DATE,MEM_ID) values (23,'공휴일이 언제일까요?','공휴일이 언제일까요?? 궁금합니다',0,null,to_date('25/02/27','RR/MM/DD'),null,'uiop1234');
Insert into QNA (QNA_NUM,QNA_TITLE,QNA_CONTENT,QNA_VIEW,QNA_RE,Q_DATE,A_DATE,MEM_ID) values (25,'RE:몇권까지 대출 가능?','책은 몇권까지 대출가능한가요?',0,'3권입니다^^',to_date('25/02/27','RR/MM/DD'),to_date('25/02/27','RR/MM/DD'),'qwe123');
Insert into QNA (QNA_NUM,QNA_TITLE,QNA_CONTENT,QNA_VIEW,QNA_RE,Q_DATE,A_DATE,MEM_ID) values (24,'RE:도서관 공휴일을 알고싶습니다','도서관 공휴일은 언제입니까?',0,'우리 도서관은 통상의 공휴일(대체공휴일 포함)을 따르며, 매주 화요일은 도서관 정기 휴관일입니다.',to_date('25/02/27','RR/MM/DD'),to_date('25/02/27','RR/MM/DD'),'uiop1234');
Insert into QNA (QNA_NUM,QNA_TITLE,QNA_CONTENT,QNA_VIEW,QNA_RE,Q_DATE,A_DATE,MEM_ID) values (27,'RE:예약은 몇권까지?','예약은 몇권까지 가능한가요?',0,'2권입니다',to_date('25/02/27','RR/MM/DD'),to_date('25/02/28','RR/MM/DD'),'asd123');
REM INSERTING into RESERVATION
SET DEFINE OFF;
Insert into RESERVATION (RE_NUM,MEM_ID,BOOK_NUM) values (1,'huibao',63);
Insert into RESERVATION (RE_NUM,MEM_ID,BOOK_NUM) values (21,'asd123',80);
Insert into RESERVATION (RE_NUM,MEM_ID,BOOK_NUM) values (42,'ruibao',3);
Insert into RESERVATION (RE_NUM,MEM_ID,BOOK_NUM) values (43,'huibao',3);
Insert into RESERVATION (RE_NUM,MEM_ID,BOOK_NUM) values (24,'ruibao',80);
Insert into RESERVATION (RE_NUM,MEM_ID,BOOK_NUM) values (31,'qwe123',63);
REM INSERTING into REVIEW
SET DEFINE OFF;
Insert into REVIEW (REVIEW_NUM,BOOK_NUM,REVIEW_CONTENT,REVIEW_RATE,REVIEW_REG_DATE,MEM_ID) values (41,884,'성공비법을 알 수 있어 좋았다.',4,to_date('25/02/28','RR/MM/DD'),'hjkl123');
Insert into REVIEW (REVIEW_NUM,BOOK_NUM,REVIEW_CONTENT,REVIEW_RATE,REVIEW_REG_DATE,MEM_ID) values (2,1,'베스트셀러인 이유가 있다',5,to_date('25/02/27','RR/MM/DD'),'asd123');
Insert into REVIEW (REVIEW_NUM,BOOK_NUM,REVIEW_CONTENT,REVIEW_RATE,REVIEW_REG_DATE,MEM_ID) values (3,1,'역작',4,to_date('25/02/27','RR/MM/DD'),'asd123');
Insert into REVIEW (REVIEW_NUM,BOOK_NUM,REVIEW_CONTENT,REVIEW_RATE,REVIEW_REG_DATE,MEM_ID) values (26,3,'재밌다!',5,to_date('25/02/27','RR/MM/DD'),'qwe123');
Insert into REVIEW (REVIEW_NUM,BOOK_NUM,REVIEW_CONTENT,REVIEW_RATE,REVIEW_REG_DATE,MEM_ID) values (42,545,'말해뭐해.짱잼',5,to_date('25/02/28','RR/MM/DD'),'huibao');
Insert into REVIEW (REVIEW_NUM,BOOK_NUM,REVIEW_CONTENT,REVIEW_RATE,REVIEW_REG_DATE,MEM_ID) values (23,24,'재미있습니다',4,to_date('25/02/27','RR/MM/DD'),'ruibao');
Insert into REVIEW (REVIEW_NUM,BOOK_NUM,REVIEW_CONTENT,REVIEW_RATE,REVIEW_REG_DATE,MEM_ID) values (61,942,'ㅇㅇ',5,to_date('25/02/28','RR/MM/DD'),'qwe1234');
REM INSERTING into WISH_BOOK
SET DEFINE OFF;
Insert into WISH_BOOK (WISH_NUM,WISH_TITLE,WISH_AUTHOR,WISH_PUBLISHER,WISH_DATE,MEM_ID) values (1,'안녕','몰랑','우후',to_date('25/02/26','RR/MM/DD'),'zxcv1234');
Insert into WISH_BOOK (WISH_NUM,WISH_TITLE,WISH_AUTHOR,WISH_PUBLISHER,WISH_DATE,MEM_ID) values (2,'자바, 궁극의 어려움','잡아바','쌍용문학사',to_date('25/02/27','RR/MM/DD'),'ruibao');
Insert into WISH_BOOK (WISH_NUM,WISH_TITLE,WISH_AUTHOR,WISH_PUBLISHER,WISH_DATE,MEM_ID) values (21,'봄에는 무슨 꽃이 피나요','김지은','비상',to_date('25/02/27','RR/MM/DD'),'zxcv1234');
Insert into WISH_BOOK (WISH_NUM,WISH_TITLE,WISH_AUTHOR,WISH_PUBLISHER,WISH_DATE,MEM_ID) values (27,'자바의 정석','남궁성',null,to_date('25/02/28','RR/MM/DD'),'qwer1234');
Insert into WISH_BOOK (WISH_NUM,WISH_TITLE,WISH_AUTHOR,WISH_PUBLISHER,WISH_DATE,MEM_ID) values (29,'흔한남매1','흔한남매',null,to_date('25/02/28','RR/MM/DD'),'qwer1234');
Insert into WISH_BOOK (WISH_NUM,WISH_TITLE,WISH_AUTHOR,WISH_PUBLISHER,WISH_DATE,MEM_ID) values (26,'자바의 정석','남궁성',null,to_date('25/02/28','RR/MM/DD'),'qwe123');
Insert into WISH_BOOK (WISH_NUM,WISH_TITLE,WISH_AUTHOR,WISH_PUBLISHER,WISH_DATE,MEM_ID) values (28,'흔한 남매1','흔한 남매',null,to_date('25/02/28','RR/MM/DD'),'qwer1234');
Insert into WISH_BOOK (WISH_NUM,WISH_TITLE,WISH_AUTHOR,WISH_PUBLISHER,WISH_DATE,MEM_ID) values (41,'불타는 금요일','T.GI','Friday',to_date('25/02/28','RR/MM/DD'),'huibao');
--------------------------------------------------------
--  Constraints for Table BOOK
--------------------------------------------------------

  ALTER TABLE "BOOK" ADD PRIMARY KEY ("BOOK_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "BOOK" MODIFY ("BOOK_REG_DATE" NOT NULL ENABLE);
  ALTER TABLE "BOOK" MODIFY ("BOOK_VOLM_CNT" NOT NULL ENABLE);
  ALTER TABLE "BOOK" MODIFY ("BOOK_CATEGORY" NOT NULL ENABLE);
  ALTER TABLE "BOOK" MODIFY ("BOOK_P_YEAR" NOT NULL ENABLE);
  ALTER TABLE "BOOK" MODIFY ("BOOK_PUBLISHER" NOT NULL ENABLE);
  ALTER TABLE "BOOK" MODIFY ("BOOK_AUTHOR" NOT NULL ENABLE);
  ALTER TABLE "BOOK" MODIFY ("BOOK_TITLE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RESERVATION
--------------------------------------------------------

  ALTER TABLE "RESERVATION" ADD PRIMARY KEY ("RE_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "RESERVATION" MODIFY ("BOOK_NUM" NOT NULL ENABLE);
  ALTER TABLE "RESERVATION" MODIFY ("MEM_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BOOK_ORDER
--------------------------------------------------------

  ALTER TABLE "BOOK_ORDER" ADD PRIMARY KEY ("ORDER_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "BOOK_ORDER" MODIFY ("IS_RETURN" NOT NULL ENABLE);
  ALTER TABLE "BOOK_ORDER" MODIFY ("IS_ADD" NOT NULL ENABLE);
  ALTER TABLE "BOOK_ORDER" MODIFY ("RETURN_DATE" NOT NULL ENABLE);
  ALTER TABLE "BOOK_ORDER" MODIFY ("ORDER_DATE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table QNA
--------------------------------------------------------

  ALTER TABLE "QNA" ADD PRIMARY KEY ("QNA_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "QNA" MODIFY ("Q_DATE" NOT NULL ENABLE);
  ALTER TABLE "QNA" MODIFY ("QNA_VIEW" NOT NULL ENABLE);
  ALTER TABLE "QNA" MODIFY ("QNA_CONTENT" NOT NULL ENABLE);
  ALTER TABLE "QNA" MODIFY ("QNA_TITLE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MEMBER
--------------------------------------------------------

  ALTER TABLE "MEMBER" ADD UNIQUE ("MEM_CELL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "MEMBER" ADD PRIMARY KEY ("MEM_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "MEMBER" MODIFY ("MEM_DATE" NOT NULL ENABLE);
  ALTER TABLE "MEMBER" MODIFY ("MEM_EMAIL" NOT NULL ENABLE);
  ALTER TABLE "MEMBER" MODIFY ("MEM_CELL" NOT NULL ENABLE);
  ALTER TABLE "MEMBER" MODIFY ("MEM_NAME" NOT NULL ENABLE);
  ALTER TABLE "MEMBER" MODIFY ("MEM_PW" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table REVIEW
--------------------------------------------------------

  ALTER TABLE "REVIEW" ADD PRIMARY KEY ("REVIEW_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "REVIEW" ADD CHECK (review_rate between 1 and 5) ENABLE;
  ALTER TABLE "REVIEW" MODIFY ("REVIEW_REG_DATE" NOT NULL ENABLE);
  ALTER TABLE "REVIEW" MODIFY ("REVIEW_RATE" NOT NULL ENABLE);
  ALTER TABLE "REVIEW" MODIFY ("REVIEW_CONTENT" NOT NULL ENABLE);
  ALTER TABLE "REVIEW" MODIFY ("BOOK_NUM" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table WISH_BOOK
--------------------------------------------------------

  ALTER TABLE "WISH_BOOK" ADD PRIMARY KEY ("WISH_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "WISH_BOOK" MODIFY ("WISH_DATE" NOT NULL ENABLE);
  ALTER TABLE "WISH_BOOK" MODIFY ("WISH_AUTHOR" NOT NULL ENABLE);
  ALTER TABLE "WISH_BOOK" MODIFY ("WISH_TITLE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table NOTICE
--------------------------------------------------------

  ALTER TABLE "NOTICE" ADD PRIMARY KEY ("NOTICE_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "NOTICE" MODIFY ("NOTICE_REG_DATE" NOT NULL ENABLE);
  ALTER TABLE "NOTICE" MODIFY ("NOTICE_VIEW" NOT NULL ENABLE);
  ALTER TABLE "NOTICE" MODIFY ("NOTICE_CONTENT" NOT NULL ENABLE);
  ALTER TABLE "NOTICE" MODIFY ("NOTICE_TITLE" NOT NULL ENABLE);
