create table products
(
    id    integer,
    name  varchar(255) not null,
    price numeric      not null,
    count integer      not null,
    constraint products_id_pk primary key (id)
);

create table carts
(
    id        integer,
    promocode varchar(255),
    constraint carts_id_pk primary key (id)
);

create table clients
(
    id       integer,
    name     varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255),
    cart_id  integer,
    constraint clients_id_pk primary key (id),
    constraint client_cart_id_fk foreign key (cart_id) references carts (id)
);

create table products_carts
(
    id         integer,
    id_product integer not null,
    count      integer not null,
    id_cart    integer not null,
    constraint products_carts_id_pk primary key (id),
    constraint product_client_products_id_fk foreign key (id_product) references products (id),
    constraint product_client_cart_id_fk foreign key (id_cart) references carts (id),
);
