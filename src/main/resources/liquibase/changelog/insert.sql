insert into ROLES (ID, NAME) VALUES ('ROLE_USER', 'ROLE_USER');
insert into ROLES (ID, NAME) VALUES ('ROLE_MODERATOR', 'ROLE_MODERATOR');
insert into ROLES (ID, NAME) VALUES ('ROLE_ADMIN', 'ROLE_ADMIN');

insert into USERS (ID, USERNAME, PASSWORD) VALUES ('USER_ADMIN-001', 'admin', '$2a$10$R6V0fLhxSkrnpGUNka52POn1QkitBqvvrCdzz67SQHS8ae6NWcHLO');

insert into USER_ROLES(USER_ID,ROLE_ID) VALUES('USER_ADMIN-001','ROLE_ADMIN');