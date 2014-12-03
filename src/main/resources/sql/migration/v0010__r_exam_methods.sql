CREATE TABLE r_exam_methods
(
   id integer NOT NULL, 
   name character varying(32) NOT NULL, 
   type_id integer NOT NULL, 
   CONSTRAINT pk_r_exam_methods_id PRIMARY KEY (id), 
   CONSTRAINT fk_r_exam_methods_type_id_r_exam_type_id FOREIGN KEY (type_id) REFERENCES r_exam_type (id) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_exam_methods
  IS 'Метод обследования';

INSERT INTO r_exam_methods VALUES (1, 'Флюорограф стационарный', 1);
INSERT INTO r_exam_methods VALUES (2, 'Флюорограф передвижной', 1);
INSERT INTO r_exam_methods VALUES (3, 'Рентгенограф', 2);
INSERT INTO r_exam_methods VALUES (4, 'Струнный компьютерный томограф', 2);
