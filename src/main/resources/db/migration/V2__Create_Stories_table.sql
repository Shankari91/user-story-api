CREATE TABLE stories
(
  id          INT AUTO_INCREMENT PRIMARY KEY,
  summary     VARCHAR(50)                                         NOT NULL,
  description VARCHAR(100)                                        NOT NULL,
  cost        INT                                                 NOT NULL,
  type        ENUM ('ENHANCEMENT', 'BUGFIX', 'DEVELOPMENT', 'QA') NOT NULL,
  complexity  ENUM ('LOW', 'MID', 'HIGH')                         NOT NULL,
  user_id     int                                                 NOT NULL
);


ALTER TABLE stories
  ADD CONSTRAINT FK_stories_user_id
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION;