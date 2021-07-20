package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
//    Removed @ResponseBody because we added thymeleaf
    public String home(){
//        thymeleaf recognizes the home file in templates folder
        return "home";
    }




}
