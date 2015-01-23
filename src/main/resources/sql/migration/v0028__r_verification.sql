CREATE TABLE r_verification
(
   id integer NOT NULL, 
   name character varying(40) NOT NULL, 
   CONSTRAINT pk_r_verification_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_verification
  IS 'Верификация диагноза';

INSERT INTO r_verification VALUES (1, 'Да');
INSERT INTO r_verification VALUES (2, 'Нет');
