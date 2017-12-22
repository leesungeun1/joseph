/* comment.sql */

-- 댓글 테이블
 create table comment7(
  comment_no number(20) primary key /*댓글 번호*/
  ,comment_name varchar2(100) /*댓글이름*/
  ,comment_cont varchar2(4000) not null /*댓글 내용*/
  ,comment_date date /*댓글 등록날짜*/
  ,g_no number(20) not null
  references guest10(g_no) /*guest10 테이블의 g_no 필드를 주인키로
  가리키고 있다. 외래키(foreign key) 설정 */
 );
 
 create sequence comment_no_seq
 increment by 1 start with 1 nocache;
 
-- select * from comment7 order by comment_no asc;
-- 
-- select count(comment_no) as 댓글개수 from comment7 where g_no=1;
-- 
-- select * from comment7 where g_no=1 order by comment_no asc;
 

 