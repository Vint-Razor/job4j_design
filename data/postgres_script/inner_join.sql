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
insert into unit("name") values('Karl');
insert into unit("name") values('Bob');

select u.name, c.class
from unit as u join unit_class as c on u.unit_class_id = c.id;

select u.name as Имя, c.class as Класс
from unit as u join unit_class as c on u.unit_class_id = c.id;

select u.name "Имя персонажа", c.class "Класс персонажа"
from unit u join unit_class c on u.unit_class_id = c.id;

select * from unit;