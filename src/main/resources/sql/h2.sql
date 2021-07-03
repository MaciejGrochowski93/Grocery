INSERT INTO user (email, password, first_name, money, enabled, roles)
VALUES
('user', '$2y$09$49dK3na3iLNVc4WxTeR73.mz64354fgOJnxtBIP0TsB73q84QDXTa', 'user', 100, 1, 'ROLE_USER'),
('admin', '$2y$09$XNFc2W.deA4E5ZGCRAENHO19w975a11InffKrumSgQHFajt8VrB7e', 'admin', 100, 1, 'ROLE_ADMIN');


INSERT INTO products (name, brand, category, country_prod, date_expiration, amount, price)
VALUES
('Lion', 'Nestle', 'Candies', 'Poland', '2022-03-22', 1, 1.79),
('Mars', 'Nestle', 'Candies', 'Poland', '2022-02-11', 1, 2.19),
('Bounty', 'Nestle', 'Candies', 'Poland', '2022-05-17', 1, 2.69),
('Delice', 'Kinder', 'Candies', 'UK', '2022-03-06', 1, 2.49),
('Bueno', 'Kinder', 'Candies', 'UK', '2022-01-11', 1, 2.79),

('Super Color 5 kg', 'Persil', 'Chemicals', 'Germany', '2026-02-17', 1, 39.99),
('Super White 7 kg', 'Bonux', 'Chemicals', 'Germany', '2027-07-12', 1, 59.99),
('Super Black 10 kg', 'Vanish', 'Chemicals', 'France', '2025-05-10', 1, 69.99);


INSERT INTO products (name, category, country_prod, amount, price)
VALUES
('Wheat bread', 'Breadstuff', 'Poland', 1, 2.99),
('Rye bread', 'Breadstuff', 'Poland', 1, 3.79),
('Kaiser roll', 'Breadstuff', 'Poland', 1, 0.59),
('Bun', 'Breadstuff', 'Poland', 1, 0.49),

('Tomatoes 1 kg', 'Vegetables', 'Italy', 1, 5.99),
('Spinach 1 kg', 'Vegetables', 'Italy', 1, 4.49),
('Carrots 1 kg', 'Vegetables', 'UK', 1, 2.49),
('Potatoes 1 kg', 'Vegetables', 'France', 1, 1.99);


INSERT INTO products (name, brand, category, country_prod, amount, price)
VALUES
('Black Spiced', 'Kraken', 'Alcohol', 'Jamaica', 1, 99.99),
('Gingerbread', 'Captain Morgan', 'Alcohol', 'Jamaica', 1, 39.99),
('Double Aged Dark', 'Plantation', 'Alcohol', 'Jamaica', 1, 69.99),
('Pineapple', 'Plantation', 'Alcohol', 'Jamaica', 1, 149.99),
('Spiced Rum', 'Sailor Jerry', 'Alcohol', 'Jamaica', 1, 79.99);