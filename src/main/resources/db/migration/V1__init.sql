-- DROP SEQUENCE hibernate_sequence;
-- DROP TABLE  customers, location, order_detail, order_detail_products, orders, product, product_category;
-- DROP SCHEMA TESTSHOP;
--
-- CREATE SCHEMA IF NOT EXISTS TESTSHOP;

/*CREATE SCHEMA IF NOT EXISTS TESTSHOP;
SET SCHEMA TESTSHOP;
*/
create sequence hibernate_sequence start with 1 increment by 1;
create table customers (customer_id integer generated by default as identity, first_name varchar(255), last_name varchar(255), user_name varchar(255) not null, primary key (customer_id));
create table location (location_id integer generated by default as identity, city varchar(255), country varchar(255), county varchar(255), street_address varchar(255), name varchar(255), primary key (location_id));
create table order_detail (quantity integer not null, order_id integer not null, product_id integer not null, primary key (order_id, product_id));
create table orders (order_id integer generated by default as identity, city varchar(255), country varchar(255), county varchar(255), street_address varchar(255), date timestamp, customer_id integer not null, primary key (order_id));
create table product (product_id integer generated by default as identity, description varchar(255), name varchar(255), price decimal(19,2), weight decimal(19,2), category_id integer, supplier_id integer, primary key (product_id));
create table product_category (category_id integer generated by default as identity, description varchar(255), name varchar(255), primary key (category_id));
create table shipping_info (id integer generated by default as identity, quantity integer not null, location_id integer, order_id integer, product_id integer, primary key (id));
create table stock (quantity integer not null, location_id integer not null, product_id integer not null, primary key (location_id, product_id));
create table supplier (supplier_id integer not null, name varchar(255), primary key (supplier_id));
alter table customers add constraint UK_2nn30ukoh3qlhdyigtupb6ivb unique (user_name);
alter table order_detail add constraint FKrws2q0si6oyd6il8gqe2aennc foreign key (order_id) references orders;
alter table order_detail add constraint FKb8bg2bkty0oksa3wiq5mp5qnc foreign key (product_id) references product;
alter table orders add constraint FKpxtb8awmi0dk6smoh2vp1litg foreign key (customer_id) references customers;
alter table product add constraint FK5cypb0k23bovo3rn1a5jqs6j4 foreign key (category_id) references product_category;
alter table product add constraint FK2kxvbr72tmtscjvyp9yqb12by foreign key (supplier_id) references supplier;
alter table shipping_info add constraint FKa9y078xf8pipd874t8dftsrry foreign key (location_id) references location;
alter table shipping_info add constraint FK45idk6acnnfditxgyyk613m6r foreign key (order_id) references orders;
alter table shipping_info add constraint FKbq6dqx4gq3t6tty5y1a6pnalv foreign key (product_id) references product;
alter table stock add constraint FK6t3m0kaf6fubuus331gf7xmn8 foreign key (location_id) references location;
alter table stock add constraint FKjghkvw2snnsr5gpct0of7xfcf foreign key (product_id) references product;
