--Чтение подтвержденных данных (read committed)
delete from products;
alter SEQUENCE products_id_seq restart with 1;

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

--Первая транзакция:
BEGIN TRANSACTION;
SELECT * FROM products;

--Вторая транзакция:
BEGIN TRANSACTION;
SELECT * FROM products;

--Первая транзакция:
insert into products (name, count, price) VALUES ('product_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';
select * from products;

--Вторая транзакция:
select * from products;

--Первая транзакция:
commit;

--Вторая транзакция:
select * from products;
commit;

