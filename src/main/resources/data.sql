INSERT INTO PRODUCT (id, name, current_price, description, rating, quantity, weight, created_on, updated_on)
VALUES
(1, 'Basketball', 15, 'Sports item', 0, 15, 500, now(), now());

INSERT INTO PRICE_HISTORY (id, price, product_id, created_on, updated_on)
VALUES
(1, 15, 1, now(), now());