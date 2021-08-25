
CREATE TABLE BOOK (
	ID VARCHAR PRIMARY KEY NOT NULL,
	TITLE VARCHAR UNIQUE,
	AUTHOR VARCHAR  NOT NULL,
	PUBLISHER VARCHAR NOT NULL,
	PUBLICATION_YEAR INT NOT NULL
);


CREATE TABLE CURRENT_STOCK (
	ID VARCHAR PRIMARY KEY NOT NULL,
	TITLE VARCHAR
);