package com.washinSystems.homepage.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "C_role")
public class CodeRole {
	@Id
    @GeneratedValue
    @Column(name="role_id")
    private Integer roleId;// INT(255) NOT NULL PRIMARY KEY,
	@Column(name="start_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date startDate;// DATE NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	@Column(name="end_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date endDate;// DATE DEFAULT '9999-12-31',
	@Column(name="role_name")
    private String roleName;// VARCHAR(100) DEFAULT '',
    @Column(name="description")
    private String description;// VARCHAR(100) DEFAULT '',
    @Column(name="del_f")
    private Boolean delF;// BOOLEAN DEFAULT false,
    @Column(name="registration_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date registrationDate;// DATE DEFAULT CURRENT_TIMESTAMP(),
    @Column(name="registered_person")
    private Integer registeredPerson;// INT(255) DEFAULT 0,
    @Column(name="edit_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date editDate;// DATE DEFAULT CURRENT_TIMESTAMP(),
    @Column(name="editor")
    private Integer editor;// INT(255) DEFAULT 0

}
