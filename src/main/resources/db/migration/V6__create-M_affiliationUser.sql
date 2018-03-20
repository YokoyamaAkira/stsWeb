CREATE TABLE M_affiliationUser (id INT(255)  NOT NULL PRIMARY KEY,
                      userName VARCHAR(100) DEFAULT '',
                      useMailF BOOLEAN DEFAULT false,
                      roleId INT(100) DEFAULT 0,
                      mailaddress VARCHAR(100) DEFAULT '',
                      nickName VARCHAR(100) DEFAULT '',
                      delF BOOLEAN DEFAULT false,
                      registrationDate DATE DEFAULT CURRENT_TIMESTAMP(),
                      registeredPerson INT(255) DEFAULT 0,
                      editDate DATE DEFAULT CURRENT_TIMESTAMP(),
                      editor INT(255) DEFAULT 0);
INSERT INTO M_affiliationUser (id,userName, roleId,mailaddress,nickName) VALUES (1,'user1', '1', 'test@washin.co.jp', 'ÉKÉN');
INSERT INTO M_affiliationUser (id,userName, roleId,mailaddress,nickName) VALUES (2,'user2', '2', 'test2@washin.co.jp', 'ÉKÉNÉKÉN');