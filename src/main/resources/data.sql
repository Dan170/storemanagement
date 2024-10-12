INSERT INTO PRODUCT (name, current_price, description, rating, quantity, weight, archived, created_on, updated_on)
VALUES
('Basketball', 15, 'Sports item', 0, 15, 500, false, now(), now());

INSERT INTO PRICE_HISTORY (price, product_id, created_on, updated_on)
VALUES
(15, (SELECT id FROM PRODUCT WHERE name = 'Basketball'), now(), now());