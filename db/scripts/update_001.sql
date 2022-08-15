create table if not exists engine (
    id serial primary key,
    engine_name varchar(50) not null
);


create table if not exists drivers (
    id serial primary key,
    first_name varchar(40) not null,
    second_name varchar (40) not null
);


create table if not exists cars (
    id serial primary key,
    car_name varchar (50) not null,
    driver_id int not null references drivers(id)
);


create table if not exists history_owner (
    id serial primary key,
    driver_id int not null references drivers(id),
    car_id int not null references cars(id)
);