create table if not exists users (
    id serial primary key,
    first_name text not null,
    second_name text not null,
    login text unique,
    password text
);

create table if not exists marks (
    id serial primary key,
    name text unique
);

create table if not exists engines (
    id serial primary key,
    name text unique
);

create table if not exists bodyCars (
    id serial primary key,
    name text unique
);

create table if not exists advertisements (
    id serial primary key,
    header text not null,
    description text not null,
    isCell BOOLEAN default false,
    photo bytea,
    created timestamp default current_timestamp,
    user_id int not null references users(id),
    bodyCar_id int not null references bodyCars(id),
    engine_id int not null references engines(id),
    mark_id int not null references marks(id)
);

create table if not exists cars (
    id serial primary key,
    name text unique,
    mark_id int not null references marks(id),
    engine_id int not null references engines(id),
    bodyCar_id int not null references bodyCars(id)
);

create table if not exists advertisements_bodyCars
(
    advertisement_id int not null references advertisements(id),
    bodyCar_id int not null references bodyCars(id)
);

create table if not exists advertisements_engines
(
    advertisement_id int not null references advertisements(id),
    engine_id int not null references engines(id)
);

create table if not exists advertisements_marks
(
    advertisement_id int not null references advertisements(id),
    mark_id int not null references marks(id)
);