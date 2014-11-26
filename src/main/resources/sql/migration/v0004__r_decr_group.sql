CREATE TABLE r_decr_group
(
   id integer NOT NULL, 
   name character varying(255) NOT NULL, 
   CONSTRAINT pk_r_decr_group_id PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE r_decr_group
  IS 'Декретированная группа риска';

INSERT INTO r_decr_group VALUES (1, 'Работники организаций по переработке и реализации пищевых продуктов, в том числе молока и молочных продуктов');
INSERT INTO r_decr_group VALUES (2, 'Работники организаций бытового обслуживания населения');
INSERT INTO r_decr_group VALUES (3, 'Работники водопроводных сооружений');
INSERT INTO r_decr_group VALUES (4, 'Сотрудники медицинских организаций, в том числе немедицинский персонал');
INSERT INTO r_decr_group VALUES (5, 'Работники санаторно-курортных, образовательных, оздоровительных и спортивных учреждений для детей и подростков');
INSERT INTO r_decr_group VALUES (6, 'Учащиеся (подростки)');
INSERT INTO r_decr_group VALUES (7, 'Иностранные граждане и лица без гражданства, в том числе, осуществляющие трудовую деятельность на территории РФ');
INSERT INTO r_decr_group VALUES (8, 'Работники предприятий, организаций и учреждений');
INSERT INTO r_decr_group VALUES (9, 'Работники  сельского хозяйства');
INSERT INTO r_decr_group VALUES (10, 'Лица, проживающие в стационарных учреждениях социального обслуживания и учреждениях социальной помощи для лиц без определенного места жительства');
INSERT INTO r_decr_group VALUES (11, 'Работники учреждений социального обслуживания для детей и подростков');
INSERT INTO r_decr_group VALUES (12, 'Работники организаций социального обслуживания для престарелых и инвалидов');
INSERT INTO r_decr_group VALUES (13, 'Неорганизованное население (домохозяйки, пенсионеры, инвалиды и другие неработающие лица)');
INSERT INTO r_decr_group VALUES (14, 'Иностранные граждане и лица без гражданства, в том числе, осуществляющие трудовую деятельность');
INSERT INTO r_decr_group VALUES (15, 'Беженцы');
INSERT INTO r_decr_group VALUES (16, 'Вынужденные переселенцы');
INSERT INTO r_decr_group VALUES (17, 'Мигранты');
