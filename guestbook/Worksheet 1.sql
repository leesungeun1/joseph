-- 원문 테이블
  create table guest10(
    g_no number(20) primary key
   ,g_name varchar2(50)
   ,g_title varchar2(100)
   ,g_pwd varchar2(20)
   ,g_cont varchar2(4000)
   ,g_hit number(20) default 0
   ,g_date date
  );
  
  create sequence g_no_seq10
  increment by 1 start with 1 nocache;