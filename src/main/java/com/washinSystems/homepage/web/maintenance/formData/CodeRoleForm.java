package com.washinSystems.homepage.web.maintenance.formData;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CodeRoleForm {

    private Integer roleId;// INT(255) NOT NULL PRIMARY KEY,
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date startDate;// DATE NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date endDate;// DATE DEFAULT '9999-12-31',
	@Size(min = 1, max = 100)
	private String roleName;// VARCHAR(100) DEFAULT '',
	@Size(min = 1, max = 100)
	private String description;// VARCHAR(100) DEFAULT '',
	private Boolean delF;// BOOLEAN DEFAULT false,
}
