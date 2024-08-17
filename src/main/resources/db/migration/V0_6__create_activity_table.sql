create table if not exists activity (
                                        price float(53),
                                        description varchar,
                                        image_url varchar,
                                        name varchar not null,
                                        id varchar(255) PRIMARY KEY     DEFAULT uuid_generate_v4())