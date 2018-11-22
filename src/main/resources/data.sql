insert into medicine (id, name, generic) values (1, 'Dorflex',0);
insert into medicine (id, name, generic) values (2, 'Amoxilina',0);
insert into medicine (id, name, generic) values (3, 'Minoxidil',0);
insert into medicine (id, name, generic) values (4, 'Dipirona',0);
insert into medicine (id, name, generic) values (5, 'Bromoprida',0);
insert into medicine (id, name, generic) values (6, 'Carvedilol',0);
insert into medicine (id, name, generic) values (7, 'Rivotril',0);
insert into medicine (id, name, generic) values (8, 'Dorflex 2',0);
insert into medicine (id, name, generic) values (9, 'Dorflex 3',0);
insert into medicine (id, name, generic) values (10,'Dorflex 4',0);


insert into user (id, name, username, password, cpf, email) values (5000,'Paulo Henrique', 'paulo','admin','00000000000','paulo@med4you.com');
insert into user (id, name, username, password, cpf, email) values (5001,'Eduardo', 'eduardo','admin','11111111111','eduardo@med4you.com');
insert into user (id, name, username, password, cpf, email, responsable_user_id, firebase_token) values (5002,'Filipe', 'filipe','admin','22222222222','filipe@med4you.com', 5001,'d9MV6594FXQ%3AAPA91bFhcVhEblFhIPOf2wAZpkvWvFKU0-GwHrZNCT3iDHWyKw3hqujejDwwdQ1_wXxQQHQb-CwQJ7hvSe9BMPo9Kw-JawqWjQbfGvPWbyOicOG0rhdr1BS8E4ISeM0rQPgKaSusJZ2o');
insert into user (id, name, username, password, cpf, email, responsable_user_id) values (5003,'Administrador', 'admin','admin','33333333333','admin@med4you.com', 5000);



Insert into doctor (id, name) values (1, 'Pedro');
Insert into doctor (id, name) values (2, 'Zé');