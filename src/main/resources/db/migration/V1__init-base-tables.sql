create table users
(
    id       serial primary key,
    login    varchar(100) unique not null,
    password varchar(100) not null
);

create table locations
(
    id        serial primary key,
    name      varchar(100) not null,
    user_id   int          not null,
    latitude  numeric      not null,
    longitude numeric      not null,
    CONSTRAINT uq_user_location UNIQUE (user_id, latitude, longitude)
);

create table sessions
(
    id         UUID default random_uuid() primary key,
    user_id    int       not null,
    expires_at TIMESTAMP not null
);