create table client
(
	id serial primary key,
	"name" varchar(255)
);
create table phone_number
(
	id serial primary key,
	"number" bigint
);
create table client_phone
(
	client_id int references client(id) unique,
	phone_id int references phone_number(id) unique
);
insert into client("name") values ('D. Johnson');
insert into client("name") values ('Y. Woo');
insert into client("name") values ('H. Merphy');
insert into phone_number("number") values (89011234567);
insert into phone_number("number") values (89021234513);
insert into phone_number("number") values (89021784567);
insert into client_phone(client_id, phone_id) values (1, 2);
insert into client_phone(client_id, phone_id) values (3, 1);
insert into client_phone(client_id, phone_id) values (2, 3);