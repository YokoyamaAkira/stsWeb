package com.washinSystems.homepage.domain;

import java.util.Date;

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
@Table(name = "affiliationEvents")
public class NoticeEvent {
	@Id
    @GeneratedValue
    private Integer id;
	private Date eventDate;// 			DATE DEFAULT CURRENT_TIMESTAMP(),
    private Date postingDate;// 		DATE DEFAULT DATEADD('DAY', 1, CURRENT_TIMESTAMP()),
	private Date postingEndDate;// 		DATE DEFAULT DATEADD('MONTH', 1, CURRENT_TIMESTAMP()),
    private String title;// 			VARCHAR(100) DEFAULT '',
    private String eventContents;// 	VARCHAR(100) DEFAULT '',
    private Boolean postingEndF;// 		BOOLEAN DEFAULT false,
    private Date registrationDate;// 	DATE DEFAULT CURRENT_TIMESTAMP(),
    private Integer registeredPerson;// INT(255) DEFAULT 0,
    private Date editDate;// 			DATE DEFAULT CURRENT_TIMESTAMP(),
    private Integer editor;// 			INT(255) DEFAULT 0
}
