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
    car_name varchar (50) not null
);


create table if not exists history_owner (
    id serial primary key,
    driver_id int not null references drivers(id),
    car_id int not null references cars(id)
);

create table if not exists marks (
    id serial primary key,
    marks varchar (50) not null
);

create table if not exists carcase_cars (
    id serial primary key,
    carcase varchar (50) not null
);


create table if not exists advertisements (
    id serial primary key,
    desciption_ad varchar (240) not null,
    marks_id int not null references marks(id),
    carcase_cars_id int not null references carcase_cars(id),
    is_cell boolean default false,
    photo_car byte not null
);

create table if not exists author (
    id serial primary key,
    author_first_name varchar (50) not null,
    author_second_name varchar (50) not null,
    advertisement_id int not null references advertisements(id)
);
