CREATE TABLE r_medical_org_region
(
  kod_okato character varying(5),
  reg_id integer NOT NULL,
  name character varying(254) NOT NULL,
  okrname character varying(254),
  okrug integer,
  CONSTRAINT pk_r_medical_org_region_reg_id PRIMARY KEY (reg_id)
)
WITH (
  OIDS=FALSE
);
COMMENT ON TABLE r_medical_org_region
  IS 'Регион нахождения медицинской организации';

INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('79000', 1, 'Республика Адыгея', NULL, 2);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('80000', 2, 'Республика Башкортостан', NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('81000', 3, 'Республика Бурятия', NULL, 5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('84000', 4, 'Республика Алтай', NULL, 5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('82000', 5, 'Республика Дагестан', NULL, 8);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('26000', 6, 'Республика Ингушетия', NULL, 8);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('83000', 7, 'Кабардино-Балкарская Республика', NULL, 8);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('85000', 8, 'Республика Калмыкия', NULL,  2);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('91000', 9, 'Карачаево-Черкесская Республика', NULL, 8);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('86000', 10, 'Республика Карелия', NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('87000', 11, 'Республика Коми', NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('88000', 12, 'Республика Марий Эл', NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('89000', 13, 'Республика Мордовия',  NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('98000', 14, 'Республика Саха (Якутия)', NULL, 4);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('90000', 15, 'Республика Северная Осетия-Алания', NULL, 8);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('92000', 16, 'Республика Татарстан', NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('93000', 17, 'Республика Тыва', NULL,5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('94000', 18, 'Удмуртская Республика', NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('95000', 19, 'Республика Хакасия',  NULL, 5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('96000', 20, 'Чеченская Республика',  NULL, 8);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('97000', 21, 'Чувашская Республика',  NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('01000', 22, 'Алтайский край', NULL,  5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('03000', 23, 'Краснодарский край',  NULL, 2);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('04000', 24, 'Красноярский край', NULL, 5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('05000', 25, 'Приморский край',  NULL, 4);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('07000', 26, 'Ставропольский край',  NULL, 8);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('08000', 27, 'Хабаровский край',  NULL, 4);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('10000', 28, 'Амурская область',  NULL, 4);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('11000', 29, 'Архангельская область',  NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('12000', 30, 'Астраханская область',  NULL, 2);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('14000', 31, 'Белгородская область',  NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('15000', 32, 'Брянская область',  NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('17000', 33, 'Владимирская область',  NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('18000', 34, 'Волгоградская область', NULL, 2);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('19000', 35, 'Вологодская область',  NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('20000', 36, 'Воронежская область',  NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('24000', 37, 'Ивановская область',  NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('25000', 38, 'Иркутская область',  NULL, 5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('27000', 39, 'Калининградская область',  NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('29000', 40, 'Калужская область', NULL,  1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('30000', 41, 'Камчатский край', NULL,  4);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('32000', 42, 'Кемеровская область',  NULL, 5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('33000', 43, 'Кировская область',  NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('34000', 44, 'Костромская область',  NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('37000', 45, 'Курганская область',  NULL, 6);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('38000', 46, 'Курская область',  NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('41000', 47, 'Ленинградская область', NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('42000', 48, 'Липецкая область', NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('44000', 49, 'Магаданская область',  NULL, 4);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('46000', 50, 'Московская область',  NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('47000', 51, 'Мурманская область',  NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('22000', 52, 'Нижегородская область', NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('49000', 53, 'Новгородская область',  NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('50000', 54, 'Новосибирская область', NULL, 5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('52000', 55, 'Омская область', NULL,  5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('53000', 56, 'Оренбургская область',  NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('54000', 57, 'Орловская область', NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('56000', 58, 'Пензенская область',  NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('57000', 59, 'Пермский край', NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('58000', 60, 'Псковская область', NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('60000', 61, 'Ростовская область', NULL, 2);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('61000', 62, 'Рязанская область',  NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('36000', 63, 'Самарская область',  NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('63000', 64, 'Саратовская область',  NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('64000', 65, 'Сахалинская область',  NULL, 4);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('65000', 66, 'Свердловская область', NULL, 6);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('66000', 67, 'Смоленская область', NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('68000', 68, 'Тамбовская область', NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('28000', 69, 'Тверская область', NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('69000', 70, 'Томская область', NULL, 5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('70000', 71, 'Тульская область',NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('71000', 72, 'Тюменская область',  NULL, 6);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('73000', 73, 'Ульяновская область',  NULL, 7);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('75000', 74, 'Челябинская область', NULL, 6);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('76000', 75, 'Забайкальский край', NULL, 5);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('78000', 76, 'Ярославская область',NULL, 1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('45000', 77, 'г. Москва', NULL,  1);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('40000', 78, 'г. Санкт-Петербург',  NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('99000', 79, 'Еврейская АО', NULL,  4);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('11100', 80, 'Ненецкий АО',  NULL, 3);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('71100', 81, 'Ханты-Мансийский АО', NULL, 6);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('77000', 82, 'Чукотский АО', NULL, 4);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('71140', 83, 'Ямало-Ненецкий АО', NULL, 6);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('55000', 84, 'г. Байконур', NULL,  0);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('35000', 85, 'Республика Крым', NULL, 9);
INSERT INTO r_medical_org_region (kod_okato, reg_id, name, okrname, okrug) VALUES ('67000', 86, 'г. Севастополь', NULL, 9);

ALTER TABLE r_medical_org_ter
  ADD CONSTRAINT fk FOREIGN KEY (reg_id) REFERENCES r_medical_org_region (reg_id)
   ON UPDATE NO ACTION ON DELETE NO ACTION;
