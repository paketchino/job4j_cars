create table if not exists marks (
                                     id serial primary key,
                                     engine_name varchar (50)
);

insert into marks (id, engine_name)
values (1, 'BMW X5');

insert into marks (id, engine_name)
values (2, 'BMW X3');