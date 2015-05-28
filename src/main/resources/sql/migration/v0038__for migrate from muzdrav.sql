ALTER TABLE patient
  ADD COLUMN npasp integer;
COMMENT ON COLUMN patient.npasp IS 'Ид. пациента в Муздрав';

ALTER TABLE examination
  ADD COLUMN muz_id integer;
COMMENT ON COLUMN patient.npasp IS 'Ид. обследования в Муздрав';

ALTER TABLE examination
  ADD COLUMN muz_dataz date;
COMMENT ON COLUMN patient.npasp IS 'Дата изменения записи в Муздрав';

INSERT INTO r_gender (id, name) VALUES (3, 'Пол не определен');
ALTER TABLE patient ALTER COLUMN tel TYPE character varying(26);

ALTER TABLE patient ALTER COLUMN liv_reg TYPE character varying(120);
ALTER TABLE patient ALTER COLUMN liv_city TYPE character varying(120);
ALTER TABLE patient ALTER COLUMN liv_street TYPE character varying(120);

INSERT INTO r_citizen(id, name) VALUES(0, 'Отсутствует информация');

CREATE INDEX idx_npasp
   ON patient (npasp ASC NULLS LAST);

