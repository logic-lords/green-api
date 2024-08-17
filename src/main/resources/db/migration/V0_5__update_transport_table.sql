ALTER TABLE IF EXISTS "transport"
    ADD COLUMN fuel_type varchar check ( type in ('DIESEL','GASOLINE') );