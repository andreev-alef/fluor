CREATE TABLE r_soc_group
(
   id integer NOT NULL, 
   name character varying(255) NOT NULL, 
   CONSTRAINT pk_r_soc_group_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_soc_group
  IS 'Социальная группа риска';

INSERT INTO r_soc_group VALUES (1, 'Лица, находящиеся в тесном бытовом или профессиональном контакте с источником туберкулезной инфекции');
INSERT INTO r_soc_group VALUES (2, 'Лица, снятые с диспансерного учета в медицинских противотуберкулезных организациях в связи с выздоровлением, в течении первых 3-х лет с момента выявления заболевания');
INSERT INTO r_soc_group VALUES (3, 'Лица, перенесшие туберкулез и имеющие остаточные изменения в легких, в течение первых 3 лет с момента выявления заболевания');
INSERT INTO r_soc_group VALUES (4, 'ВИЧ-инфицированные');
INSERT INTO r_soc_group VALUES (5, 'Пациенты, состоящие на диспансерном учете в наркологических и психиатрических учреждениях');
INSERT INTO r_soc_group VALUES (6, 'Лица, состоящие в группе профилактического наркологического и психиатрического учета в связи с употреблением психоактивных веществ и препаратов');
INSERT INTO r_soc_group VALUES (7, 'Подследственные, содержащиеся в следственных изоляторах, и осужденные, содержащиеся в исправительных учреждениях');
INSERT INTO r_soc_group VALUES (8, 'Лица, освобожденные из следственных изоляторов и исправительных учреждений, в течении первых 2 лет после освобождения');
INSERT INTO r_soc_group VALUES (9, 'Лица, по роду своей профессиональной  деятельности имеющие контакт с контингентом подследственных и осужденных');
INSERT INTO r_soc_group VALUES (10, 'Лица без определенного места жительства');
