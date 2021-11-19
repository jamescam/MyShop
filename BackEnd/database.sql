--create new schema shopping_app

DROP TABLE IF EXISTS transactions cascade;
DROP TABLE IF EXISTS users cascade;
DROP TABLE IF EXISTS products cascade;

create table users (
	user_id serial primary key,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	pass_word varchar(64) not null,
	is_manager boolean default false,
	balance Numeric(12,2) not null default 0,
	email varchar(100) unique not null
);


create table products (
	product_id serial primary key,
	product_name varchar(30) not null unique,
	product_price Numeric(12,2) not null
);

create table transactions (
	user_id serial references users ON DELETE CASCADE,
	transaction_id serial primary key,
	transaction_date timestamp,
	product_id serial references products,
	is_paid boolean default false,
	quantity int not null
);



