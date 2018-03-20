CREATE TABLE C_rloe (roleId INT(255) NOT NULL PRIMARY KEY,
                      startDate DATE NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                      endDate DATE DEFAULT '9999-12-31',
                      roleName VARCHAR(100) DEFAULT '',
                      description VARCHAR(100) DEFAULT '',
                      delF BOOLEAN DEFAULT false,
                      registrationDate DATE DEFAULT CURRENT_TIMESTAMP(),
                      registeredPerson INT(255) DEFAULT 0,
                      editDate DATE DEFAULT CURRENT_TIMESTAMP(),
                      editor INT(255) DEFAULT 0);
INSERT INTO C_rloe (roleId,roleName,description) VALUES (1, 'admin', 'システム管理者');
INSERT INTO C_rloe (roleId,roleName,description) VALUES (2, 'editor', 'イベント編集者');
