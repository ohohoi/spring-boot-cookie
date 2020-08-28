package com.example.cookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookieController {
    @GetMapping("/get/cookies")
    public String cookies(Model model) {

        return "cookies.html";
    }
}
