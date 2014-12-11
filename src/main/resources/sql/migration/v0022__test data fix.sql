UPDATE patient SET _ver_parent_id = 3 WHERE id = 3;

UPDATE examination SET patient_id = 2 WHERE id = 1;
UPDATE examination SET patient_id = 2 WHERE id = 2;
UPDATE examination SET patient_id = 3 WHERE id = 3;
