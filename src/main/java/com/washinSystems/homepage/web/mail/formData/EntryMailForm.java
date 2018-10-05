package com.washinSystems.homepage.web.mail.formData;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.washinSystems.homepage.localValidator.NotEmptyAny;

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
@NotEmptyAny(fields = {"tel", "mobile"}, message="どちらかを記入してください")
public class EntryMailForm {

	@NotEmpty(message="どちらかを選択してください")
	private String graduate;
	private Integer graduateID;

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
	@NotNull(message="年：選択してください")
	private Integer birthdayY;
	@NotNull(message="月：選択してください")
	private Integer birthdayM;
	@NotNull(message="日：選択してください")
	private Integer birthdayD;
	@NotNull(message="年：選択してください")
	private Integer graduateY;
	@NotNull(message="月：選択してください")
	private Integer graduateM;
	@NotNull(message="日：選択してください")
	private Integer graduateD;
	
	@NotEmpty(message="郵便番号：記入してください")
	@Pattern(regexp = "^([0-9]{7})?$",message="郵便番号：半角数字の７桁で記入")
	private String postalCode;
	@NotEmpty(message="都道府県：選択してください")
	private String prefectures;
	@NotEmpty(message="市町村番地：記入してください")
	@Pattern(regexp = "^[^ -~｡-ﾟ]*$",message="市町村番地：全角で記入")
	private String municipality;
	@Pattern(regexp = "^[^ -~｡-ﾟ]*$",message="建物名・部屋番号地：全角で記入")
	private String buildingName;
	@Pattern(regexp = "^[0-9]*$",message="自宅番号：半角数字で記入")
	private String tel;
	@Pattern(regexp = "^[0-9]*$",message="携帯番号：半角数字で記入")
	private String mobile;
	@NotEmpty(message="メールアドレス：記入してください")
	@Email(message="メールアドレスの形式で記入してください")
	private String mailAddress;
}
