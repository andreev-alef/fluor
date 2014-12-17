ALTER TABLE examination
  DROP CONSTRAINT r_medical_org_policlinic;
ALTER TABLE examination
  DROP COLUMN med_pol_id;
ALTER TABLE examination
   ALTER COLUMN med_reg_id DROP NOT NULL;
ALTER TABLE examination
   ALTER COLUMN med_city_id DROP NOT NULL;
ALTER TABLE examination
   ALTER COLUMN med_lpu_id DROP NOT NULL;
ALTER TABLE examination
   ALTER COLUMN method_id DROP NOT NULL;
ALTER TABLE examination
  DROP CONSTRAINT fk_medical_org_region;
ALTER TABLE examination
  DROP CONSTRAINT fk_medical_org_ter;
