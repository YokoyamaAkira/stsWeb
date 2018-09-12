package com.washinSystems.homepage.web.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.washinSystems.homepage.service.EmailService;
import com.washinSystems.homepage.web.mail.formData.ContactMailForm;
import com.washinSystems.homepage.web.mail.formData.EntryMailForm;
import com.washinSystems.homepage.web.maintenance.common.StringConstant;

@Controller
@RequestMapping("mail")
public class MailController {

	@Autowired
	EmailService emailService;

	@ModelAttribute
	EntryMailForm setUpForm() {
		return new EntryMailForm();
	}

	@ModelAttribute
	ContactMailForm setUpForm2() {
		return new ContactMailForm();
	}

	@GetMapping(path = "entry")
	String entryForm() {
		return "mail/entry";
	}

	// String checkEntryForm(@Validated EntryMailForm form, BindingResult
	// result) {
	@PostMapping(path = "checkInput_Entry")
	String checkEntryForm(@Validated EntryMailForm form, BindingResult result) {
		if (result.hasErrors()) {
			if (!(form.getGraduate() == null)){
				if (form.getGraduate().equals("新卒")) {
					form.setGraduateID(1);
				} else if (form.getGraduate().equals("中途")) {
					form.setGraduateID(2);
				} else {
					form.setGraduateID(0);
				}
			}
			return "mail/entry";
		}
		return "mail/checkInput_Entry";
	}

	@PostMapping(path = "sendEntry")
	String sendEntryForm(EntryMailForm form) {
		emailService.send(form);
		return "mail/thanks";
	}

	@PostMapping(path = "sendEntry", params = StringConstant.Params_goToBack)
	String goToBack(EntryMailForm form) {
		if (form.getGraduate().equals("新卒")) {
			form.setGraduateID(1);
		} else if (form.getGraduate().equals("中途")) {
			form.setGraduateID(2);
		} else {
			form.setGraduateID(0);
		}
		return "/mail/entry";
	}

	@GetMapping(path = "contact")
	String contactForm() {
		return "mail/contact";
	}

	// String checkEntryForm(@Validated ContactMailForm form, BindingResult
	// result) {
	@PostMapping(path = "checkInput_Contact")
	String checkEntryForm(@Validated ContactMailForm form, BindingResult result) {
		if (result.hasErrors()) {
			if (!(form.getQuestionItem() == null)){
				if (form.getQuestionItem().equals("採用について ")) {
					form.setQuestionItemID(1);
				} else if (form.getQuestionItem().equals("意見、問合せ")) {
					form.setQuestionItemID(2);
				} else if (form.getQuestionItem().equals("その他")) {
					form.setQuestionItemID(3);
				} else {
					form.setQuestionItemID(0);
				}
			}
			return "mail/contact";
		}
		form.setContentsQuestionForHTML(form.getContentsQuestion().replaceAll("\\r\\n", "<br/>"));
		return "mail/checkInput_Contact";
	}

	@PostMapping(path = "sendContact")
	String sendEntryForm(ContactMailForm form) {
		emailService.send(form);
		return "mail/thanks2";
	}

	@PostMapping(path = "sendContact", params = StringConstant.Params_goToBack)
	String goToBack(ContactMailForm form) {
		if (form.getQuestionItem().equals("採用について ")) {
			form.setQuestionItemID(1);
		} else if (form.getQuestionItem().equals("意見、問合せ")) {
			form.setQuestionItemID(2);
		} else if (form.getQuestionItem().equals("その他")) {
			form.setQuestionItemID(3);
		} else {
			form.setQuestionItemID(0);
		}
		return "/mail/contact";
	}
}