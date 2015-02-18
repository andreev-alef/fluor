UPDATE patient SET user_id = 1 WHERE user_id IS NULL;
UPDATE examination SET user_id = 1 WHERE user_id IS NULL;

ALTER  TABLE  patient  ALTER  COLUMN user_id SET NOT NULL;
ALTER  TABLE  examination  ALTER  COLUMN user_id SET NOT NULL;
