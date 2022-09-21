
DELETE FROM products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

create or replace procedure delete_zero_row()
language 'plpgsql'
as
$$
	BEGIN
		delete from products 
		where count <= 0;
	END;
$$;

create or replace function f_delete_row(u_id integer)
returns bool
language 'plpgsql'
as 
$$
	DECLARE
		result bool;
	BEGIN
		select into result exists(
			select 1 from products where id = u_id
		);
		if result then
			delete from products where id = u_id;
		end if;
		return result;
	END;
$$;

select f_insert_data('prod_12', 'producer_12', 23, 100);
select f_insert_data('prod_13', 'producer_13', 33, 40);
select f_insert_data('prod_14', 'producer_14', 53, 130);

select f_update_data(23, 0, 1);

call delete_zero_row();

select f_delete_row(2);
