package com.washinSystems.homepage.web.mail.formData;

import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class ContactMailForm {
	@NotEmpty(message="メールアドレス：記入してください")
	@Email(message="メールアドレス：メールアドレスの形式で記入してください")
	private String mailAddress;
	@NotEmpty(message="氏：記入してください")
	@Pattern(regexp = "^[^ -~｡-ﾟ]*$",message="氏：全角で記入")
	private String lastName;
	@NotEmpty(message="名：記入してください")
	@Pattern(regexp = "^[^ -~｡-ﾟ]*$",message="名：全角で記入")
	private String firstName;
	@NotEmpty(message="し：記入してください")
	@Pattern(regexp = "^[ぁ-ん]*$",message="し：全角ひらがなで記入")
	private String kanaLastName;
	@NotEmpty(message="めい：記入してください")
	@Pattern(regexp = "^[ぁ-ん]*$",message="めい：全角ひらがなで記入")
	private String kanaFirstName;
	@NotEmpty(message="項目：選択してください")
	private String questionItem;
	private Integer questionItemID;
	@NotEmpty(message="質問内容：記入してください")
	private String contentsQuestion;
	private String contentsQuestionForHTML;
	private String ipV4;
	private Timestamp sendTime;
}
