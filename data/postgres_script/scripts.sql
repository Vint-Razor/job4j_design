
--1. Создать таблицы и заполнить их начальными данными
CREATE TABLE departments(
    id serial PRIMARY KEY,
    name varchar(255)
);
CREATE TABLE employeers(
    id serial PRIMARY KEY,
    name varchar(255),
    department_id int REFERENCES departments(id)
);

INSERT INTO departments(name)
VALUES ('IT отдел'), ('Юридический отдел'), ('Отдел продаж'),
    ('Программисты'), ('Отдел клининга');

--2. Выполнить запросы с left, rigth, full, cross соединениями
INSERT INTO employeers (name, department_id)
VALUES ('Иванов', 1), ('Петров', 1), ('Сидоров', 2),
('Пупкин', 3), ('Бклоногов', 4), ('Тупилов', 4), ('Садыков', 4);

SELECT em."name" as имя, dep.name as отдел
FROM employeers AS em
LEFT join departments AS dep 
ON em.department_id = dep.id;

SELECT em."name" as имя, dep.name as отдел
FROM employeers as em
RIGHT join departments as dep
ON em.department_id = dep.id;

SELECT * FROM employeers as em
FULL join departments as dep
ON em.department_id = dep.id;

SELECT * FROM employeers as em
CROSS join departments as dep

--3. Используя left join найти департаменты, у которых нет работников
SELECT * FROM departments as dep
LEFT join employeers as em
ON em.department_id = dep.id
WHERE em.department_id is null;


--4. Используя left и right join написать запросы, которые давали бы одинаковый
--результат (порядок вывода колонок в эти запросах также должен быть идентичный). 
SELECT * FROM employeers as em 
LEFT join departments as dep
ON em.department_id = dep.id;

SELECT * FROM employeers as em 
RIGHT join departments as dep
ON em.department_id = dep.id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
--Используя cross join составить все возможные разнополые пары
CREATE TABLE teens(
    id serial PRIMARY KEY,
    name varchar(255),
    gender varchar(10)
);

INSERT INTO teens (name, gender)
VALUES ('Егор', 'муж'), ('Семен', 'муж'),
('Алена', 'жен'), ('Мария', 'жен');

SELECT * FROM teens as t
CROSS join teens
WHERE t.gender != teens.gender;
