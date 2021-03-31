/*
  Study Room Booking Web Service - Data Structures
  2021-03-29
 */

create table rooms
(
    room_id int primary key,
    room_name varchar(50) not null,
    open_date date,
    close_date date
);

create table seats
(
    seat_id int primary key,
    room_id int not null,
    open_date date,
    close_date date,
    constraint fk_seats foreign key (room_id) references rooms(room_id)
);

create table users
(
    user_id serial primary key,
    create_date date not null,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    is_active boolean not null,
    entry_time timestamp default current_timestamp
);

create sequence accounts_sequence start 1000 increment 1;

create table enum_contact
(
    type_id int primary key,
    description varchar(20) not null
);

create table contacts
(
    contact_id serial primary key,
    user_id int not null,
    type_id int not null,
    start_date date,
    end_date date,
    entry_time timestamp default current_timestamp,
    constraint fk_users foreign key (user_id) references users(user_id),
    constraint fk_contact_type foreign key (type_id) references enum_contact(type_id)
);

create sequence contacts_sequence start 1000 increment 1;

create table booking
(
    booking_id serial primary key,
    user_id int not null,
    room_id int not null,
    seat_id int not null,
    start_time timestamp not null,
    end_time timestamp not null,
    entry_time timestamp default current_timestamp,
    status char(1),
    constraint fk_users foreign key (user_id) references users(user_id),
    constraint fk_rooms foreign key (room_id) references rooms(room_id),
    constraint fk_seats foreign key (seat_id) references seats(seat_id)
);

create index booking_idx_start_time on booking (start_time);
create index booking_idx_end_time on booking (end_time);

create table credential
(
    id serial primary key,
    user_id int not null,
    user_name varchar(20) not null,
    passwords varchar(100) not null,
    entry_time timestamp default current_timestamp,
    constraint fk_users foreign key (user_id) references users(user_id)
);