
create table roles(
	id serial primary key,
	"name" varchar(255)
);
create table users(
	id serial primary key,
	"name" varchar(255),
	role_id int references roles
);
create table category(
	id serial primary key,
	"name" varchar(255)
);
create table states(
	id serial primary key,
	"name" varchar(255)
);
create table item(
	id serial primary key,
	category_id int references category,
	state_id int references states,
	user_id int references users
);
create table "comments"(
	id serial primary key,
	item_id int,
	"comment" text
);
create table attachs(
	id serial primary key,
	item_id int references item,
	"file" bytea
);
create table rules(
	id serial primary key,
	"name" varchar(255)
);
create table rules_role(
	id serial primary key,
	rules_id int references rules,
	roles_id int references roles
);