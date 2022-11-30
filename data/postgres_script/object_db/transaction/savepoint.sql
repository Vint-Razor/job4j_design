
DELETE FROM products;
DELETE FROM history_of_price;
ALTER SEQUENCE products_id_seq RESTART with 1;
ALTER SEQUENCE history_of_price_id_seq RESTART with 1;

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

begin;

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);

commit;

SELECT * FROM products;

begin;

delete from products;

drop table products;

rollback;

SELECT * FROM products;

begin;

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 17, 45);

savepoint first_savepoint;

delete from products where price = 115;
update products set price = 75 where name = 'product_1';

select * from products;

rollback to first_savepoint;

select * from products;

commit;




