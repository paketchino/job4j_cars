create table if not exists users (
    id serial primary key,
    author_first_name varchar (50) not null,
    author_second_name varchar (50) not null,
    login text,
    password text
);
ALTER TABLE users ADD CONSTRAINT login UNIQUE (login);

create table if not exists marks (
    id serial primary key,
    mark_name varchar (50)
);
ALTER TABLE marks ADD CONSTRAINT mark_name UNIQUE (mark_name);

create table if not exists engines (
    id serial primary key,
    engine_name varchar (50)
);
ALTER TABLE engines ADD CONSTRAINT engine_name UNIQUE (engine_name);

create table if not exists bodyCars (
    id serial primary key,
    carcase varchar (50)
);
ALTER TABLE bodyCars ADD CONSTRAINT carcase UNIQUE (carcase);

insert into users (id, author_first_name, author_second_name, login, password)
values (1, 'user first name', 'user second name', 'paketchino', 'password');

insert into marks (id, mark_name)
values (1, 'BMW X5');

insert into marks (id, mark_name)
values (2, 'BMW X3');

insert into engines (id, engine_name)
values (1, '2.5L');

insert into engines (id, engine_name)
values (2, '3.5L');

insert into bodyCars (id, carcase)
values (1, 'SEDAN');

insert into bodyCars (id, carcase)
values (2, 'HATCHBACK');

