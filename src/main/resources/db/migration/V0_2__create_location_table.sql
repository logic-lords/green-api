create table location (
    created_datetime timestamp(6) without time zone,
    location_type varchar(255) check (location_type in ('CITY','OTHERS')),
    name varchar(255) not null,
    id varchar PRIMARY KEY DEFAULT uuid_generate_v4())