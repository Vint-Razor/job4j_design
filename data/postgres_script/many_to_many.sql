create table equipment
(
	id serial primary key,
	"name" varchar
);
create table class_equipment
(
	id serial primary key,
	class_id int references unit_class(id),
	equipment_id int references equipment(id)
);
insert into equipment("name") values ('armour');
insert into equipment("name") values ('sword');
insert into equipment("name") values ('bow');
insert into equipment("name") values ('poison');
insert into class_equipment(class_id, equipment_id) values (1, 1);
insert into class_equipment(class_id, equipment_id) values (1, 2);
insert into class_equipment(class_id, equipment_id) values (2, 1);
insert into class_equipment(class_id, equipment_id) values (2, 3);
insert into class_equipment(class_id, equipment_id) values (3, 4);
insert into class_equipment(class_id, equipment_id) values (3, 2);