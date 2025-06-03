-- Insert a sample cart
INSERT INTO carts (id, user_id) VALUES (1, 'user123');

-- Insert items in that cart
INSERT INTO cart_items (id, product_id, product_name, quantity, price, cart_id) VALUES
(1, 'P001', 'Product A', 2, 499.99, 1),
(2, 'P002', 'Product B', 1, 249.50, 1);
