create table if not exists users (
    id serial primary key,
    author_first_name varchar (50),
    author_second_name varchar (50),
    login text,
    password text
);
