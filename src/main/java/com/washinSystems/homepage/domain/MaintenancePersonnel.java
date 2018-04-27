package com.washinSystems.homepage.domain;

import java.sql.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//最初の単語はすべて小文字.それ以降の単語は頭だけ大文字、頭以外は小文字の命名はＮＧの様。
//基本はテーブル名項目名は、単語と単語の間にアンダースコアを挿入して命名する。すべて大文字が推奨されるようだ。
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "affiliation_user")
public class MaintenancePersonnel {
	@Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;// 					INT(255)  NOT NULL PRIMARY KEY,
	@Column(name="user_name")
	private String userName;// 				VARCHAR(100) DEFAULT '',
	@Column(name="use_mail_f")
	private Boolean useMailF;// 				BOOLEAN DEFAULT false,
	@Column(name="role_id")
    private Integer roleId;// 				INT(100) DEFAULT 0,
	@Column(name="mailaddress")
    private String mailaddress;// 			VARCHAR(100) DEFAULT '',
	@Column(name="nick_name")
    private String nickName;// 				VARCHAR(100) DEFAULT '',
	@Column(name="del_f")
    private Boolean delF;// 				BOOLEAN DEFAULT false,
	@Column(name="registration_date")
    private Date registrationDate;// 		DATE DEFAULT CURRENT_TIMESTAMP(),
	@Column(name="registered_person")
    private Integer registeredPerson;// 	INT(255) DEFAULT 0,
	@Column(name="edit_date")
    private Date editDate;//				DATE DEFAULT CURRENT_TIMESTAMP(),
	@Column(name="editor")
    private Integer editor;// 				INT(255) DEFAULT 0
}
