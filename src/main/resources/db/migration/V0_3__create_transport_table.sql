create table if not exists transport (
    co2emission float(53),
    fuel_consumption_per_km float(53),
    onboard int,
    created_datetime timestamp(6) with time zone,
    id varchar(255) PRIMARY KEY     DEFAULT uuid_generate_v4(),
    places_number varchar(255),
    type varchar(255) check (type in ('PLANE','BUS','CAR', 'MOTO')),
    size varchar(255) check (size in ('SMALL','MID','LARGE')))