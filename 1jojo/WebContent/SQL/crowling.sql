/* 사용자계정정보 */
CREATE TABLE useraccount (
	No NUMBER NOT NULL, /* 사용자번호 */
	ID VARCHAR2(20) NOT NULL, /* 아이디 */
	PASS VARCHAR2(20) NOT NULL /* 암호 */
);

COMMENT ON TABLE useraccount IS '사용자계정정보';

COMMENT ON COLUMN useraccount.No IS '사용자번호';

COMMENT ON COLUMN useraccount.ID IS '아이디';

COMMENT ON COLUMN useraccount.PASS IS '암호';

CREATE UNIQUE INDEX PK_useraccount
	ON useraccount (
		No ASC
	);

ALTER TABLE useraccount
	ADD
		CONSTRAINT PK_useraccount
		PRIMARY KEY (
			No
		);

/* QnA게시판 */
CREATE TABLE qna_board (
	board_Num NUMBER NOT NULL, /* 게시물번호 */
	No NUMBER, /* 사용자번호 */
	board_name VARCHAR2(20), /* 이름 */
	board_pass VARCHAR2(20), /* 게시물암호 */
	board_subject VARCHAR2(40), /* 제목 */
	board_content VARCHAR2(1000), /* 본문 */
	board_file VARCHAR2(100), /* 첨부파일 */
	board_re_ref NUMBER, /* 글그룹 */
	board_re_lev INTEGER, /* 글깊이 */
	board_re_seq INTEGER, /* 글순서 */
	board_readcount INTEGER, /* 조회수 */
	board_date DATE /* 게시시간 */
);

COMMENT ON TABLE qna_board IS 'QnA게시판';

COMMENT ON COLUMN qna_board.board_Num IS '게시물번호';

COMMENT ON COLUMN qna_board.No IS '사용자번호';

COMMENT ON COLUMN qna_board.board_name IS '이름';

COMMENT ON COLUMN qna_board.board_pass IS '게시물암호';

COMMENT ON COLUMN qna_board.board_subject IS '제목';

COMMENT ON COLUMN qna_board.board_content IS '본문';

COMMENT ON COLUMN qna_board.board_file IS '첨부파일';

COMMENT ON COLUMN qna_board.board_re_ref IS '글그룹';

COMMENT ON COLUMN qna_board.board_re_lev IS '글깊이';

COMMENT ON COLUMN qna_board.board_re_seq IS '글순서';

COMMENT ON COLUMN qna_board.board_readcount IS '조회수';

COMMENT ON COLUMN qna_board.board_date IS '게시시간';

CREATE UNIQUE INDEX PK_qna_board
	ON qna_board (
		board_Num ASC
	);

ALTER TABLE qna_board
	ADD
		CONSTRAINT PK_qna_board
		PRIMARY KEY (
			board_Num
		);

/* 업체검색결과 */
CREATE TABLE search_qual (
	search_com_No VARCHAR(12) NOT NULL, /* 번호 */
	No NUMBER, /* 사용자번호 */
	com_qual VARCHAR2(1000), /* 업체지원자격 */
	com_preex VARCHAR2(1000), /* 업체우대사항 */
	com_frequncy INTEGER /* 빈도수 */
);

COMMENT ON TABLE search_qual IS '업체검색결과';

COMMENT ON COLUMN search_qual.search_com_No IS '번호';

COMMENT ON COLUMN search_qual.No IS '사용자번호';

COMMENT ON COLUMN search_qual.com_qual IS '업체지원자격';

COMMENT ON COLUMN search_qual.com_preex IS '업체우대사항';

COMMENT ON COLUMN search_qual.com_frequncy IS '빈도수';

/* 해당업체 리스트 테이블 */
CREATE TABLE search_list (
	com_link VARCHAR2(500) NOT NULL, /* 지원자격포함 기업 링크 */
	search_com_No VARCHAR(12) NOT NULL, /* 번호 */
	com_qual VARCHAR2(1000), /* 업체지원자격 */
	com_preex VARCHAR2(1000), /* 업체우대사항 */
	com_name VARCHAR2(100) /* 지원자격포함 기업명 */
);

COMMENT ON TABLE search_list IS '해당업체 리스트 테이블';

COMMENT ON COLUMN search_list.com_link IS '지원자격포함 기업 링크';

COMMENT ON COLUMN search_list.search_com_No IS '번호';

COMMENT ON COLUMN search_list.com_qual IS '업체지원자격';

COMMENT ON COLUMN search_list.com_preex IS '업체우대사항';

COMMENT ON COLUMN search_list.com_name IS '지원자격포함 기업명';

CREATE UNIQUE INDEX PK_search_list
	ON search_list (
		com_link ASC
	);

ALTER TABLE search_list
	ADD
		CONSTRAINT PK_search_list
		PRIMARY KEY (
			com_link
		);

/* 포럼게시판 */
CREATE TABLE forum_board (
	board_Num NUMBER NOT NULL, /* 게시물번호 */
	No NUMBER, /* 사용자번호 */
	board_name VARCHAR2(20), /* 이름 */
	board_pass VARCHAR2(20), /* 게시물암호 */
	board_subject VARCHAR2(40), /* 제목 */
	board_content VARCHAR2(1000), /* 본문 */
	board_file VARCHAR2(100), /* 첨부파일 */
	board_re_ref NUMBER, /* 글그룹 */
	board_re_lev INTEGER, /* 글깊이 */
	board_re_seq INTEGER, /* 글순서 */
	board_readcount INTEGER, /* 조회수 */
	board_date DATE /* 게시시간 */
);

COMMENT ON TABLE forum_board IS '포럼게시판';

COMMENT ON COLUMN forum_board.board_Num IS '게시물번호';

COMMENT ON COLUMN forum_board.No IS '사용자번호';

COMMENT ON COLUMN forum_board.board_name IS '이름';

COMMENT ON COLUMN forum_board.board_pass IS '게시물암호';

COMMENT ON COLUMN forum_board.board_subject IS '제목';

COMMENT ON COLUMN forum_board.board_content IS '본문';

COMMENT ON COLUMN forum_board.board_file IS '첨부파일';

COMMENT ON COLUMN forum_board.board_re_ref IS '글그룹';

COMMENT ON COLUMN forum_board.board_re_lev IS '글깊이';

COMMENT ON COLUMN forum_board.board_re_seq IS '글순서';

COMMENT ON COLUMN forum_board.board_readcount IS '조회수';

COMMENT ON COLUMN forum_board.board_date IS '게시시간';

CREATE UNIQUE INDEX PK_forum_board
	ON forum_board (
		board_Num ASC
	);

ALTER TABLE forum_board
	ADD
		CONSTRAINT PK_forum_board
		PRIMARY KEY (
			board_Num
		);

ALTER TABLE qna_board
	ADD
		CONSTRAINT FK_useraccount_TO_qna_board
		FOREIGN KEY (
			No
		)
		REFERENCES useraccount (
			No
		);

ALTER TABLE search_qual
	ADD
		CONSTRAINT FK_useraccount_TO_search_qual
		FOREIGN KEY (
			No
		)
		REFERENCES useraccount (
			No
		);

ALTER TABLE forum_board
	ADD
		CONSTRAINT FK_useraccount_TO_forum_board
		FOREIGN KEY (
			No
		)
		REFERENCES useraccount (
			No
		);
		

create sequence board222_seq
start with 1
increment by 1
nocache;


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