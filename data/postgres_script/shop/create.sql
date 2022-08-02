create table users(
	id serial primary key,
	"name" varchar(255),
	role_id int
);
create table item(
	id serial primary key,
	category_id int,
	state_id int,
	user_id int
);
create table category(
	id serial primary key,
	"name" varchar(255)
);
create table states(
	id serial primary key,
	"name" varchar(255)
);
create table roles(
	id serial primary key,
	"name" varchar(255)
);
create table "comments"(
	id serial primary key,
	item_id int,
	"comment" text
);
create table attachs(
	id serial primary key,
	item_id int,
	"file" bytea
);
create table rules(
	id serial primary key,
	"name" varchar(255)
);
create table rules_role(
	id serial primary key,
	rules_id int references rules(id),
	roles_id int references roles(id)
);