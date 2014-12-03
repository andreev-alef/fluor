CREATE TABLE r_medical_org_ter
(
   reg_id integer NOT NULL, 
   ter_id integer NOT NULL, 
   name character varying(32) NOT NULL, 
   CONSTRAINT pk_r_medical_org_ter_reg_id_ter_id PRIMARY KEY (reg_id, ter_id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_medical_org_ter
  IS 'Территория нахождения медицинской организации';

INSERT INTO r_medical_org_ter VALUES (42, 1, 'АНЖЕРО-СУДЖЕНСК Г.');
INSERT INTO r_medical_org_ter VALUES (42, 2, 'БЕЛОВО Г.');
INSERT INTO r_medical_org_ter VALUES (42, 3, 'БЕРЕЗОВСКИЙ Г.');
INSERT INTO r_medical_org_ter VALUES (42, 4, 'ГУРЬЕВСК Г.');
INSERT INTO r_medical_org_ter VALUES (42, 5, 'КЕМЕРОВО Г.');
INSERT INTO r_medical_org_ter VALUES (42, 6, 'КИСЕЛЕВСК Г.');
INSERT INTO r_medical_org_ter VALUES (42, 7, 'ЛЕНИНСК-КУЗНЕЦКИЙ  Г.');
INSERT INTO r_medical_org_ter VALUES (42, 8, 'МЕЖДУРЕЧЕНСК  Г.');
INSERT INTO r_medical_org_ter VALUES (42, 9, 'МЫСКИ Г.');
INSERT INTO r_medical_org_ter VALUES (42, 10, 'НОВОКУЗНЕЦК Г.');
INSERT INTO r_medical_org_ter VALUES (42, 11, 'ОСИННИКИ Г.');
INSERT INTO r_medical_org_ter VALUES (42, 12, 'ПРОКОПЬЕВСК Г.');
INSERT INTO r_medical_org_ter VALUES (42, 13, 'ТАЙГА  Г.');
INSERT INTO r_medical_org_ter VALUES (42, 14, 'ТАШТАГОЛ - ТАШТАГОЛЬСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 15, 'ЮРГА Г.');
INSERT INTO r_medical_org_ter VALUES (42, 16, 'БЕЛОВСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 17, 'ИЖМОРСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 18, 'КЕМЕРОВСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 19, 'КРАПИВИНСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 20, 'ЛЕНИНСК-КУЗНЕЦКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 21, 'МАРИИНСК И МАРИИНСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 22, 'НОВОКУЗНЕЦКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 23, 'ПРОКОПЬЕВСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 24, 'ПРОМЫШЛЕННОВСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 25, 'ТИСУЛЬСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 26, 'ТОПКИ И ТОПКИНСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 27, 'ТЯЖИНСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 28, 'ЧЕБУЛИНСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 29, 'ЮРГИНСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 30, 'ЯЙСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 31, 'ЯШКИНСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 32, 'ГУРЬЕВСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 34, 'ПОЛЫСАЕВО Г.');
INSERT INTO r_medical_org_ter VALUES (42, 35, 'КАЛТАН Г.');
INSERT INTO r_medical_org_ter VALUES (42, 36, 'ТОПКИ Г.');
INSERT INTO r_medical_org_ter VALUES (42, 37, 'МАРИИНСКИЙ РАЙОН');
INSERT INTO r_medical_org_ter VALUES (42, 39, 'КРАСНОБРОДСКИЙ  ПГТ');
