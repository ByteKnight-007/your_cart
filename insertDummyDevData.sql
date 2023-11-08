-- Script to insert dummy dev data into the database.
-- You first need to register two users into the system before running this script.

-- Replace the id here with the first user id you want to have ownership of the orders.
DO $$
DECLARE
    userId1 INT;
    userId2 INT;
    product1 INT;
    product2 INT;
    product3 INT;
    product4 INT;
    product5 INT;
    address1 INT;
    address2 INT;
    order1 INT;
    order2 INT;
    order3 INT;
    order4 INT;
    order5 INT;
BEGIN
    --put the user_id you have
    userId1 := 6;
    userId2 := 7;

    DELETE FROM product_order_quantity;
    DELETE FROM product_order;
    DELETE FROM inventory;
    DELETE FROM product;
    DELETE FROM address;

    INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #1', 'Product one short description.', 'This is a very long description of product #1.', 5.50) RETURNING id INTO product1;
    INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #2', 'Product two short description.', 'This is a very long description of product #2.', 10.56) RETURNING id INTO product2;
    INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #3', 'Product three short description.', 'This is a very long description of product #3.', 2.74) RETURNING id INTO product3;
    INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #4', 'Product four short description.', 'This is a very long description of product #4.', 15.69) RETURNING id INTO product4;
    INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #5', 'Product five short description.', 'This is a very long description of product #5.', 42.59) RETURNING id INTO product5;

    INSERT INTO inventory (product_id, quantity) VALUES (product1, 5);
    INSERT INTO inventory (product_id, quantity) VALUES (product2, 8);
    INSERT INTO inventory (product_id, quantity) VALUES (product3, 12);
    INSERT INTO inventory (product_id, quantity) VALUES (product4, 73);
    INSERT INTO inventory (product_id, quantity) VALUES (product5, 2);

    INSERT INTO address (address_line_1, address_line_2, city, country, user_id) VALUES ('123 Tester Hill', 'Honolulu', 'Testerton', 'England', userId1) RETURNING id INTO address1;
    INSERT INTO address (address_line_1, address_line_2, city, country, user_id) VALUES ('312 Spring Boot', 'Canyon', 'Hibernate', 'England', userId2) RETURNING id INTO address2;

    INSERT INTO product_order (address_id, user_id) VALUES (address1, userId1) RETURNING id INTO order1;
    INSERT INTO product_order (address_id, user_id) VALUES (address1, userId1) RETURNING id INTO order2;
    INSERT INTO product_order (address_id, user_id) VALUES (address1, userId1) RETURNING id INTO order3;
    INSERT INTO product_order (address_id, user_id) VALUES (address2, userId2) RETURNING id INTO order4;
    INSERT INTO product_order (address_id, user_id) VALUES (address2, userId2) RETURNING id INTO order5;

    -- Insert data into web_order_quantities
    INSERT INTO product_order_quantity (order_id, product_id, quantity) VALUES (order1, product1, 5);
    INSERT INTO product_order_quantity (order_id, product_id, quantity) VALUES (order1, product2, 5);
    INSERT INTO product_order_quantity (order_id, product_id, quantity) VALUES (order2, product3, 5);
    INSERT INTO product_order_quantity (order_id, product_id, quantity) VALUES (order2, product2, 5);
    INSERT INTO product_order_quantity (order_id, product_id, quantity) VALUES (order2, product5, 5);
    INSERT INTO product_order_quantity (order_id, product_id, quantity) VALUES (order3, product3, 5);
    INSERT INTO product_order_quantity (order_id, product_id, quantity) VALUES (order4, product4, 5);
    INSERT INTO product_order_quantity (order_id, product_id, quantity) VALUES (order4, product2, 5);
    INSERT INTO product_order_quantity (order_id, product_id, quantity) VALUES (order5, product3, 5);
    INSERT INTO product_order_quantity (order_id, product_id, quantity) VALUES (order5, product1, 5);
END $$;
