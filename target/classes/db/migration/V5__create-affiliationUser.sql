CREATE TABLE affiliation_user (id INT PRIMARY KEY AUTO_INCREMENT,
                      user_name VARCHAR(100) DEFAULT '',
                      use_mail_f BOOLEAN DEFAULT false,
                      role_id INT(100) DEFAULT 0,
                      mailaddress VARCHAR(100) DEFAULT '',
                      nick_name VARCHAR(100) DEFAULT '',
                      del_f BOOLEAN DEFAULT false,
                      registration_date DATE DEFAULT CURRENT_TIMESTAMP(),
                      registered_person INT(255) DEFAULT 0,
                      edit_date DATE DEFAULT CURRENT_TIMESTAMP(),
                      editor INT(255) DEFAULT 0);
INSERT INTO affiliation_user (id,user_name, role_id,mailaddress,nick_name) VALUES (1,'user1', '1', 'test@washin.co.jp', 'ÉKÉN');
INSERT INTO affiliation_user (id,user_name, role_id,mailaddress,nick_name) VALUES (2,'user2', '2', 'test2@washin.co.jp', 'ÉKÉNÉKÉN');