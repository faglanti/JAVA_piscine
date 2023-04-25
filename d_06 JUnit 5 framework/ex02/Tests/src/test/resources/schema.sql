CREATE TABLE if not exists product (
    id INTEGER primary key IDENTITY,
    name VARCHAR(50) not null,
    price INTEGER not null
);