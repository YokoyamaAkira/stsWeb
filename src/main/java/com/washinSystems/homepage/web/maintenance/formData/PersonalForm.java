package com.washinSystems.homepage.web.maintenance.formData;

import lombok.Data;

@Data
public class PersonalForm {
	private Integer id ;//INT PRIMARY KEY AUTO_INCREMENT,
	private String userName;// VARCHAR(100) DEFAULT '',
	private Integer roleId;// INT(100);// DEFAULT 0,
	private String roleName;
    private String mailaddress;// VARCHAR(100) DEFAULT '',
    private String nickName;// VARCHAR(100) DEFAULT '',
    private String password;
    private Boolean delF;// BOOLEAN DEFAULT false,
    private Boolean useMailF;// BOOLEAN DEFAULT false,
}
