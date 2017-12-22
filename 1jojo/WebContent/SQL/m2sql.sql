create table useraccount (
No NUMBER,
id varchar2(30),
pass varchar2(40),
primary key(No)
);

create sequence useraccount_seq
start with 1
increment by 1
nocache;

create sequence ua_seq
start with 1
increment by 1
nocache;

insert into USERACCOUNT values ("sss", "1111");

select * from tab;
select * from USERACCOUNT;
drop table useraccount purge;
select * from user_sequences;

drop sequence useraccount_seq;
