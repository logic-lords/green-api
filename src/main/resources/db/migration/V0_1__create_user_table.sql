create extension if not exists "uuid-ossp";

CREATE TABLE IF NOT EXISTS "user"(
    id  VARCHAR     CONSTRAINT user_pk  PRIMARY KEY     DEFAULT uuid_generate_v4(),
    username    VARCHAR     NOT NULL,
    first_name    VARCHAR    ,
    last_name    VARCHAR   ,
    email    VARCHAR     NOT NULL    CONSTRAINT user_email_unique   UNIQUE,
    phone_number    VARCHAR   CONSTRAINT user_phone_number_unique   UNIQUE,
    role varchar check (role in ('ADMIN','USER')),
    password    VARCHAR     NOT NULL,
    created_datetime timestamp without time zone
)