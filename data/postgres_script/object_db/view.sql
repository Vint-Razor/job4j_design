
create table lang(
	id serial primary key,
	name varchar(20)
);

insert into lang (name) values ('Java'), ('C#'), ('Pyphon'), ('C'), ('C++');

create table level (
	id serial primary key,
	name varchar(7)
);

insert into level (name) values ('Junior'), ('Middle'), ('Senior');

create table resume (
	id serial primary key,
	name varchar(255),
	lang_id integer REFERENCES lang(id),
	level_id INTEGER REFERENCES level(id),
	salary INTEGER
);

insert into resume (name, lang_id, level_id, salary) values 
('Иванов С.Ю.', 1, 1, 100000), 
('Петров П.Ф.', 3, 2, 100000), 
('Сидоров Р.К.', 2, 3, 200000),
('Алексеев Р.Г.', 4, 1, 40000),
('Федоров Б.О.', 4, 2, 45000),
('Романов В.О.', 5, 1, 55000);


create view show_middle_programmers_less_than_50000
	AS select r.name AS имя,
	lng.name AS "язык программирования",
	lv.name AS уровень, 
	r.salary AS зарплата from resume AS r
	join lang AS lng ON r.lang_id = lng.id
	join "level" AS lv ON r.level_id = lv.id
	WHERE r.salary <= 50000 AND lv."name" = 'Middle';

SELECT * FROM show_middle_programmers_less_than_50000;