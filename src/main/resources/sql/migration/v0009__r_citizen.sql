CREATE TABLE r_citizen
(
   id integer NOT NULL, 
   name character varying(96) NOT NULL, 
   CONSTRAINT pk_r_citizen_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_citizen
  IS 'Гражданство';

INSERT INTO r_citizen VALUES (1, 'Гражданин Российской Федерации');
INSERT INTO r_citizen VALUES (2, 'Гражданин Российской Федерации и иностранного государства (двойное гражданство)');
INSERT INTO r_citizen VALUES (3, 'Иностранный гражданин');
INSERT INTO r_citizen VALUES (4, 'Лицо без гражданства');
