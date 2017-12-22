CREATE TABLE search_list(
	search_com_No VARCHAR2(12),
	com_qual VARCHAR2(1000),
	com_preex VARCHAR2(1000),
	com_name VARCHAR2(100),
	com_link VARCHAR2(500)
);

drop table search_list purge;
select * from tab;
select * from search_list;

CREATE TABLE search_qual(
	search_com_No VARCHAR2(12),
	No NUMBER,
	com_qual VARCHAR2(1000),
	com_preex VARCHAR2(1000),
	com_frequency NUMBER
);

drop table search_qual purge;
select * from tab;
select * from search_qual;

