ALTER TABLE users
  ADD COLUMN last_name character varying(64);
ALTER TABLE users
  ADD COLUMN first_name character varying(64);
ALTER TABLE users
  ADD COLUMN father_name character varying(64);
ALTER TABLE users
  ADD COLUMN med_reg_id integer;
ALTER TABLE users
  ADD COLUMN med_city_id integer;
ALTER TABLE users
  ADD COLUMN med_lpu_id integer;
ALTER TABLE users
  ADD COLUMN med_pol_id integer;
ALTER TABLE users
  ADD CONSTRAINT med_reg_id_med_reg_city_id_med_reg_lpu_id_med_pol_id_r_medical_org_policlinic FOREIGN KEY (med_reg_id, med_city_id, med_lpu_id, med_pol_id) REFERENCES r_medical_org_policlinic (reg_id, ter_id, lpu_id, pol_id) ON UPDATE NO ACTION ON DELETE NO ACTION;
COMMENT ON COLUMN users.last_name IS 'Фамилия';
COMMENT ON COLUMN users.first_name IS 'Имя';
COMMENT ON COLUMN users.med_reg_id IS 'Регион прикрепления сотрудника';
COMMENT ON COLUMN users.med_city_id IS 'Город прикрепления сотрудника';
COMMENT ON COLUMN users.med_lpu_id IS 'ЛПУ прикрепления сотрудника';
COMMENT ON COLUMN users.med_pol_id IS 'Поликлиника прикрепления сотрудника';

UPDATE users SET last_name ='Иванов', first_name='Иван', father_name='Иванович', 
	med_reg_id='42', med_city_id='10', med_lpu_id='20', med_pol_id='20' where id=1;
UPDATE users SET last_name ='Петров', first_name='Василий', father_name='Николаевич',
	med_reg_id='42', med_city_id='10', med_lpu_id='20', med_pol_id='601' where id=2;

ALTER TABLE users
   ALTER COLUMN last_name SET NOT NULL;
ALTER TABLE users
   ALTER COLUMN first_name SET NOT NULL;