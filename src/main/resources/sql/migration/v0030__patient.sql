ALTER TABLE patient
  ADD COLUMN user_id integer;
COMMENT ON COLUMN patient.user_id IS 'идентификатор пользователя, который внес запись';

ALTER TABLE patient
  ADD CONSTRAINT fk_patient_user_id_users_id FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
      