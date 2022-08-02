insert into rules ("name") values ('create_accaunt');
insert into rules ("name") values ('create_order');
insert into rules ("name") values ('personal_accaunt');
insert into rules ("name") values ('viewing_product');

insert into roles ("name") values ('user');
insert into roles ("name") values ('admin');

insert into rules_role (rules_id, roles_id) values (1, 1);
insert into rules_role (rules_id, roles_id) values (1, 2);
insert into rules_role (rules_id, roles_id) values (2, 1);
insert into rules_role (rules_id, roles_id) values (2, 2);
insert into rules_role (rules_id, roles_id) values (2, 3);
insert into rules_role (rules_id, roles_id) values (2, 4);

insert into states ("name") values ('in_procesing');
insert into states ("name") values ('redy_to_ship');
insert into states ("name") values ('send');
insert into states ("name") values ('received');

insert into category ("name") values ('sport');
insert into category ("name") values ('electronics');
insert into category ("name") values ('children_prod');
insert into category ("name") values ('garden');