CREATE TABLE r_exam_type
(
   id integer NOT NULL, 
   name character varying(16) NOT NULL, 
   CONSTRAINT pk_r_exam_type_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_exam_type
  IS 'Тип обследования';

INSERT INTO r_exam_type VALUES (1, 'Флюорография');
INSERT INTO r_exam_type VALUES (2, 'Рентгенография');
