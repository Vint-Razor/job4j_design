CREATE TABLE devices(
	id serial PRIMARY KEY,
	name varchar(255),
	price float
);

CREATE TABLE people(
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE devaices_people(
	id serial PRIMARY KEY,
	device_id int REFERENCES devices(id),
	people_id int REFERENCES people(id)
);

INSERT INTO people(name) VALUES ('Иван'),('Марья'),('Агофон');

INSERT INTO devices (name, price) 
VALUES ('ноутбук', 50000),
('смартфон', 11000),
('смарт часы', 3000),
('квадрокоптер', 32000),
('робот пылесос', 30000);

INSERT INTO devaices_people(people_id, device_id)
VALUES (1, 1), (1, 2),
(2, 2), (2, 4), (2, 5),
(3, 3);

SELECT avg(price) as "средняя цена" FROM devices;

SELECT p.name as "имя пользователя", avg(d.price) as "средняя цена устройств"
FROM devaices_people as dp
JOIN people 	as p 	ON dp.people_id = p.id
JOIN devices 	as d 	ON dp.device_id = d.id
GROUP by p.name;

SELECT p.name as "имя пользователя", avg(d.price) as "средняя цена устройств"
FROM devaices_people as dp
JOIN people 	as p 	ON dp.people_id = p.id
JOIN devices 	as d 	ON dp.device_id = d.id
GROUP by p.name
HAVING avg(d.price) > 5000;