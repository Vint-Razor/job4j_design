
CREATE TABLE company
(
	id integer NOT NULL,
	"name" CHARACTER VARYING,
	CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
	id integer NOT NULL,
	"name" CHARACTER VARYING,
	company_id integer REFERENCES company(id),
	CONSTRAINT person_pkey PRIMARY KEY (id)
);


insert into company (id, name) values (1, 'SONY');
insert into company (id, name) values (2, 'Apple');
insert into company (id, name) values (3, 'Google');
insert into company (id, name) values (4, 'Samsung');
insert into company (id, name) values (5, 'Хiaomi');

SELECT * FROM company;

insert into person (id, name, company_id) values (1, 'Валеев Вадим', 2);
insert into person (id, name, company_id) values (2, 'Жукова Анна', 2);
insert into person (id, name, company_id) values (3, 'Ибатулин Марат', 1);
insert into person (id, name, company_id) values (4, 'Каримов Владимир', 3);
insert into person (id, name, company_id) values (5, 'Леонов Владимир', 4);
insert into person (id, name, company_id) values (6, 'Любимов Александр', 5);
insert into person (id, name, company_id) values (7, 'Пашинин Александр', 2);
insert into person (id, name, company_id) values (8, 'Пустовой Павел', 3);
insert into person (id, name, company_id) values (9, 'Сорокин Григорий', 5);
insert into person (id, name, company_id) values (10, 'Тюремских Семен', 5);

/* 1. В одном запросе получить
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.
*/
SELECT p.name, c.name AS company
FROM person AS p
JOIN company AS c
ON p.company_id = c.id
WHERE c.id != 5;

/*
2. Необходимо выбрать название компании с максимальным
количеством человек + количество человек в этой компании.
Нужно учесть, что таких компаний может быть несколько.
*/
CREATE VIEW person_counter AS
	SELECT company_id, count(company_id) AS mycount
	FROM person
	GROUP by company_id;

SELECT c.name, count(company_id)
FROM person AS p
JOIN company AS c
ON c.id = p.company_id
GROUP by c.name
HAVING count(p.company_id) = (SELECT max(mycount) FROM person_counter) 
	