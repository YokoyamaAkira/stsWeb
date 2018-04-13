package com.washinSystems.homepage.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "affiliationUser")
public class MaintenancePersonnel {
	@Id
    @GeneratedValue
    private Integer id;// 					INT(255)  NOT NULL PRIMARY KEY,
	private String userName;// 				VARCHAR(100) DEFAULT '',
	private Date useMailF;// 				BOOLEAN DEFAULT false,
    private Integer roleId;// 				INT(100) DEFAULT 0,
    private String mailaddress;// 			VARCHAR(100) DEFAULT '',
    private String nickName;// 				VARCHAR(100) DEFAULT '',
    private Boolean delF;// 				BOOLEAN DEFAULT false,
    private Date registrationDate;// 		DATE DEFAULT CURRENT_TIMESTAMP(),
    private Integer registeredPerson;// 	INT(255) DEFAULT 0,
    private Date editDate ;//				DATE DEFAULT CURRENT_TIMESTAMP(),
    private Integer editor;// 				INT(255) DEFAULT 0
}
