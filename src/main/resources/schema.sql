DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
  id         INTEGER DEFAULT NEXTVAL('global_seq'),
  name       VARCHAR NOT NULL UNIQUE,
  address    VARCHAR NOT NULL UNIQUE,
  age        INTEGER NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE accounts
(
  id      INTEGER DEFAULT nextval('global_seq'),
  user_id INTEGER NOT NULL,
  amount  BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE transactions
(
  id          INTEGER DEFAULT nextval('global_seq'),
  debit_id    INTEGER,
  refill_id   INTEGER,
  amount      BIGINT NOT NULL,
  date        TIMESTAMP NOT NULL DEFAULT now(),
  description VARCHAR,
  PRIMARY KEY (id),
  FOREIGN KEY (refill_id) REFERENCES accounts (id),
  FOREIGN KEY (debit_id) REFERENCES accounts (id)
);