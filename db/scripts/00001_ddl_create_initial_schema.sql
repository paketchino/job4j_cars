create table if not exists users (
    id serial primary key,
    first_name text not null,
    second_name text not null,
    login text unique,
    password text not null
);

create table if not exists marks (
    id serial primary key,
    name text unique not null
);

create table if not exists engines (
    id serial primary key,
    name text unique not null
);

create table if not exists car_bodies (
    id serial primary key,
    name text unique not null
);

create table if not exists advertisements (
    id serial primary key,
    header text not null,
    description text not null,
    isCell BOOLEAN default false,
    photo bytea not null,
    created timestamp default current_timestamp
);

create table if not exists cars (
    id serial primary key,
    name text unique not null,
    mark_id int not null references marks(id),
    engine_id int not null references engines(id),
    bodyCar_id int not null references bodyCars(id)
);
