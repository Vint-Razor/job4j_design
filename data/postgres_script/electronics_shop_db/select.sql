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