select * from tab;
select * from board222;

CREATE TABLE BOARD222(
	BOARD_NUM NUMBER,
	BOARD_NAME VARCHAR2(20),
	BOARD_PASS VARCHAR2(15),
	BOARD_SUBJECT VARCHAR2(50),
	BOARD_CONTENT VARCHAR2(2000),
	BOARD_FILE VARCHAR2(50),
	BOARD_RE_REF NUMBER,
	BOARD_RE_LEV NUMBER,
	BOARD_RE_SEQ NUMBER,
	BOARD_READCOUNT NUMBER,
	BOARD_DATE DATE,
	PRIMARY KEY(BOARD_NUM)
);

create sequence QNA_BOARD_SEQ
start with 1
increment by 1
nocache;
create sequence FORUM_BOARD_SEQ
start with 1
increment by 1
nocache;

create sequence ua_seq
start with 1
increment by 1
nocache;

drop table board222 purge;
select * from tab;
select * from seq;
select * from board222;

delete from board222;




insert into	qna_board values(	qna_board_seq.nextval, NULL, NULL, NULL,'�׽�Ʈ',
		'1234',
		'����',
		'����',
		NULL,
		qna_board_seq.nextval,
		0,
		0,
		0,
		sysdate
		);	
