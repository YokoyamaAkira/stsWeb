package com.washinSystems.homepage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("maintenance")
public class LoginController {
    @GetMapping(path = "loginScreen")
    String loginForm() {
        return "maintenance/loginScreen";
    }
}