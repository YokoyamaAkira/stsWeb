package com.washinSystems.homepage.web.mail.formData;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

//入力チェック：複数項目の相関チェックをするアノテーションの作り方　STS ＋Spring Boot＋thymeleaf 
//http://arakan-pgm-ai.hatenablog.com/entry/2017/04/08/003859
//指定したフィールドのいずれかが空ではないという相関チェックをBean Validationで実装する
//https://dev.classmethod.jp/server-side/java/bean-validation-notemptyany-impl/
//指定したフィールドのいずれかが空ではないことを検証するバリデーション
//https://dev.classmethod.jp/server-side/java/bean-validation-notemptyany-impl/
//Thymeleafでのエラーの処理
//https://blog.okazuki.jp/entry/2015/07/05/115155

@Data
public class EntryMailForm {

	@NotEmpty
	private String graduate;
	private Integer graduateID;

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
	@NotNull
	private Integer birthdayY;
	@NotNull
	private Integer birthdayM;
	@NotNull
	private Integer birthdayD;
	@NotNull
	private Integer graduateY;
	@NotNull
	private Integer graduateM;
	@NotNull
	private Integer graduateD;
	
	@NotEmpty
	@Pattern(regexp = "^([0-9]{7})?$")
	private String postalCode;
	@NotEmpty
	private String prefectures;
	@NotEmpty
	@Pattern(regexp = "^[^ -~｡-ﾟ]*$")
	private String municipality;
	@Pattern(regexp = "^[^ -~｡-ﾟ]*$")
	private String buildingName;
	
	private Integer tel;
	private Integer mobile;
	@NotEmpty
	@Email
	private String mailAddress;
}
