CREATE TABLE public.patient_id
(
   id serial NOT NULL, 
   CONSTRAINT pk_patient_id_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE public.patient_id
  IS 'Идентификатор пациента';

INSERT INTO patient_id VALUES (1);
INSERT INTO patient_id VALUES (3);

SELECT setval('public.patient_id_id_seq', 3, true);

UPDATE examination SET patient_id = 1 WHERE id = 1;
UPDATE examination SET patient_id = 1 WHERE id = 2;
UPDATE examination SET patient_id = 3 WHERE id = 3;

ALTER TABLE patient
  ADD CONSTRAINT fk_patient_id_patient_id_id FOREIGN KEY (_ver_parent_id) REFERENCES patient_id (id)
   ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE INDEX fki_patient_id_patient_id_id
  ON patient(_ver_parent_id);

ALTER TABLE examination DROP CONSTRAINT fk_patient_id;

ALTER TABLE examination
  ADD CONSTRAINT fk_examination_patient_id_patient_id_id FOREIGN KEY (patient_id) REFERENCES patient_id (id)
   ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE INDEX fki_examination_patient_id_patient_id_id
  ON examination(patient_id);
