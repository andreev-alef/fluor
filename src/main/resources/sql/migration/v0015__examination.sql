CREATE TABLE examination
(
  id serial NOT NULL,
  dat date NOT NULL,
  med_reg_id integer NOT NULL,
  med_city_id integer NOT NULL,
  med_lpu_id integer NOT NULL,
  med_pol_id integer NOT NULL,
  method_id integer NOT NULL,
  result_id integer,
  follow_up boolean NOT NULL DEFAULT false,
  CONSTRAINT pk PRIMARY KEY (id),
  CONSTRAINT fk_r_exam_methods FOREIGN KEY (method_id)
      REFERENCES r_exam_methods (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_r_result_type FOREIGN KEY (result_id)
      REFERENCES r_result_type (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT r_medical_org_policlinic FOREIGN KEY (med_reg_id, med_city_id, med_lpu_id, med_pol_id)
      REFERENCES r_medical_org_policlinic (reg_id, ter_id, lpu_id, pol_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE examination
  IS 'Обследование';
COMMENT ON COLUMN examination.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN examination.dat IS 'Дата';
COMMENT ON COLUMN examination.med_reg_id IS 'Ид. региона';
COMMENT ON COLUMN examination.med_city_id IS 'Ид. города';
COMMENT ON COLUMN examination.med_lpu_id IS 'Ид. ЛПУ';
COMMENT ON COLUMN examination.med_pol_id IS 'Ид. поликлиники';
COMMENT ON COLUMN examination.method_id IS 'Ид. метода';
COMMENT ON COLUMN examination.result_id IS 'Ид. результата';
COMMENT ON COLUMN examination.follow_up IS 'Дообследование';
