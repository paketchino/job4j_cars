drop table if exists users cascade;

drop table if exists bodycars cascade;
drop table if exists engines cascade;
drop table if exists cars cascade;
drop table if exists marks cascade;
drop table if exists advertisement cascade;

create table if not exists users (
                                     id serial primary key,
                                     author_first_name varchar (50) not null,
                                     author_second_name varchar (50) not null,
                                     login text,
                                     password text
);

ALTER TABLE users ADD CONSTRAINT login UNIQUE (login);

insert into users (id, author_first_name, author_second_name, login, password)
values (1, 'user first name', 'user second name', 'paketchino', 'password');

drop table if exists users cascade;