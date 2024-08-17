ALTER TABLE IF EXISTS "transport"
    ADD COLUMN fuel_type varchar check ( fuel_type in ('DIESEL','GASOLINE') );