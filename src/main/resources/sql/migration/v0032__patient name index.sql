CREATE INDEX idx_patient_names_parent_id_idx
  ON patient
  USING btree
  (last_name, first_name, father_name, _ver_parent_id);
