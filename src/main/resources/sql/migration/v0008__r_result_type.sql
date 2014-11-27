CREATE TABLE r_result_type
(
   id integer NOT NULL, 
   name character varying(32) NOT NULL, 
   CONSTRAINT pk_r_result_type_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_result_type
  IS 'Тип результата';

INSERT INTO r_result_type VALUES (1, 'Норма');
INSERT INTO r_result_type VALUES (2, 'Туберкулез активный');
INSERT INTO r_result_type VALUES (3, 'Теберкулез неактивный');
INSERT INTO r_result_type VALUES (4, 'Новообразования');
INSERT INTO r_result_type VALUES (5, 'Силикоз');
INSERT INTO r_result_type VALUES (6, 'Саркоидоз');
INSERT INTO r_result_type VALUES (7, 'Пневмония');
INSERT INTO r_result_type VALUES (8, 'ХОБЛ');
INSERT INTO r_result_type VALUES (9, 'Прочее');
