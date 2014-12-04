CREATE TABLE patient
(
  id serial NOT NULL,
  _ver_parent_id integer NOT NULL,
  _ver_creation_date date NOT NULL DEFAULT ('now'::text)::date,
  _ver_active boolean NOT NULL DEFAULT true,
  last_name character varying(64) NOT NULL,
  first_name character varying(64) NOT NULL,
  father_name character varying(64),
  dat_birth date NOT NULL,
  dat_death date,
  sex_id integer NOT NULL,
  citizen_id integer NOT NULL,
  tel character varying(16),
  liv_reg_id uuid,
  liv_reg character varying(32),
  liv_city_id uuid,
  liv_city character varying(64),
  liv_street_id uuid,
  liv_street character varying(64),
  liv_house_id uuid,
  liv_house character varying(8),
  liv_facility_id uuid,
  liv_facility character varying(8),
  liv_building_id uuid,
  liv_building character varying(8),
  liv_flat_id uuid,
  liv_flat character varying(8),
  med_reg_id integer,
  med_city_id integer,
  med_lpu_id integer,
  med_pol_id integer,
  soc_group_id integer,
  med_group_id integer,
  decr_group_id integer,
  CONSTRAINT pk_patient_id PRIMARY KEY (id),
  CONSTRAINT fk_patient_citizen_id_r_citizen_id FOREIGN KEY (citizen_id)
      REFERENCES r_citizen (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_patient_decr_group_id_r_decr_group_id FOREIGN KEY (decr_group_id)
      REFERENCES r_decr_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_patient_med_group_id_r_med_group_id FOREIGN KEY (med_group_id)
      REFERENCES r_med_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_patient_med_reg_id_med_city_id_med_lpu_id_med_pol_id_r_medic FOREIGN KEY (med_reg_id, med_city_id, med_lpu_id, med_pol_id)
      REFERENCES r_medical_org_policlinic (reg_id, ter_id, lpu_id, pol_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_patient_sex_id_r_gender_id FOREIGN KEY (sex_id)
      REFERENCES r_gender (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_patient_soc_group_id_r_soc_group_id FOREIGN KEY (soc_group_id)
      REFERENCES r_soc_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uc_patient_ver_parent_id_ver_active UNIQUE (_ver_parent_id, _ver_active)
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE patient
  IS 'Пациент';
COMMENT ON COLUMN patient.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN patient._ver_parent_id IS 'Родительский идентификатор (для версионности)';
COMMENT ON COLUMN patient._ver_creation_date IS 'Дата создания записи (для версионности)';
COMMENT ON COLUMN patient._ver_active IS 'Активна ли запись, является ли текущей (для версионности)';
COMMENT ON COLUMN patient.last_name IS 'Фамилия';
COMMENT ON COLUMN patient.first_name IS 'Имя';
COMMENT ON COLUMN patient.father_name IS 'Отчество';
COMMENT ON COLUMN patient.dat_birth IS 'Дата рождения';
COMMENT ON COLUMN patient.dat_death IS 'Дата смерти';
COMMENT ON COLUMN patient.sex_id IS 'Ид. пола';
COMMENT ON COLUMN patient.citizen_id IS 'Ид. гражданства';
COMMENT ON COLUMN patient.tel IS 'Номер телефона';
COMMENT ON COLUMN patient.liv_reg_id IS 'Регион проживания (ФИАС)';
COMMENT ON COLUMN patient.liv_reg IS 'Регион проживания';
COMMENT ON COLUMN patient.liv_city_id IS 'Город проживания (ФИАС)';
COMMENT ON COLUMN patient.liv_city IS 'Город проживания';
COMMENT ON COLUMN patient.liv_street_id IS 'Улица проживания (ФИАС)';
COMMENT ON COLUMN patient.liv_street IS 'Улица проживания';
COMMENT ON COLUMN patient.liv_house_id IS 'Номер дома проживания (ФИАС)';
COMMENT ON COLUMN patient.liv_house IS 'Номер дома проживания';
COMMENT ON COLUMN patient.liv_facility_id IS 'Номер корпуса проживания (ФИАС)';
COMMENT ON COLUMN patient.liv_facility IS 'Номер корпуса проживания';
COMMENT ON COLUMN patient.liv_building_id IS 'Номер строения проживания (ФИАС)';
COMMENT ON COLUMN patient.liv_building IS 'Номер строения проживания';
COMMENT ON COLUMN patient.liv_flat_id IS 'Номер квартиры проживания (ФИАС)';
COMMENT ON COLUMN patient.liv_flat IS 'Номер квартиры проживания';
COMMENT ON COLUMN patient.med_reg_id IS 'Ид. региона прикрепления';
COMMENT ON COLUMN patient.med_city_id IS 'Ид. города прикрепления';
COMMENT ON COLUMN patient.med_lpu_id IS 'Ид. ЛПУ прикрепления';
COMMENT ON COLUMN patient.med_pol_id IS 'Ид. поликлиники прикрепления';
COMMENT ON COLUMN patient.soc_group_id IS 'Ид. социальной группы риска';
COMMENT ON COLUMN patient.med_group_id IS 'Ид. социальной группы риска';
COMMENT ON COLUMN patient.decr_group_id IS 'Ид. декретированной группы риска';

CREATE INDEX fki_patient_citizen_id_r_citizen_id
  ON patient
  USING btree
  (citizen_id);

CREATE INDEX fki_patient_decr_group_id_r_decr_group_id
  ON patient
  USING btree
  (decr_group_id);

CREATE INDEX fki_patient_med_group_id_r_med_group_id
  ON patient
  USING btree
  (med_group_id);

CREATE INDEX fki_patient_med_reg_id_med_city_id_med_lpu_id_med_pol_id_r_medi
  ON patient
  USING btree
  (med_reg_id, med_city_id, med_lpu_id, med_pol_id);

CREATE INDEX fki_patient_sex_id_r_gender_id
  ON patient
  USING btree
  (sex_id);

CREATE INDEX fki_patient_soc_group_id_r_soc_group_id
  ON patient
  USING btree
  (soc_group_id);
