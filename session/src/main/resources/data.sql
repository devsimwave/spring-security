insert into Users (id ,email, password, name, isActivated)
values (1, 'admin@admin', '$2a$12$0Y27n/wvGoh7fSkzDk3/OeeC0VpPC1X6SXOflajI.QW1X8PwBaJqG', 'admin', 1);

-- user123!
insert into Users (id ,email, password, name, isActivated)
values (2, 'user@user', '$2a$12$U0Snh24MyokpYvZ.xFhZ0.Y5wkelJw4bADDBgUxmqvhMHL77VeLUa', 'user', 1);


insert into AUTHORITY (id, authorityName,USERSID ) values (1,'ROLE_USER', 1);
insert into AUTHORITY (id, authorityName,USERSID ) values (2,'ROLE_ADMIN', 1);

insert into AUTHORITY (id, authorityName,USERSID ) values (3,'ROLE_USER', 2);

