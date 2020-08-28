package com.example.cookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {
    @GetMapping("/get/cookies")
    public String cookies(HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap) {

        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        boolean hasVisitCountCookie = false;

        //check has cookies
        if (cookies != null) {
            //check has visitCount cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visitCount")) {
                    hasVisitCountCookie = true;
                    cookieValue = cookie.getValue();
                }
            }
        }

        if (hasVisitCountCookie) {
            try {
                int i = Integer.parseInt(cookieValue);
                cookieValue = Integer.toString(++i);
            }catch(Exception ex) {
                cookieValue = "1";
            }
        }
        else {
            cookieValue = "1";
        }

        Cookie visitCountCookie = new Cookie("visitCount", cookieValue);
        visitCountCookie.setMaxAge(2 * 60 * 60 * 24 * 365);   //  2years
        //               2                              2seconds
        //               2 * 60                         2minutes
        //               2 * 60 * 60                    2hours
        //               2 * 60 * 60 * 24               2days
        //               -1                             until browser is open
        visitCountCookie.setPath("/");

        response.addCookie(visitCountCookie);

        modelMap.addAttribute("visitCountCookie", cookieValue);

        return "cookies";
    }
}
