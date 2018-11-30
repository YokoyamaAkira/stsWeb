package com.washinSystems.homepage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

//import com.washinSystems.homepage.service.bean.StringForEmail;
import com.washinSystems.homepage.web.mail.formData.ContactMailForm;
import com.washinSystems.homepage.web.mail.formData.EntryMailForm;

@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;

	// @Autowired
	// private StringForEmail stringForEmail;

	@Async
	public void send(EntryMailForm form) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("a_yokoyama@washin.co.jp");
		msg.setTo("a_yokoyama@washin.co.jp");
		// msg.setBcc("送信先アドレス2");
		// msg.setCc(new String[] {"送信先アドレス3", "送信先アドレス4"});
		msg.setSubject("和心システムWEB | 「エントリー」より");
		// stringForEmail.makeText(form);
		msg.setText(makeText(form));
		mailSender.send(msg);
	}

	public String makeText(EntryMailForm form) {
		String sendText = "【エントリーより】\r\n" + "------------------------------------------------\r\n" + "[採用] "
				+ form.getGraduate() + "\r\n" + "[お名前(氏)] " + form.getLastName() + "\r\n" + "[お名前(名)] "
				+ form.getFirstName() + "\r\n" + "[ふりがな(し)] " + form.getKanaLastName() + "\r\n" + "[ふりがな(めい)] "
				+ form.getKanaFirstName() + "\r\n" + "[生年月日(年)] " + form.getBirthdayY() + "\r\n" + "[生年月日(月)] "
				+ form.getBirthdayM() + "\r\n" + "[生年月日(日)] " + form.getBirthdayD() + "\r\n" + "[卒業または卒業見込(年)] "
				+ form.getGraduateY() + "\r\n" + "[卒業または卒業見込(月)] " + form.getGraduateM() + "\r\n" + "[卒業または卒業見込(日)] "
				+ form.getGraduateD() + "\r\n" + "[郵便番号] " + form.getPostalCode() + "\r\n" + "[ご住所(都道府県)] "
				+ form.getPrefectures() + "\r\n" + "[ご住所(市町村番地)] " + form.getMunicipality() + "\r\n" + "[ご住所(建物名・部屋番号)]"
				+ form.getBuildingName() + "\r\n" + "[電話番号(自宅番号)] " + form.getTel() + "\r\n" + "[電話番号(携帯番号)] "
				+ form.getMobile() + "\r\n" + "[メールアドレス] " + form.getMailAddress() + "\r\n"
				+ "------------------------------------------------" + "\r\n" + "送信日時：" + form.getSendTime() + "\r\n"
				+ "ホストIP：" + form.getIpV4();
		return sendText;
	}

	@Async
	public void send(ContactMailForm form) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("a_yokoyama@washin.co.jp");
		msg.setTo("a_yokoyama@washin.co.jp");
		// msg.setBcc("送信先アドレス2");
		// msg.setCc(new String[] {"送信先アドレス3", "送信先アドレス4"});
		msg.setSubject("和心システムWEB | 「お問合せ」フォームより");
		// stringForEmail.makeText(form);
		msg.setText(makeText(form));
		mailSender.send(msg);
	}

	public String makeText(ContactMailForm form) {
		String sendText = "【お問合せより】" + "\r\n" + "------------------------------------------------" + "\r\n"
				+ "[メールアドレス] " + form.getMailAddress() + "\r\n" + "[お名前(氏)] " + form.getLastName() + "\r\n"
				+ "[お名前(名)] " + form.getFirstName() + "\r\n" + "[ふりがな(し)] " + form.getKanaLastName() + "\r\n"
				+ "[ふりがな(めい)] " + form.getKanaFirstName() + "\r\n" + "[お問い合わせカテゴリー] " + form.getQuestionItem() + "\r\n"
				+ "[お問い合わせ内容]" + "\r\n" + form.getContentsQuestion() + "\r\n"
				+ "------------------------------------------------" + "\r\n" + "送信日時：" + form.getSendTime() + "\r\n"
				+ "ホストIP：" + form.getIpV4();
		return sendText;
	}
}
