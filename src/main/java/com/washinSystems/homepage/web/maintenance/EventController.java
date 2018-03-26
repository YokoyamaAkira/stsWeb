package com.washinSystems.homepage.web.maintenance;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.washinSystems.homepage.domain.Customer;
import com.washinSystems.homepage.service.CustomerService;
import com.washinSystems.homepage.service.LoginUserDetails;
import com.washinSystems.homepage.web.CustomerForm;

import java.util.List;

@Controller
@RequestMapping("maintenance/newsOperation")
public class EventController {
    @Autowired
    CustomerService customerService;

    @ModelAttribute
    CustomerForm setUpForm() {
        return new CustomerForm();
    }

    @GetMapping
    String list(Model model) {
        List<Customer> customerTests = customerService.findAll();
        model.addAttribute("customers", customerTests);
        return "maintenance/newsOperation/eventsList";
    }

    @PostMapping(path = "create")
    String create(@Validated CustomerForm form, BindingResult result, Model model,
                  @AuthenticationPrincipal LoginUserDetails userDetails) {
        if (result.hasErrors()) {
            return list(model);
        }
        Customer customerTest = new Customer();
        BeanUtils.copyProperties(form, customerTest);
        customerService.create(customerTest, userDetails.getUser());
        return "redirect:/maintenance/newsOperation";
    }

    @GetMapping(path = "eventEdit", params = "form")
    String editForm(@RequestParam Integer id, CustomerForm form) {
        Customer customerTest = customerService.findOne(id);
        BeanUtils.copyProperties(customerTest, form);
        return "maintenance/newsOperation/eventEdit";
    }

    @PostMapping(path = "eventEdit")
    String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result,
                @AuthenticationPrincipal LoginUserDetails userDetails) {
        if (result.hasErrors()) {
            return editForm(id, form);
        }
        Customer customerTest = new Customer();
        BeanUtils.copyProperties(form, customerTest);
        customerTest.setId(id);
        customerService.update(customerTest, userDetails.getUser());
        return "redirect:/maintenance/newsOperation";
    }

    @GetMapping(path = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/maintenance/newsOperation";
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
        customerService.delete(id);
        return "redirect:/maintenance/newsOperation";
    }
}