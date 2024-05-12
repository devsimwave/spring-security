insert into Users (id ,email, password, name, isActivated)
values (1, 'admin@admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);


insert into AUTHORITY (authorityName,USERSID ) values ('ROLE_USER', 1);
insert into AUTHORITY (authorityName,USERSID ) values ('ROLE_ADMIN', 1);

