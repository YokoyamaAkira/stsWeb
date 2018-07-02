package com.washinSystems.homepage.web.maintenance;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.washinSystems.homepage.domain.NoticeEvent;
import com.washinSystems.homepage.service.NoticeEventService;
import com.washinSystems.homepage.web.LoginUserDetails;
import com.washinSystems.homepage.web.maintenance.common.StringConstant;
import com.washinSystems.homepage.web.maintenance.formData.EventForm;

import java.util.List;

@Controller
@RequestMapping("maintenance/newsOperation")
public class EventController {
	@Autowired
	NoticeEventService noticeEventService;

	@ModelAttribute
	EventForm setUpForm() {
		return new EventForm();
	}

	// in page
	@GetMapping
	String list(Model model) {
		List<NoticeEvent> noticeEvents = noticeEventService.findAll();
		model.addAttribute("noticeEvents", noticeEvents);
		return StringConstant.Absolute_Path_eventsList;
	}

	// from listPage to createPage
	@GetMapping(path = "create", params = "new_edit")
	String newEvent(@Validated EventForm form, BindingResult result, Model model,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return list(model);
		}
		form.setId(null);//新規作成のためイベントIDを設定しない。これをもって新規作成画面と更新画面を切り替える
		return StringConstant.Absolute_Path_editEvent;
	}

	// from listPage to createPage
	@GetMapping(path = "create", params = "renew_edit")
	String renewEvent(@RequestParam Integer id, EventForm form) {
		if (id > 0) {
			NoticeEvent noticeEvent = noticeEventService.findOne(id);
			BeanUtils.copyProperties(noticeEvent, form);
		}
		form.setId(null);//新規作成のためイベントIDを設定しない。これをもって新規作成画面と更新画面を切り替える
		return StringConstant.Absolute_Path_editEvent;
	}

	// insert data to DB
	@PostMapping(path = "eventEdit", params = "create")
	String create(@RequestParam Integer id, @Validated EventForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return renewEvent(id, form);
		}
		NoticeEvent noticeEvent = new NoticeEvent();
		BeanUtils.copyProperties(form, noticeEvent);
		noticeEvent.setId(null);
		noticeEventService.create(noticeEvent, userDetails.getUser());
		return StringConstant.Redirect_Path_newsOperation;
	}

	// from listPage to editPage
	@GetMapping(path = "eventEdit", params = "form")
	String editForm(@RequestParam Integer id, EventForm form) {
		NoticeEvent noticeEvent = noticeEventService.findOne(id);
		BeanUtils.copyProperties(noticeEvent, form);
		return StringConstant.Absolute_Path_editEvent;
	}

	// update data to DB
	@PostMapping(path = "eventEdit", params = "edit")
	String edit(@RequestParam Integer id, @Validated EventForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return editForm(id, form);
		}
		NoticeEvent noticeEvent = new NoticeEvent();
		BeanUtils.copyProperties(form, noticeEvent);
		noticeEventService.update(noticeEvent, userDetails.getUser());
		return StringConstant.Redirect_Path_newsOperation;
	}

	// from workPage to listPage
	@PostMapping(path = "eventEdit", params = StringConstant.Params_goToBack)
	String goToTop() {
		return StringConstant.Redirect_Path_newsOperation;
	}
	
	// from workPage to listPage
	@PostMapping(path = "createEvent", params = StringConstant.Params_goToBack)
	String goToTop2() {
		return StringConstant.Redirect_Path_newsOperation;
	}

	//delete data from listPage
	@PostMapping(path = "delete")
	String delete(@RequestParam Integer id) {
		noticeEventService.delete(id);
		return StringConstant.Redirect_Path_newsOperation;
	}
}