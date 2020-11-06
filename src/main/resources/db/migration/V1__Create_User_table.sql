CREATE TABLE users
(
  id         INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name  VARCHAR(50) NOT NULL,
  email      VARCHAR(50) NOT NULL,
  password   VARCHAR(100) NOT NULL,
  role       ENUM ('ADMIN', 'USER')  NOT NULL
);


ALTER TABLE users
  ADD CONSTRAINT UQ_USERS_EMAIL UNIQUE (email);