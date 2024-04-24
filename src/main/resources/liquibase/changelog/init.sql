create table if not exists BOOK(
  ID varchar(40) NOT NULL,
  CREATED_DATE datetime DEFAULT current_timestamp,
  CREATED_BY varchar(100) DEFAULT 'SYSTEM',
  VERSION int DEFAULT 1,
  UPDATED_DATE datetime DEFAULT current_timestamp,
  UPDATED_BY varchar(100) DEFAULT 'SYSTEM',
  TITLE VARCHAR(100),
  AUTHOR VARCHAR(100),
  PUBLISHER VARCHAR(100),
  PUBLISHED_YEAR int,
  ROOM_LOCATION VARCHAR(100),
  CABINET_LOCATION VARCHAR(100),
  SHELF_LOCATION VARCHAR(100),
  TOTAL_BOOK int,
  CRITERIA VARCHAR(100),
  GENRE VARCHAR(100)
);

create table if not exists JOURNAL(
  ID varchar(40) NOT NULL,
  CREATED_DATE datetime DEFAULT current_timestamp,
  CREATED_BY varchar(100) DEFAULT 'SYSTEM',
  VERSION int DEFAULT 1,
  UPDATED_DATE datetime DEFAULT current_timestamp,
  UPDATED_BY varchar(100) DEFAULT 'SYSTEM',
  TITLE VARCHAR(100),
  AUTHOR VARCHAR(100),
  PUBLISHER VARCHAR(100),
  PUBLISHED_YEAR int,
  ROOM_LOCATION VARCHAR(100),
  CABINET_LOCATION VARCHAR(100),
  SHELF_LOCATION VARCHAR(100),
  journal_type VARCHAR(100)
);

create table if not exists MEMBER(
  ID varchar(40) NOT NULL,
  CREATED_DATE datetime DEFAULT current_timestamp,
  CREATED_BY varchar(100) DEFAULT 'SYSTEM',
  VERSION int DEFAULT 1,
  UPDATED_DATE datetime DEFAULT current_timestamp,
  UPDATED_BY varchar(100) DEFAULT 'SYSTEM',
  NAME varchar(100),
  NIK varchar(50),
  PHONE_NUMBER VARCHAR(30),
  EMAIL varchar(50),
  status varchar(50),
  total_book_taken int
);

create table if not exists BORROWED_BOOK(
  ID varchar(40) NOT NULL,
  CREATED_DATE datetime DEFAULT current_timestamp,
  CREATED_BY varchar(100) DEFAULT 'SYSTEM',
  VERSION int DEFAULT 1,
  UPDATED_DATE datetime DEFAULT current_timestamp,
  UPDATED_BY varchar(100) DEFAULT 'SYSTEM',
  book_id varchar(40),
  member_id varchar(40),
  from_date DATE,
  to_date DATE,
  status varchar(50),
  total int
);

create table if not exists USERS(
  ID varchar(40) NOT NULL,
  username varchar(100),
  password varchar(100)
);

create table if not exists ROLES(
  ID varchar(40) NOT NULL,
  name varchar(100)
);

create table if not exists USER_ROLES(
  USER_ID varchar(40) NOT NULL,
  ROLE_ID varchar(40) NOT NULL
);

ALTER TABLE BOOK
ADD CONSTRAINT u_id_book UNIQUE (id);

ALTER TABLE JOURNAL
ADD CONSTRAINT u_id_journal UNIQUE (id);

ALTER TABLE MEMBER
ADD CONSTRAINT u_id_member UNIQUE (id);

ALTER TABLE BORROWED_BOOK
ADD CONSTRAINT u_id_borrowed_book UNIQUE (id);

ALTER TABLE USERS
ADD CONSTRAINT u_id_users UNIQUE (id);

ALTER TABLE ROLES
ADD CONSTRAINT u_id_roles UNIQUE (id);

ALTER TABLE BORROWED_BOOK
    ADD CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES BOOK (id);

ALTER TABLE BORROWED_BOOK
    ADD CONSTRAINT fk_member_id FOREIGN KEY (member_id) REFERENCES MEMBER(id);

ALTER TABLE USER_ROLES
    ADD CONSTRAINT fk_user_id FOREIGN KEY (USER_ID) REFERENCES USERS (id);

ALTER TABLE USER_ROLES
    ADD CONSTRAINT fk_role_id FOREIGN KEY (ROLE_ID) REFERENCES ROLES (id);