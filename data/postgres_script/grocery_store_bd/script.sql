
CREATE TABLE type(
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE product(
	id serial PRIMARY KEY,
	name varchar(255),
	type_id int REFERENCES type(id),
	expired_date date,
	price float
);

INSERT INTO type (name) VALUES ('СЫР'), ('МОЛОКО'), ('МЯСО'), ('МОРОЖЕНОЕ');

INSERT INTO product (name, type_id, expired_date, price)
VALUES ('мороженое пломбир', 4, '20-08-2022', 50),
('мороженное мясо', 3, '4-08-2022', 350),
('сыр моцарелла', 1, '30-08-2022', 338),
('сыр плавленный', 1, '10-10-2023', 220),
('молоко 2.5%', 2, '1-08-2022', 60),
('молоко 3.5%', 2, '10-08-2022', 70),
('молоко топлённое 4%', 2, '10-08-2022', 50),
('мороженое эскимо', 4 , '12-12-2022', 80);

--Написать запрос получение всех продуктов с типом "СЫР"
SELECT p."name"
FROM product as p
JOIN "type" as t 
ON p.type_id = t.id AND t.name = 'СЫР';

--Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT p."name" 
FROM product as p
WHERE p."name" LIKE '%мороженое%';

--Написать запрос, который выводит все продукты, срок годности которых уже истек
SELECT p.name, p.expired_date
FROM product as p
WHERE p.expired_date < CURRENT_DATE;

--Написать запрос, который выводит самый дорогой продукт.
SELECT name, price
FROM product
ORDER by price DESC LIMIT 1;

--Написать запрос, который выводит для каждого типа
--количество продуктов к нему принадлежащих. В виде имя_типа, количество
SELECT t.name as имя_типа, count(*) as количество
FROM type	 as t
JOIN product as p ON p.type_id = t.id
GROUP by t.name;

--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p."name"
FROM product as p
JOIN "type" as t 
ON p.type_id = t.id AND (t."name" = 'СЫР' OR t."name" = 'МОЛОКО');

--Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name as имя_типа, count(*) as количество
FROM type	 as t
JOIN product as p ON p.type_id = t.id
GROUP by t.name
HAVING count(*) < 10;

--Вывести все продукты и их тип.
SELECT p.name as продукты, t.name тип
FROM product 	as p
JOIN "type" 	as t
ON p.type_id = t.id
ORDER by t.name;
