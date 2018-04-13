package com.washinSystems.homepage.web.maintenance.formData;

import java.util.Date;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EventForm {
	private Integer id;
	private Date eventDate;// 			DATE DEFAULT CURRENT_TIMESTAMP(),
    private Date postingDate;// 		DATE DEFAULT DATEADD('DAY', 1, CURRENT_TIMESTAMP()),
	private Date postingEndDate;// 		DATE DEFAULT DATEADD('MONTH', 1, CURRENT_TIMESTAMP()),
	@Size(min = 1, max = 100)
	private String title;// 			VARCHAR(100) DEFAULT '',
	@Size(min = 1, max = 100)
	private String eventContents;// 	VARCHAR(100) DEFAULT '',
    private Boolean postingEndF;// 		BOOLEAN DEFAULT false,
}
