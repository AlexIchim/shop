ALTER TABLE revenue
  DROP  COLUMN local_date_time;
ALTER TABLE revenue
  ADD COLUMN date timestamp;