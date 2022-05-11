DROP TABLE IF EXISTS tbl_customer;
DROP TABLE IF EXISTS tbl_account;
DROP TABLE IF EXISTS tbl_log;

CREATE TABLE tbl_customer (
	customerId varchar(255) not null PRIMARY KEY,
	customerName varchar(255),
	phoneNum varchar(255),
	customerAdd varchar(255)
);

CREATE TABLE tbl_account (
	accountId varchar(255) not null PRIMARY KEY,
	customerId varchar(255),
	openDate varchar(255),
	balance varchar(255)
);

CREATE TABLE tbl_log (
	logId varchar(255) not null PRIMARY KEY,
	guid varchar(255),
	prgno varchar(255),
	trxDateTime varchar(255),
	resDateTime varchar(255),
	logTime varchar(255),
	bizData clob,
	requestType varchar(255), 
	responseType varchar(255)
);
