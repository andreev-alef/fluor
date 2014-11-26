CREATE TABLE r_habitat
(
   id integer NOT NULL, 
   name character varying(255) NOT NULL, 
   CONSTRAINT pk_r_habitat_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_habitat
  IS 'Тип места проживания';

INSERT INTO r_habitat VALUES (1, 'Городской');
INSERT INTO r_habitat VALUES (2, 'Сельский');
