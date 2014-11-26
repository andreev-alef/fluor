CREATE TABLE r_gender
(
   id integer NOT NULL, 
   name character varying(16) NOT NULL, 
   CONSTRAINT pk_r_gender_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_gender
  IS 'Пол';

INSERT INTO r_gender VALUES (1, 'Мужской');
INSERT INTO r_gender VALUES (2, 'Женский');
