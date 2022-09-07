create table if not exists users (
    id serial primary key,
    author_first_name varchar (50) not null,
    author_second_name varchar (50) not null,
    login text UNIQUE,
    password text
);

create table if not exists marks (
    id serial primary key,
    mark_name varchar (50) UNIQUE
);

create table if not exists engines (
    id serial primary key,
    engine_name varchar (50) UNIQUE
);

create table if not exists bodyCars (
    id serial primary key,
    carcase varchar (50) UNIQUE
);

create table if not exists advertisements (
    id serial primary key,
    header varchar (60) not null,
    description_ad varchar (100) not null,
    isCell BOOLEAN default false,
    photo_car bytea,
    created timestamp default current_timestamp,
    users_id int not null references users(id),
    bodyCars_id int not null references bodyCars(id),
    engines_id int not null references engines(id)
);