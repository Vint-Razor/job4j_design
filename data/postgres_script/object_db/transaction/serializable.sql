--Сериализация (serializable)
delete from products;
alter SEQUENCE products_id_seq restart with 1;

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

--Первая транзакция:
BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;

--Вторая транзакция:
BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;

--Первая транзакция:
select sum(count) from products;
update products set count = 26 where name = 'product_1';

--Вторая транзакция:
select sum(count) from products;
update products set count = 26 where name = 'product_2';
commit;

--Первая транзакция:
COMMIT;
