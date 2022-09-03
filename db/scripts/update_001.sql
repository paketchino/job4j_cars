create table if not exists users (
    id serial primary key,
    author_first_name varchar (50) not null,
    author_second_name varchar (50) not null,
    login text,
    password text
);
