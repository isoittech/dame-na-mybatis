-- User table  --
DROP TABLE IF EXISTS m_user;
CREATE TABLE m_user (
  id SERIAL NOT NULL,
  email VARCHAR(64) NOT NULL UNIQUE,
  challenge VARCHAR(128) NOT NULL,
  attestation VARCHAR(64) NOT NULL,
  PRIMARY KEY (id)
);
select setval ('m_user_id_seq', 1, false);