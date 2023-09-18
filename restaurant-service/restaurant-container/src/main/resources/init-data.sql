INSERT INTO restaurant.restaurants(id, name, active)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb41', 'restaurant_1', TRUE);
INSERT INTO restaurant.restaurants(id, name, active)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb42', 'restaurant_2', FALSE);

INSERT INTO restaurant.products(id, name, price, available)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb43', 'product_1', 25.00, FALSE);
INSERT INTO restaurant.products(id, name, price, available)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb44', 'product_2', 50.00, TRUE);
INSERT INTO restaurant.products(id, name, price, available)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb45', 'product_3', 20.00, FALSE);
INSERT INTO restaurant.products(id, name, price, available)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb56', 'product_4', 40.00, TRUE);

INSERT INTO restaurant.restaurant_products(id, restaurant_id, product_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb57', 'd215b5f8-0249-4dc5-89a3-51fd148cfb45', 'd215b5f8-0249-4dc5-89a3-51fd148cfb47');
INSERT INTO restaurant.restaurant_products(id, restaurant_id, product_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb58', 'd215b5f8-0249-4dc5-89a3-51fd148cfb45', 'd215b5f8-0249-4dc5-89a3-51fd148cfb48');
INSERT INTO restaurant.restaurant_products(id, restaurant_id, product_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb59', 'd215b5f8-0249-4dc5-89a3-51fd148cfb46', 'd215b5f8-0249-4dc5-89a3-51fd148cfb49');
INSERT INTO restaurant.restaurant_products(id, restaurant_id, product_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb10', 'd215b5f8-0249-4dc5-89a3-51fd148cfb46', 'd215b5f8-0249-4dc5-89a3-51fd148cfb50');
