ALTER TABLE examination
  ADD COLUMN user_id integer;
  
ALTER TABLE examination
  ADD CONSTRAINT fk_examination_user_id_users_id FOREIGN KEY (user_id) REFERENCES users (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
COMMENT ON COLUMN examination.user_id IS 'Идентификатор пользователя, который добавил запись';
