
CREATE TABLE car_bodies(
	id serial PRIMARY KEY,
	name varchar(100)
);
CREATE TABLE car_engines(
	id serial PRIMARY KEY,
	name VARCHAR(100)
);
CREATE TABLE car_transmissions(
	id serial PRIMARY KEY,
	name VARCHAR(100)
);
CREATE TABLE car(
	id serial PRIMARY KEY,
	name VARCHAR(100),
	body_id int REFERENCES car_bodies(id),
	engine_id int REFERENCES car_engines(id),
	transmission_id int REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies (name) VALUES ('седан');
INSERT INTO car_bodies (name) VALUES ('хечбек');
INSERT INTO car_bodies (name) VALUES ('пикап');

INSERT INTO car_engines (name) VALUES ('80');
INSERT INTO car_engines (name) VALUES ('98');
INSERT INTO car_engines (name) VALUES ('106');
INSERT INTO car_engines (name) VALUES ('200');

INSERT INTO car_transmissions (name) VALUES ('4 механика');
INSERT INTO car_transmissions (name) VALUES ('5 механика');
INSERT INTO car_transmissions (name) VALUES ('6 механика');
INSERT INTO car_transmissions (name) VALUES ('5 автомат');

INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('Vests', 1, 3, 4);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('Granta', 1, 1, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('Priora', 1, 2, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('Kalina', 2, 1, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('Priora', 2, 2, 2);
INSERT INTO car (name, body_id, transmission_id) VALUES ('Desyatka', 1, 2);

--Вывести список всех машин и все привязанные к ним детали.
SELECT	car.name as name,
		bod.name as body_name,
		eng.name as engine_name,
		trs.name as transmission_name
FROM car 
LEFT JOIN car_bodies 		as bod 	ON car.body_id = bod.id
LEFT JOIN car_engines 		as eng	ON car.engine_id = eng.id
LEFT JOIN car_transmissions	as trs	ON car.transmission_id = trs.id

--Вывести кузовы, которые не используются НИ в одной машине.
SELECT bod."name" AS "кузов отсутствует"
FROM car_bodies AS bod
LEFT join car ON car.body_id = bod.id
WHERE car.body_id is null;

--Вывести двигатели, которые не используются НИ в одной машине
SELECT eng.name AS "двигатель недоступен" 
FROM car_engines AS eng
LEFT join car ON car.engine_id = eng.id;
WHERE car.engine_id is null;

--Вывести коробки передач, которые не используются НИ в одной машине
SELECT trs."name" AS "кробка недоступна" 
FROM car_transmissions AS trs
LEFT join car ON car.transmission_id = trs.id
WHERE car.transmission_id is null;
