INSERT INTO patient VALUES (1, 1, '2014-12-09', false, 'Уванов', 'Яндрей', 'Векторович', '2000-09-12', NULL, 1, 1, '+7 999 854 50 77', NULL, 'Кемеровская', NULL, 'Новокузнецк', NULL, 'Ермакова', NULL, '34', NULL, NULL, NULL, NULL, NULL, '8', 42, 10, 21, 212, NULL, NULL, NULL);
INSERT INTO patient VALUES (2, 1, '2014-12-09', true, 'Иванов', 'Андрей', 'Викторович', '2000-09-12', NULL, 1, 1, '+7 999 854 50 77', NULL, 'Кемеровская', NULL, 'Новокузнецк', NULL, 'Ермакова', NULL, '34', NULL, NULL, NULL, NULL, NULL, '8', 42, 10, 21, 212, NULL, NULL, NULL);
INSERT INTO patient VALUES (3, 2, '2014-12-09', true, 'Кузьмина', 'Елена', 'Викторовна', '1966-08-11', NULL, 2, 2, '+7 888 587 14 99', NULL, 'Кемеровская', NULL, 'Новокузнецк', NULL, 'Кирова', NULL, '48', NULL, NULL, NULL, NULL, NULL, '5', 42, 10, 20, 201, NULL, NULL, NULL);

SELECT pg_catalog.setval('patient_id_seq', 3, true);
