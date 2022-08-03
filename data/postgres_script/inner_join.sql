select u.name, c.class
from unit as u join unit_class as c on u.unit_class_id = c.id;

select u.name as Имя, c.class as Класс
from unit as u join unit_class as c on u.unit_class_id = c.id;

select u.name "Имя персонажа", c.class "Класс персонажа"
from unit u join unit_class c on u.unit_class_id = c.id;