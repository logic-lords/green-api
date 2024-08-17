create table if not exists "trip" (
    distance float(53),
    created_datetime timestamp(6) with time zone,
    end_location_id varchar(255) references location(id),
    id varchar(255) PRIMARY KEY     DEFAULT uuid_generate_v4(),
    start_location_id varchar(255) references location(id),
    transport_id varchar(255) references transport(id),
    onboard int,
    user_id varchar(255) references "user"(id))