INSERT INTO examination (id, dat, med_reg_id, med_city_id, med_lpu_id, med_pol_id, method_id, result_id, follow_up, patient_id) VALUES (1, '2014-12-10', 42, 10, 21, 212, 1, 1, false, 1);
INSERT INTO examination (id, dat, med_reg_id, med_city_id, med_lpu_id, med_pol_id, method_id, result_id, follow_up, patient_id) VALUES (2, '2012-06-05', 42, 10, 24, 241, 2, 3, false, 1);
INSERT INTO examination (id, dat, med_reg_id, med_city_id, med_lpu_id, med_pol_id, method_id, result_id, follow_up, patient_id) VALUES (3, '2011-03-16', 42, 10, 22, 222, 2, 1, false, 2);

SELECT pg_catalog.setval('examination_id_seq', 3, true);
