insert into Users (id ,email, password, name, isActivated)
values (1, 'admin@admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);

insert into Users (id ,email, password, name, isActivated)
values (2, 'user@user', '$2a$12$o/8Y88Yq.1aR4viU4AwcdO.fTl362nm7VWt7n3stzHuL3tJ2/X6bS', 'user', 1);


insert into AUTHORITY (id, authorityName,USERSID ) values (1,'ROLE_USER', 1);
insert into AUTHORITY (id, authorityName,USERSID ) values (2,'ROLE_ADMIN', 1);

insert into AUTHORITY (id, authorityName,USERSID ) values (3,'ROLE_USER', 2);

