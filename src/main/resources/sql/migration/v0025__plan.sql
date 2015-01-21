CREATE TABLE public.plan
(
   id serial NOT NULL, 
   med_reg_id integer NOT NULL, 
   med_city_id integer, 
   med_lpu_id integer, 
   year integer NOT NULL, 
   soc_group_id integer, 
   med_group_id integer, 
   decr_group_id integer, 
   cnt_beg_year integer, 
   cnt_plan_pat integer, 
   cnt_plan_exam integer, 
   CONSTRAINT pk_plan_id PRIMARY KEY (id), 
   CONSTRAINT fk_plan_reg_city_lpu FOREIGN KEY (med_reg_id, med_city_id, med_lpu_id) REFERENCES r_medical_org_main (reg_id, ter_id, lpu_id) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT fk_plan_soc_group FOREIGN KEY (soc_group_id) REFERENCES r_soc_group (id) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT fk_plan_med_group FOREIGN KEY (med_group_id) REFERENCES r_med_group (id) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT fk_plan_decr_group FOREIGN KEY (decr_group_id) REFERENCES r_decr_group (id) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON COLUMN public.plan.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN public.plan.med_reg_id IS 'Ид. региона';
COMMENT ON COLUMN public.plan.med_city_id IS 'Ид. города';
COMMENT ON COLUMN public.plan.med_lpu_id IS 'Ид. ЛПУ';
COMMENT ON COLUMN public.plan.year IS 'Год';
COMMENT ON COLUMN public.plan.soc_group_id IS 'Ид. социальной группы риска';
COMMENT ON COLUMN public.plan.med_group_id IS 'Ид. медицинской группы риска';
COMMENT ON COLUMN public.plan.decr_group_id IS 'Ид. декретированной группы риска';
COMMENT ON COLUMN public.plan.cnt_beg_year IS 'Количество лиц, подлежащих обследованию на начало года';
COMMENT ON COLUMN public.plan.cnt_plan_pat IS 'План на год (в лицах)';
COMMENT ON COLUMN public.plan.cnt_plan_exam IS 'План на год (в исследованиях)';
COMMENT ON TABLE public.plan
  IS 'План обследований на год';

