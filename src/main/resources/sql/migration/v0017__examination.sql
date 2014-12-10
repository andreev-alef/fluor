ALTER TABLE examination
  ADD COLUMN patient_id integer NOT NULL;
ALTER TABLE examination
  ADD CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patient (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
COMMENT ON COLUMN examination.patient_id IS 'Ид пациента';
