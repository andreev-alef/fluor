CREATE TABLE r_user_role
(
  id integer NOT NULL,
  name character varying(50) NOT NULL,
  CONSTRAINT pk_r_user_role_id PRIMARY KEY (id)
);

INSERT INTO r_user_role VALUES (1, 'MANAGER');
INSERT INTO r_user_role VALUES (2, 'USER');

CREATE TABLE users
(
  id serial NOT NULL,
  login character varying(50) NOT NULL,
  password character varying(32) NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT pk_users_id PRIMARY KEY (id),
  CONSTRAINT fk_users_role_id_r_user_role_id FOREIGN KEY (role_id)
      REFERENCES r_user_role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT users_login_unique UNIQUE (login)
);
CREATE INDEX fki_users_role_id_r_user_role_id
  ON users
  USING btree
  (role_id);

INSERT INTO users VALUES (2, 'User', 'c81e728d9d4c2f636f067f89cc14862c', 2); -- Пароль:2
INSERT INTO users VALUES (1, 'Manager', 'c4ca4238a0b923820dcc509a6f75849b', 1); --Пароль:1
SELECT pg_catalog.setval('users_id_seq', 2, true);
