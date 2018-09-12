package com.washinSystems.homepage.web.mail.formData;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class ContactMailForm {
	@NotEmpty
	@Email
	private String mailAddress;
	@NotEmpty
	@Pattern(regexp = "^[^ -~｡-ﾟ]*$")
	private String lastName;
	@NotEmpty
	@Pattern(regexp = "^[^ -~｡-ﾟ]*$")
	private String firstName;
	@NotEmpty
	@Pattern(regexp = "^[ぁ-ん]*$")
	private String kanaLastName;
	@NotEmpty
	@Pattern(regexp = "^[ぁ-ん]*$")
	private String kanaFirstName;
	@NotEmpty
	private String questionItem;
	private Integer questionItemID;
	@NotEmpty
	private String contentsQuestion;
	private String contentsQuestionForHTML;
}
