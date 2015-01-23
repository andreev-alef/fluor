ALTER TABLE examination DROP COLUMN follow_up;
ALTER TABLE examination
  ADD COLUMN verification_id integer;
COMMENT ON COLUMN examination.verification_id IS 'верификация диагноза';
ALTER TABLE examination
  ADD CONSTRAINT fk_verification_id FOREIGN KEY (verification_id) REFERENCES r_verification (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
