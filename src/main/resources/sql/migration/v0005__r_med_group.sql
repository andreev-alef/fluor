CREATE TABLE r_med_group
(
   id integer NOT NULL, 
   name character varying(255) NOT NULL, 
   CONSTRAINT pk_r_med_group_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_med_group
  IS 'Медицинская группа риска';

INSERT INTO r_med_group VALUES (1, 'Больные неспецифическими заболеваниями органов дыхания');
INSERT INTO r_med_group VALUES (2, 'Больные хроническими заболеваниями органов желудочно-кишечного тракта');
INSERT INTO r_med_group VALUES (3, 'Больные хроническими заболеваниями мочеполовой системы');
INSERT INTO r_med_group VALUES (4, 'Больные сахарным диабетом');
INSERT INTO r_med_group VALUES (5, 'Больные онкогематологическими заболеваниями');
INSERT INTO r_med_group VALUES (6, 'Лица, получающие кортикостероидную, лучевую и цитостатическую терапию, блокаторы ФНО-а, генно-инженерные биологические препараты');
INSERT INTO r_med_group VALUES (7, 'Лица, имеющие остаточные изменения в легких после перенесенного заболевания в течение первых 3-х лет с момента выявления заболевания');
