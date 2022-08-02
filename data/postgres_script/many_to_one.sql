create table unit_class
(
	id serial primary key,
	"class" varchar(255)
);
create table unit 
(
	id serial primary key,
	"name" varchar(255),
	unit_class_id int references unit_class(id)
);
insert into unit_class("class") values ('warrior');
insert into unit_class("class") values ('archer');
insert into unit_class("class") values ('mage');
insert into unit("name", unit_class_id) values('John', 1);
insert into unit("name", unit_class_id) values('Bill', 1);
insert into unit("name", unit_class_id) values('Sam', 2);
insert into unit("name", unit_class_id) values('Robin', 2);
insert into unit("name", unit_class_id) values('Veniamin', 3);