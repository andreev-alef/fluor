UPDATE r_user_role SET name = 'Базовый' WHERE id = 1;
UPDATE r_user_role SET name = 'Первичный' WHERE id = 2;

INSERT INTO r_user_role(id, name) VALUES(3, 'Расширенный');
INSERT INTO r_user_role(id, name) VALUES(4, 'Экспертный');
INSERT INTO r_user_role(id, name) VALUES(5, 'Полный');
INSERT INTO r_user_role(id, name) VALUES(6, 'Технический');

UPDATE users SET role_id = 6 WHERE login = 'Manager';

INSERT INTO users(login, password, role_id, last_name, first_name, 
			father_name, med_reg_id, med_city_id, med_lpu_id, med_pol_id) 
			VALUES('Базовый', 'c4ca4238a0b923820dcc509a6f75849b', 1, 
			'Базовый', 'Иван', 'Иванович', 42, 10, 20, 20);
INSERT INTO users(login, password, role_id, last_name, first_name, 
			father_name, med_reg_id, med_city_id, med_lpu_id, med_pol_id) 
			VALUES('Первичный', 'c4ca4238a0b923820dcc509a6f75849b', 2, 
			'Первичный', 'Василий', 'Николаевич', 42, 10, 20, 601);
INSERT INTO users(login, password, role_id, last_name, first_name, 
			father_name, med_reg_id, med_city_id, med_lpu_id, med_pol_id) 
			VALUES('Расширенный', 'c4ca4238a0b923820dcc509a6f75849b', 3, 
			'Расширенный', 'Петр', 'Алексеевич', 42, 10, 20, null);
INSERT INTO users(login, password, role_id, last_name, first_name, 
			father_name, med_reg_id, med_city_id, med_lpu_id, med_pol_id) 
			VALUES('Экспертный', 'c4ca4238a0b923820dcc509a6f75849b', 4, 
			'Экспертный', 'Николай', 'Иванович', 42, 10, 20, null);
INSERT INTO users(login, password, role_id, last_name, first_name, 
			father_name, med_reg_id, med_city_id, med_lpu_id, med_pol_id) 
			VALUES('Полный', 'c4ca4238a0b923820dcc509a6f75849b', 5, 
			'Полный', 'Александр', 'Васильевич', 42, 10, null, null);
INSERT INTO users(login, password, role_id, last_name, first_name, 
			father_name, med_reg_id, med_city_id, med_lpu_id, med_pol_id) 
			VALUES('Технический', 'c4ca4238a0b923820dcc509a6f75849b', 6, 
			'Технический', 'Андрей', 'Иванович', 42, 10, null, null);
			