INSERT INTO carts (id, promocode) VALUES (1, 'PROMO1');
INSERT INTO clients (id, name, username, password, email, cart_id) VALUES (1, 'Oleg', 'oleg', '123', 'mail', 1);
INSERT INTO products (id, name, price, count) VALUES (1, 'Product1', 100.00, 10);
INSERT INTO products (id, name, price, count) VALUES (2, 'Product2', 100.00, 10);
INSERT INTO products (id, name, price, count) VALUES (3, 'Product3', 100.00, 10);
INSERT INTO products_carts (id, id_product, id_cart, count) VALUES (1, 1, 1, 10);
INSERT INTO products_carts (id, id_product, id_cart, count) VALUES (2, 2, 1, 10);
INSERT INTO products_carts (id, id_product, id_cart, count) VALUES (3, 3, 1, 10);