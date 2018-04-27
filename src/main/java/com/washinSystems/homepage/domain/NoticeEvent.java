package com.washinSystems.homepage.domain;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "affiliation_events")
public class NoticeEvent {
	@Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;
	@Column(name="event_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date eventDate;// 			DATE DEFAULT CURRENT_TIMESTAMP(),
	@Column(name="posting_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date postingDate;// 		DATE DEFAULT DATEADD('DAY', 1, CURRENT_TIMESTAMP()),
	@Column(name="posting_end_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date postingEndDate;// 		DATE DEFAULT DATEADD('MONTH', 1, CURRENT_TIMESTAMP()),
	@Column(name="title")
    private String title;// 			VARCHAR(100) DEFAULT '',
	@Column(name="event_contents")
    private String eventContents;// 	VARCHAR(100) DEFAULT '',
	@Column(name="posting_end_f")
    private Boolean postingEndF;// 		BOOLEAN DEFAULT false,
	@Column(name="registration_date")
    private Date registrationDate;// 	DATE DEFAULT CURRENT_TIMESTAMP(),
	@Column(name="registered_person")
    private Integer registeredPerson;// INT(255) DEFAULT 0,
	@Column(name="edit_date")
    private Date editDate;// 			DATE DEFAULT CURRENT_TIMESTAMP(),
	@Column(name="editor")
    private Integer editor;// 			INT(255) DEFAULT 0
}
