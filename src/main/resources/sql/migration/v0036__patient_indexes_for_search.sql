CREATE INDEX idx_last_name
   ON patient (last_name text_pattern_ops ASC NULLS LAST);
   
CREATE INDEX idx_first_name
   ON patient (first_name text_pattern_ops ASC NULLS LAST);

CREATE INDEX idx_father_name
   ON patient (father_name text_pattern_ops ASC NULLS LAST);
