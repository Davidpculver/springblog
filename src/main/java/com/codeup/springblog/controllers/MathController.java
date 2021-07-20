package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

//    will return int bc of browser magic... but we want to return strings, so must String.valueOf

//    @GetMapping("/add/{num1}/and/{num2}")
//    @ResponseBody
//    public int addNums(@PathVariable int num1, @PathVariable int num2){
//        return num1 + num2;
//    }

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String addNums(@PathVariable int num1, @PathVariable int num2){
        return String.valueOf(num1 + num2);
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtractNums(@PathVariable int num1, @PathVariable int num2){
        return String.valueOf(num2 - num1);
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiplyNums(@PathVariable int num1, @PathVariable int num2){
        return String.valueOf(num1 * num2);
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divideNums(@PathVariable int num1, @PathVariable int num2){
        return String.valueOf(num1 / num2);
    }


}
