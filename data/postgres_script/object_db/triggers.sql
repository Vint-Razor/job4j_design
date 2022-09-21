
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--Триггер должен срабатывать после вставки данных, statement уровень
create or replace function tax()
	returns trigger as
$$
	BEGIN
		update products
		set price = price + price * 0.18
		where id = (select id from inserted);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
	after insert on products
	referencing new table as inserted
	for each statement
	execute procedure tax();
	
-- Триггер должен срабатывать до вставки данных, row уровень
create or replace function tax_row()
	returns trigger as
$$
	BEGIN
		new.price = new.price + new.price * 0.18;
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_row_trigger
	before insert
	on products
	for each row
	execute procedure tax_row();

create table history_of_price (
	id serial primary key,
	name varchar(50),
	price integer,
	date TIMESTAMP
);

--триггер на row уровне, при вставке продукта заносить в таблицу history_of_price.
create or replace function save()
	returns trigger as
$$
	BEGIN
		insert into history_of_price (name, price, date)
		values (new."name", new.price, current_date);
		return new;
	END;
$$
LANGUAGE 'plpgsql';	

create trigger save_trigger
	after insert
	on products
	for each row
	execute procedure save();	

insert into products (name, producer, count, price)
values ('prod_12', 'producer_12', 30, 100);	
insert into products (name, producer, count, price)
values ('prod_22', 'producer_22', 48, 100);

select p.name as наименование,
p.price as "цена после прихода",
h.price as "цена до прихода"
from products as p
join history_of_price as h on p."name" = h."name";
