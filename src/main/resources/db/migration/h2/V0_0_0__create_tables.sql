-- User table  --
CREATE TABLE m_user (
  id INTEGER NOT NULL AUTO_INCREMENT,
  email VARCHAR(64) NOT NULL UNIQUE,
  challenge VARCHAR(128) NOT NULL,
  attestation VARCHAR(64) NOT NULL,
  primary key(id)
);
