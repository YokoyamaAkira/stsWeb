package com.washinSystems.homepage.web.maintenance;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.washinSystems.homepage.domain.LoginUserDetails;
import com.washinSystems.homepage.domain.NoticeEvent;
import com.washinSystems.homepage.service.NoticeEventService;
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

    @GetMapping
    String list(Model model) {
        List<NoticeEvent> noticeEvents = noticeEventService.findAll();
        model.addAttribute("noticeEvents", noticeEvents);
        return "maintenance/newsOperation/eventsList";
    }

    @PostMapping(path = "create")
    String create(@Validated EventForm form, BindingResult result, Model model,
                  @AuthenticationPrincipal LoginUserDetails userDetails) {
        if (result.hasErrors()) {
            return list(model);
        }
        NoticeEvent noticeEvent = new NoticeEvent();
        BeanUtils.copyProperties(form, noticeEvent);
        noticeEventService.create(noticeEvent, userDetails.getUser());
        return "redirect:/maintenance/newsOperation";
    }

    @GetMapping(path = "eventEdit", params = "form")
    String editForm(@RequestParam Integer id, EventForm form) {
    	NoticeEvent noticeEvent = noticeEventService.findOne(id);
        BeanUtils.copyProperties(noticeEvent, form);
        return "maintenance/newsOperation/eventEdit";
    }

    @PostMapping(path = "eventEdit")
    String edit(@RequestParam Integer id, @Validated EventForm form, BindingResult result,
                @AuthenticationPrincipal LoginUserDetails userDetails) {
        if (result.hasErrors()) {
            return editForm(id, form);
        }
        NoticeEvent noticeEvent = new NoticeEvent();
        BeanUtils.copyProperties(form, noticeEvent);
        noticeEvent.setId(id);
        noticeEventService.update(noticeEvent, userDetails.getUser());
        return "redirect:/maintenance/newsOperation";
    }

    @GetMapping(path = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/maintenance/newsOperation";
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
    	noticeEventService.delete(id);
        return "redirect:/maintenance/newsOperation";
    }
}