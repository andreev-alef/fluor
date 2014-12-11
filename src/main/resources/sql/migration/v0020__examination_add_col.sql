ALTER TABLE examination
  ADD COLUMN type_id integer ;
COMMENT ON COLUMN examination.type_id IS 'Ид вид обследования';
update examination set type_id=1;
update examination set type_id=2 where id=2;
ALTER TABLE examination
   ALTER COLUMN type_id SET NOT NULL;
ALTER TABLE examination
  ADD CONSTRAINT fk_type_id FOREIGN KEY (type_id) REFERENCES r_exam_type (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE examination
  ADD CONSTRAINT fk_medical_org_region FOREIGN KEY (med_reg_id) 
	REFERENCES r_medical_org_region (reg_id) 
	ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE examination ADD
  CONSTRAINT fk_medical_org_ter FOREIGN KEY (med_reg_id, med_city_id)
      REFERENCES r_medical_org_ter (reg_id, ter_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE examination ADD
  CONSTRAINT fk_medical_org_main FOREIGN KEY (med_reg_id, med_city_id, med_lpu_id)
      REFERENCES r_medical_org_main (reg_id, ter_id, lpu_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
